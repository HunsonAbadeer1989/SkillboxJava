import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParseCSV {
    private static final double ZERO_MONEY = 0.0;
    public static List<SpendNote> spendsList = new ArrayList<>();
    public static List<ProfitNote> profitsList = new ArrayList<>();
    public static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        String csvFile = "resources/movementListCSV.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // skip first line

            while ((line = br.readLine()) != null) {
                String[] splitDoc = line.split(cvsSplitBy);
                Pattern SPENDING_PATTERN = Pattern.compile("([/|\\\\]\\s?\\w+\\s?\\w+?\\s?>?\\w+\\s{4})");
                Matcher spendMatch = SPENDING_PATTERN.matcher(splitDoc[5]);
                if (spendMatch.find()) {
                    String spendType = spendMatch.group(1).substring(1).trim();
                    SpendNote spendNote = new SpendNote(spendType,
                            Double.parseDouble(splitDoc[7].replaceAll(",", ".")));
                    ProfitNote profitNote = new ProfitNote(spendType,
                            Double.parseDouble(splitDoc[6].replaceAll(",", ".")));
                    spendsList.add(spendNote);
                    profitsList.add(profitNote);
                    Transaction transaction = new Transaction(spendType,
                            Double.parseDouble(splitDoc[6].replaceAll(",", ".")),
                                    Double.parseDouble(splitDoc[7].replaceAll(",", ".")));
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double totalSpend = spendsList.stream().mapToDouble(SpendNote::getMoney).sum();
        double totalProfit = profitsList.stream().mapToDouble(ProfitNote::getMoney).sum();

        transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getDescription,
                        Collectors.mapping(Summary::fromTransaction,
                         Collectors.reducing(new Summary(0.0, 0.0), Summary::merge))))
                             .forEach((s, summ) -> System.out.println(s + "\t" + summ.income + "\t" + summ.withdraw));
        System.out.printf("Total spends: %.2f RUB \n", totalSpend);
        System.out.printf("Total income: %.2f RUB \n", totalProfit);


    }

    private static class Summary {
        double income;
        double withdraw;

        Summary(double income, double withdraw) {
            this.income = income;
            this.withdraw = withdraw;
        }

        // сложение сумм
        static Summary merge(Summary s1, Summary s2) {
            return new Summary(s1.income + s2.income, s1.withdraw + s2.withdraw);
        }

        // mapper - конвертация из Transaction
        static Summary fromTransaction(Transaction t) {
            return new Summary(t.getIncome(), t.getWithdraw());
        }

        /** First version of Home Work, leave it here just in case.
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
         System.out.printf("Total spends: %.2f RUB \n", totalSpend);
         System.out.println();

         System.out.println("List total profits! \n=============================");
         totalProfits.entrySet().forEach(System.out::println);
         System.out.printf("Total income: %.2f RUB \n", totalProfit);
         */
    }
}