
abstract class Staff
{
    private String employeeName;
    private double fixedSalary;
    private static int count;

    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    abstract double getMonthSalary();

    protected Staff(double fixedSalary){
        this.employeeName = "Employee " + ++count;
        this.fixedSalary = fixedSalary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getFixedSalary() {
        return fixedSalary;
    }

    @Override
    public String toString() {
        return String.format("Name %s, month salary = %.2f", getEmployeeName(), getMonthSalary());
    }
}