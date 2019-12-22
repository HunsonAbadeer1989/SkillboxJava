import java.time.LocalDate;

public class DepositAccount extends Account {

    DepositAccount(double fund) {
        super(fund);
        setDateOfLastAdd(LocalDate.now());
    }

    private LocalDate dateOfLastAdd;

    void setDateOfLastAdd(LocalDate dateOfLastAdd) {
        this.dateOfLastAdd = dateOfLastAdd;
    }

    @Override
    void addMoney(double cash) {
        setDateOfLastAdd(LocalDate.now());
        setFund(getFund() + cash);
    }

    @Override
    boolean withdrawMoney(double cash) {
        if (wasMonthAgo()) {
            if (cash > getFund()) {
                System.out.println("You haven't this amount of money!");
                return false;
            } else {
                setFund(getFund() - cash);
                return true;
            }
        } else {
            System.out.printf("To soon! You can take your money %s%n", dateOfLastAdd.plusMonths(1));
            return false;
        }
    }

    @Override
    boolean transferTo(Account account, double money) {
        return super.transferTo(account, money);
    }

    boolean wasMonthAgo() {
//        LocalDate now = LocalDate.now();
        LocalDate now = dateOfLastAdd.plusMonths(2); //If it still here, that mean I will check method in future
        LocalDate lastChange = dateOfLastAdd;
        return !now.minusMonths(1).isBefore(lastChange);
    }
}