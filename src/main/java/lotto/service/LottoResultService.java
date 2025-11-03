package lotto.service;

import static lotto.utils.LottoConstants.LOTTO_PRICE_UNIT;

import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;

public class LottoResultService {
    private static final long ZERO_COUNT = 0L;

    public LottoResult calculateResult(WinningNumbers winningNumbers, Lottos lottos) {
        Map<LottoRank, Long> result = countWinningRanks(winningNumbers, lottos);
        fillEmptyRanks(result);
        return LottoResult.create(result);
    }

    private Map<LottoRank, Long> countWinningRanks(WinningNumbers winningNumbers, Lottos lottos) {
        return lottos.getLottos()
                .stream()
                .map(winningNumbers::checkWinningResult)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }

    private void fillEmptyRanks(Map<LottoRank, Long> result) {
        for (LottoRank rank : LottoRank.values()) {
            result.putIfAbsent(rank, ZERO_COUNT);
        }
    }

    public double calculateProfitRate(Lottos lottos, LottoResult lottoResult) {
        long winningAmountSum = lottoResult.calculateTotalPrize();
        int amount = lottos.getLottosSize() * LOTTO_PRICE_UNIT;
        return (double) winningAmountSum / amount * 100;
    }
}
