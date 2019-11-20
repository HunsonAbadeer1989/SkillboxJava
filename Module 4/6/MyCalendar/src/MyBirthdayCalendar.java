import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyBirthdayCalendar
{
    public static void main(String[] args) {

        Calendar myCalendar = Calendar.getInstance(new Locale("ru"));
        Calendar calendar = Calendar.getInstance();
        myCalendar.set(1989, Calendar.JULY, 8);

        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyy - EEEE", new Locale("ru"));

        for (int i = 0; myCalendar.get(Calendar.YEAR) <= calendar.get(Calendar.YEAR); )
        {
            Date myBirthDate = myCalendar.getTime();
            System.out.println(i + " - " + df.format(myBirthDate));
            myCalendar.add(Calendar.YEAR, 1);
            i++;
        }
    }
}