import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    private static final int COUNT_OF_PROCESSORS = 8;
    public static void main(String[] args) {
        String srcFolder = "/users/hunsonabadeer/Desktop/src";
        String dstFolder = "/users/hunsonabadeer/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(COUNT_OF_PROCESSORS);

        if (files != null) {
            for (File file : files) executor.execute(new ImageResizer(file, dstFolder, start));
        }

        executor.shutdown();

    }

}
