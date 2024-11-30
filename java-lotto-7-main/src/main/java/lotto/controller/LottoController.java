package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView input;
    private final OutputView output;

    private static final String INVALIDATE_BONUS_DUPLICATE = "보너스 번호가 당첨 번호와 중복됩니다.";

    public LottoController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        int amount = inputUserAmount() / 1000;
        List<Lotto> LottoTickets = issueLottoTickets(amount);
        Lotto winningLotto = inputWinningNumber();
        int bonusNumber = inputBonusNumber(winningLotto);
    }

    private int inputUserAmount(){
        try{
            return input.inputUserAmount();
        } catch (IllegalArgumentException e) {
            output.printErrorMessage(e.getMessage());
            return inputUserAmount();
        }
    }

    private List<Lotto> issueLottoTickets(int count){
        List<Lotto> lottoTickets = new ArrayList<>();
        for(int i=0;i<count;i++){
            Lotto lottoTicket = new Lotto(LottoService.makeLottoNumber());
            lottoTickets.add(lottoTicket);
        }
        return lottoTickets;
    }

    private Lotto inputWinningNumber(){
        try{
            return new Lotto(input.inputWinningNumber());
        }catch (IllegalArgumentException e){
            output.printErrorMessage(e.getMessage());
            return inputWinningNumber();
        }
    }

    private int inputBonusNumber(Lotto winningLotto){
        try{
            int number = input.inputBonusNumber();
            validateBonusNumber(winningLotto, number);
            return number;
        }catch (IllegalArgumentException e){
            output.printErrorMessage(e.getMessage());
            return inputBonusNumber(winningLotto);
        }
    }

    private void validateBonusNumber(Lotto winningLotto, int number){
        if(winningLotto.isBonusMatch(number)){
            throw new IllegalArgumentException(INVALIDATE_BONUS_DUPLICATE);
        }
    }
}
