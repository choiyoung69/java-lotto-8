package lotto.domain;

import static lotto.utils.LottoConstants.LOTTO_PRICE_UNIT;

public class LottoCount {

    private final int count;

    private LottoCount(int count) {
        this.count = count;
    }

    public static LottoCount create(int amount) {
        validate(amount);
        return new LottoCount(amount / LOTTO_PRICE_UNIT);
    }

    public int getCount() {
        return count;
    }

    private static void validate(int amount) {
        validatePositiveAmount(amount);
        validateLottoPriceUnit(amount);
    }

    private static void validatePositiveAmount(int amount) {
        if(amount <= 0){
            throw new IllegalArgumentException("로또 구입 금액은 0원보다 커야 합니다");
        }
    }

    private static void validateLottoPriceUnit(int amount) {
        if (amount % LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException("로또 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
