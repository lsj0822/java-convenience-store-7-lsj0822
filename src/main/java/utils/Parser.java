package utils;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public final static String CONTENT_DELIMITER = ",";
    public final static String QUERY_DELIMITER = "-";
    public final static String BRACKETS_LEFT = "[";
    public final static String BRACKETS_RIGHT = "]";

    public static List<String> parse(String inputContent) {
        List<String> parsedContent = splitting(inputContent, CONTENT_DELIMITER);
        return trim(parsedContent);
    }

    public static ArrayList<List<String>> parseQuery(List<String> inputQueries) {
        ArrayList<List<String>> returnContent = new ArrayList<>();
        for (String inputQuery : inputQueries) {
            returnContent.add(parseSingleQuery(inputQuery));
        }
        return returnContent;
    }

    public static List<String> parseSingleQuery(String query) {
        int left = query.indexOf(BRACKETS_LEFT), right = query.indexOf(BRACKETS_RIGHT);
        String extractedQuery = query.substring(left + 1, right);
        List<String> parsedQuery = splitting(extractedQuery, QUERY_DELIMITER);
        return trim(parsedQuery);
    }

    private static List<String> splitting(String inputContent, String delimiter) {
        return List.of(inputContent.split(delimiter));
    }

    private static List<String> trim(List<String> splittedContent) {
        return splittedContent.stream().map(String::strip).toList();
    }
}
