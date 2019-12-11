import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    private static List<String> agendaList = new ArrayList<>();

    public static void main(String[] args) {
        boolean endProgram = false; // bool for control while cycle line 17
        Scanner scan = new Scanner(System.in);
        agendaList.add("Important business!");
        agendaList.add("Really hot business!");
        agendaList.add("Very important business!");
        agendaList.add("My own business!");
        agendaList.add("Family business!");

        while (!endProgram) {
            Pattern commandPattern = Pattern.compile("(?<comm>[A-Z]{3,6})\\s?(?<num>\\d+)?\\s*(?<business>[\\w+\\s+]*)");
            System.out.println("Input your command: ");
            String command = scan.nextLine();
            Matcher commandMatcher = commandPattern.matcher(command);
            if (commandMatcher.find()) {
                switch (commandMatcher.group("comm")) {
                    case "LIST":
                        printList();
                        break;
                    case "ADD":
                        if (commandMatcher.group("num") != null) {
                            if (indexInList( commandMatcher)) {
                                if (isEmpty(getBusiness(commandMatcher))) {
                                    System.out.println("You forget input business!");
                                } else {
                                    addBusiness( getNum(commandMatcher), getBusiness(commandMatcher));
                                }
                            } else {
                                if (isEmpty(getBusiness(commandMatcher))) {
                                    System.out.println("You forget input business!");
                                } else {
                                    addBusiness(getBusiness(commandMatcher));
                                }
                            }
                        } else {
                            if (isEmpty(getBusiness(commandMatcher))) {
                                System.out.println("You forget input business!");
                            } else {
                                addBusiness(getBusiness(commandMatcher));
                            }
                        }
                        break;
                    case "DELETE":
                        if (commandMatcher.group("num") != null && indexInList( commandMatcher)) {
                            deleteBusiness( getNum(commandMatcher));
                        } else {
                            System.out.println("List hasn't that number!");
                        }
                        break;
                    case "EDIT":
                        if (commandMatcher.group("num") != null) {
                            if (indexInList( commandMatcher)) {
                                if (isEmpty(getBusiness(commandMatcher))) {
                                    System.out.println("You forget input business!");
                                } else {
                                    editBusiness( getNum(commandMatcher), getBusiness(commandMatcher));
                                }
                            } else {
                                System.out.println("List have no business for this number!");
                            }
                        } else {
                            System.out.println("List have no business for this number!");
                        }
                        break;
                    case "END":
                        endProgram = true;
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

    private static String getBusiness(Matcher commandMatcher) {
        return commandMatcher.group("business");
    }
    private static int getNum(Matcher commandMatcher) {
        return Integer.parseInt(commandMatcher.group("num"));
    }
    private static boolean indexInList( Matcher commandMatcher) {
        return getNum(commandMatcher) <= agendaList.size();
    }
    private static void printList() {
        int business = 0;
        for (String x : agendaList)
            System.out.printf("%d - %s\n", business++, x);
    }
    private static void addBusiness(String business) {
        agendaList.add(business);
    }
    private static void addBusiness( int to, String business) {
        agendaList.add(to, business);
    }
    private static void deleteBusiness( int number) {
        if (number <= agendaList.size()) {
            agendaList.remove(number);
        } else {
            System.out.println("List have no business by this number!");
        }
    }
    private static void editBusiness( int number, String business) {
        if (number <= agendaList.size()) {
            agendaList.remove(number);
            agendaList.add(number, business);
        } else {
            System.out.println("List have no business by this number!");
        }
    }
    private static boolean isEmpty(String str) {
        return str != null && str.equals("");
    }
}