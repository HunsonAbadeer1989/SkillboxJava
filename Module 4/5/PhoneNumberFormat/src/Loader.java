import java.util.Formatter;
import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input phone number: ");
        String userInput = scan.nextLine();
        String phone = null;

        String clearPhoneNumber = userInput.replaceAll("\\.?\\D", "");

        if (clearPhoneNumber.length() > 10) { // Cut the first digit if length > 10
            phone = clearPhoneNumber.replaceAll("^\\d", "");
        }

        System.out.println(String.valueOf(phone).replaceFirst("(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "+7 $1 $2-$3-$4"));

    }
}
