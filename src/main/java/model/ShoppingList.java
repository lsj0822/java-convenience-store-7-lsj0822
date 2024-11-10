package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.shoppingitem.NormalItem;
import model.shoppingitem.PromotionItem;
import model.shoppingitem.ShoppingItem;
import utils.Parser;
import validator.QueryType;
import validator.Validation;

public class ShoppingList {
    private static ArrayList<ShoppingItem> shoppingItems = new ArrayList<>();

    public ShoppingList(List<String> queries) {
        if (shoppingItems.size() == 0) {
            initiateShoppingList(queries);
        }
    }

    private void initiateShoppingList(List<String> queries) {
        for (String query : queries) {
            List<String> itemAttributes = Parser.parse(query);
            Validation.ValidateContentLength(itemAttributes, QueryType.PRODUCT);
            Validation.ValidateContentsType(itemAttributes, QueryType.PRODUCT);
            ShoppingItem item = ItemFactory.generate(itemAttributes);
            addShoppingList(item);
        }
    }

    public void addShoppingList(ShoppingItem item) {
        if (!isInShoppingList(item)) {
            shoppingItems.add(item);
        }
    }

    public boolean isInShoppingList(ShoppingItem addItem) {
        boolean addResult = false;
        Iterator<ShoppingItem> iterator = shoppingItems.iterator();
        while (iterator.hasNext() && !addResult) {
            ShoppingItem currentItem = iterator.next();
            addResult = checkSingleShoppingItem(currentItem, addItem);
        }
        return addResult;
    }

    public static ShoppingItem findShoppingItem(String itemName) {
        return shoppingItems.stream()
                .filter(item -> isEqualName(item, itemName))
                .findFirst()
                .orElse(null);
    }

    public boolean checkSingleShoppingItem(ShoppingItem shoppingItem, ShoppingItem addItem) {
        boolean equalName = isEqualName(shoppingItem, addItem.getItemName());
        boolean equalItem = shoppingItem.equals(addItem);
        if (equalName && equalItem) {
            addItemToExisted(shoppingItem, addItem);
            return true;
        }
        if (equalName && !equalItem) {
            throw new IllegalArgumentException("[Error] 이미 존재하는 물품입니다.");
        }
        return false;
    }

    public static boolean isEqualName(ShoppingItem shoppingItem, String compareName) {
        return shoppingItem.getItemName().equals(compareName);
    }

    public void addItemToExisted(ShoppingItem shoppingItem, ShoppingItem addItem) {
        Integer quantity = addItem.getQuantity();
        if (addItem instanceof NormalItem) {
            shoppingItem.modifyNormalQuantity(quantity);
        }
        if (addItem instanceof PromotionItem) {
            shoppingItem.modifyPromotionQuantity(quantity);
        }
    }

    public static ArrayList<String> printShoppingItems() {
        ArrayList<String> printedItems = new ArrayList<>();
        for (ShoppingItem shoppingItem : shoppingItems) {
            printedItems.add(shoppingItem.printItemStatus());
        }
        return printedItems;
    }


}
