import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class MyBirthdayCalendar
{
    public static void main(String[] args) {

        Calendar myCalendar = Calendar.getInstance(new Locale("ru"));
        Calendar calendar = Calendar.getInstance();
        myCalendar.set(1989, Calendar.JULY, 8);

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("ru"));

        for (int i = 0; myCalendar.get(Calendar.YEAR) <= calendar.get(Calendar.YEAR); ) {
            LocalDate myBirthDate = LocalDate.of(myCalendar.get(Calendar.YEAR), Month.JULY, 8);
            System.out.println(i + " - " + formatter.format(myBirthDate.getDayOfWeek()) + " \t\t " + myBirthDate);
            myCalendar.add(Calendar.YEAR, 1);
            i++;
        }
    }
}