import java.util.List;

abstract class Staff extends Employee
{
    private String employeeName;
    private double salary;
    private static int count;

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