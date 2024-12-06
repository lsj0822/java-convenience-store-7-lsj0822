package view;

import java.util.ArrayList;
import model.BuyList;

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

    public static void printReceipt(BuyList buyList) {
        printBuyTotalItems(buyList);
        printPromotion(buyList);
        printPriceInformation(buyList);
    }

    public static void printBuyTotalItems(BuyList buyList) {
        printReceiptHeader();
        ArrayList<String> prints = buyList.toPrintTotals();
        for (String print : prints) {
            System.out.println(print);
        }
    }

    private static void printReceiptHeader() {
        printBlank();
        System.out.println(ReceiptMessages.STORE_HEADER.getMessage());
        System.out.println(ReceiptMessages.COLUMN_HEADER.getMessage());
    }

    public static void printPromotion(BuyList buyList) {
        System.out.println(ReceiptMessages.PROMOTION_HEADER.getMessage());
        printBuyTotalPromotions(buyList);
    }

    public static void printBuyTotalPromotions(BuyList buyList) {
        ArrayList<String> prints = buyList.toPrintPromotions();
        for (String print : prints) {
            System.out.println(print);
        }
    }

    public static void printPriceInformation(BuyList buyList) {
        System.out.println(ReceiptMessages.LINE.getMessage());
        Integer totalQuantity = buyList.toTotalQuantity();
        Integer initialPrice = buyList.toTotalPrice();
        Integer promotedPrice = buyList.toPromotedPrice();
        Integer membershipDiscountPrice = buyList.toMemberShipPrice();
        System.out.printf(ReceiptMessages.TOTAL_QUANTITY_AND_PRICE_FORMAT.getMessage(), totalQuantity, initialPrice);
        System.out.printf(ReceiptMessages.PROMOTION_PRICE_FORMAT.getMessage(),
                "-" + String.format("%,d", promotedPrice));
        System.out.printf(ReceiptMessages.MEMBERSHIP_PRICE_FORMAT.getMessage(),
                "-" + String.format("%,d", membershipDiscountPrice));
        System.out.printf(ReceiptMessages.RESULT_FORMAT.getMessage(),
                initialPrice - promotedPrice - membershipDiscountPrice);
    }
}
