import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Loader {
    private static final long  MEGABYTE = 1024L * 1024L;
    public static void main(String[] args) {
        File folder = new File("/users/hunsonabadeer/IdeaProjects");
        System.out.println(folder.isDirectory());
        long size = 0L;
        try {
            size = folderSize(folder);
        }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }
        System.out.printf( "Your directory is: %s bytes", size);
        System.out.printf( "Your directory is: %s Mb", (double) size / MEGABYTE);
    }

    public static long folderSize(File directory){
        long size = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                size += file.length();
            else
                size += folderSize(file);
        }
        return size;
//        return Files.walk(directory.toPath()).mapToLong(p -> p.toFile().length() ).sum();
    }
}
