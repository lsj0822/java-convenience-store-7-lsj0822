package view;

import java.util.ArrayList;

public class OutputView {

    public static void printMessage(Messages message) {
        printBlank();
        System.out.println(message.getMessage());
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printLackOfItemMessage(String itemName, int quantity) {
        printBlank();
        System.out.printf(Messages.INPUT_ASK_LACK_OF_ITEM.getMessage(), itemName, quantity);
    }

    public static void printPromotionMessage(String itemName) {
        printBlank();
        System.out.printf(Messages.INPUT_ASK_PROMOTION.getMessage(), itemName);
    }

    public static void printBlank() {
        System.out.println();
    }

    public static void printGroups(ArrayList<String> groups) {
        printBlank();
        for (String group : groups) {
            System.out.println(group);
        }
    }

}
