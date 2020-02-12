import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParseCSV {
    private static final double ZERO_MONEY = 0.0;
    public static List<SpendNote> spendsList = new ArrayList<>();
    public static List<ProfitNote> profitsList = new ArrayList<>();

    public static void main(String[] args) {
        String csvFile = "resources/movementListCSV.csv";
        String line = "";
        String cvsSplitBy = ";";

        double spend = 0.0;
        double receipt = 0.0;

        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // skip first line

            while ((line = br.readLine()) != null) {
                String[] splitDoc = line.split(cvsSplitBy);
                Pattern SPENDING_PATTERN = Pattern.compile("([/|\\\\]\\s?\\w+\\s?\\w+?\\s?>?\\w+\\s{4})");
                Matcher spendMatch = SPENDING_PATTERN.matcher(splitDoc[5]);
                if (spendMatch.find()) {
                    String spendType = spendMatch.group(1).substring(1).trim();
                    SpendNote spendNote = new SpendNote(spendType, Double.parseDouble(splitDoc[7].replaceAll(",", ".")));
                    ProfitNote profitNote = new ProfitNote(spendType, Double.parseDouble(splitDoc[6].replaceAll(",", ".")));
                    spendsList.add(spendNote);
                    profitsList.add(profitNote);
                }
                records.add(splitDoc[6] + " " + splitDoc[7]);
                receipt += Double.parseDouble(splitDoc[6].replaceAll(",", "."));
                spend += Double.parseDouble(splitDoc[7].replaceAll(",", "."));
            }
            sortList();
            removeProfits();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Double> totalSpends =
                spendsList.stream()
                        .collect(Collectors.groupingBy(
                                SpendNote::getSpendCase,
                                    Collectors.summingDouble(
                                            SpendNote::getMoney)));
        Map<String, Double> totalProfits =
                profitsList.stream()
                        .filter(el -> el.getMoney() != ZERO_MONEY)
                            .collect(Collectors.groupingBy(
                                ProfitNote::getSpendCase,
                                    Collectors.summingDouble(
                                        ProfitNote::getMoney)));
        System.out.print("List total spends! \n=============================\n");
        totalSpends.entrySet().forEach(System.out::println);
        System.out.printf("Total spends: %.2f RUB \n", spend);
        System.out.println();

        System.out.println("List total profits! \n=============================");
        totalProfits.entrySet().forEach(System.out::println);

        System.out.printf("Total income: %.2f RUB \n", receipt);

    }

    public static void sortList() {
        spendsList.sort(Comparator.comparing(SpendNote::getSpendCase));
    }

    public static void removeProfits() {
        double zeroMoney = 0.0;
        spendsList.removeIf(spendNote -> spendNote.getMoney() == zeroMoney);
    }


}