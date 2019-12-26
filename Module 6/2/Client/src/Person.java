public class Person extends Client
{
    public Person(double fund) {
        super(fund);
    }

    @Override
    public void withdrawMoney(double cash) {
        if (cash < 0){
            System.out.println("Wrong amount!");
        }
        else if (cash <= getFund()){
            super.withdrawMoney(cash);
        }
        else {
            System.out.println("You have not this amount of money!");
        }
    }
}
