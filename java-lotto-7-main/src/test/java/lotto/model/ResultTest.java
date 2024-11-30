package lotto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ResultTest {
    @Test
    public void FIRST_Result를_찾는다() {
        int matchCount = 6;
        boolean isBonusMatch = false;

        Result result = Result.findResult(matchCount, isBonusMatch);

        assertEquals(Result.FIRST, result);
    }
}
