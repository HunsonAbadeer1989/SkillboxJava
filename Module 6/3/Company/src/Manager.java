
public class Manager extends Staff
{
    private static final double MANAGER_BONUS = 0.05;
    private static final double MIN_SALARY = 10_000.0;
    private static final double MAX_SALARY = 30_000.0;

    public Manager() {
        setSalary(Math.random() * (MAX_SALARY - MIN_SALARY) + MIN_SALARY);
    }

    @Override
    public double getMonthSalary() {
        return getSalary() + getCompany().getIncome() * MANAGER_BONUS;
    }
}
