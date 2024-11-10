package model;

import java.util.List;
import model.shoppingitem.NormalItem;
import model.shoppingitem.PromotionItem;
import model.shoppingitem.ShoppingItem;
import store.BuyController;
import utils.KoreanFormatter;
import validator.Validation;

public class Cart {
    private String itemName;
    private Integer buyQuantity;
    private Integer buyPromotion;
    private Integer buyNormal;
    private ShoppingItem matchedShoppingItem;

    public Cart(List<String> cartAttributes) {
        this.itemName = cartAttributes.get(0);
        this.buyQuantity = Integer.parseInt(cartAttributes.get(1));
        Validation.ValidateInShoppingList(itemName);
        matchedShoppingItem = ShoppingList.findShoppingItem(itemName);
        Validation.ValidateBuyQuantity(matchedShoppingItem, buyQuantity);
        BuyController.execute(matchedShoppingItem, this);
    }

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void buySetting(Integer promotion, Integer normal) {
        this.buyPromotion = promotion;
        this.buyNormal = normal;
    }

    public String toPrintTotal() {
        return printItemNames() + String.format("%d\t%,d", toTotalQuantity(), toTotalPrice());
    }

    public String toPrintPromotion() {
        if (matchedShoppingItem instanceof NormalItem) {
            return null;
        }
        int promotionAppliedQuantity = buyPromotion / (matchedShoppingItem.getPromotion().getBuy() + 1);
        if (promotionAppliedQuantity == 0) {
            return null;
        }
        return printItemNames() + String.format("%d", promotionAppliedQuantity);
    }

    public String printItemNames() {
        int spaces = KoreanFormatter.countSpace(itemName);
        if (spaces <= 9) {
            return itemName + "\t\t";
        }
        if (spaces <= 17) {
            return itemName + "\t";
        }
        return KoreanFormatter.cutTooMuchLetters(itemName) + "\t";
    }

    public Integer toTotalQuantity() {
        if (buyNormal == null && buyPromotion == null) {
            return 0;
        }
        if (buyNormal == null) {
            return buyPromotion;
        }
        if (buyPromotion == null) {
            return buyNormal;
        }
        return buyNormal + buyPromotion;
    }

    public Integer toTotalPrice() {
        return toTotalQuantity() * matchedShoppingItem.getPrice();
    }

    public Integer toTotalPromotedPrice() {
        if (matchedShoppingItem instanceof NormalItem) {
            return 0;
        }
        int promotionAppliedQuantity = buyPromotion / (matchedShoppingItem.getPromotion().getBuy() + 1);
        return promotionAppliedQuantity * matchedShoppingItem.getPrice();
    }

    public Integer toNormalPrice() {
        return buyNormal * matchedShoppingItem.getPrice();
    }

    public void substractFromShoppingItem() {
        matchedShoppingItem.modifyNormalQuantity(-buyNormal);
        if (matchedShoppingItem instanceof PromotionItem) {
            matchedShoppingItem.modifyPromotionQuantity(-buyPromotion);
        }
    }

}
