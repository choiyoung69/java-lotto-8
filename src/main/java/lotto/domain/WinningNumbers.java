package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final Lotto numbers;
    private final int bonusNumber;

    private WinningNumbers(Lotto lotto, int bonusNumber) {
        this.numbers = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers create(Lotto lotto, int bonusNumber) {
        validate(lotto, bonusNumber);
        return new WinningNumbers(lotto, bonusNumber);
    }

    public LottoRank checkWinningResult(Lotto lotto) {
        int matchCount = lotto.countMatch(numbers);
        boolean bonusMatch = lotto.containBonusNumber(bonusNumber);

        System.out.println(bonusMatch);

        return LottoRank.calculateRank(matchCount, bonusMatch);
    }

    private static void validate(Lotto numbers, int bonusNumber) {
        validateOutOfRangeBonusNumber(bonusNumber);
        validateNoDuplicate(numbers, bonusNumber);
    }

    private static void validateOutOfRangeBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1~45 사이여야 합니다");
        }
    }

    private static void validateNoDuplicate(Lotto numbers, int bonusNumber) {
        if (numbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호와 로또 번호는 중복될 수 없습니다");
        }
    }
}
