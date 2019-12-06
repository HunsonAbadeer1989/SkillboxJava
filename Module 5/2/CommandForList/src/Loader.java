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
            Pattern commandPattern = Pattern.compile("(?<comm>[A-Z]{3,6})\\s?(?<num>\\d+)?\\s*(?<business>[\\w+\\s+]*)");
            System.out.println("Input your command: ");
            String command = scan.nextLine();
            Matcher commandMatcher = commandPattern.matcher(command);
            if (commandMatcher.find()) {
                switch (commandMatcher.group("comm")) {
                    case "LIST":
                        printList(agendaList);
                        break;
                    case "ADD":
                        if (hasNum(commandMatcher.group("num"))) {
                            if (indexInList(agendaList, commandMatcher)) {
                                if (isEmpty(getBusiness(commandMatcher))) {
                                    System.out.println("You forget input business!");
                                } else {
                                    addBusiness(agendaList, getNum(commandMatcher), getBusiness(commandMatcher));
                                }
                            } else {
                                if (isEmpty(getBusiness(commandMatcher))) {
                                    System.out.println("You forget input business!");
                                } else {
                                    addBusiness(agendaList, getBusiness(commandMatcher));
                                }
                            }
                        } else {
                            if (isEmpty(getBusiness(commandMatcher))) {
                                System.out.println("You forget input business!");
                            } else {
                                addBusiness(agendaList, getBusiness(commandMatcher));
                            }
                        }
                        break;
                    case "DELETE":
                        if (hasNum(commandMatcher.group("num")) && indexInList(agendaList, commandMatcher)) {
                            deleteBusiness(agendaList, getNum(commandMatcher));
                        } else {
                            System.out.println("List hasn't that number!");
                        }
                        break;
                    case "EDIT":
                        if (hasNum(commandMatcher.group("num"))) {
                            if (indexInList(agendaList, commandMatcher)) {
                                if (isEmpty(getBusiness(commandMatcher))) {
                                    System.out.println("You forget input business!");
                                } else {
                                    editBusiness(agendaList, getNum(commandMatcher), getBusiness(commandMatcher));
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
        return getIndex(commandMatcher.group("num"));
    }
    private static boolean indexInList(List<String> agendaList, Matcher commandMatcher) {
        return inList(agendaList, getNum(commandMatcher));
    }
    private static void printList(List<String> list) {
        int business = 0;
        for (String x : list)
            System.out.printf("%d - %s\n", business++, x);
    }
    private static void addBusiness(List<String> list, String business) {
        list.add(business);
    }
    private static void addBusiness(List<String> list, int to, String business) {
        list.add(to, business);
    }
    private static void deleteBusiness(List<String> list, int number) {
        if (number <= list.size()) {
            list.remove(number);
        } else {
            System.out.println("List have no business by this number!");
        }
    }
    private static void editBusiness(List<String> list, int number, String business) {
        if (number <= list.size()) {
            list.remove(number);
            list.add(number, business);
        } else {
            System.out.println("List have no business by this number!");
        }
    }
    private static boolean inList(List<String> list, int num) {
        return num <= list.size();
    }
    private static boolean hasNum(String str) {
        return str != null;
    }
    private static int getIndex(String str) {
        return Integer.parseInt(str);
    }
    private static boolean isEmpty(String str) {
        return str != null && str.equals("");
    }
}