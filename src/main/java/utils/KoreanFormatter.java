package utils;

public class KoreanFormatter {
    public static int countSpace(String item) {
        int spaces = 0;
        for (int i = 0; i < item.length(); i++) {
            spaces += checkSingleLetter(item.charAt(i));
        }
        return spaces;
    }

    private static int checkSingleLetter(char letter) {
        if (letter >= '가' && letter <= '힣') {
            return 2;
        }
        return 1;
    }

    public static String cutTooMuchLetters(String item) {
        String returnValue = "";
        int currentSpace = 0;
        for (int i = 0; i < item.length(); i++) {
            returnValue += item.charAt(i);
            currentSpace += checkSingleLetter(item.charAt(i));
            if (currentSpace > 19) {
                break;
            }
        }
        return returnValue;
    }
}
