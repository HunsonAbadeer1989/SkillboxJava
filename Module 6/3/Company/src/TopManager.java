public class TopManager extends Staff
{
    private static final int BONUS_PAY_BORDER = 10_000_000;
    private static final double TOP_MANAGER_BONUS = 1.5;
    private static final double MIN_SALARY = 40_000.0;
    private static final double MAX_SALARY = 50_000.0;

    public TopManager() {
        setFixedSalary(Math.random() * (MAX_SALARY - MIN_SALARY) + MIN_SALARY);
    }

    @Override
    public double getMonthSalary() {
        return getCompany().getIncome() < BONUS_PAY_BORDER ? getFixedSalary() * TOP_MANAGER_BONUS : getFixedSalary();
    }
}
