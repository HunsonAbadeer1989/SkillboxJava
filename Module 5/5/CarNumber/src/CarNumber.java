import java.util.*;

public class CarNumber {

    public static List<String> getCarsNumbers(){
        char[] letters = {'A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y'};
        int digits = 10;
        int reg = 197;

        List<String> setGenerator = new ArrayList<>();

        for(char let: letters){
            for(int i = 0; i < digits; i++){
                String number = String.format("%1$c%2$d%2$d%2$d%1$c%1$c", let, i );
                setGenerator.add(number);
            }
        }
        for(char let1: letters)
            for(char let2: letters)
                for(char let3: letters)
                    for(int i = 0; i < digits; i++){
                        String number2 = String.format("%2$c%1d00%3$c%4$c", i, let1, let2, let3);
                        setGenerator.add(number2);
                    }
        List<String> carNumbers = new ArrayList<>();
        for(String set: setGenerator){
            for(int i = 0; i <= reg; i++){
                String number = String.format("%s%03d", set, i );
                carNumbers.add(number);
            }
        }
        Collections.sort(carNumbers);
        return carNumbers;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        List<String> carsNumbers = getCarsNumbers();
//        for(String num: carsNumbers){
//            System.out.println(num);
//        }

        /** Contain Search - is about 3.988571s key was found */
        long endOfContainSearch = System.nanoTime() - start;
        System.out.println(carsNumbers.contains("Y999YY197"));
        System.out.println(String.format("Contain search:[%fs]", (float) endOfContainSearch/1000000000 ));

        /** Binary Search - is about 4.023740s found in 287092 position
         * Useful when array isn't changing often. Because update it really slow. */
        long endOfBinarySearch = System.nanoTime() - start;
        System.out.println(Collections.binarySearch(carsNumbers, "Y999YY197"));
        System.out.println(String.format("Binary search:[%fs]", (float) endOfBinarySearch/1000000000 ));

        /** HashSet Search - is about 4.215626s key was found
         * Constant time of search, insert and delete of element.
         * Useful in case when order of elements is not necessary. */
        Set<String> hashSetNumbers = new HashSet<>(carsNumbers);
        long endOfHashSearch = System.nanoTime() - start;
        System.out.println(hashSetNumbers.contains("Y999YY197"));
        System.out.println(String.format("HashSet search: [%fs]", (float) endOfHashSearch/1000000000 ));

        /** TreeSet Search - is about 5.221025s key was found
         * The time of search, insert and delete isn't good as HashSet. But fine O(log n)
         *  Useful when we need order of elements. */
        Set<String> treeSetNumbers = new TreeSet<>(carsNumbers);
        long endOfTreeSearch = System.nanoTime() - start;
        System.out.println(treeSetNumbers.contains("Y999YY197"));
        System.out.println(String.format("TreeSet search: [%fs]", (float) endOfTreeSearch/1000000000 ));
    }
}