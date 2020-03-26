import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {
    private ConcurrentHashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank(){
        accounts = new ConcurrentHashMap<>(16, 0.9f,1);
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if (accounts.containsKey(fromAccountNum)
                && accounts.containsKey(toAccountNum)) {

            Account fromAccount = accounts.get(fromAccountNum);
            Account toAccount = accounts.get(toAccountNum);
            if (fromAccount.isCheck() || toAccount.isCheck()) {
                System.out.println("Accounts is checking, you can't transfer now, try again later.");
            } else {
                if (fromAccountNum.equals(toAccountNum)) {
                    System.out.println("You cant transfer money from single account.");
                } else {
                    if (!fromAccount.isBlocked() && !toAccount.isBlocked()) {

                            if (fromAccount.canWithdraw(amount)) { // canWithdraw marked synchronized now
                                if (fromAccountNum.compareTo(toAccountNum) < 0) {
                                    synchronized (fromAccount) {
                                        synchronized (toAccount) {
                                            transferMoney(fromAccount, toAccount, amount);
                                        }
                                    }
                                } else {
                                    synchronized (toAccount) {
                                        synchronized (fromAccount) {
                                            transferMoney(fromAccount, toAccount, amount);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("You can't withdraw this sum.");
                            }

                    } else {
                        System.out.println("Wrong operation. Account(s) was blocked.");
                    }
                }
            }
        } else {
            System.out.println("Wrong!!! Check account numbers and try again.");
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        if (accounts.containsKey(accountNum)) {
            return accounts.get(accountNum).checkBalance();
        } else {
            System.out.printf("Can't find account with number %s", accountNum);
            return 0;
        }
    }

    protected void addAccount(Account acc) {
        accounts.put(acc.getAccNumber(), acc);
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }

    private void lockAccounts(Account first, Account second) {
                first.setBlocked();
                second.setBlocked();
    }

    private void transferMoney(Account fromAccount, Account toAccount, long amount) {
        fromAccount.withdraw(amount);
        toAccount.addMoney(amount);
        try {
            String fromAccountNum = fromAccount.getAccNumber();
            String toAccountNum = toAccount.getAccNumber();
            if (amount > 50_000 && isFraud(fromAccountNum, toAccountNum, amount)) {
                lockAccounts(fromAccount, toAccount);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected long getBankBalance(){
       return accounts.values().stream().mapToLong(Account::checkBalance).sum();
    }
}