package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000, "6개 일치"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, 1_500_000, "5개 일치"),
    FOURTH(4, false, 50_000, "4개 일치"),
    FIFTH(3, false, 5_000, "3개 일치"),
    MISS(0, false, 0, "");

    private final int matchCount;
    private final boolean bonusMatch;
    private final long prize;
    private final String message;

    LottoRank(int matchCount, boolean bonusMatch, long prize, String message) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
        this.message = message;
    }

    public static LottoRank calculateRank(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> rank.bonusMatch == bonusMatch)
                .findFirst()
                .orElse(MISS);
    }

    public long getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
