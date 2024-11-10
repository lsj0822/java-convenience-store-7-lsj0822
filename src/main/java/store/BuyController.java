package store;

import model.Cart;
import model.shoppingitem.PromotionItem;
import model.shoppingitem.ShoppingItem;
import view.ErrorMessages;
import view.InputView;

public class BuyController {
    static Integer totalQuantity;

    public static void execute(ShoppingItem item, Cart buy) {
        totalQuantity = buy.getBuyQuantity();
        if (item instanceof PromotionItem && item.checkPromotion()) {
            checkPromotion((PromotionItem) item);
            checkLackOfPromotionQuantity((PromotionItem) item);
            buyPromotionSetting((PromotionItem) item, buy);
            return;
        }
        buy.buySetting(0, totalQuantity);
    }

    private static void checkPromotion(PromotionItem item) {
        int promotionBuy = item.getPromotion().getBuy();
        if (totalQuantity % (promotionBuy + 1) == promotionBuy && totalQuantity < item.getPromotionQuantity()) {
            String query = InputView.inputAskPromotion(item.getItemName()).toUpperCase();
            renewBuyQuantity(query);
        }
    }

    private static void checkLackOfPromotionQuantity(PromotionItem item) {
        if (item.getPromotionQuantity() < totalQuantity) {
            Integer promotionUnit = toPromotionUnit(item);
            Integer normalQuantity = toCalculatedNormalQuantity(item, promotionUnit);
            String query = InputView.inputAskLackOfItem(item.getItemName(), normalQuantity);
            renewLackOfItem(query, normalQuantity);
        }
    }

    private static Integer toPromotionUnit(PromotionItem item) {
        return item.getPromotion().getBuy() + 1;
    }

    private static Integer toCalculatedNormalQuantity(PromotionItem item, Integer unit) {
        return totalQuantity - item.getPromotionQuantity() / unit * unit;
    }

    private static void renewBuyQuantity(String query) {
        if (query.startsWith("Y")) {
            totalQuantity++;
        }
        if (query.startsWith("N")) {
            return;
        }
        throw new IllegalArgumentException(ErrorMessages.ERROR_ETC.getMessage());
    }

    private static void renewLackOfItem(String query, Integer normalQuantity) {
        if (query.startsWith("Y")) {
            return;
        }
        if (query.startsWith("N")) {
            totalQuantity -= normalQuantity;
        }
        throw new IllegalArgumentException(ErrorMessages.ERROR_ETC.getMessage());
    }

    private static void buyPromotionSetting(PromotionItem item, Cart buy) {
        if (item.getPromotionQuantity() < totalQuantity) {
            Integer promotionUnit = toPromotionUnit(item);
            Integer normalQuantity = toCalculatedNormalQuantity(item, promotionUnit);
            buy.buySetting(totalQuantity - normalQuantity, normalQuantity);
        }
    }
}