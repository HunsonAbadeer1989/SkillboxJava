public class ProfitNote {

    private static double count;

    private String spendSource;
    private double money;


    public ProfitNote(String spendSource, double money) {
        this.spendSource = spendSource;
        this.money = money;
        count = getCount() + money;
    }

    public static double getCount() {
        return count;
    }

    public String getSpendSource() {
        return spendSource;
    }

    public void setSpendSource(String spendSource) {
        this.spendSource = spendSource;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
