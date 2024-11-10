package view;

public enum Messages {
    OUTPUT_WELCOME_MESSAGE("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다."),
    INPUT_ITEMS_QUERY("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    INPUT_ASK_PROMOTION("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n"),
    INPUT_ASK_LACK_OF_ITEM("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n"),
    INPUT_ASK_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
    INPUT_ASK_BYE_MORE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),
    RECEIPT_STORE_HEADER("==============W 편의점================"),
    RECEIPT_COLUMN_HEADER("상품명\t\t수량\t금액"),
    RECEIPT_PROMOTION_HEADER("=============증\t정==============="),
    RECEIPT_LINE("====================================");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
