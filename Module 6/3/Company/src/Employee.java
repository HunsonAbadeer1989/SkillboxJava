abstract class Employee
{
    int BONUS_PAY_BORDER = 10_000_000;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    abstract double getMonthSalary();


}
