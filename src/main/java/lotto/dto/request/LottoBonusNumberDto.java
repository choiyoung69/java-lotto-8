package lotto.dto.request;

import lotto.utils.Validators;

public record LottoBonusNumberDto(String bonusNumber) {
    public LottoBonusNumberDto(String bonusNumber) {
        Validators.validateNotBlank(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    @Override
    public String bonusNumber() {
        return bonusNumber;
    }
}
