import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test_1_RUB {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Account baseAcc = new Account(bank, "base");
        baseAcc.addMoney(200);

        var e = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int transferTo = i;
            bank.addAccount(new Account(bank, String.valueOf(transferTo)));
            e.submit(() -> {
                bank.transfer("base", String.valueOf(transferTo), 1);
            });
        }

        e.shutdown();

        try {
            e.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("First account summ: " + baseAcc.checkBalance() + " [expected: 199]");
    }

}
