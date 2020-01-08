import java.util.List;

abstract class Staff implements Employee
{
    protected List<Staff> staffList;
    private String employeeName;
    private double salary;
    private static int count;
    private String companyName;
    private double companyIncome;

    protected Staff(){
        this.employeeName = "Employee " + ++count;
    }

    protected Staff(String name, double salary, Company company){
        this.employeeName = name;
        this.salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getCompanyIncome() {
        return companyIncome;
    }

    public void setCompanyIncome(double companyIncome) {
        this.companyIncome = companyIncome;
    }

    public List<Staff> getStaff(){
        return staffList;
    }

    @Override
    public String toString() {
        return String.format("Name %s, month salary = %.2f", getEmployeeName(), getMonthSalary());
    }
}