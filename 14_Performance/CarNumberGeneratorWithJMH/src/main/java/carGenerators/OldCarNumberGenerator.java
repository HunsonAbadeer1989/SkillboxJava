package carGenerators;

import java.io.FileOutputStream;
import java.io.IOException;

public class OldCarNumberGenerator {

    public void createNumbers() throws IOException {
        long start = System.currentTimeMillis();

        FileOutputStream writer = new FileOutputStream("res/numbers.txt");

        char[] letters= {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for(int i = 1, regionCode = 100; i < regionCode; i++) {
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            String carNumber = firstLetter + padNumber(number, 3) +
                                    secondLetter + thirdLetter + padNumber(regionCode, 2);
                            writer.write(carNumber.getBytes());
                            writer.write('\n');
                        }
                    }
                }
            }
        }

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }


    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
