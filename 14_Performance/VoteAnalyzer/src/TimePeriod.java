import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod>
{
    private long from;
    private long to;
    private Date current;
    private Date compared;
    private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");


    /**
     * Time period within one day
     * @param from
     * @param to
     */
    public TimePeriod(long from, long to)
    {
        this.from = from;
        this.to = to;
//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        if(!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(to))))
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
    }

    public TimePeriod(Date from, Date to)
    {
        this.from = from.getTime();
        this.to = to.getTime();
//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        if(!dayFormat.format(from).equals(dayFormat.format(to)))
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
    }

    public void appendTime(Date visitTime)
    {
        long visitTimeTs = visitTime.getTime();
//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        if(!Long.valueOf(from).equals(visitTimeTs)) {
            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
        }
        if(visitTimeTs < from) {
            from = visitTimeTs;
        }
        if(visitTimeTs > to) {
            to = visitTimeTs;
        }
    }

    public String toString()
    {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String from = dateFormat.format(this.from);
        String to = timeFormat.format(this.to);
        return from + "-" + to;
    }

    @Override
    public int compareTo(TimePeriod period)
    {
//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        //            current = dayFormat.parse(dayFormat.format(new Date(from)));
//            compared = dayFormat.parse(dayFormat.format(new Date(period.from)));
        String cur = String.valueOf(from);
        String comp = String.valueOf(period.from);
        //        return current.compareTo(compared);
        return cur.compareTo(comp);
    }
}
