public class Loader {
    public static void main(String[] args) {

        String[] rainbowColors = {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};

        for (int i = 0; i < rainbowColors.length / 2; i++) {
            String temp = rainbowColors[i];
            rainbowColors[i] = rainbowColors[rainbowColors.length - i - 1];
            rainbowColors[rainbowColors.length - i - 1] = temp;
        }
        for (String a : rainbowColors) {
            System.out.println(a);
        }

    }
}

