public class ProfitNote {

    private static double count;

    private String spendCase;
    private double money;


    public ProfitNote(String spendSource, double money) {
        this.spendCase = spendSource;
        this.money = money;
    }

    public static double getCount() {
        return count;
    }

    public String getSpendCase() {
        return spendCase;
    }

    public void setSpendCase(String spendCase) {
        this.spendCase = spendCase;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
