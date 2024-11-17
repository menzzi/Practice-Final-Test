package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final String INVALIDATE_DATE_INFORMATION_FORMAT = "유효하지 않은 입력 값입니다. 다시 입력해 주세요.";

    public String[] inputDateInformation(){
        System.out.println("비상 근무를 배정할 월과 시작 요일을 입력하세요>");
        String userInput = Console.readLine();
        validateDateInformationFormat(userInput);
        validateMonth(userInput);
        return userInput.split(",");
    }

    public String inputWeekdayWorker(){
        System.out.println("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        return Console.readLine();
    }

    public String inputWeekendWorker(){
        System.out.println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        return Console.readLine();
    }

    private void validateDateInformationFormat(String input){
        String regex = "^[0-9\\-가-힣,]*$";
        if (input.contains(regex)) {
            throw new IllegalArgumentException(INVALIDATE_DATE_INFORMATION_FORMAT);
        }
    }

    private void validateMonth(String input){
        String month = input.split(",")[0];
        try{
            int number = Integer.parseInt(month);
            if(number < 1 || number > 12){
                throw new IllegalArgumentException(INVALIDATE_DATE_INFORMATION_FORMAT);
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(INVALIDATE_DATE_INFORMATION_FORMAT);
        }
    }
}
