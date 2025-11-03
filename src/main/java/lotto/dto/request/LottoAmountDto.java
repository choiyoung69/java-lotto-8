package lotto.dto.request;

import lotto.utils.Validators;

public record LottoAmountDto(String amount){
    public LottoAmountDto{
        Validators.validateNotBlank(amount);
    }
}
