import java.io.File;

public class Main {
    private static final int COUNT_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        String srcFolder = "/users/hunsonabadeer/Desktop/src";
        String dstFolder = "/users/hunsonabadeer/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        ImageResizer[] imageResizers = new ImageResizer[COUNT_OF_PROCESSORS];

        for (int i = 0; i < COUNT_OF_PROCESSORS; i++) {
            assert files != null;
            int arrSize = Math.incrementExact(files.length / COUNT_OF_PROCESSORS);
            File[] tempArr = new File[arrSize];
            System.arraycopy(files, ((files.length / COUNT_OF_PROCESSORS) * i), tempArr, 0, tempArr.length);
            imageResizers[i] = new ImageResizer(tempArr, dstFolder, start);
        }

        for (ImageResizer imageResizer : imageResizers) {
            new Thread(imageResizer).start();
        }

    }

}
