package store;

import java.util.List;
import view.InputView;
import view.Messages;
import view.OutputView;

public class StoreController {
    private final String productsFileAddress = "src/main/resources/products.md";
    private final String promotionsFileAddress = "src/main/resources/promotions.md";

    public void start() {
        CVSInformationController();
    }

    public void CVSInformationController() {
        OutputView.printMessage(Messages.OUTPUT_WELCOME_MESSAGE);
        List<String> products = InputView.readFile(productsFileAddress);
        List<String> promotions = InputView.readFile(promotionsFileAddress);
    }
}
