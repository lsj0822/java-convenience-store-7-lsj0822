package utils;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;

public class CompareDate {
    public static boolean compareBetween(LocalDate startDate, LocalDate endDate) {
        LocalDate nowDate = DateTimes.now().toLocalDate();
        return nowDate.isBefore(endDate) && nowDate.isAfter(startDate);
    }
}
