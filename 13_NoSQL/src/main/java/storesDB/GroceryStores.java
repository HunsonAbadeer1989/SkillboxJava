package storesDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GroceryStores {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StorageMongoDB storageMongoDB = new StorageMongoDB();
        storageMongoDB.createGroceryAndStores();

        while (true) {
            System.out.print("Input your command: ");
            try {
                String command = br.readLine();
                if (command.equalsIgnoreCase("STAT")) {
                    try {
                        storageMongoDB.getProductsStatistic();
                    } catch (NullPointerException ex) {
                        System.out.println("STORAGE IS EMPTY!");
                    }
                } else {
                    String[] commands = command.split(" ");
                    if (commands.length == 2 && commands[0].equalsIgnoreCase("ADD_STORE")) {
                        storageMongoDB.addStore(commands[1]);
                    } else if (commands.length == 3 && commands[0].equalsIgnoreCase("ADD_PRODUCT")) {
                        try {
                            storageMongoDB.addProduct(commands[1], Integer.parseInt(commands[2]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (commands.length == 3 && commands[0].equalsIgnoreCase("PUT_PRODUCT")) {
                        storageMongoDB.putProductInStore(commands[1], commands[2]);
                    } else {
                        System.out.println("Wrong input!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
