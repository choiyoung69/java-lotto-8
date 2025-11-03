package lotto.domain.generator;

import static lotto.utils.LottoConstants.LOTTO_NUMBER_MAX;
import static lotto.utils.LottoConstants.LOTTO_NUMBER_MIN;
import static lotto.utils.LottoConstants.LOTTO_SIZE;

import lotto.domain.Lotto;
import camp.nextstep.edu.missionutils.Randoms;

public class RandomLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(
                LOTTO_NUMBER_MIN,
                LOTTO_NUMBER_MAX,
                LOTTO_SIZE));
    }
}
