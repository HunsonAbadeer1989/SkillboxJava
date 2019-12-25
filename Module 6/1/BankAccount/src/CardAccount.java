public class CardAccount extends Account {

    private static final double TAX = 0.01;

    public CardAccount(double fund) {
        super(fund);
    }

    @Override
    void addMoney(double cash) {
        super.addMoney(cash);
    }

    @Override
    boolean withdrawMoney(double cash) {
        cash = cashWithTax(cash);
        if (isCanWithdraw(cash)) {
            super.withdrawMoney(cash);
            return true;
        } else {
            System.out.println("You can't withdraw this amount of money!");
            return false;
        }
    }

    private boolean isCanWithdraw(double cash) {
        return cash + cash * TAX <= getFund();
    }

    @Override
    boolean transferTo(Account account, double money) {
        return super.transferTo(account, money);
    }

    private double cashWithTax(double cash) {
        return cash + cash * TAX;
    }
}


