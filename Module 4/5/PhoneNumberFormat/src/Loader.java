import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input phone number: ");
        String userInput = scan.nextLine();

        Pattern p = Pattern.compile("^\\+?\\w?[\\s|-]?\\(?(\\w{3})\\)?[\\s|-]?(\\w{3})[\\s|-]?(\\w{2})[\\s|-]?(\\w{2})$");
        Matcher m = p.matcher(userInput);
        boolean result = m.find();

        if (result) {
            StringBuffer strbuff = new StringBuffer();
            m.appendReplacement(strbuff, "+7 $1 $2-$3-$4");
            System.out.println(strbuff);
        }
        else{
            System.out.println("Your phone isn't valid.");
        }
    }
}
