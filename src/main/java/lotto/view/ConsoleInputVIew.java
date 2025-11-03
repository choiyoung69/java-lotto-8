package lotto.view;

import lotto.dto.LottoAmountDto;
import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputVIew {
    private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";

    public LottoAmountDto inputPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
        return new LottoAmountDto(Console.readLine());
    }
}
