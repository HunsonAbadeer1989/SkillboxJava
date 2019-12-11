import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
        public static void main(String[] args) throws IOException
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей.";

        // The feature
        Pattern patt = Pattern.compile("(\\d+)");
        Matcher m = patt.matcher(text);
        int result = 0;

        while ( m.find() ){
            result += Integer.parseInt(m.group(1));
        }
        System.out.println("Sum with pattern anm matcher: " + result);

        // This is the answer on Homework 4.1.
        String[] numbers = text.replaceAll("[а-я-\\D]+|[А-Я-\\D]+", " ").trim().split(" ");

        int[] salaries = new int[numbers.length];

        int sum = 0;

        for (int i = 0; i < salaries.length; i++) {
            salaries[i] = Integer.parseInt(numbers[i]);
        }

        for (int k : salaries){
            sum += k;
        }

        System.out.println("Total salary: " + sum);


        // This is the answer on homework 4.5.1
        int vasyaSalary = Integer.parseInt(numbers[0]);
        int petyaSalary = Integer.parseInt(numbers[1]);
        int mashaSalary = Integer.parseInt(numbers[2]);

        System.out.println("Vasya salary: " + vasyaSalary);
        System.out.println("Petya salary: " + petyaSalary);
        System.out.println("Masha salary: " + mashaSalary);


    }
}