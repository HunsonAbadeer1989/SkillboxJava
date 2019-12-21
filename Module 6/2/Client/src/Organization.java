public class Organization implements Client
{
    private static final double TAX = 0.01;

    Organization(double fund){
        this.fund = fund;
    }

    private double fund;

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    @Override
    public void addMoney(double money) {
        if ( money < 0) {
            System.out.println("Wrong amount!");
        }
        else {
            setFund(getFund() + money);
        }
    }

    @Override
    public void withdrawMoney(double money) {
        if (isCanWithdraw(cashWithTax(money))) {
            setFund(getFund() - cashWithTax(money));
        } else {
            System.out.println("You can't withdraw this amount of money!");
        }
    }

    private boolean isCanWithdraw(double cash) {
        return cash + cash * TAX <= getFund();
    }

    private double cashWithTax(double cash) {
        return cash + cash * TAX;
    }

    @Override
    public void balance() {
        System.out.println(getFund());
    }
}