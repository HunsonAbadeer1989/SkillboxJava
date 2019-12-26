import java.time.LocalDate;

public class DepositAccount extends Account {

    private LocalDate dateOfLastAdd;

    DepositAccount(double fund) {
        super(fund);
        setDateOfLastAdd(LocalDate.now());
    }

    void setDateOfLastAdd(LocalDate dateOfLastAdd) {
        this.dateOfLastAdd = dateOfLastAdd;
    }

    @Override
    void addMoney(double cash) {
        setDateOfLastAdd(LocalDate.now());
        super.addMoney(cash);
    }

    @Override
    boolean withdrawMoney(double cash) {
        if (wasMonthAgo()) {
            if (cash > getFund()) {
                System.out.println("You haven't this amount of money!");
                return false;
            } else {
                super.withdrawMoney(cash);
                return true;
            }
        } else {
            System.out.printf("To soon! You can take your money %s%n", dateOfLastAdd.plusMonths(1));
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