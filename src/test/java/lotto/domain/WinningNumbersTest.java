package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningNumbersTest {

    @ParameterizedTest
    @MethodSource("invalidSizeNumbers")
    @DisplayName("당첨 번호가 6개가 아닐 때 예외처리된다")
    void 당첨번호가_6개가_아니면_예외처리된다(List<Integer> numbers) {
        int bonusNumber = 8;

        assertThatThrownBy(() -> WinningNumbers.create(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개");
    }

    private static Stream<List<Integer>> invalidSizeNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidRangeNumbers")
    @DisplayName("당첨 번호가 1에서 45 사이가 아닐 때 예외처리된다")
    void 당첨번호가_정상범위가_아니면_예외처리된다(List<Integer> numbers) {
        int bonusNumber = 8;

        assertThatThrownBy(() -> WinningNumbers.create(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45");
    }

    private static Stream<List<Integer>> invalidRangeNumbers() {
        return Stream.of(
                List.of(-1, 2, 3, 4, 5, 6),
                List.of(0, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 46),
                List.of(1, 2, 3, 4, 5, 100)
        );
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 당첨_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> WinningNumbers.create(List.of(1, 2, 3, 4, 5, 5), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("invalidRangeBonusNumber")
    @DisplayName("보너스 번호가 1에서 45 사이가 아닐 때 예외처리된다")
    void 보너스번호가_정상범위가_아니면_예외처리된다(Integer bonusNumber) {
        List<Integer> numbers  = List.of(1,2,3,4,5,6);
        assertThatThrownBy(() -> WinningNumbers.create(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45");
    }

    private static Stream<Integer> invalidRangeBonusNumber() {
        return Stream.of(
                -1,
                0,
                46,
                100
        );
    }

    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외가 발생한다.")
    @Test
    void 당첨번호와_보너스번호가에_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> WinningNumbers.create(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
