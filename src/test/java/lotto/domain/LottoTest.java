package lotto.domain;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("로또 번호가 6개일 때 정상 생성된다")
    void 로또번호가_정상입력되면_정상처리된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.numbers()).hasSize(6);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("invalidSizeNumbers")
    @DisplayName("로또 번호가 6개가 아닐 때 예외처리된다")
    void 로또번호가_6개가_아니면_예외처리된다(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
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
    @MethodSource("outOfRangeNumbers")
    @DisplayName("로또 번호가 1~45 사이가 아닐 때 예외처리된다")
    void 로또번호가_범위를_벗어나면_예외처리된다(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45");
    }

    private static Stream<List<Integer>> outOfRangeNumbers() {
        return Stream.of(
                List.of(0, 2, 3, 4, 5, 6),
                List.of(-1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 100),
                List.of(1, 2, 3, 4, 5, 50)
        );
    }
}
