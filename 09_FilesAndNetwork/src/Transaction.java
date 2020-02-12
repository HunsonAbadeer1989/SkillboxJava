public class Transaction {

    private String description;
    private double income;
    private double withdraw;

    public Transaction(String description, double income, double withdraw) {
        this.description = description;
        this.income = income;
        this.withdraw = withdraw;
    }

    public String getDescription() {
        return description;
    }

    public double getIncome() {
        return income;
    }

    public double getWithdraw() {
        return withdraw;
    }

}
