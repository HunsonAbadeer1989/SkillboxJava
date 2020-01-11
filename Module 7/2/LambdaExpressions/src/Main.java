import org.w3c.dom.ls.LSOutput;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.sql.Date.valueOf;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();

        // Homework 7.2
        staff.stream().
                sorted(Comparator.comparing(Employee::getName)).
                sorted(Comparator.comparing(Employee::getSalary)).
                forEach( System.out::println);

        System.out.println();

        //Homework 7.8
        LocalDate date2017 = LocalDate.of(2017, 1, 1);
        staff.stream().filter(e -> e.getWorkStart().
                after(valueOf(date2017))).
                map(Employee::getSalary).
                max(Integer::compareTo).
                ifPresent(System.out::println);
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}