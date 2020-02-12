public class ProfitNote {

    private static double count;

    private String spendCase;
    private double money;


    public ProfitNote(String spendSource, double money) {
        this.spendCase = spendSource;
        this.money = money;
    }

    public String getSpendCase() {
        return spendCase;
    }

    public double getMoney() {
        return money;
    }

}