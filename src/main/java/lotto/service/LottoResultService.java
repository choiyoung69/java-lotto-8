package lotto.service;

import java.util.stream.Collectors;
import lotto.domain.LottoCount;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;

public class LottoResultService {
    public LottoResult calculateResult(WinningNumbers winningNumbers, Lottos lottos) {

        return LottoResult.create(lottos.getLottos()
                .stream()
                .map(winningNumbers::checkWinningResult)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting())));
    }

    public double calculateProfitRate(Lottos lottos, LottoResult lottoResult) {
        long winningAmountSum = lottoResult.calculateTotalPrize();
        return (double) winningAmountSum / (lottos.getLottos().size() * LottoCount.LOTTO_PRICE_UNIT);
    }
}
