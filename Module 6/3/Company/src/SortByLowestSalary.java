import java.util.Comparator;

public class SortByLowestSalary implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return (int) o1.getMonthSalary() - (int) o2.getMonthSalary();
    }
}
