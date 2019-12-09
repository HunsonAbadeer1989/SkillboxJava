import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {

    public static List<String> emailList = new LinkedList<>();

    public static void main(String[] args) {
        boolean end = false;
        Scanner scan = new Scanner(System.in);
        emailList.add("hankhill1957@gmail.com");
        emailList.add("dalegribble1957@yandex.ru");
        emailList.add("boomhauer1957@mail.ru");
        emailList.add("billdotrew1957@gmail.com");

        while (!end) {
            Pattern commandPattern = Pattern.compile("^([A-Z]+)\\s?(([a-zA-Z_]+)([a-zA-Z0-9_]*)@" +
                    "(gmail|yandex|mail)\\.(ru|com)$)*");
            System.out.println("Input command 'LIST' , 'ADD' or 'DELETE' and email': ");
            String userInput = scan.nextLine();
            Matcher commandMatcher = commandPattern.matcher(userInput);
            if (commandMatcher.find()) {
                switch (commandMatcher.group(1)) {
                    case "ADD":
                        if (commandMatcher.group(1) != null && commandMatcher.group(1).equals("")) {
                            addInList(commandMatcher.group(2));
                        } else {
                            System.out.println("Incorrect input!");
                        }
                        break;
                    case "LIST":
                        printList();
                        break;
                    case "DELETE":
                        if ( commandMatcher.group(2) != null ){
                            if ( inList(commandMatcher.group(2)) ) {
                                deleteFromList(commandMatcher.group(2));
                            }
                        }
                        else {
                                System.out.println("Wrong input!");
                        }
                        break;
                    case "END":
                        System.out.println("Good bye!");
                        end = true;
                        break;
                }
            } else {
                System.out.println("Incorrect input! Try again, only ADD, LIST, DELETE and END commands.");
            }
        }
    }

    public static void printList() {
        for (String email : emailList) {
            System.out.println(email);
        }
    }

    public static void addInList(String email) {
        emailList.add(email);
    }
    public static void deleteFromList( String email ) {
        emailList.remove( email );
    }
    public static boolean inList( String email) {
        return emailList.contains(email);
    }
}
