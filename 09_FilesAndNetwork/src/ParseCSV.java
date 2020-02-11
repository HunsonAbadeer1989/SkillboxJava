import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseCSV {

    public static List<SpendNote> spendsList = new ArrayList<>();
    public static List<ProfitNote> profitsList = new ArrayList<>();

    public static void main(String[] args) {
        String csvFile = "resources/movementListCSV.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // skip first line

            while ((line = br.readLine()) != null) {
                String[] splitDoc = line.split(cvsSplitBy);
                Pattern SPENDING_PATTERN = Pattern.compile("([\\/|\\\\]\\s?\\w+\\s?\\w+?\\s?>?\\w+\\s{4})");
                Matcher spendMatch = SPENDING_PATTERN.matcher(splitDoc[5]);
                if(spendMatch.find()){
                    String spendType = spendMatch.group(1).substring(1).trim();
                    SpendNote spendNote = new SpendNote(spendType, Double.parseDouble(splitDoc[7].replaceAll(",",".")));
                    ProfitNote profitNote = new ProfitNote(spendType, Double.parseDouble(splitDoc[6].replaceAll(",",".")));
                    spendsList.add(spendNote);
                    profitsList.add(profitNote);
                }
            }
            sortList();
            removeProfits();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Total income: %.2f RUB \n", ProfitNote.getCount());
        System.out.printf("Total spends: %.2f RUB \n", SpendNote.getCount());

        System.out.println("List of spends!");
        spendsList.forEach(System.out::println);
    }

    public static void sortList(){
        spendsList.sort(Comparator.comparing(SpendNote::getSpendCase));
    }

    public static void removeProfits(){
        double zeroMoney = 0.0;
        spendsList.removeIf(spendNote -> spendNote.getMoney() == zeroMoney);
    }
}