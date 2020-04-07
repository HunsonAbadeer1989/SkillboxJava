import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test_10_Accounts implements Runnable{

    private static final int RANDOM_TRANSACTIONS = (int) (Math.random() * 4500) + 500;

    private Bank bank;
    private HashMap<String, Account> accounts;

    public Test_10_Accounts(Bank bank) {
        this.bank = bank;
        this.accounts = bank.getAccounts();
    }

    @Override
    public void run() {
        for(int i = 0; i < RANDOM_TRANSACTIONS; i++){
            String fromAccNumber = String.valueOf((int) (Math.random() * accounts.values().size()));
            String randomAccNumber = String.valueOf((int) (Math.random() * accounts.values().size()));
            long amount = 100 + (long) (Math.random() * 900);
            bank.transfer(fromAccNumber, randomAccNumber, amount);
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();

        for(int i = 1; i <= 10; i++){
            Account account = new Account(bank, String.valueOf(i));
            account.addMoney(1000);
            bank.addAccount(account);
        }

        long moneyBeforeTrans = bank.getBankBalance();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(new Loader(bank)));
        }
        threadList.forEach(Thread::start);

        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long moneyAfterTrans = bank.getBankBalance();

        System.out.println(moneyBeforeTrans);
        System.out.println(moneyAfterTrans);
        System.out.println("Count of transactions" + RANDOM_TRANSACTIONS);
    }

}
