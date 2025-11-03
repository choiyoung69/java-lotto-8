package lotto.view;

import lotto.dto.response.LottoPurchaseResponseDto;
import lotto.dto.response.LottoResultDto;

public interface OutputView {
    void printLottoPurchaseResult(LottoPurchaseResponseDto lottoPurchaseResponseDto);
    void printLottoResult(LottoResultDto lottoResultDto);
    void println();
}
