package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public record LottoPurchaseResponseDto(int count, List<List<Integer>> lottos) {
    public static LottoPurchaseResponseDto create(Lottos lottos) {
        return new LottoPurchaseResponseDto(lottos.getLottos().size(), lottos.getLottos()
                .stream()
                .map(Lotto::getNumbers)
                .toList());
    }
}
