package lotto.domain.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos create(LottoCount lottoCount, LottoGenerator lottoGenerator) {
         return new Lottos(IntStream.range(0, lottoCount.getCount())
                .mapToObj(i -> lottoGenerator.generate())
                .collect(Collectors.toUnmodifiableList()));
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
