import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String FILE = "src/main/resources/site_map.txt";

    public static void main(String[] args) {
//        final String URL = "https://lenta.ru/";
//        final String URL = "https://skillbox.ru/";
        final String URL = "https://secure-headland-59304.herokuapp.com";

        ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ConcurrentHashMap<String, String> linksMap = fjp.invoke(new SiteMapBuilder(URL));
        List<String> sortedLinks = new ArrayList<>(linksMap.keySet());
        Collections.sort(sortedLinks, Comparator.naturalOrder());
        writeLinksInFile(sortedLinks, URL);
    }

    private static void writeLinksInFile(Collection<String> collection, String URL) {
        int urlArr = URL.split("/+").length;
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write(URL + "\n");
            for (String link : collection) {
                StringBuilder tempLink = new StringBuilder();
                String[] tempArrLinks = link.split("/+");
                for (int i = tempArrLinks.length; i > urlArr; i--) {
                    tempLink.append("\t");
                }
                writer.write(tempLink + link + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}