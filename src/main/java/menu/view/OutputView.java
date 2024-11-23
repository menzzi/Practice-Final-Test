package menu.view;

import java.util.List;

public class OutputView {
    private final String ERROR_MESSAGE_FORMAT = "[ERROR] %s";
    private final String SERVICE_LAUNCH_STATEMENT = "점심 메뉴 추천을 시작합니다.";
    private final String MENU_RECOMMENDATION_RESULT_STATEMENT = "메뉴 추천 결과입니다.";
    private final String MENU_RECOMMENDATION_END_STATEMENT = "추천을 완료했습니다.";
    private final String PRINT_FORMAT = "[ %s ]";

    public void printErrorMessage(String errorMessage){
        System.out.printf(ERROR_MESSAGE_FORMAT,errorMessage);
        System.out.println();
    }

    public void printServiceLaunchStatement(){
        System.out.println(SERVICE_LAUNCH_STATEMENT);
    }

    public void printResult(List<String> dayOfTheWeek){
        System.out.println(MENU_RECOMMENDATION_RESULT_STATEMENT);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("구분");
        for(String day : dayOfTheWeek){
            stringBuilder.append(" | ");
            stringBuilder.append(day);
        }
        System.out.printf(PRINT_FORMAT,stringBuilder.toString());
        System.out.println();
    }

    public void printMenuRecommendationResult(String toStringMessage){
        System.out.printf(PRINT_FORMAT,toStringMessage);
        System.out.println();
    }

    public void printEndMessage(){
        System.out.println(MENU_RECOMMENDATION_END_STATEMENT);
    }
}
