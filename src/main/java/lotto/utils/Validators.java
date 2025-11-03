package lotto.utils;

public class Validators {

    private Validators() {
    }

    public static void validateNotBlank(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("입력은 비어있으면 안됩니다.");
        }
    }
}
