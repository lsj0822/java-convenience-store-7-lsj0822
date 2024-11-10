package model;

import java.util.ArrayList;
import java.util.List;

public class BuyList {
    private ArrayList<Cart> carts = new ArrayList<>();
    private ArrayList<List<String>> buyItems;
    private boolean membership;

    public BuyList(ArrayList<List<String>> buyItemsReceipt) {
        this.buyItems = buyItems;
        cartFactory(buyItemsReceipt);
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    private void cartFactory(ArrayList<List<String>> buyItems) {
        for (List<String> itemQuery : buyItems) {
            carts.add(new Cart(itemQuery));
        }
    }

    public ArrayList<String> toPrintTotals() {
        ArrayList<String> returnContents = new ArrayList<>();
        for (Cart cart : carts) {
            returnContents.add(cart.toPrintTotal());
        }
        return returnContents;
    }

    public ArrayList<String> toPrintPromotions() {
        ArrayList<String> returnedContents = new ArrayList<>();
        for (Cart cart : carts) {
            if (cart.toPrintPromotion() == null) {
                continue;
            }
            returnedContents.add(cart.toPrintPromotion());
        }
        return returnedContents;
    }

    public Integer toTotalQuantity() {
        Integer totalQuantity = 0;
        for (Cart cart : carts) {
            totalQuantity += cart.toTotalQuantity();
        }
        return totalQuantity;
    }

    public Integer toTotalPrice() {
        Integer totalPrice = 0;
        for (Cart cart : carts) {
            totalPrice += cart.toTotalPrice();
        }
        return totalPrice;
    }

    public Integer toPromotedPrice() {
        Integer totalPromotedPrice = 0;
        for (Cart cart : carts) {
            totalPromotedPrice += cart.toTotalPromotedPrice();
        }
        return totalPromotedPrice;
    }

    public Integer toMemberShipPrice() {
        Integer totalNormalPrice = 0;
        if (!membership) {
            return 0;
        }
        for (Cart cart : carts) {
            totalNormalPrice += cart.toNormalPrice();
        }
        return totalNormalPrice * 3 / 10;
    }

    public void substractQuantity() {
        for (Cart cart : carts) {
            cart.substractFromShoppingItem();
        }
    }
}
