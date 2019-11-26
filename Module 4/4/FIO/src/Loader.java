import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input Surname Name Patronymic: ");
        Person person = new Person();

        person.setSurName(scan.next());
        person.setName(scan.next());
        person.setPatronymic(scan.next());

        System.out.println(person.toString());
    }
}
