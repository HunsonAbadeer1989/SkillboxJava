public class Manager extends Staff
{
    private static final double MANAGER_BONUS = 0.05;
    private static final double MIN_SALARY = 10_000.0;
    private static final double MAX_SALARY = 30_000.0;
    private static final double MAX_SALES_PER_MONTH = 150_000.0;
    private double salesAmount;

    public Manager() {
        super((Math.random() * (MAX_SALARY - MIN_SALARY) + MIN_SALARY));
        setSalesAmount(Math.random() * MAX_SALES_PER_MONTH);
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    @Override
    public double getMonthSalary() {
        return getFixedSalary() + getCompany().getIncome() * MANAGER_BONUS;
    }
}
