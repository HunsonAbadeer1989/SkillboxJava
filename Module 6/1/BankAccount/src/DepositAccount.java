import java.time.LocalDate;

public class DepositAccount extends Account {

    DepositAccount(double fund) {
        this.fund = fund;
        setDateOfLastAdd(LocalDate.now());
    }

    private double fund;
    private LocalDate dateOfLastAdd;

    void setDateOfLastAdd(LocalDate dateOfLastAdd) {
        this.dateOfLastAdd = dateOfLastAdd;
    }

    void setFund(double fund) {
        this.fund = fund;
    }

    double getFund() {
        return fund;
    }

    @Override
    void addMoney(double cash) {
        setDateOfLastAdd(LocalDate.now());
        setFund(getFund() + cash);
    }

    @Override
    void withdrawMoney(double cash) {
        if (wasMonthAgo()) {
            if (cash > getFund()) {
                System.out.println("You haven't this amount of money!");
            } else {
                setFund(getFund() - cash);
            }
        } else {
            System.out.printf("To soon! You can take your money %s%n", dateOfLastAdd.plusMonths(1));
        }
    }

    @Override
    boolean transferTo(Account account, double money) {
        if (money < 0) {
            return false;
        }
        if (wasMonthAgo()) {
            withdrawMoney(money);
            account.addMoney(money);
            return true;
        } else {
            return false;
        }
    }

    boolean wasMonthAgo() {
        LocalDate now = LocalDate.now();
//        LocalDate now = dateOfLastAdd.plusMonths(2); //If it still here, that mean I will check method in future
        LocalDate lastChange = dateOfLastAdd;
        return !now.minusMonths(1).isBefore(lastChange);
    }
}