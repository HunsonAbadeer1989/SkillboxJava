import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String FILE = "src/main/resources/site_map.txt";

    public static void main(String[] args) {
//        final String URL = "https://lenta.ru/";
        final String URL = "https://secure-headland-59304.herokuapp.com";

        Set<String> linksSet = new ForkJoinPool().invoke(new SiteMapBuilder(URL));
//        Set<String> linksSet = SiteMapBuilder.getLinks(URL);
        List<String> sortedLinks = new ArrayList<>(linksSet);
        Collections.sort(sortedLinks);
        writeLinksInFile(sortedLinks);
    }

    private static void writeLinksInFile(Collection<String> collection) {
        try (FileWriter writer = new FileWriter(FILE)) {
            for (String link : collection) {
                StringBuilder tempLink = new StringBuilder();
                String[] tempArrLinks = link.split("/+");
                for (int i = tempArrLinks.length; i > 0; i--) {
                    tempLink.append("\t");
                }
                writer.write(tempLink + link + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}