import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class MyBirthdayCalendar
{
    public static void main(String[] args) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("ru"));
        LocalDate myBD = LocalDate.of(1989, Month.JULY, 8);
        LocalDate now = LocalDate.now();
        int count = 0;
        do {
            System.out.printf(" %d - %s - %s \n", count++, myBD, formatter.format(myBD));
            myBD = myBD.plusYears(1);
        } while ( myBD.isBefore(now));
    }
}