package model;

import java.util.ArrayList;
import java.util.List;
import utils.Parser;
import validator.QueryType;
import validator.Validation;

public class PromotionList {
    private static ArrayList<Promotion> promotions = new ArrayList<>();

    public PromotionList(List<String> queries) {
        initialFactory(queries);
    }

    public void initialFactory(List<String> queries) {
        for (String query : queries) {
            List<String> promotionAttributes = Parser.parse(query);
            Validation.ValidateContentLength(promotionAttributes, QueryType.PROMOTION);
            Validation.ValidateContentsType(promotionAttributes, QueryType.PROMOTION);
            addPromotion(promotionAttributes);
        }
    }

    private void addPromotion(List<String> promotionAttributes) {
        Promotion newPromotion = new Promotion(promotionAttributes);
        promotions.add(newPromotion);
    }

    public static Promotion returnPromotion(String promotionName) {
        return promotions.stream()
                .filter(promotion -> matchPromotions(promotion, promotionName))
                .findFirst()
                .orElse(null);
    }

    private static boolean matchPromotions(Promotion promotion, String promotionName) {
        return promotion.getName().equals(promotionName);
    }
}
