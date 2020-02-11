import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kata {
    public static void main(String[] args) {
        int[] k = digitize(12345678);
        for (int i : k) {
            System.out.println(i);
        }
    }

    //    348597 => [7,9,5,8,4,3]
    public static int[] digitize(long n) {
        String[] strArray = Long.toString(n).split("");
        List<String> stringList = Arrays.asList(strArray);
        int[] a = stringList.stream().mapToInt(Integer::parseInt)

                .toArray();
        return a;
    }
}
