package view;

import camp.nextstep.edu.missionutils.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    public static List<String> readFile(String fileAddress) {
        try {
            FileReader reader = new FileReader(fileAddress);
            BufferedReader bufferedReader = new BufferedReader(reader);
            return readFileToGroup(bufferedReader);
        } catch (IOException exception) {
            OutputView.printErrorMessage(exception);
        }
        return null;
    }

    private static List<String> readFileToGroup(BufferedReader reader) throws IOException {
        List<String> returnedList = new ArrayList<>();
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            returnedList.add(line);
        }
        return returnedList;
    }

    public String inputItemsQuery() {
        OutputView.printMessage(Messages.INPUT_ITEMS_QUERY);
        return Console.readLine();
    }

    public String inputAskPromotion(String itemName) {
        OutputView.printPromotionMessage(itemName);
        return Console.readLine();
    }

    public String inputAskLackOfItem(String itemName, int quantity) {
        OutputView.printLackOfItemMessage(itemName, quantity);
        return Console.readLine();
    }

    public String inputAskCommon(Messages message) {
        OutputView.printMessage(message);
        return Console.readLine();
    }
}
