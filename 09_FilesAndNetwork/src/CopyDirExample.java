import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CopyDirExample {
    private static final String SRC_DIR = "/Users/hunsonabadeer/Desktop/srcFolder";
    private static final String DST_DIR = "/Users/hunsonabadeer/Desktop/dstFolder";

    public static void main(String[] args) {
        copyDirectory(SRC_DIR, DST_DIR);
    }

    private static void copyDirectory(String src, String dst) {
        Path sourceParentFolder = Paths.get(src);
        Path destinationParentFolder = Paths.get(dst);
        try {
            Stream<Path> allFilesPathStream = Files.walk(sourceParentFolder);
            Consumer<? super Path> action = (Consumer<Path>) t -> {
                try {
                    String destinationPath = t.toString().replaceAll(sourceParentFolder.toString(),
                            destinationParentFolder.toString());
                    Files.copy(t, Paths.get(destinationPath));
                } catch (FileAlreadyExistsException e) {
                } catch (IOException e) {
                    e.printStackTrace();
                }

            };
            allFilesPathStream.forEach(action);

        } catch (FileAlreadyExistsException e) {
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}