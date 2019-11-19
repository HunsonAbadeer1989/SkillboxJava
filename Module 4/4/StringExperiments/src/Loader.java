import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Loader
{
        public static void main(String[] args) throws IOException
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей.";

        String[] numbers = text.replaceAll("[а-я-\\D]+|[А-Я-\\D]+", " ").trim().split(" ");

        // This is the answer on Homework 4.1.
        int[] salaries = new int[numbers.length];

        int sum = 0;

        for (int i = 0; i < salaries.length; ) {
            for (String a : numbers) {
                salaries[i] = Integer.parseInt(a);
                i++;
            }
        }

        for (int k : salaries){
            sum += k;
        }

        System.out.println("Total salary: " + sum);

//          This is the answer on homework 4.5.1
        int vasyaSalary = Integer.parseInt(numbers[0]);
        int petyaSalary = Integer.parseInt(numbers[1]);
        int mashaSalary = Integer.parseInt(numbers[2]);

        System.out.println("Vasya salary: " + vasyaSalary);
        System.out.println("Petya salary: " + petyaSalary);
        System.out.println("Masha salary: " + mashaSalary);

        Scanner scan = new Scanner(System.in);

        System.out.println("Input Surname Name Patronymic: ");

        String[] str = scan.nextLine().split(" ", 3);

        System.out.println("Surname: " + str[0]);
        System.out.println("Name: " + str[1]);
        System.out.println("Patronymic: " + str[2]);

    }
}