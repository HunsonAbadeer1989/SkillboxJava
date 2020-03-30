import java.util.concurrent.atomic.AtomicLong;

public class Account {
    //    private AtomicLong money;
    private volatile Long money;
    private String accNumber;
    private Bank bank;
    private volatile boolean blocked;
    private volatile boolean isChecking;

    public Account(Bank bank, String accNumber) {
        this.bank = bank;
        this.accNumber = accNumber;
        blocked = false;
//        money = new AtomicLong();
        money = 0L;
        this.bank.addAccount(this);
        isChecking = false;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked() {
        this.blocked = true;
    }

    public long checkBalance() {
        synchronized (this) {
            return money.longValue();
        }
    }

    public void addMoney(long money) {
        if (money > 0) {
//            this.money.addAndGet(money);
            setMoney(this.money + money);
        } else {
            System.out.println("Deposit must be positive!");
        }
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void withdraw(long amount) {
        if (canWithdraw(amount)) {
//            this.money.addAndGet(-amount);
            setMoney(this.money - amount);

        } else {
            System.out.println("You can't withdraw negative number!");
        }
    }

    public synchronized boolean canWithdraw(long amount) {
        if (amount > 0 && amount <= this.checkBalance()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCheck() {
        return isChecking;
    }

    @Override
    public String toString() {
        return "Account " + accNumber;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
