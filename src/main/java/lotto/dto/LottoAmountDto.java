package lotto.dto;

import lotto.utils.Validators;

public record LottoAmountDto(String amount){
    public LottoAmountDto(String amount){
        Validators.validateNotBlank(amount);
        this.amount = amount;
    }

    @Override
    public String amount() {
        return amount;
    }
}
