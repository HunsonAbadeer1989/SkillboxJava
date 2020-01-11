import java.util.ArrayList;
import java.util.List;

public class Company {

    private String companyName;
    private double income;
    private List<Staff> employeeList = new ArrayList<>();

    public Company(String name) {
        this.companyName = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getIncome() {
        return this.income;
    }

    public void setIncome() {
        double income = 0.0;
        for (Staff employee : employeeList) {
            if (employee instanceof TopManager) {
                income += employee.getMonthSalary();
            }
        }
        this.income = income;
    }

    public boolean hire(Staff emp, Company company){
        emp.setCompany(company);
        employeeList.add(emp);
        setIncome();
        return true;
    }

    public void hireAll(int operators, int managers, int topManagers, Company company){
        for(int i = 0; i < operators; i++){
            hire(new Operator(), company);
        }
        for(int i = 0; i < managers; i++){
            hire(new Manager(), company);
        }
        for(int i = 0; i < topManagers; i++){
            hire(new TopManager(), company);
            setIncome();

        }
    }

    public void fire(int percent){
        int size = employeeList.size();
        int firePercent = size * percent /100;
        for(int i = 0; i < firePercent; i++) {
            employeeList.remove(employeeList.get(0));
            setIncome();
        }
    }

    public List<Staff> getTopSalaryStaff(int count){
        employeeList.sort(new SortByTopSalary());
        ArrayList<Staff> topSalaryList = new ArrayList<>(employeeList);
        System.out.printf("Top highest salary of %d: \n", count);
        for(int i = 0; i < count; i++){
            System.out.println(topSalaryList.get(i));
        }
        return topSalaryList;
    }

    public List<Staff> getLowestSalaryStaff(int count){
        employeeList.sort(new SortByTopSalary().reversed());
        ArrayList<Staff> lowSalaryList = new ArrayList<>(employeeList);
        System.out.printf("Top lowest salary of %d: \n", count);
        for(int i = 0; i < count; i++){
            System.out.println(lowSalaryList.get(i));
        }
        return lowSalaryList;
    }

    @Override
    public String toString() {
        return "Company " +
                "name='" + getCompanyName() + '\'' +
                ", company income=" + getIncome();
    }
}