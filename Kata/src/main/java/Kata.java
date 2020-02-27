import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Kata {
    public static int[] digitize(long n) {
        String[] strArray = Long.toString(n).split("");
        List<String> stringList = Arrays.asList(strArray);
        Collections.reverse(stringList);
        return stringList.stream().mapToInt(Integer::parseInt)
                .toArray();
    }

    public static String[] dirReduc(String[] arr) {
        // Your code here.
        for(int t = 0; t < arr.length; t++){
            StringBuilder str = new StringBuilder().append(arr[t]);
            if(str.append(arr[++t]).equals("NORTHSOUTH") || str.append(arr[++t]).equals("SOUTHNORTH"))
        }
        Pattern p = Pattern.compile("(\\.\\s|\\,\\s)");
        return new String[] {};
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(digitize(321)));

    }
}

public class DirReductionTest {
    @Test
    public void testSimpleDirReduc() {
        assertArrayEquals("\"NORTH\", \"SOUTH\", \"SOUTH\", \"EAST\", \"WEST\", \"NORTH\", \"WEST\"",
                new String[]{"WEST"},
                DirReduction.dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"}));
        assertArrayEquals("\"NORTH\",\"SOUTH\",\"SOUTH\",\"EAST\",\"WEST\",\"NORTH\"",
                new String[]{},
                DirReduction.dirReduc(new String[]{"NORTH","SOUTH","SOUTH","EAST","WEST","NORTH"}));
    }
}