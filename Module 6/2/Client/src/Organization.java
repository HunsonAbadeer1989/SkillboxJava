public class Organization extends Client
{
    private static final double TAX = 0.01;

    public Organization(double fund) {
        super(fund);
    }

    @Override
    public void withdrawMoney(double cash) {
        cash = cash + cash * TAX;
        if (isCanWithdraw(cash)) {
            super.withdrawMoney(cash);
        } else {
            System.out.println("You can't withdraw this amount of money!");
        }
    }

    private boolean isCanWithdraw(double cash) {
        return cash <= getFund();
    }
}