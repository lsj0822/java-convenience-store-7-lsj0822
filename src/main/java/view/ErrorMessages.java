package view;

public enum ErrorMessages {
    ERROR_INVALID_FORMAT("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    ERROR_INVALID_ITEM("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."),
    ERROR_QUANTITY_EXCEED_LIMITATION("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    ERROR_INVALID_RESOURCE_FORMAT("[ERROR] 입력된 리소스 파일의 형식이 맞지 않습니다."),
    ERROR_ETC("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
