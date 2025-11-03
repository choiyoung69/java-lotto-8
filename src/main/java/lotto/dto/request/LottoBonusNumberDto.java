package lotto.dto.request;

import lotto.utils.Validators;

public record LottoBonusNumberDto(String bonusNumber) {
    public LottoBonusNumberDto{
        Validators.validateNotBlank(bonusNumber);
    }
}
