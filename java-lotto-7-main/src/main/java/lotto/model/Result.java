package lotto.model;

import java.text.DecimalFormat;

public enum Result {
    NOTHING(0,0,false),
    FIFTH(3,5000,false),
    FOURTH(4,50000,false),
    THIRD(3,1500000,false),
    SECOND(2,30000000,true),
    FIRST(1,2000000000,false);

    private final int matchCount;
    private final int prizeAmount;
    private final boolean isBonusMatch;

    public static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###,###");

    Result(int matchCount, int prizeAmount, boolean isBonusMatch){
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
        this.isBonusMatch = isBonusMatch;
    }

    public static Result findResult(int matchCount, boolean isBonusMatch){
        for(Result result : Result.values()){
            if(result.matchCount == matchCount && result.isBonusMatch == isBonusMatch){
                return result;
            }
        }
        return NOTHING;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public String toString(int count) {
        return matchCount + "개 일치 (" + NUMBER_FORMAT.format(prizeAmount) + ") - %d개%n";
    }
}
