public class CardAccount extends Account {

    private static final double TAX = 0.01;

    public CardAccount(double fund) {
        super(fund);
    }

    @Override
    boolean withdrawMoney(double cash) {
        cash = cash + cash * TAX;
        if (isCanWithdraw(cash)) {
            super.withdrawMoney(cash);
            return true;
        } else {
            System.out.println("You can't withdraw this amount of money!");
            return false;
        }
    }

    private boolean isCanWithdraw(double cash) {
        return cash <= getFund();
    }
}


