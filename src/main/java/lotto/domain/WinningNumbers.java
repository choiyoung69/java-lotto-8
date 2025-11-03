package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> numbers;
    private final int bonusNumber;

    private WinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers create(List<Integer> numbers, int bonusNumber) {
        validate(numbers, bonusNumber);
        return new WinningNumbers(numbers, bonusNumber);
    }

    public LottoRank checkWinningResult(Lotto lotto) {
        int matchCount = lotto.countMatch(numbers);
        boolean bonusMatch = lotto.containBonusNumber(bonusNumber);

        return LottoRank.calculateRank(matchCount, bonusMatch);
    }

    private static void validate(List<Integer> numbers, int bonusNumber) {
        validateNumbersSize(numbers);
        validateOutOfRangeNumbers(numbers);
        validateOutOfRangeBonusNumber(bonusNumber);
        validateNoDuplicate(numbers, bonusNumber);
    }

    private static void validateNumbersSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private static void validateOutOfRangeNumbers(List<Integer> numbers) {
        boolean outOfRange = numbers.stream()
                .anyMatch(number -> number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX);
        if (outOfRange) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1~45 사이여야 합니다");
        }
    }

    private static void validateOutOfRangeBonusNumber(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이여야 합니다");
        }
    }

    private static void validateNoDuplicate(List<Integer> numbers, int bonusNumber) {
        validateNumberNoDuplicate(numbers);
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호와 로또 번호는 중복될 수 없습니다");
        }
    }

    private static void validateNumberNoDuplicate(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }
}
