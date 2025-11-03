package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("로또 개수만큼 로또가 생성된다")
    void 로또개수만큼_로또가_생성된다() {
        LottoCount count = LottoCount.create(2000);
        LottoGenerator generator = () -> new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Lottos lottos = Lottos.create(count, generator);

        assertThat(lottos.getLottos()).hasSize(2);
    }

}
