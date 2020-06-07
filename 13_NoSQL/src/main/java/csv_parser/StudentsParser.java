package csv_parser;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentsParser {

    public static void main(String[] args) {
        String csvFile = "resources/movementListCSV.csv";
        String line = "";
        String cvsSplitBy = "\\t";


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] splitDoc = line.split(cvsSplitBy);
                Pattern SPENDING_PATTERN = Pattern.compile("(\\w+\\s\\w+)\\t(\\d+)\\t([\\w*,?]*)");
                Matcher spendMatch = SPENDING_PATTERN.matcher(splitDoc[3]);
                if (spendMatch.find()) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
