package lotto.service;

import static lotto.utils.LottoConstants.LOTTO_PRICE_UNIT;

import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;

public class LottoResultService {
    public LottoResult calculateResult(WinningNumbers winningNumbers, Lottos lottos) {
        Map<LottoRank, Long> result = lottos.getLottos()
                .stream()
                .map(winningNumbers::checkWinningResult)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));

        for (LottoRank rank : LottoRank.values()) {
            result.putIfAbsent(rank, 0L);
        }

        return LottoResult.create(result);
    }

    public double calculateProfitRate(Lottos lottos, LottoResult lottoResult) {
        long winningAmountSum = lottoResult.calculateTotalPrize();
        int amount = lottos.getLottosSize() * LOTTO_PRICE_UNIT;
        return (double) winningAmountSum / amount * 100;
    }
}
