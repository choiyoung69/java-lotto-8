package lotto.view;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoRank;
import lotto.dto.response.LottoPurchaseResponseDto;
import lotto.dto.response.LottoResultDto;

public class ConsoleOutputView implements OutputView {

    private static final String PURCHASE_RESULT_MESSAGE = "\n%d개를 구매했습니다.\n";
    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_SUFFIX = "]";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";

    private static final String WINNING_STATISTICS_HEADER = "당첨 통계\n";
    private static final String SEPARATOR = "---\n";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.\n";
    private static final String WINNING_RESULT_FORMAT = "%s (%s원) - %d개\n";

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
        System.out.println(sb.toString());
    }

    @Override
    public void printLottoResult(LottoResultDto lottoResultDto) {
        StringBuilder sb = new StringBuilder();

        sb.append(WINNING_STATISTICS_HEADER)
                .append(SEPARATOR);

        lottoResultDto.lottoResult().entrySet().stream()
                .filter(entry -> entry.getKey() != LottoRank.MISS)
                .sorted(Map.Entry.<LottoRank, Long>comparingByKey(Comparator.reverseOrder()))
                .forEach(entry -> {
                    LottoRank rank = entry.getKey();
                    sb.append(String.format(WINNING_RESULT_FORMAT,
                            rank.getMessage(),
                            String.format("%,d", rank.getPrize()),
                            entry.getValue()));

                });

        sb.append(String.format(PROFIT_RATE_MESSAGE, lottoResultDto.rateOfReturn()));
        System.out.println(sb.toString());
    }

    public void println() {
        System.out.println();
    }
}
