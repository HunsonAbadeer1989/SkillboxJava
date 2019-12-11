import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println( "Input Surname Name Patronymic: " );
        String[] snp = { "Surname: ", "Name: ", "Patronymic: " };
        String[] fio = scan.nextLine().split(" " );
        Pattern p = Pattern.compile( "(\\w+)" );
        for ( int i = 0; i < fio.length; i++ ) {
            String currstr = fio[i];
            String outstr;
            Matcher m = p.matcher(currstr);
            boolean result = m.find();
            if ( result ) {
                StringBuffer str = new StringBuffer();
                do {
                    m.appendReplacement(str,"$1");
                    result = m.find();
                } while ( result );
                m.appendTail(str);
                outstr = str.toString();
            }
            else {
                outstr = currstr;
            }

            System.out.println( snp[i] + outstr );
        }
    }
}