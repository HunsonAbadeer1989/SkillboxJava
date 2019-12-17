public class CardAccount extends Account {

    private double fund;
    private static final double TAX = 0.01;

    public CardAccount(double fund) {
        this.fund = fund;
    }

    double getFund() {
        return fund;
    }

    void setFund(double fund) {
        this.fund = fund;
    }

    @Override
    void addMoney(double cash) {
        setFund(getFund() + cash);
    }

    @Override
    void withdrawMoney(double cash) {
        cash = cash + cash * TAX;
        boolean canWithdraw = cash + cash * TAX <= getFund();
        if (canWithdraw){
             setFund(getFund() - cash);
    }
        else {
            System.out.println("You can't withdraw this amount of money!");
        }
    }

}


