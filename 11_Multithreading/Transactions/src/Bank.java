import java.util.HashMap;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Bank {
    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank() {
        accounts = new HashMap<String, Account>();
    }

    public boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        sleep(1000);

        Account from = accounts.get(fromAccountNum);
        Account to = accounts.get(toAccountNum);

        from.setOnCheck(true);
        to.setOnCheck(true);

        boolean result = random.nextBoolean();

        from.setOnCheck(false);
        to.setOnCheck(false);

        return result;
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
//                    System.out.println("You cant transfer money from same account.");
                } else {
                    if (!fromAccount.isBlocked() && !toAccount.isBlocked()) {

                        transferMoney(fromAccount, toAccount, amount);
                        System.out.printf("Transfer from: %s , to %s \n", fromAccount.toString(), toAccount.toString());
                        System.out.printf("\t\t%s: %s, %s: %s \n", fromAccount.toString(), fromAccount.checkBalance(), toAccount.toString(), toAccount.checkBalance());

                    } else {
//                        System.out.println("Wrong operation. Account(s) was blocked.");
                    }
                }
            }
        } else {
//            System.out.println("Wrong!!! Check account numbers and try again.");
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

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    private synchronized void lockAccounts(Account first, Account second) {
        first.setBlocked();
        second.setBlocked();
    }

    private void transferMoney(Account fromAccount, Account toAccount, long amount) {
        synchronized (fromAccount) {
            if (fromAccount.canWithdraw(amount)) {
                fromAccount.withdraw(amount);
                toAccount.addMoney(amount);
//                lockAccounts(fromAccount, toAccount);

                try {
                    String fromAccountNum = fromAccount.getAccNumber();
                    String toAccountNum = toAccount.getAccNumber();
                    if (amount > 50_000 && isFraud(fromAccountNum, toAccountNum, amount)) {
                        lockAccounts(fromAccount, toAccount);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("You can't withdraw this sum.");
            }
        }
    }

    protected long getBankBalance() {
        return accounts.values().stream().mapToLong(Account::checkBalance).sum();
    }

}