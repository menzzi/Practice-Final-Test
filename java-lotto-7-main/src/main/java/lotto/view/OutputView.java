package lotto.view;

public class OutputView {
    public static final String ERROR_FORMAT = "[ERROR] %s%n";
    public static final String PURCHASE_NUMBER_MESSAGE = "%d개를 구매했습니다.%n";


    public void printErrorMessage(String errorMessage){
        System.out.printf(ERROR_FORMAT,errorMessage);
    }

    public void printLottoCount(int count){
        System.out.printf(PURCHASE_NUMBER_MESSAGE,count);
    }

    public void printLottoTicket(String LottoTicket){
        System.out.println(LottoTicket);
    }
}
