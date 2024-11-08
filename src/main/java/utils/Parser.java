package utils;

import java.util.List;

public class Parser {
    public final static String DELIMITER = ",";

    public static List<String> parse(String inputContent) {
        List<String> parsedContent = splitting(inputContent);
        return trim(parsedContent);
    }

    private static List<String> splitting(String inputContent) {
        return List.of(inputContent.split(DELIMITER));
    }

    private static List<String> trim(List<String> splittedContent) {
        return splittedContent.stream().map(String::strip).toList();
    }
}
