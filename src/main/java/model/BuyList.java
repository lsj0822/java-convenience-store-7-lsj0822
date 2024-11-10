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

    public Integer getCartSize() {
        return carts.size();
    }

}
