public class Person implements Client
{
    Person(double found){
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
        if ( money < 0) {
            System.out.println("Wrong amount!");
        }
        else {
            setFound(getFound() + money);
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
