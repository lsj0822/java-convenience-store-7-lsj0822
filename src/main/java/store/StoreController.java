package store;

import java.util.ArrayList;
import java.util.List;
import model.BuyList;
import model.PromotionList;
import model.ShoppingList;
import utils.Parser;
import validator.Validation;
import view.InputView;
import view.Messages;
import view.OutputView;

public class StoreController {
    private final String productsFileAddress = "src/main/resources/products.md";
    private final String promotionsFileAddress = "src/main/resources/promotions.md";

    public void start() {
        MarketInformationController();
        programController();
    }

    public void MarketInformationController() {
        List<String> products = InputView.readFile(productsFileAddress);
        List<String> promotions = InputView.readFile(promotionsFileAddress);
        PromotionList promotionList = new PromotionList(promotions);
        ShoppingList shoppingList = new ShoppingList(products);
    }

    public void programController() {
        OutputView.printMessage(Messages.OUTPUT_WELCOME_MESSAGE);
        OutputView.printGroups(ShoppingList.printShoppingItems());
        InputItemController();
    }

    public void InputItemController() {
        try {
            String inputItemsQuery = InputView.inputItemsQuery();
            List<String> parsedQuery = Parser.parse(inputItemsQuery);
            Validation.ValidateItemsQuery(parsedQuery);
            ArrayList<List<String>> itemQuery = Parser.parseQuery(parsedQuery);
            ShoppingController(itemQuery);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            InputItemController();
        }
    }

    public void ShoppingController(ArrayList<List<String>> inputQuery) {
        try {
            BuyList buyList = new BuyList(inputQuery);
            String query = InputView.inputAskCommon(Messages.INPUT_ASK_MEMBERSHIP).toUpperCase();
            buyList.setMembership(membershipSetting(query));
            printReceipt(buyList);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            InputItemController();
        }
    }

    public boolean membershipSetting(String query) {
        return Validation.ValidateUserSelection(query);
    }

    public void printReceipt(BuyList buyList) {
        OutputView.printReceipt(buyList);
        renewShoppinigList(buyList);
    }

    public void renewShoppinigList(BuyList buyList) {
        buyList.substractQuantity();
        replayProgram();
    }

    public void replayProgram() {
        try {
            String query = InputView.inputAskCommon(Messages.INPUT_ASK_BYE_MORE).toUpperCase();
            boolean result = Validation.ValidateUserSelection(query);
            if (result) {
                programController();
            }
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            replayProgram();
        }

    }
}
