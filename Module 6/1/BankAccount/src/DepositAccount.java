import java.time.LocalDate;

public class DepositAccount extends Account{

    DepositAccount(double fund){
        this.fund = fund;
    }

    private double fund;
    private LocalDate dateOfLastAdd;

    LocalDate getDateOfLastAdd() {
        return dateOfLastAdd;
    }

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
        if (wasMonthAgo()){
            if ( cash > getFund()){
                System.out.println("You haven't this amount of money!");
            }
            else {
                setFund(getFund() - cash);
            }
        }
        else {
            System.out.printf("To soon! You can take your money %s%n", getDateOfLastAdd().plusMonths(1));
        }
    }

    boolean wasMonthAgo(){
        LocalDate now = LocalDate.now();
        LocalDate lastChange = getDateOfLastAdd();
        return now.minusMonths(1).isAfter(lastChange) || now.minusMonths(1).isEqual(lastChange);
    }
}