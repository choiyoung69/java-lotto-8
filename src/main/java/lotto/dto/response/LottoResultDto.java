package lotto.dto.response;

import java.util.Map;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

public record LottoResultDto(Map<LottoRank, Long> lottoResult, double rateOfReturn) {
    public static LottoResultDto create(LottoResult lottoResult, double rateOfReturn) {
        return new LottoResultDto(lottoResult.getLottoResult(), rateOfReturn);
    }
}
