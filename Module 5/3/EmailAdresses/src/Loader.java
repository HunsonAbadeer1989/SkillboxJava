import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
        boolean end = false;
        Scanner scan = new Scanner(System.in);
        List<String> emailList = new LinkedList<>();

        emailList.add("hankhill1957@gmail.com");
        emailList.add("dalegribble1957@yandex.ru");
        emailList.add("boomhauer1957@mail.ru");
        emailList.add("billdotrew1957@gmail.com");

        while (!end) {
            Pattern p = Pattern.compile("^([A-Z]{3,4})\\s?(([a-zA-Z_]+)([a-zA-Z0-9_]*)@(gmail|yandex|mail)\\.(ru|com)$)*");
            System.out.println("Input command 'LIST' or 'ADD' and email': ");
            String userInput = scan.nextLine();
            Matcher m = p.matcher(userInput);
            if ( m.find() ) {
                switch (m.group(1)) {
                    case "ADD":
                        addInList(emailList, m.group(2));
                        break;
                    case "LIST":
                        printList(emailList);
                        break;
                    case "END":
                        System.out.println("Good bye!");
                        end = true;
                        break;
                }
            }
            else {
                System.out.println("Incorrect input! Try again, only ADD, LIST and END commands.");
            }
        }
    }

    public static void printList(List<String> list) {
            for(String email: list){
                System.out.println(email);
            }
    }

    public static void addInList(List<String> list, String email){
            list.add(email);
            printList(list);
    }
}
