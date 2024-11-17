package oncall.view;

public class OutputView {
    private final String EMERGENCY_SCHEDULE_FORMAT = "%s월 %d일 %s %s";
    private final String ERROR_FORMAT = "[ERROR] %s";

    public void printEmergencySchedule(String month, int date, String dayOfWeek, String name){
        System.out.printf(EMERGENCY_SCHEDULE_FORMAT,month,date,dayOfWeek,name);
        System.out.println();
    }

    public void printErrorMessage(String errorMessage){
        System.out.printf(ERROR_FORMAT,errorMessage);
        System.out.println();
    }
}
