package model;

import java.util.List;

public class ItemFactory {
    public static ShoppingItem generate(List<String> inputInformation) {
        if (inputInformation.get(3).equals("null")) {
            return normalItemFactory(inputInformation);
        }
        return promotionItemFactory(inputInformation);
    }

    public static PromotionItem promotionItemFactory(List<String> information) {
        return new PromotionItem(information);
    }

    public static NormalItem normalItemFactory(List<String> information) {
        return new NormalItem(information);
    }
}
