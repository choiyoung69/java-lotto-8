package lotto.view;

import lotto.dto.request.LottoAmountDto;
import lotto.dto.request.LottoWinningNumbersDto;

public interface InputView {
    LottoAmountDto inputPurchaseAmount();
    LottoWinningNumbersDto inputWinningNumbers();
}
