package model;

import java.util.List;

public class PromotionItem implements ShoppingItem {
    private String itemName;
    private Integer price;
    private Integer promotionQuantity;
    private Integer normalQuantity;
    private Promotion promotion;

    public PromotionItem(List<String> information) {
        itemName = information.get(0);
        price = Integer.parseInt(information.get(1));
        promotionQuantity = Integer.parseInt(information.get(2));
        promotion = new Promotion(information.get(3));
    }

    public boolean equals(String itemName, Integer price, Promotion promotion) {
        return this.itemName.equals(itemName)
                && this.price.equals(price)
                && this.promotion.equals(promotion);
    }

    public void addQuantity(int promotionQuantity, int normalQuantity) {
        this.promotionQuantity += promotionQuantity;
        this.normalQuantity += normalQuantity;
    }
}
