package validator;

import java.time.LocalDate;
import java.util.List;

public class Validation {
    public static void ValidateContentLength(List<String> query, QueryType type) {
        if (query.size() == type.getQueryLength()) {
            throw new IllegalArgumentException("[ERROR] 입력된 리소스 파일의 형식이 맞지 않습니다.");
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
            throw new IllegalArgumentException("[ERROR] 입력된 리소스 파일의 형식이 맞지 않습니다.");
        }
    }

    public static void ValidateLocalDate(String content) {
        try {
            LocalDate.parse(content);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("[ERROR] 입력된 리소스 파일의 형식이 맞지 않습니다.");
        }
    }
}
