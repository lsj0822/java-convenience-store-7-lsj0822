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

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public Promotion getPromotion() {
        return null;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(ShoppingItem item) {
        if (item instanceof NormalItem) {
            return this.itemName.equals(item.getItemName()) &&
                    this.price.equals(item.getPrice());
        }
        return false;
    }

    @Override
    public void addNormalQuantity(Integer quantity) {
        this.quantity += quantity;
    }

    @Override
    public void addPromotionQuantity(Integer quantity) {
        return;
    }

    @Override
    public String printItemStatus() {
        if (quantity != 0) {
            return String.format("- %s %,d원 %,d개", itemName, price, quantity);
        }
        return String.format("- %s %,d원 재고 없음", itemName, price);
    }
}
