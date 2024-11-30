package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView input;
    private final OutputView output;

    public LottoController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        int amount = inputUserAmount() / 1000;
        List<Lotto> LottoTickets = issueLottoTickets(amount);
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
}
