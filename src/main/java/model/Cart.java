package model;

import java.util.List;
import model.shoppingitem.ShoppingItem;
import store.BuyController;
import validator.Validation;

public class Cart {
    private String itemName;
    private Integer buyQuantity;
    private Integer buyPromotion;
    private Integer buyNormal;

    public Cart(List<String> cartAttributes) {
        this.itemName = cartAttributes.get(0);
        this.buyQuantity = Integer.parseInt(cartAttributes.get(1));
        Validation.ValidateInShoppingList(itemName);
        ShoppingItem findedShoppingItem = ShoppingList.findShoppingItem(itemName);
        Validation.ValidateBuyQuantity(findedShoppingItem, buyQuantity);
        BuyController.execute(findedShoppingItem, this);
    }

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void buySetting(Integer promotion, Integer normal) {
        this.buyPromotion = promotion;
        this.buyNormal = normal;
    }
}
