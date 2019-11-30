import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class MyBirthdayCalendar
{
    public static void main(String[] args) {
        // cant find way to print days of week in russian without using java.util.Locale in DateTImeFormatter
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
        LocalDate myBD = LocalDate.of(1989, Month.JULY, 8);
        LocalDate now = LocalDate.now();
        int count = 0;
        do {
            System.out.printf(" %d - %s - %s \n", count++, myBD, formatter.format(myBD));
            myBD = myBD.plusYears(1);
        } while ( myBD.isBefore(now));
    }
}