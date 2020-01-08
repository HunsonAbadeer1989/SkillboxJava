
public class Manager extends Staff
{
    private static final double MANAGER_BONUS = 0.05;
    private EmployeeType type = EmployeeType.MANAGER_TYPE;
    private static final double MIN_SALARY = 10_000.0;
    private static final double MAX_SALARY = 30_000.0;

    public Manager() {
        super();
        setSalary(Math.random() * (MAX_SALARY - MIN_SALARY) + MIN_SALARY);
    }

    public Manager(String name, double managerSalary, Company company) {
        super(name, managerSalary, company);
    }

    @Override
    public double getMonthSalary() {
        return getSalary() + getCompanyIncome() * MANAGER_BONUS;
    }
}
