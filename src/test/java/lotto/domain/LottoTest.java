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
        assertThat(lotto.getNumbers()).hasSize(6);
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
    @MethodSource("invalidSizeGetNumbers")
    @DisplayName("로또 번호가 6개가 아닐 때 예외처리된다")
    void 로또번호가_6개가_아니면_예외처리된다(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개");
    }

    private static Stream<List<Integer>> invalidSizeGetNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7)
        );
    }

    @ParameterizedTest
    @MethodSource("outOfRangeGetNumbers")
    @DisplayName("로또 번호가 1~45 사이가 아닐 때 예외처리된다")
    void 로또번호가_범위를_벗어나면_예외처리된다(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45");
    }

    private static Stream<List<Integer>> outOfRangeGetNumbers() {
        return Stream.of(
                List.of(0, 2, 3, 4, 5, 6),
                List.of(-1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 100),
                List.of(1, 2, 3, 4, 5, 50)
        );
    }

    @Test
    @DisplayName("하나도 일치하지 않으면 0을 반환한다")
    void 일치하지_않음() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto mine = new Lotto(List.of(7, 8, 9, 10, 11, 12));

        int matchCount = mine.countMatch(winning);

        assertThat(matchCount).isZero();
    }

    @Test
    @DisplayName("1개 일치하면 1을 반환한다")
    void 한개_일치() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto mine = new Lotto(List.of(1, 7, 8, 9, 10, 11));

        int matchCount = mine.countMatch(winning);

        assertThat(matchCount).isEqualTo(1);
    }

    @Test
    @DisplayName("2개 일치하면 2을 반환한다")
    void 두개_일치() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto mine = new Lotto(List.of(1, 2, 7, 8, 9, 10));

        int matchCount = mine.countMatch(winning);

        assertThat(matchCount).isEqualTo(2);
    }

    @Test
    @DisplayName("3개 일치하면 3을 반환한다")
    void 세개_일치() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto mine = new Lotto(List.of(1, 2, 3, 7, 8, 9));

        int matchCount = mine.countMatch(winning);

        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("4개 일치하면 4를 반환한다")
    void 네개_일치() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto mine = new Lotto(List.of(1, 2, 3, 4, 7, 8));

        int matchCount = mine.countMatch(winning);

        assertThat(matchCount).isEqualTo(4);
    }

    @Test
    @DisplayName("5개 일치하면 5를 반환한다")
    void 다섯개_일치() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto mine = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        int matchCount = mine.countMatch(winning);

        assertThat(matchCount).isEqualTo(5);
    }

    @Test
    @DisplayName("6개 일치하면 6를 반환한다")
    void 여섯개_일치() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto mine = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        int matchCount = mine.countMatch(winning);

        assertThat(matchCount).isEqualTo(6);
    }

    @Test
    @DisplayName("보너스 번호를 포함하면 true를 반환한다")
    void 보너스번호_포함() {
        int bonusNumber = 1;
        Lotto mine = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        boolean result = mine.containBonusNumber(bonusNumber);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("보너스 번호를 포함하지 않으면 false를 반환한다")
    void 보너스번호_미포함() {
        int bonusNumber = 7;
        Lotto mine = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        boolean result = mine.containBonusNumber(bonusNumber);

        assertThat(result).isFalse();
    }

}
