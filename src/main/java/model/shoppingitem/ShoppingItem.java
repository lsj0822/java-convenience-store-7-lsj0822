package model.shoppingitem;

import model.Promotion;

public interface ShoppingItem {
    public abstract String getItemName();

    public abstract Integer getPrice();

    public abstract Promotion getPromotion();

    public abstract Integer getQuantity();

    public abstract boolean equals(ShoppingItem item);

    public abstract void addNormalQuantity(Integer normalQuantity);

    public abstract void addPromotionQuantity(Integer promotionQuantity);

    public abstract String printItemStatus();

    public abstract boolean checkPromotion();
}
