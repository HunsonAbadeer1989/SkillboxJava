import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CarNumberGenerator implements Runnable {
    private FileOutputStream writer;
    private int fromRegion;
    private int toRegion;

    public CarNumberGenerator(int fileNumber, int fromRegion, int toRegion) throws FileNotFoundException {
        StringBuilder fileName = (new StringBuilder()).append("res/numbers").append(fileNumber).append(".txt");
        this.writer = new FileOutputStream(fileName.toString());
        this.fromRegion = fromRegion;
        this.toRegion = toRegion;
    }

    public void run() {
        long start = System.currentTimeMillis();

        try {
            char[] letters = new char[]{'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

            for (int region = fromRegion; region < toRegion ; region++) {
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                builder.append(firstLetter).append(padNumber(number, 3))
                                        .append(secondLetter).append(thirdLetter)
                                        .append(padNumber(region, 2))
                                        .append("\n");
                            }
                        }
                    }
                }
                writer.write(builder.toString().getBytes());
            }

            this.writer.flush();
            this.writer.close();
        } catch (IOException var19) {
            var19.getStackTrace();
        }

        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder builder = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - builder.length();

        for (int i = 0; i < padSize; ++i) {
            builder.insert(0, "0");
        }

        return builder.toString();
    }
}
