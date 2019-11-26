public class Loader
{
    public static void main(String[] args) {

        String[][] cross = { { "X", " ", " ", " ", "X" }, { " ", "X", " ", "X", " " },
                { " ", " ", "X", " ", " "}, { " ", "X", " ", "X", " " }, { "X", " ", " ", " ", "X" } };

        for(String[] x : cross ){
            System.out.println();
            for (String x1: x){
                System.out.print(x1);
            }
        }
        System.out.println();
    }
}
