package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoService {
    public List<Integer> makeLottoNumber(){
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}