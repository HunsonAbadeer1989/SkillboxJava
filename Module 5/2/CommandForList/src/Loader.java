import java.util.*;

public class Loader
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        List<String> agendaList = new ArrayList<>();
        agendaList.add("Important business!");
        agendaList.add("Really business!");
        agendaList.add("Very business!");
        agendaList.add("My own business!");
        agendaList.add("Family business!");

        

//        printList(agendaList);
//        System.out.println("Delete business: ");
//        deleteBusiness(agendaList, scan.nextInt());
//        printList(agendaList);
//        System.out.println("Input business and position : ");
//        editBusiness(agendaList, scan.nextInt(), scan.nextLine().trim() );
//        printList(agendaList);

    }

    public static void printList(List<String> list) {
        int business = 0;
        for (String x : list)
            System.out.printf("%d - %s\n", business++, x);
    }

    public static void addBusiness(List<String> list, String business) {
        list.add(business);
    }

    public static void addBusiness(List<String> list, String business, int to) {
        list.add(to, business);
    }

    public static void deleteBusiness(List<String> list, int number){
        list.remove(number);
    }

    public static void editBusiness(List<String> list, int number, String business){
        list.remove(number);
        list.add(number, business);
    }
}
