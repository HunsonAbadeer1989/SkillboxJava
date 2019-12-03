import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Input number of side.");
        int size = scan.nextInt();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ( i == j ) {
                    System.out.print("X");
                } else if ( ( i + j ) == ( size - 1 ) ) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println(" ");
        }
    }
}
