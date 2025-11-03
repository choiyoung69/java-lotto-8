package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("로또 번호와 당첨번호가 6개 일치하면 LottoRank에서 FIRST가 생성된다.")
    @Test
    void 로또_번호와_당첨번호가_6개_일치하면_LottoRank에서_FIRST가_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.create(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoRank lottoRank = winningNumbers.checkWinningResult(lotto);

        assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("로또 번호와 당첨번호가 5개 일치하고 보너스 번호가 일치하면 LottoRank에서 SECOND가 생성된다.")
    @Test
    void 로또_번호와_당첨번호가_5개_일치하고_보너스번호가_일치하면_LottoRank에서_SECOND가_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.create(List.of(1, 2, 3, 4, 5, 7), 6);

        LottoRank lottoRank = winningNumbers.checkWinningResult(lotto);

        assertThat(lottoRank).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("로또 번호와 당첨번호가 5개 일치하면 LottoRank에서 THIRD가 생성된다.")
    @Test
    void 로또_번호와_당첨번호가_5개_일치하면_LottoRank에서_THIRD가_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.create(List.of(1, 2, 3, 4, 7, 8), 9);

        LottoRank lottoRank = winningNumbers.checkWinningResult(lotto);

        assertThat(lottoRank).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("로또 번호와 당첨번호가 4개 일치하면 LottoRank에서 FOURTH가 생성된다.")
    @Test
    void 로또_번호와_당첨번호가_4개_일치하면_LottoRank에서_FOURH가_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.create(List.of(1, 2, 3, 4, 7, 8), 9);

        LottoRank lottoRank = winningNumbers.checkWinningResult(lotto);

        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH);
    }

    @DisplayName("로또 번호와 당첨번호가 3개 일치하면 LottoRank에서 FIFTH가 생성된다.")
    @Test
    void 로또_번호와_당첨번호가_3개_일치하면_LottoRank에서_FIFTH가_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.create(List.of(1, 2, 3, 7, 8, 9), 10);

        LottoRank lottoRank = winningNumbers.checkWinningResult(lotto);

        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("로또 번호와 당첨번호가 3개 이하로 일치하면 LottoRank에서 MISS가 생성된다.")
    @Test
    void 로또_번호와_당첨번호가_3개_이하로_일치하면_LottoRank에서_MISS가_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.create(List.of(1, 2, 7, 8, 9, 10), 11);

        LottoRank lottoRank = winningNumbers.checkWinningResult(lotto);

        assertThat(lottoRank).isEqualTo(LottoRank.MISS);
    }
}
