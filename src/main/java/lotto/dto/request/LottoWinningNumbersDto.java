package lotto.dto.request;

import lotto.utils.Validators;

public record LottoWinningNumbersDto(String winningNumbers) {
    public LottoWinningNumbersDto{
        Validators.validateNotBlank(winningNumbers);
    }
}
