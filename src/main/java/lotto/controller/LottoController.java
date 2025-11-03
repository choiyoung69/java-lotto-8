package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.dto.LottoAmountDto;
import lotto.service.LottoGenerateService;
import lotto.service.LottoResultService;
import lotto.utils.Parser;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerateService lottoGenerateService;
    private final LottoResultService lottoResultService;

    public LottoController(InputView inputView, OutputView outputView,
                           LottoGenerateService lottoGenerateService,
                           LottoResultService lottoResultService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerateService = lottoGenerateService;
        this.lottoResultService = lottoResultService;
    }

    public void run() {
        LottoPurchaseResposeDto lottoPurchaseDto = buyLottos();
    }

    private LottoPurchaseResponseDto buyLottos() {
        while (true) {
            try {
                LottoAmountDto lottoAmountDto = inputView.inputPurchaseAmount();
                int amount = Parser.parseToInteger(lottoAmountDto.amount());
                Lottos lottos = lottoGenerateService.buyRandomLottos(amount);
                LottoPurchaseResponseDto lottoPurchaseDto = LottoPurchaseDto.create(amount, lottos);
                OutputView.printLottoPurchaseResult(lottoPurchaseDto);
                return lottoPurchaseDto;
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }


}
