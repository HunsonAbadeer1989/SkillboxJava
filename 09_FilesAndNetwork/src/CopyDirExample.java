import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CopyDirExample {
    private static final String SRC_DIR = "/Users/hunsonabadeer/Desktop/srcFolder";
    private static final String DST_DIR = "/Users/hunsonabadeer/Desktop/dstFolder";

    public static void main(String[] args) {
//        copyDirectory(SRC_DIR, DST_DIR);
        copyEntireDir(SRC_DIR, DST_DIR);
    }

    private static void copyDirectory(String src, String dst) {
        Path sourceParentFolder = Paths.get(src);
        Path destinationParentFolder = Paths.get(dst);
        try {
            if (!Files.exists(destinationParentFolder)) {
                System.out.println("File not found!");
            }
            Stream<Path> allFilesPathStream = Files.walk(sourceParentFolder);
            Consumer<? super Path> action = (Consumer<Path>) t -> {
                try {
                    String destinationPath = t.toString().replaceAll(sourceParentFolder.toString(),
                            destinationParentFolder.toString());
                    Files.copy(t, Paths.get(destinationPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            };
            allFilesPathStream.forEach(action);
        } catch (FileAlreadyExistsException e) {
            e.getMessage();
        }
        catch (NoSuchFileException ex){
            ex.getStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyEntireDir(String src, String dst){
        File srcDir = new File(src);
        File destDir = new File(dst);

        try {
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}