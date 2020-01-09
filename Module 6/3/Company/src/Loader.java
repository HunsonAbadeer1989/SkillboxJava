public class Loader
{
    public static void main(String[] args) {
        Company company1 = new Company("Nuka-Cola");
        company1.hireAll(180, 80, 10, company1);
        company1.getTopSalaryStaff(15);
        company1.getLowestSalaryStaff(30);
        company1.fire(50);
        company1.getTopSalaryStaff(15);
        company1.getLowestSalaryStaff(30);
        System.out.println(company1.getIncome());
    }
}
