import com.google.gson.Gson;
import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    public static StationIndex mosMetro = new StationIndex("My Moscow Metro");
    private static StationsIndexes stationIndex;

    private static Document getPage() throws IOException {
        String url = "https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_" +
                "%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_" +
                "%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_" +
                "%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0";
        return Jsoup.connect(url).maxBodySize(0).get();
    }

    public static void main(String[] args) throws IOException {

        parsePage();

        createJson(); //  Создадим json документ
        deserializeJsonToStationIndex(); // достанем инфу из созданного документа

        resultOfHomework(); // вывод необходимой инфы в консоль
    }


    private static void parsePage() throws IOException {
        Document page = getPage();
        Elements table = page.select("table.standard.sortable tr");
        table.remove(0);
        try {
            for (Element row : table) {
                Element lineCell = row.select("td").first();
                String lineNumber = lineCell.select("span.sortkey").first().text();

                Element lineN = row.child(0).select("span[title]").last();
                final String lineName = lineN.attr("title");

                final String stationLineNumber = row.select("span.sortkey").first().text();
                final String stationName = row.child(1).select("span").text();

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
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private static void createJson() {
        try {
            PrintWriter printWriter = new PrintWriter("src/main/resources/map.json");
            printWriter.write(serializeStationsIndexes());
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (StackOverflowError er) {
            er.getStackTrace();
        }
    }

    private static String serializeStationsIndexes() {
        List<Line> lines = new ArrayList<>(mosMetro.getLines().values());
        HashMap<String, List<Station>> stations = new HashMap<>();


        for (Line line : mosMetro.getLines().values()) {
            List<Station> stationList = new ArrayList<>();
            for (Station station : mosMetro.getStations()) {
                String a = station.getLine().getNumber();
                String b = line.getNumber();
                if (a.equals(b)) {
                    stationList.add(station);
                    stations.put(station.getLine().getNumber(), stationList);
                }
            }
        }

        StationsIndexes jsonStationIndexes = new StationsIndexes(lines, stations);
        return new Gson().toJson(jsonStationIndexes);
    }

    private static class StationsIndexes {
        List<Line> lines;
        HashMap<String, List<Station>> stations;

        public StationsIndexes(List<Line> lines, HashMap<String, List<Station>> stations) {
            this.lines = lines;
            this.stations = stations;
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
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/map.json"));
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
                        System.out.printf("Line : %s have %s stations. \n" ,line.getNumber(),
                                stationIndex.getStations().get(stationNumber).size());
                    }
                }
            }
        }catch  (NullPointerException ex){
            ex.getStackTrace();
        }
    }


}