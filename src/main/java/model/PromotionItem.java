package model;

import java.util.List;

public class PromotionItem implements ShoppingItem {
    private String itemName;
    private Integer price;
    private Integer promotionQuantity;
    private Integer normalQuantity = 0;
    private Promotion promotion;

    public PromotionItem(List<String> information) {
        itemName = information.get(0);
        price = Integer.parseInt(information.get(1));
        promotionQuantity = Integer.parseInt(information.get(2));
        promotion = PromotionList.returnPromotion(information.get(3));
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
        return promotion;
    }

    @Override
    public Integer getQuantity() {
        return promotionQuantity;
    }

    @Override
    public boolean equals(ShoppingItem item) {
        if (item instanceof NormalItem) {
            return this.itemName.equals(item.getItemName()) &&
                    this.price.equals(item.getPrice());
        }
        // Item이 NormalItem이 아니면 무조건 PromotionItem이다.
        return this.itemName.equals(item.getItemName()) &&
                this.price.equals(item.getPrice()) &&
                this.promotion.equals(item.getPromotion());
    }

    @Override
    public void addNormalQuantity(Integer normalQuantity) {
        this.normalQuantity = this.normalQuantity + normalQuantity;
    }

    @Override
    public void addPromotionQuantity(Integer promotionQuantity) {
        this.promotionQuantity += promotionQuantity;
    }

    @Override
    public String printItemStatus() {
        return printPromotionStatus() + "\n" + printNormalStatus();
    }

    private String printPromotionStatus() {
        if (promotionQuantity != 0) {
            return String.format("- %s %,d원 %,d개 %s", itemName, price, promotionQuantity, promotion.getName());
        }
        return String.format("- %s %,d원 재고 없음 %s", itemName, price, promotion.getName());
    }

    private String printNormalStatus() {
        if (normalQuantity != 0) {
            return String.format("- %s %,d원 %,d개", itemName, price, normalQuantity);
        }
        return String.format("- %s %,d원 재고 없음", itemName, price);
    }
}
