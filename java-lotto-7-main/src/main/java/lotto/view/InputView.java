package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputView {
    public static String ENTER_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    public static String ENTER_WINNING_NUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";
    public static String ENTER_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

    public static String INVALIDATE_NUMERIC_FORMAT = "숫자를 입력해주세요.";
    public static String INVALIDATE_AMOUNT_FORMAT = "구입금액은 1,000원 단위입니다.";
    public static String INVALIDATE_INPUT_FORMAT = "올바르지 않은 형식입니다.";

    public static String INVALIDATE_LOTTO_RANGE = "로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static String INVALIDATE_LOTTO_DUPLICATE = "로또 번호는 중복되면 안됩니다.";
    public static String INVALIDATE_LOTTO_COUNT = "로또 번호는 6자리입니다.";

    public static final Pattern NUMERIC_PATTERN = Pattern.compile("^[0-9,]+$");

    public int inputUserAmount() {
        System.out.println(ENTER_AMOUNT_MESSAGE);
        String input = Console.readLine();
        return validateAmount(input);
    }

    public List<Integer> inputWinningNumber() {
        System.out.println(ENTER_WINNING_NUMBER_MESSAGE);
        String input = Console.readLine();
        return validateWinningNumber(input);
    }

    public int inputBonusNumber() {
        System.out.println(ENTER_BONUS_NUMBER_MESSAGE);
        String input = Console.readLine();
        return validateBonusNumber(input);
    }

    private int validateAmount(String input) {
        int amount = validateNumericFormat(input);
        if (amount % 1000 != 0 || amount == 0) {
            throw new IllegalArgumentException(INVALIDATE_AMOUNT_FORMAT);
        }
        return amount;
    }

    private List<Integer> validateWinningNumber(String input) {
        if (!NUMERIC_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(INVALIDATE_INPUT_FORMAT);
        }
        List<String> numbers = Arrays.asList(input.split(","));
        return validateWinningNumberRange(numbers);
    }

    private int validateBonusNumber(String input) {
        int number = validateNumericFormat(input);
        validateNumberRange(number);
        return number;
    }

    private List<Integer> validateWinningNumberRange(List<String> numbers) {
        List<Integer> winningNumber = new ArrayList<>();
        for (String StringNumber : numbers) {
            int number = validateNumericFormat(StringNumber);
            validateNumberRange(number);
            if (winningNumber.contains(number)) {
                throw new IllegalArgumentException(INVALIDATE_LOTTO_DUPLICATE);
            }
            winningNumber.add(number);
        }
        if (winningNumber.size() != 6) {
            throw new IllegalArgumentException(INVALIDATE_LOTTO_COUNT);
        }
        return winningNumber;
    }

    private int validateNumericFormat(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALIDATE_NUMERIC_FORMAT);
        }
    }

    private void validateNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(INVALIDATE_LOTTO_RANGE);
        }
    }
}
