package lotto.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;

public class LottoResultService {
    public Map<LottoRank, Long> calculateResult(List<Integer> numbers, int bonusNumber, Lottos lottos) {
        WinningNumbers winningNumbers = WinningNumbers.create(numbers, bonusNumber);

        Map<LottoRank, Long> lottoResult = lottos.getLottos()
                .stream()
                .map(winningNumbers::checkWinningResult)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));
    }
}
