package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView input = new InputView();
        OutputView output = new OutputView();

        LottoController lottoController = new LottoController(input, output);
        lottoController.run();
    }
}
