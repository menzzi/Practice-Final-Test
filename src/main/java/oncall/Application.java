package oncall;

import oncall.controller.EmergencyWorkController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView input = new InputView();
        OutputView output = new OutputView();

        EmergencyWorkController emergencyWorkController = new EmergencyWorkController(input,output);
        emergencyWorkController.run();
    }
}
