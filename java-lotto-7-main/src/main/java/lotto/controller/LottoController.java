package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.Result;
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
        output.printLottoCount(amount);

        List<Lotto> LottoTickets = issueLottoTickets(amount);
        for(Lotto lotto : LottoTickets){
            output.printLottoTicket(lotto.toString());
        }

        Lotto winningLotto = inputWinningNumber();
        int bonusNumber = inputBonusNumber(winningLotto);

        Map<Result,Integer> results = LottoService.findResults(LottoTickets,winningLotto,bonusNumber);
        printResult(results,amount);
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

    private void printResult(Map<Result,Integer> results, int amount){
        output.printResultMessage();
        int totalAmount = 0;
        for(Result result : results.keySet()){
            if(result.name().equals("NOTHING")){
                continue;
            }
            totalAmount += result.getPrizeAmount() * results.get(result);
            output.printResult(result.toString(results.get(result)));
        }
        output.printProfit(LottoService.calculateProfit(amount,totalAmount));
    }

    private void validateBonusNumber(Lotto winningLotto, int number){
        if(winningLotto.isBonusMatch(number)){
            throw new IllegalArgumentException(INVALIDATE_BONUS_DUPLICATE);
        }
    }
}
