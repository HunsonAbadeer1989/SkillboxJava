public class Loader
{
    public static void main(String[] args) {
        Person p2 = new Person(100.0);
        Person p3 = new Person(200.0);
        Person p1 = new Person(300.0);

        p1.addMoney(50);
        p1.withdrawMoney(10.0);
        p1.withdrawMoney(-10.0);
        p1.withdrawMoney(1000.0);
        System.out.println(p1.balance());
    }
}
