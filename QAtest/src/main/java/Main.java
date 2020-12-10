import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static String filePath = "/src/main/resources/QAtest.txt";
    public static long lines;
    public static long aChars;

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            while(reader.readLine() != null){
                lines++;
                aChars += reader.readLine().chars().filter(ch -> ch == 'a').count();
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Count of lines: %d \n", lines);
        System.out.printf("Count of 'a': %d", aChars);

    }
}
