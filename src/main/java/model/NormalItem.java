package model;

import java.util.List;

public class NormalItem implements ShoppingItem {
    private String itemName;
    private Integer price;
    private Integer quantity;

    public NormalItem(List<String> information) {
        itemName = information.get(0);
        price = Integer.parseInt(information.get(1));
        quantity = Integer.parseInt(information.get(2));
    }

    public String getItemName() {
        return itemName;
    }

    public String toString() {
        return itemName + "|" + price;
    }

    public boolean equals(String itemName, Integer price) {
        return this.itemName.equals(itemName) && this.price.equals(price);
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
