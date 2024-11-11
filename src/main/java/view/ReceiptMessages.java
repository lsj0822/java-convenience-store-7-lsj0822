package view;

public enum ReceiptMessages {
    STORE_HEADER("==============W 편의점================"),
    COLUMN_HEADER("상품명\t\t수량\t금액"),
    PROMOTION_HEADER("=============증\t정==============="),
    LINE("===================================="),
    TOTAL_QUANTITY_AND_PRICE_FORMAT("총구매액\t\t%,d\t%,8d\n"),
    PROMOTION_PRICE_FORMAT("\"행사할인\\t\\t\\t%8s\\n\""),
    MEMBERSHIP_PRICE_FORMAT("\"멤버십할인\\t\\t\\t%8s\\n\""),
    RESULT_FORMAT("내실돈\t\t\t%,8d\n");

    private String message;

    ReceiptMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
