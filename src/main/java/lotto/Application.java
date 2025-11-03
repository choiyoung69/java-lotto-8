package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoGenerateService;
import lotto.service.LottoResultService;
import lotto.view.ConsoleInputVIew;

public class Application {
    public static void main(String[] args) {
        ConsoleInputVIew inputView = new ConsoleInputVIew();
        OutputView outputView = new OutputView();

        LottoController lottoController = new LottoController(inputView, outputView, new LottoGenerateService(),
                new LottoResultService());

    }
}
