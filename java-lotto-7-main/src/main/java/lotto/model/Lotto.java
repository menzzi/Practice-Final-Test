package lotto.model;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public final String INVALIDATE_LOTTO_NUMBER = "로또 번호는 6개여야 합니다.";
    public final String INVALIDATE_LOTTO_RANGE = "로또 번호는 1부터 45 사이의 숫자여야 합니다.";

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(INVALIDATE_LOTTO_NUMBER);
        }

        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(INVALIDATE_LOTTO_RANGE);
            }
        }
    }

    // TODO: 추가 기능 구현
    public int compareWithWinningNumber(Lotto WinningNumber) {
        int matchCount = 0;
        for (int lottoIndex = 0; lottoIndex < 6; lottoIndex++) {
            for (int winningNumberIndex = 0; winningNumberIndex < 6; winningNumberIndex++) {
                if (lottoIndex == winningNumberIndex) {
                    matchCount++;
                }
            }
        }
        return matchCount;
    }

    public boolean isBonusMatch(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public String toString(){
        return numbers.toString();
    }
}
