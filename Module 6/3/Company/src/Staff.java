
abstract class Staff
{
    private String employeeName;
    private double salary;
    private static int count;

    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    abstract double getMonthSalary();

    protected Staff(){
        this.employeeName = "Employee " + ++count;
    }

    protected Staff(String name, double salary){
        this.employeeName = name;
        this.salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Name %s, month salary = %.2f", getEmployeeName(), getMonthSalary());
    }
}