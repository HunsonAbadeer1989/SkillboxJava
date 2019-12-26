public class IP extends Client
{
    private static final double TAX = 0.01;
    private static final double HALF_TAX = 0.01;

    public IP(double fund) {
        super(fund);
    }

    @Override
    public void addMoney(double cash) {
        if (cash < 0){
            System.out.println("Wrong amount!");
        }
        else if (cash < 1000.0){
            super.addMoney(cash + cash * TAX);
        }
        else {
            super.addMoney( cash + cash * HALF_TAX);
        }
    }

    @Override
    public void withdrawMoney(double cash) {
        if (cash < 0){
            System.out.println("Wrong amount!");
        }
        else if (cash <= super.balance()){
            super.withdrawMoney(cash);
        }
        else {
            System.out.println("You have not this amount of money!");
        }
    }

}