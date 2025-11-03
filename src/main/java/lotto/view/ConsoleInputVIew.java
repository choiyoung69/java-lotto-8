package lotto.view;

import lotto.dto.request.LottoAmountDto;
import camp.nextstep.edu.missionutils.Console;
import lotto.dto.request.LottoBonusNumberDto;
import lotto.dto.request.LottoWinningNumbersDto;

public class ConsoleInputVIew implements InputView{
    private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

    @Override
    public LottoAmountDto inputPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
        return new LottoAmountDto(Console.readLine());
    }

    @Override
    public LottoWinningNumbersDto inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return new LottoWinningNumbersDto(Console.readLine());
    }

    @Override
    public LottoBonusNumberDto inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return new LottoBonusNumberDto(Console.readLine());
    }
}
