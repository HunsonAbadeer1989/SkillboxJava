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
        if (isCanWithdraw(cashWithTax(cash))) {
            setFund(getFund() - cashWithTax(cash));
        } else {
            System.out.println("You can't withdraw this amount of money!");
        }
    }

    private boolean isCanWithdraw(double cash) {
        return cash + cash * TAX <= getFund();
    }

    @Override
    boolean transferTo(Account account, double money) {
        if (money < 0){
            return false;
        }
        if (isCanWithdraw(cashWithTax(money))) {
            withdrawMoney(money);
            account.addMoney(money);
            return true;
        } else {
            return false;
        }
    }

    private double cashWithTax(double cash) {
        return cash + cash * TAX;
    }
}


