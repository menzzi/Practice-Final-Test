package oncall.service;

public class CalendarManagement {
    private static final String holidayMark = "(휴일)";

    public static String markHoliday(String day, int date, int[] holiday) {
        if (!day.equals("일") && !day.equals("토")) {
            if (isMatchHoliday(holiday, date)) {
                return day + holidayMark;
            }
        }
        return day;
    }

    private static boolean isMatchHoliday(int[] holiday, int date) {
        for (int day : holiday) {
            if (day == date) {
                return true;
            }
        }
        return false;
    }
}
