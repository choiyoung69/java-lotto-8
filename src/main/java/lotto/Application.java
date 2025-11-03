package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoGenerateService;
import lotto.service.LottoResultService;
import lotto.view.ConsoleInputVIew;
import lotto.view.ConsoleOutputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ConsoleInputVIew inputView = new ConsoleInputVIew();
        OutputView outputView = new ConsoleOutputView();

        LottoController lottoController = new LottoController(inputView, outputView, new LottoGenerateService(), new LottoResultService());

    }
}
