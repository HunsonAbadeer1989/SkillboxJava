public class TopManager extends Staff
{
    private static final double TOP_MANAGER_BONUS = 1.5;
    private EmployeeType type = EmployeeType.TOP_MANAGER_TYPE;
    private static final double MIN_SALARY = 40_000.0;
    private static final double MAX_SALARY = 50_000.0;

    public TopManager() {
        super();
        setSalary(Math.random() * (MAX_SALARY - MIN_SALARY) + MIN_SALARY);
    }

    public TopManager(String name, double topManagerSalary, Company company) {
        super(name, topManagerSalary, company);
    }

    @Override
    public double getMonthSalary() {
        return getCompanyIncome() < BONUS_PAY_BORDER ? getSalary() * TOP_MANAGER_BONUS : getSalary();
    }
}
