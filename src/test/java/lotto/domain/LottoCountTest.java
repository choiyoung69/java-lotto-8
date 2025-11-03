package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCountTest {

    @Test
    @DisplayName("로또 금액이 정상 입력되었을 때 정상 처리")
    void 로또금액이_정상입력되면_정상처리된다() {
        LottoCount lottoCount = LottoCount.create(10000);

        assertThat(lottoCount.getCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 금액이 1000원 단위가 아닐 때 에러 처리")
    void 로또금액이_1000원단위가_아닐때_에러처리된다() {
        assertThatThrownBy(() -> LottoCount.create(10123))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000원 단위");
    }

    @Test
    @DisplayName("로또 금액이 음수면 에러 처리")
    void 로또금액이_음수면_예외처리된다() {
        assertThatThrownBy(() -> LottoCount.create(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0원보다");
    }

    @Test
    @DisplayName("로또 금액이 0원이면 에러 처리")
    void 로또금액이_0원이면_예외처리된다() {
        assertThatThrownBy(() -> LottoCount.create(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0원보다");
    }

}
