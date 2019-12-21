public class IP implements Client
{
    private static final double TAX = 0.01;
    private static final double HALF_TAX = 0.01;

    IP(double found){
    this.found = found;
}

    private double found;

    public double getFound() {
        return found;
    }

    public void setFound(double found) {
        this.found = found;
    }

    @Override
    public void addMoney(double money) {
        if (money < 0){
            System.out.println("Wrong amount!");
        }
        else if (money < 1000.0){
            setFound(getFound() + money * TAX);
        }
        else {
            setFound(getFound() + money * HALF_TAX);
        }
    }

    @Override
    public void withdrawMoney(double money) {
        if (money < 0){
            System.out.println("Wrong amount!");
        }
        else if (money <= getFound()){
            setFound(getFound() - money);
        }
        else {
            System.out.println("You have not this amount of money!");
        }
    }

    @Override
    public void balance() {
        System.out.println(getFound());
    }
}