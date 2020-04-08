import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test_10_Accounts{

    private static final int RANDOM_TRANSACTIONS = (int) (Math.random() * 4500) + 500;

    private Bank bank;
    private HashMap<String, Account> accounts;

    public Test_10_Accounts(Bank bank) {
        this.bank = bank;
        this.accounts = bank.getAccounts();
    }

    public static void main(String[] args) {
        Bank bank = new Bank();

        for(int i = 1; i <= 10; i++){
            Account account = new Account(bank, String.valueOf(i));
            account.addMoney(1000);
            bank.addAccount(account);
        }

        long moneyBeforeTrans = bank.getBankBalance();

        var e = Executors.newFixedThreadPool(10);
        for(int i = 0; i <= RANDOM_TRANSACTIONS; i++){
            e.submit(() -> {
                String fromAccNumber = String.valueOf((int) (Math.random() * 10));
                String randomAccNumber = String.valueOf((int) (Math.random() * 10));
                long amount = 100 + (long) (Math.random() * 900);
                bank.transfer(fromAccNumber, randomAccNumber, amount);
            });
        }

        e.shutdown();

        try {
            e.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        long moneyAfterTrans = bank.getBankBalance();

        System.out.println(moneyBeforeTrans);
        System.out.println(moneyAfterTrans);
        System.out.println("Count of transactions: " + RANDOM_TRANSACTIONS);
    }

}
