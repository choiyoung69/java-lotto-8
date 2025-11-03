package lotto.dto.request;

import lotto.utils.Validators;

public record LottoWinningNumbersDto(String winningNumbers) {

    public LottoWinningNumbersDto(String winningNumbers) {
        Validators.validateNotBlank(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    @Override
    public String winningNumbers() {
        return winningNumbers;
    }
}
