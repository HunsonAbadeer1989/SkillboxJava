import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Loader implements Runnable{
    private static final int COUNT_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private Bank bank;
    private ConcurrentHashMap<String, Account> accounts;

    public Loader(Bank bank) {
        this.bank = bank;
        this.accounts = bank.getAccounts();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000; i++) {
            String randomAccNumber = String.valueOf((int) (Math.random() * accounts.values().size()));
            accounts.values().forEach(fromAccount -> {
                long amount = 100 + (long) (Math.random() * 60_000);
                bank.transfer(fromAccount.getAccNumber(), randomAccNumber, amount);
            });
        }
    }

    public static void main(String[] args){
        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Account account = new Account(bank, String.valueOf(i));
            accounts.add(account);
            account.addMoney(10_000 + (long) (Math.random() * 200_000));
        }
        long moneyInBankBeforeThreads = bank.getBankBalance();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < COUNT_OF_PROCESSORS; i++) {
            threadList.add(new Thread(new Loader(bank)));
        }
        threadList.forEach(Thread::start);

        long moneyInBankAfterThreads = bank.getBankBalance();

        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(moneyInBankBeforeThreads);
        System.out.println(moneyInBankAfterThreads);
    }
}
