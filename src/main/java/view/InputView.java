package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputItemsQuery(){
        OutputView.printMessage(Messages.INPUT_ITEMS_QUERY);
        return Console.readLine();
    }

    public String inputAskPromotion(String itemName){
        OutputView.printPromotionMessage(itemName);
        return Console.readLine();
    }

    public String inputAskLackOfItem(String itemName, int quantity){
        OutputView.printLackOfItemMessage(itemName, quantity);
        return Console.readLine();
    }

    public String inputAskMembership(){
        OutputView.printMessage(Messages.INPUT_ASK_MEMBERSHIP);
        return Console.readLine();
    }

    public String inputAskBuyMore(){
        OutputView.printMessage(Messages.INPUT_ASK_BYE_MORE);
        return Console.readLine();
    }
}
