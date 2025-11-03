package lotto.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;

public class LottoResultService {
    public LottoResult calculateResult(Lotto numbers, int bonusNumber, Lottos lottos) {
        WinningNumbers winningNumbers = WinningNumbers.create(numbers, bonusNumber);

        return LottoResult.create(lottos.getLottos()
                .stream()
                .map(winningNumbers::checkWinningResult)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting())));
    }

    public long calculateTotalPrize(LottoResult lottoResult) {
        return lottoResult.calculateTotalPrize();
    }

    public double calculateProfitRate(LottoCount lottoCount, long winningAmountSum) {
        return (double)winningAmountSum / (lottoCount.getCount() * LottoCount.LOTTO_PRICE_UNIT);
    }
}
