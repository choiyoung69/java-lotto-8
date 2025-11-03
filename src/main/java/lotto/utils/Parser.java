package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static final String COMMA_DELIMITER = ",";

    private Parser() {
    }

    public static List<Integer> parseToIntegerList(String input) {
        try {
            return Arrays.stream(input.split(COMMA_DELIMITER))
                    .map(Parser::parseToInteger)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값에는 숫자만 포함되어야 합니다.");
        }
    }

    public static int parseToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(input + "은(는) 숫자가 아닙니다.");
        }
    }
}
