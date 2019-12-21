import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {

        DepositAccount myDepositAccount = new DepositAccount(500.0);
        myDepositAccount.addMoney(500.0);
        myDepositAccount.addMoney(700.0);
        System.out.println(myDepositAccount.getFund());
        myDepositAccount.withdrawMoney(2000);
        System.out.println(myDepositAccount.getFund());
        myDepositAccount.withdrawMoney(100);
        System.out.println();

        CardAccount myCardAcc = new CardAccount(500.0);
        myCardAcc.addMoney(500);
        myCardAcc.withdrawMoney(100.0);
        System.out.println(myCardAcc.getFund());
        myCardAcc.withdrawMoney(10000.0);

        myDepositAccount.transferTo(myCardAcc, 50);
        myCardAcc.transferTo(myDepositAccount, -300);
        System.out.println(myDepositAccount.getFund());
        System.out.println(myCardAcc.getFund());

    }
}
