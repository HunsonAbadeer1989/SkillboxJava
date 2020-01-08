import java.util.ArrayList;
import java.util.List;

public class Company {

    private String companyName;
    private double income;
    private List<Employee> employeeList = new ArrayList<>();

    public Company(String name, double income) {
        this.companyName = name;
        this.income = income;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getIncome() {
        return income;
    }

    public boolean hire(Employee emp){
        if (emp instanceof Operator) {
            ((Operator) emp).setCompanyName(getCompanyName());
            ((Operator) emp).setCompanyIncome(getIncome());
            return employeeList.add(emp);
        }
        else if (emp instanceof Manager) {
            ((Manager) emp).setCompanyName(getCompanyName());
            ((Manager) emp).setCompanyIncome(getIncome());
            return employeeList.add(emp);
        }
        else if (emp instanceof TopManager ){
            ((TopManager) emp).setCompanyName(getCompanyName());
            ((TopManager) emp).setCompanyIncome(getIncome());
            return employeeList.add(emp);
        }
        return false;
    }

    public boolean hire(EmployeeType type, int count){
        switch(type){
            case TOP_MANAGER_TYPE:
                for(int i = 0; i < count; i++){
                    employeeList.add(new TopManager());
                }
                return true;
            case MANAGER_TYPE:
                for(int i = 0; i < count; i++){
                    employeeList.add(new Manager());
                }
                return true;
            case OPERATOR_TYPE:
                for(int i = 0; i < count; i++){
                    employeeList.add(new Operator());
                }
                return true;
            default: return false;
        }
    }

    public void hireAll(int operators, int managers, int topManagers){
        for(int i = 0; i < operators; i++){
            Staff staff = new Operator();
            employeeList.add(staff);
        }
        for(int i = 0; i < managers; i++){
            Staff staff = new Manager();
            employeeList.add(staff);
        }
        for(int i = 0; i < topManagers; i++){
            Staff staff = new TopManager();
            employeeList.add(staff);
        }

    }

    public void fire(int percent){
        int size = employeeList.size();
        int firePercent = size * percent /100;
        for(int i = 0; i < firePercent; i++) {
            employeeList.remove(employeeList.get(0));
        }
    }

    public void getEmployeeList(){
        System.out.println(String.format("Employees of %s company:", getCompanyName()));
        for(Employee e: employeeList) {
            System.out.println(e);
        }
    }

    public List<Employee> getTopSalaryStaff(int count){
        employeeList.sort(new SortByTopSalary());
        ArrayList<Employee> topSalaryList = new ArrayList<>();
        System.out.printf("Top salary of %d: \n", count);
        for(int i = 0; i < count; i++){
            topSalaryList.add(employeeList.get(i));
            System.out.println(topSalaryList.get(i));
        }
        return topSalaryList;
    }

    public List<Employee> getLowestSalaryStaff(int count){
        employeeList.sort(new SortByLowestSalary());
        ArrayList<Employee> lowSalaryList = new ArrayList<>();
        System.out.printf("Top salary of %d: \n", count);
        for(int i = 0; i < count; i++){
            lowSalaryList.add(employeeList.get(i));
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
