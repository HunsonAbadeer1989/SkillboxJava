import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Loader {
    private static final long MEGABYTE = 1024L * 1024L;

    public static void main(String[] args) {
        File folder = new File("/users/hunsonabadeer/IdeaProjects");
        long size = folderSize(folder);
        System.out.printf("Your directory is: %s bytes or %.4s Mb \n", size, (double) size / MEGABYTE);

    }

    public static long folderSize(File directory) {
        long size = 0;
        try {
            return Files.walk(directory.toPath()).mapToLong(p -> p.toFile().length()).sum();
        } catch (IOException ex) {
            ex.getMessage();
        }
        return size;
    }

}
