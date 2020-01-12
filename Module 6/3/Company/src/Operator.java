public class Operator extends Staff
{
    private static final double MIN_SALARY = 5_000.0;
    private static final double MAX_SALARY = 10_000.0;

    protected Operator(){
        setFixedSalary(Math.random() * (MAX_SALARY - MIN_SALARY) + MIN_SALARY);
    }

    @Override
    public double getMonthSalary() {
        return getFixedSalary();
    }

}
