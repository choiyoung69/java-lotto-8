package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.dto.response.LottoPurchaseResponseDto;
import lotto.dto.response.LottoResultDto;
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
        Lottos lottos = buyLottos();

        Lotto lotto = inputWinningNumbers();
        outputView.println();

        WinningNumbers winningNumbers = inputBonusNumber(lotto);
        outputView.println();

        calculateResult(winningNumbers, lottos);
    }

    private Lottos buyLottos() {
        while (true) {
            try {
                int amount = Parser.parseToInteger(inputView.inputPurchaseAmount().amount());
                Lottos lottos = lottoGenerateService.buyRandomLottos(amount);
                LottoPurchaseResponseDto lottoPurchaseDto = LottoPurchaseResponseDto.create(lottos);
                outputView.printLottoPurchaseResult(lottoPurchaseDto);
                return lottos;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private Lotto inputWinningNumbers() {
        while (true) {
            try {
                List<Integer> parsedWinningNumbers = Parser.parseToIntegerList(inputView.inputWinningNumbers().winningNumbers());
                return new Lotto(parsedWinningNumbers);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private WinningNumbers inputBonusNumber(Lotto lotto) {
        while (true) {
            try {
                int bonusNumber = Parser.parseToInteger(inputView.inputBonusNumber().bonusNumber());
                return WinningNumbers.create(lotto, bonusNumber);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private void calculateResult(WinningNumbers winningNumbers, Lottos lottos) {
        LottoResult lottoResult = lottoResultService.calculateResult(winningNumbers, lottos);
        double rateOfReturn = lottoResultService.calculateProfitRate(lottos, lottoResult);
        outputView.printLottoResult(LottoResultDto.create(lottoResult, rateOfReturn));
    }
}
