package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.dto.LottoAmountDto;
import lotto.dto.LottoPurchaseResponseDto;
import lotto.service.LottoGenerateService;
import lotto.service.LottoResultService;
import lotto.utils.Parser;
import lotto.view.ConsoleInputVIew;
import lotto.view.OutputView;

public class LottoController {
    private final ConsoleInputVIew inputView;
    private final OutputView outputView;
    private final LottoGenerateService lottoGenerateService;
    private final LottoResultService lottoResultService;

    public LottoController(ConsoleInputVIew inputView, OutputView outputView,
                           LottoGenerateService lottoGenerateService,
                           LottoResultService lottoResultService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerateService = lottoGenerateService;
        this.lottoResultService = lottoResultService;
    }

    public void run() {
        Lottos lottoPurchaseDto = buyLottos();
        inputWinningNumbers();
    }

    private Lottos buyLottos() {
        while (true) {
            try {
                int amount = Parser.parseToInteger(inputView.inputPurchaseAmount().amount());
                Lottos lottos = lottoGenerateService.buyRandomLottos(amount);
                LottoPurchaseResponseDto lottoPurchaseDto = LottoPurchaseResponseDto.create(lottos);
                outputView.printLottoPurchaseResult(lottoPurchaseDto);
                return lottos;
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private Lotto inputWinningNumbers() {
        while (true) {
            try {
                inputView.inputWinningNumbers();
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
