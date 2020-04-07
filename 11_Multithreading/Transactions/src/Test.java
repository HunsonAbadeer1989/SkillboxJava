import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    private Bank bank;
    private HashMap<String, Account> accounts;

    public Test(Bank bank) {
        this.bank = bank;
        this.accounts = bank.getAccounts();
    }

    public static void main(String[] args) {
        Bank bank = new Bank();

        Account baseAcc = new Account(bank, "base");
        baseAcc.addMoney(200);

        var e = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int transferTo = i;
            bank.addAccount(new Account(bank, String.valueOf(transferTo)));
            e.submit(() -> {
                bank.transfer("base", String.valueOf(transferTo), 100);
            });
        }

        e.shutdown();

        try {
            e.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        long summ = bank.getAccounts().values().stream()
                .mapToLong(Account::checkBalance)
                .sum();

        System.out.println("Total: " + summ + " [expected: 200]");
    }

}