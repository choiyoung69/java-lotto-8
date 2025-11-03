package lotto.view;

import java.util.stream.Collectors;
import lotto.dto.LottoPurchaseResponseDto;

public class ConsoleOutputView implements OutputView {

    private static final String PURCHASE_RESULT_MESSAGE = "%d개를 구매했습니다.%n";
    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_SUFFIX = "]";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";

    @Override
    public void printLottoPurchaseResult(LottoPurchaseResponseDto dto) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(PURCHASE_RESULT_MESSAGE, dto.count()));

        dto.lottos().forEach(numbers ->
                sb.append(LOTTO_PREFIX)
                        .append(numbers.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER)))
                        .append(LOTTO_SUFFIX)
                        .append("\n"));
        System.out.println();
    }
}
