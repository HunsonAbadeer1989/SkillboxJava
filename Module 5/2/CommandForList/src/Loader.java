import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
        boolean endProgram = false; // bool for control while cycle line 19
        Scanner scan = new Scanner(System.in);
        List<String> agendaList = new ArrayList<>();
        agendaList.add("Important business!");
        agendaList.add("Really hot business!");
        agendaList.add("Very important business!");
        agendaList.add("My own business!");
        agendaList.add("Family business!");

        while (!endProgram) {
            Pattern commandPattern = Pattern.compile("(?<comm>[A-Z]{3,4})\\s?(?<num>\\d+)?\\s*(?<business>[\\w+\\s+]*)");
            Pattern digitPattern = Pattern.compile("\\d+");
            System.out.println("Input your command: ");
            String command = scan.nextLine();
            Matcher commandMatcher = commandPattern.matcher(command);
            if ( commandMatcher.find() ) {
                switch (commandMatcher.group("comm")) {
                    case "LIST":
                        printList(agendaList);
                        break;
                    case "ADD":
                        Matcher dm = digitPattern.matcher(commandMatcher.group("num"));
                        if (dm.find()) {
                            addBusiness(agendaList, Integer.parseInt(commandMatcher.group("num")), commandMatcher.group("business"));
                            printList(agendaList);
                        } else {
                            addBusiness(agendaList, commandMatcher.group("num"));
                            printList(agendaList);
                        }
                        break;
                    case "DELETE":
                        deleteBusiness(agendaList, Integer.parseInt(commandMatcher.group("num")));
                        printList(agendaList);
                        break;
                    case "EDIT":
                        editBusiness(agendaList, Integer.parseInt(commandMatcher.group("num")), commandMatcher.group("business"));
                        printList(agendaList);
                        break;
                    case "END":
                        endProgram = true;
                        break;
                    case " ":
                        System.out.println("Bad input!");
                        break;
                    default:
                        System.out.println("Try again!");
                        break;
                }
            } else {
                System.out.println("Bad input!");
            }
        }
    }
    // Print list
    private static void printList(List<String> list) {
        int business = 0;
        for (String x : list)
            System.out.printf("%d - %s\n", business++, x);
    }
    // Add business in list
    private static void addBusiness(List<String> list, String business) {
        list.add(business);
    }
    // Add business in position
    private static void addBusiness(List<String> list, int to, String business) {
        list.add(to, business);
    }
    // Delete business from position
    private static void deleteBusiness(List<String> list, int number) {
        list.remove(number);
    }
    // Edit some business
    private static void editBusiness(List<String> list, int number, String business) {
        list.remove(number);
        list.add(number, business);
    }
}
