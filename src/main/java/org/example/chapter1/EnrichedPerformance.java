package org.example.chapter1;

import lombok.Getter;

@Getter
public class EnrichedPerformance {

    private final String playID;
    private final int audience;
    private final Play play;
    private final int amount;
    private final int volumeCredits;

    public EnrichedPerformance(Performance performance, Play play, int amount, int volumeCreditsFor) {
        this.playID = performance.getPlayID();
        this.audience = performance.getAudience();
        this.play = play;
        this.amount = amount;
        this.volumeCredits = volumeCreditsFor;
    }
}
