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
        List<String> carsNumbers = getCarsNumbers();
        Set<String> hashSetNumbers = new HashSet<>(carsNumbers);

        long listSearchStart = System.nanoTime();
        System.out.println(carsNumbers.contains("Y999YY197"));
        long endOfContainSearch = System.nanoTime() - listSearchStart;
        System.out.println(String.format("Contain search 1:[%fs]", (float) endOfContainSearch/1000000000 ));

        long BinarySearchStart = System.nanoTime();
        System.out.println(Collections.binarySearch(carsNumbers, "Y999YY197"));
        long endOfBinarySearch = System.nanoTime() - BinarySearchStart;
        System.out.println(String.format("Binary search:[%fs]", (float) endOfBinarySearch/1000000000 ));

        long SetSearchStart = System.nanoTime();
        System.out.println(hashSetNumbers.contains("Y999YY197"));
        long endOfHashSearch = System.nanoTime() - SetSearchStart;
        System.out.println(String.format("HashSet search 1: [%fs]", (float) endOfHashSearch/1000000000 ));

        Set<String> treeSetNumbers = new TreeSet<>(carsNumbers);
        long TreeSearchStart = System.nanoTime();
        System.out.println(treeSetNumbers.contains("Y999YY197"));
        long endOfTreeSearch = System.nanoTime() - TreeSearchStart;
        System.out.println(String.format("TreeSet search: [%fs]", (float) endOfTreeSearch/1000000000 ));
    }
}