package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public record LottoPurchaseResponseDto(int count, List<List<Integer>> lottos) {
    public static LottoPurchaseResponseDto create(int count, Lottos lottos) {
        return new LottoPurchaseResponseDto(count, lottos.getLottos()
                .stream()
                .map(Lotto::numbers)
                .toList());
    }
}
