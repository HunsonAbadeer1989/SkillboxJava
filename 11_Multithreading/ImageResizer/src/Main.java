import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        String srcFolder = "/users/hunsonabadeer/Desktop/src";
        String dstFolder = "/users/hunsonabadeer/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

        executor.execute(new ImageResizer(files, dstFolder, start));

        executor.shutdown();

    }

}
