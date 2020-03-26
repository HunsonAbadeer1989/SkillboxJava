import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Test implements Runnable {
    private Bank bank;
    private ConcurrentHashMap<String, Account> accounts;

    public Test(Bank bank) {
        this.bank = bank;
        this.accounts = bank.getAccounts();
    }

    @Override
    public void run() {
        for (int i = 1; i < 11; i++) {
            int number = (int) (2 + Math.random() * accounts.values().size());
            String randomAccNumber = String.valueOf(number); //исключаем акк номер 1
            accounts.values().forEach(fromAccount -> {
                long amount = 100;
                bank.transfer(String.valueOf(1), randomAccNumber, amount);
            });
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            Account account = new Account(bank, String.valueOf(i));
            accounts.add(account);
            account.addMoney(200);
        }

        long moneyInBankBeforeThreads = bank.getBankBalance();

        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Thread(new Test(bank)));
        threadList.forEach(Thread::start);

        long moneyInBankAfterThreads = bank.getBankBalance();

        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
// 15258
        Account acc1 = accounts.get(1);
        System.out.println(acc1.checkBalance());
        System.out.println(moneyInBankBeforeThreads);
        System.out.println(moneyInBankAfterThreads);
    }
}
