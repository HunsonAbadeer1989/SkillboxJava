public class Loader
{
    public static void main(String[] args) {
        Company company1 = new Company("Nuka-Cola",10_000_000);
//        Manager man = new Manager();
//        company1.hire(man);
//        System.out.println(man.getEmployeeName());
//        System.out.println("SALARY: " + man.getSalary());
//        Manager man2 = new Manager();
//        Manager man3 = new Manager();
//        Manager man4 = new Manager();
//        System.out.println(man4.getEmployeeName());
//        Operator operator1 = new Operator();
//        System.out.println(operator1.getEmployeeName());
//        System.out.println(man.getCompanyName());
//        System.out.println(man.getCompanyIncome());
//        company1.hire(EmployeeType.MANAGER_TYPE, 10);
//        company1.getEmployeeList();
//        company1.fire(50);
//        company1.getEmployeeList();
        company1.hireAll(180, 80, 10);
        company1.getEmployeeList();
        company1.fire(90);
        company1.getEmployeeList();
        company1.getTopSalaryStaff(5);
        company1.getLowestSalaryStaff(5);
    }
}
