import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
        boolean end = false;
        Map<String, String> phonebook = new TreeMap<>();
        phonebook.put("+79458376254", "Ann");
        phonebook.put("+79458395257", "Ann");
        phonebook.put("+7 945 838-72-53", "Anton");
        phonebook.put("+7 945 837-65-45", "Kate");

        while (!end) {
            System.out.println("Input phone number or contact: ");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equals("END")) {
                end = true;
            }
            else if (input.equals("LIST")) {
                Collection<Map.Entry<String, String>> sort = sortedByValues(phonebook);
                for (Map.Entry<String, String> entry : sort){
                    String k = entry.getKey();
                    String v = entry.getValue();
                        System.out.printf("%s, %s. \n", v, k);
                }
            }
            else if (isName(input)) {
                if (phonebook.containsValue( input )) {
                    for (Map.Entry<String, String> entry : phonebook.entrySet()){
                        String k = entry.getKey();
                        String v = entry.getValue();
                        if ( v.equals(input) ){
                            System.out.printf("The contact: %2s have phone number %1s. \n", v, k);
                        }
                    }
                }
                else {
                    System.out.println("Input phone number for: " + input);
                    String pn = scan.nextLine();
                    if (isNumber(pn)) {
                        phonebook.put(formatNumber(pn), input);
                    }
                    System.out.println("Wrong phone number!");
                }
            }
        else if (isNumber(input)) {
                if ( phonebook.containsKey(formatNumber(input))) {
                    System.out.println("The phone: " + formatNumber(input) + " " + phonebook.get(formatNumber(input)));
                }
                else {
                    System.out.println("Input name for that number: ");
                    String name = scan.nextLine();
                    if (isName(name)){
                        phonebook.put(formatNumber(input), name);
                        System.out.printf( "Number %s was added by name %s\n", formatNumber(input), name );
                    }
                }
            } else {
                System.out.println("Wrong input!");
            }
        }
    }

    private static Collection<Map.Entry<String, String>> sortedByValues(Map<String, String> phonebook) {
        List<Map.Entry<String, String>> list = new ArrayList<>(phonebook.entrySet());
        list.sort(new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list;
    }

    public static final Pattern PHONE_NUMBER = Pattern.compile("^\\+?\\w?[\\s|-]?\\(?(\\w{3})\\)?[\\s|-]?(\\w{3})" +
            "[\\s|-]?(\\w{2})[\\s|-]?(\\w{2})$");
    public static final Pattern CONTACT_NAME = Pattern.compile("(?<name>[a-zA-Z]+)");

    public static boolean isNumber(String number){
        Matcher numberMatch = PHONE_NUMBER.matcher(number);
        return numberMatch.find();
    }
    public static boolean isName(String name){
        Matcher nameMatch = CONTACT_NAME.matcher(name);
        return nameMatch.find();
    }
    public static String formatNumber(String phoneNumber) {
        return phoneNumber.replaceFirst(PHONE_NUMBER.toString(), "+7 $1 $2-$3-$4");
    }

}