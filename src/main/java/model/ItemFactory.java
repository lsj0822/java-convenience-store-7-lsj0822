package model;

import java.util.List;

public class ItemFactory {
    public void itemSelector(List<String> inputInformation){
        if(inputInformation.get(3) != null){
            promotionItemFactory(inputInformation);
            return;
        }
        normalItemFactory(inputInformation);
    }

    public PromotionItem promotionItemFactory(List<String> information){
        return new PromotionItem(information);
    }

    public NormalItem normalItemFactory(List<String> information){
        return new NormalItem(information);
    }
}
