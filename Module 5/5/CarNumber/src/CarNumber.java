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
        long start = System.currentTimeMillis();
        List<String> carsNumbers = getCarsNumbers();
        for(String num: carsNumbers){
            System.out.println(num);
        }

        /** Contain Search - is about 0.013767ms key was found */
        long endOfContainSearch = System.currentTimeMillis() - start;
        System.out.println(carsNumbers.contains("A999AA190"));
        System.out.println(String.format("Contain search:[%fms]", (float) endOfContainSearch/1000000 ));

        /** Binary Search - is about 0.013732ms found in 287092 position */
        long endOfBinarySearch = System.currentTimeMillis() - start;
        System.out.println(Collections.binarySearch(carsNumbers, "A999AA190"));
        System.out.println(String.format("Binary search:[%fms]", (float) endOfBinarySearch/1000000 ));

        /** HashSet Search - is about 0.015122ms key was found */
        Set<String> hashSetNumbers = new HashSet<>(carsNumbers);
        long endOfHashSearch = System.currentTimeMillis() - start;
        System.out.println(hashSetNumbers.contains("A999AA190"));
        System.out.println(String.format("HashSet search: [%fms]", (float) endOfHashSearch/1000000 ));

        /** TreeSet Search - is about 0.014827ms key was found */
        Set<String> treeSetNumbers = new TreeSet<>(carsNumbers);
        long endOfTreeSearch = System.currentTimeMillis() - start;
        System.out.println(treeSetNumbers.contains("A999AA190"));
        System.out.println(String.format("TreeSet search: [%fms]", (float) endOfTreeSearch/1000000 ));
    }
}
