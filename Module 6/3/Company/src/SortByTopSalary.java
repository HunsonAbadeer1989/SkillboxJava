import java.util.Comparator;

public class SortByTopSalary implements Comparator<Staff>
{
    @Override
    public int compare(Staff o1, Staff o2) {
        return (int) o2.getMonthSalary() - (int) o1.getMonthSalary();
    }
}
