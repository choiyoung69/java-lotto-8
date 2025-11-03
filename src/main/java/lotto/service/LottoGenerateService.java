package lotto.service;

import lotto.domain.LottoCount;
import lotto.domain.Lottos;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.RandomLottoGenerator;

public class LottoGenerateService {
    public Lottos buyRandomLottos(int amount) {
        LottoCount lottoCount = LottoCount.create(amount);
        LottoGenerator lottoGenerator = new RandomLottoGenerator();

        Lottos lottos = Lottos.create(lottoCount, lottoGenerator);

        return lottos;
    }
}
