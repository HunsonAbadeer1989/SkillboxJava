public class SpendNote {
    private static double count;

    private String spendCase;
    private double money;

    public SpendNote(String spendCase, double money) {
        this.spendCase = spendCase;
        this.money = money;
    }

    public String getSpendCase() {
        return spendCase;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return spendCase + " " + money;
    }
}
