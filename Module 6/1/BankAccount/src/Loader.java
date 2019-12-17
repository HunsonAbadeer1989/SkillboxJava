import java.time.LocalDate;
import java.time.Month;

public class Loader
{
    public static void main(String[] args) {

        DepositAccount myAccount = new DepositAccount(1000.0);
        myAccount.addMoney(500.0);
        myAccount.addMoney(700.0);
        System.out.println(myAccount.getFund());
        myAccount.withdrawMoney(2000);
        System.out.println(myAccount.getFund());
        myAccount.withdrawMoney(100);
        System.out.println();

        CardAccount myCardAcc = new CardAccount(500.0);
        myCardAcc.addMoney(500);
        myCardAcc.withdrawMoney(100.0);
        System.out.println(myCardAcc.getFund());
        myCardAcc.withdrawMoney(10000.0);
    }
}
