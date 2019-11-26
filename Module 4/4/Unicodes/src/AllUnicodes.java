public class AllUnicodes
{
    public static void main(String[] args) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] alphabetUpperCase = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

        for(int i =0; i < alphabetUpperCase.length; i++ )
        {
            int k = alphabetUpperCase[i];
            System.out.println("Unicode for symbol: " + alphabetUpperCase[i] + " = " + k);
        }

        for(int i =0; i < alphabet.length; i++ )
        {
            int k = alphabet[i];
            System.out.println("Unicode for symbol: " + alphabet[i] + " = " +k);
        }
    }
}
