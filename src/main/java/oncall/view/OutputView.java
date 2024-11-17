package oncall.view;

public class OutputView {
    private final String emergencyScheduleFormat = "%s월 %d일 %s %s";

    public void printEmergencySchedule(String month, int date, String dayOfWeek, String name){
        System.out.printf(emergencyScheduleFormat,month,date,dayOfWeek,name);
        System.out.println();
    }
}
