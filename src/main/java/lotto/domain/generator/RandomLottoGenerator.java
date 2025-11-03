package lotto.domain.generator;

import lotto.domain.Lotto;
import camp.nextstep.edu.missionutils.Randoms;

public class RandomLottoGenerator implements LottoGenerator {
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(
                LOTTO_NUMBER_MIN,
                LOTTO_NUMBER_MAX,
                LOTTO_NUMBER_COUNT));
    }
}
