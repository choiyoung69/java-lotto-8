package lotto.domain;

import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Long> lottoResult;

    private LottoResult(Map<LottoRank, Long> lottoResult) {
        this.lottoResult = lottoResult;
    }

    public static LottoResult create(Map<LottoRank, Long> lottoResult) {
        return new LottoResult(lottoResult);
    }

    public long calculateTotalPrize() {
        return lottoResult.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}
