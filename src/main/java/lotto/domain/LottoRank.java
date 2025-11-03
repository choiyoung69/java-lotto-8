package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0);

    private final int matchCount;
    private final boolean bonusMatch;
    private final long prize;

    LottoRank(int matchCount, boolean bonusMatch, long prize) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public static LottoRank calculateRank(int matchCount, boolean bonusMatch) {
        if (matchCount == SECOND.matchCount && bonusMatch == SECOND.bonusMatch) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }

    public long getPrize() {
        return prize;
    }
}
