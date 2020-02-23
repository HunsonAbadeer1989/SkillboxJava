import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Line;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    public static StationIndex mosMetro;
    private static StationsIndexes stationIndex;
    private static List<List<Station>> connectionList = new ArrayList<>();

    private static Elements getRows() throws IOException {
        String url = "https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_" +
                "%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_" +
                "%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_" +
                "%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0";
        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Elements tableLine = document.select("table[class=standard sortable]");
        return tableLine.select("tbody").select("tr");
    }

    public static void main(String[] args) throws IOException {

        parseLinesAndStations(getRows());

        parseConnections(getRows());

        createJson(); //  Создадим json документ
        deserializeJsonToStationIndex(); // достанем инфу из созданного документа

        resultOfHomework(); // вывод необходимой инфы в консоль
    }

    private static void parseLinesAndStations(Elements elements) {
        mosMetro = new StationIndex("My Moscow Metro");
        elements.remove(0);
        try {
            for (Element row : elements) {
                Elements lineCell = row.select("td");
                if (lineCell.size() == 7 || lineCell.size() == 8) {
                    String lineNumber = lineCell.select("span.sortkey").first().text(); // LINE NUMBER

                    Element lineN = row.child(0).select("span[title]").last();
                    final String lineName = lineN.attr("title"); // LINE NAME

                    final String stationLineNumber = row.select("span.sortkey").first().text(); // LINE NUMBER

                    final String stationName = row.child(1).select("a").first().text(); // STATION NAME

                    Line parseLine = new Line(lineNumber, lineName);
                    Station parseStation = new Station(stationName, new Line(stationLineNumber, lineName));

                    mosMetro.addLine(parseLine);
                    mosMetro.addStation(parseStation);

                    /** Обрабатываем всратый случай двух номеров линии в ячейке... Линии 8А и 11.
                     * Сколько нервов мама, сколько нервов. */
                    if (lineCell.attr("data-sort-value").equals("8.9")) {

                        String lineNumber11 = lineCell.select("span.sortkey").get(1).text();
                        Element stationCell = row.child(1).select("span").first();
                        String stationNameLine11 = stationCell.text();
                        Station parseStation11 = new Station(stationNameLine11, mosMetro.getLine(lineNumber11));
                        mosMetro.addStation(parseStation11);
                    }
                }
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private static void parseConnections(Elements elements) throws IOException {
        try {
            for (Element row : elements) {
                Elements cells = row.select("td");
                List<Station> tempConnection = new ArrayList<>();
                if (cells.size() == 7 || cells.size() == 8) {
                    final String stationName = cells.get(1).select("a").first().text(); // STATION NAME
                    final String stationLineNumber = row.select("span.sortkey").first().text(); // Line Number
                    Elements connectElements = cells.get(3).select("span");
                    for (Element connectElement : connectElements) {

                        String connectStationNumber = connectElement.select("span[class=sortkey]").text();
                        Element tempStationNameElement = connectElement.nextElementSibling();
                        if(tempStationNameElement == null){
                            continue;
                        }
                        String tempConnectStationName = tempStationNameElement.attr("title");
                        if (tempConnectStationName == null) {
                            continue;
                        }
                        String connectStationName = getConnectionStationName(tempConnectStationName);
                        if (connectStationName.equals("")) {
                            continue;
                        }
//                        System.out.println(mosMetro.getStation(stationName).getLine().getNumber() + " " + stationName + " " + connectStationNumber + " " + connectStationName);
                        tempConnection.add(mosMetro.getStation(stationName));
                        tempConnection.add(mosMetro.getStation(connectStationName));
                        connectionList.add(tempConnection);
                    }
                    mosMetro.addConnection(tempConnection);
                }
            }
        } catch (
                Exception ex) {
            ex.getStackTrace();
        }

    }


    private static String getConnectionStationName(String tempConnectStationName) {
        for (Station metStation : mosMetro.getStations()) {
            Pattern stationPattern = Pattern.compile(metStation.getName());
            Matcher staMatcher = stationPattern.matcher(tempConnectStationName);
            if (staMatcher.find()) {
                return metStation.getName();
            }
        }
        return "";
    }

    private static void createJson() {
        try {
            PrintWriter printWriter = new PrintWriter("src/main/resources/map.json");
            printWriter.write(serializeStationsIndexes(mosMetro));
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (StackOverflowError er) {
            er.getStackTrace();
        }
    }

    private static String serializeStationsIndexes(StationIndex metro) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Line> lines = new ArrayList<>(metro.getLines().values());
        HashMap<String, List<Station>> stations = new HashMap<>();
        for (Line line : metro.getLines().values()) {
            List<Station> stationList = new ArrayList<>();
            for (Station station : metro.getStations()) {
                String a = station.getLine().getNumber();
                String b = line.getNumber();
                if (a.equals(b)) {
                    stationList.add(station);
                    stations.put(station.getLine().getNumber(), stationList);
                }
            }
        }
//        Map<Station, List<Station>> connections = new TreeMap<>(connectionMap);
        List<List<Station>> connections = new ArrayList<>(connectionList);
        StationsIndexes jsonStationIndexes = new StationsIndexes(lines, stations, connections);
        return gson.toJson(jsonStationIndexes);
    }

    private static class StationsIndexes {
        List<Line> lines;
        HashMap<String, List<Station>> stations;
        List<List<Station>> connections;

        public StationsIndexes(List<Line> lines, HashMap<String, List<Station>> stations) {
            this.lines = lines;
            this.stations = stations;
        }

        public StationsIndexes(List<Line> lines, HashMap<String, List<Station>> stations, List<List<Station>> connections) {
            this.lines = lines;
            this.stations = stations;
            this.connections = connections;
        }

        public List<List<Station>> getConnections() {
            return connections;
        }

        public List<Line> getLines() {
            return lines;
        }

        public HashMap<String, List<Station>> getStations() {
            return stations;
        }

    }

    private static void deserializeJsonToStationIndex() throws IOException {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/map.json"))) {
            for (; ; ) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                json.append(line);
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        stationIndex = new Gson().fromJson(String.valueOf(json), StationsIndexes.class);
    }

    private static void resultOfHomework() {
        try {
            for (Line line : stationIndex.getLines()) {
                for (String stationNumber : stationIndex.getStations().keySet()) {
                    if (stationNumber.equals(line.getNumber())) {
                        System.out.printf("Line : %s have %s stations. \n", line.getNumber(),
                                stationIndex.getStations().get(stationNumber).size());
                    }
                }
            }
        } catch (NullPointerException ex) {
            ex.getStackTrace();
        }
    }

}