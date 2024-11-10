package validator;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import model.ShoppingList;
import model.shoppingitem.ShoppingItem;
import view.ErrorMessages;

public class Validation {
    static final String ITEM_QUERY_REGEX = "\\[.+-\\d+\\]";

    public static void ValidateContentLength(List<String> query, QueryType type) {
        if (query.size() != type.getQueryLength()) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_INVALID_RESOURCE_FORMAT.getMessage());
        }
    }

    public static void ValidateContentsType(List<String> queries, QueryType type) {
        List<Type> inputQueryTypes = type.getQueryTypes();
        for (int i = 0; i < queries.size(); i++) {
            ValidateSingleContentType(queries.get(i), inputQueryTypes.get(i));
        }
    }

    public static void ValidateSingleContentType(String query, Type type) {
        if (type.equals(Type.STRING) || type.equals(Type.STRING_OR_NULL)) {
            return;
        }
        if (type.equals(Type.INTEGER)) {
            ValidateNumber(query);
        }
        if (type.equals(Type.LOCAL_DATE)) {
            ValidateLocalDate(query);
        }
    }

    public static void ValidateNumber(String content) {
        try {
            Integer.parseInt(content);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_INVALID_RESOURCE_FORMAT.getMessage());
        }
    }

    public static void ValidateLocalDate(String content) {
        try {
            LocalDate.parse(content);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_INVALID_RESOURCE_FORMAT.getMessage());
        }
    }

    public static void ValidateItemsQuery(List<String> queries) {
        for (String query : queries) {
            ValidateSingleQuery(query);
        }
    }

    private static void ValidateSingleQuery(String query) {
        if (!Pattern.matches(ITEM_QUERY_REGEX, query)) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_INVALID_FORMAT.getMessage());
        }
    }

    public static void ValidateInShoppingList(String itemName) {
        if (ShoppingList.findShoppingItem(itemName) == null) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_INVALID_ITEM.getMessage());
        }
    }

    public static void ValidateBuyQuantity(ShoppingItem item, Integer buyQuantity) {
        if (item.getQuantity() < buyQuantity) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_QUANTITY_EXCEED_LIMITATION.getMessage());
        }
    }
}
