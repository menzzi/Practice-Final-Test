package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.validation.Validation;

public class InputView {

    public String[] inputDateInformation() {
        System.out.println("비상 근무를 배정할 월과 시작 요일을 입력하세요>");
        String userInput = Console.readLine();
        Validation.validateInputFormat(userInput);
        Validation.validateMonth(userInput);
        userInput = userInput.replaceAll(" ","");
        return userInput.split(",");
    }

    public String inputWeekdayWorker() {
        System.out.println("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        return Console.readLine();
    }

    public String inputWeekendWorker() {
        System.out.println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        return Console.readLine();
    }
}
