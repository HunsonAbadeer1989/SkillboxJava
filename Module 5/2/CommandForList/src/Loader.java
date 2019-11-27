import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String[] args)
    {
        boolean endProgram = false; // bool for control while cycle line 19

        Scanner scan = new Scanner(System.in);
        List<String> agendaList = new ArrayList<>();
        agendaList.add("Important business!");
        agendaList.add("Really hot business!");
        agendaList.add("Very important business!");
        agendaList.add("My own business!");
        agendaList.add("Family business!");

        while (!endProgram) {
            Pattern pattern = Pattern.compile("([A-Z]+)\\s?(\\d+|\\w+\\s?\\w*\\s*\\w+)?\\s*(\\w+\\s?\\w*\\s*\\w+)?");
            Pattern dp = Pattern.compile("\\d+");
            System.out.println("Input your command: ");
            String command = scan.nextLine();
            Matcher m = pattern.matcher(command);
            m.find();

            // switch check the command to use method
            try {
                switch (m.group(1)) {
                    case ("LIST"):
                        printList(agendaList);
                        break;

                    case ("ADD"):
                        Matcher dm = dp.matcher(m.group(2));
                        if (dm.find()) {
                            addBusiness(agendaList, Integer.parseInt(m.group(2)), m.group(3));
                            printList(agendaList);
                        } else {
                            addBusiness(agendaList, m.group(2));
                            printList(agendaList);
                        }
                        break;

                    case ("DELETE"):
                        deleteBusiness(agendaList, Integer.parseInt(m.group(2)));
                        printList(agendaList);
                        break;

                    case ("EDIT"):
                        editBusiness(agendaList, Integer.parseInt(m.group(2)), m.group(3));
                        printList(agendaList);
                        break;

                    case ("END"):
                        endProgram = true;
                        break;

                    default:
                        System.out.println("Try again!");
                        break;
                }
            }
            catch (RuntimeException e){
                String s = e.getMessage();
                System.out.println(s);
                System.out.println("Use only LIST, ADD, EDIT, AND DELETE commands! ");
            }
        }
    }

    // Print list
    public static void printList(List<String> list) {
        int business = 0;
        for (String x : list)
            System.out.printf("%d - %s\n", business++, x);
    }

    // Add business in list
    public static void addBusiness(List<String> list, String business) {
        list.add(business);
    }

    // Add business in position
    public static void addBusiness(List<String> list, int to, String business ) {
        list.add(to, business);
    }

    // Delete business from position
    public static void deleteBusiness(List<String> list, int number) {
        list.remove(number);
    }

    // Edit some business
    public static void editBusiness(List<String> list, int number, String business) {
        list.remove(number);
        list.add(number, business);
    }
}
