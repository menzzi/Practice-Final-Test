package menu;

import menu.controller.RecommendationController;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView input = new InputView();
        OutputView output = new OutputView();

        RecommendationController recommendationController = new RecommendationController(input, output);
        recommendationController.run();
    }
}
