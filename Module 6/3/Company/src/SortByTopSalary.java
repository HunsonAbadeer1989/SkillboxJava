import java.util.Comparator;

public class SortByTopSalary implements Comparator<Employee>
{
    @Override
    public int compare(Employee o1, Employee o2) {
        return (int) o2.getMonthSalary() - (int) o1.getMonthSalary();
    }
}
