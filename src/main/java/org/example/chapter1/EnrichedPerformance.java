package org.example.chapter1;

import lombok.Getter;

@Getter
public class EnrichedPerformance {

    private final String playID;
    private final int audience;
    private final Play play;
    private final int amount;
    private final int volumeCredits;

    public EnrichedPerformance(Performance performance, Play play) {
        this.playID = performance.getPlayID();
        this.audience = performance.getAudience();
        this.play = play;
        this.amount = amountFor(performance);
        this.volumeCredits = volumeCreditsFor(performance);
    }

    public int amountFor(Performance aPerformance) {
        int result = 0;

        switch (play.getType()) {
            case "tragedy" -> { //비극
                result = 40000;
                if (aPerformance.getAudience() > 30) {
                    result += 1000 * (aPerformance.getAudience() - 30);
                }
            }
            case "comedy" -> { //희극
                result = 30000;
                if (aPerformance.getAudience() > 20) {
                    result += 10000 + 500 * (aPerformance.getAudience() - 20);
                }
                result += 300 * aPerformance.getAudience();
            }
            default -> throw new IllegalArgumentException(String.format("알 수 없는 장르: %s", play.getType()));
        }
        return result;
    }

    private int volumeCreditsFor(Performance perf) {
        int volumeCredits = Math.max(perf.getAudience() - 30, 0);
        //희극 관객 5명마다 추가 포인트를 제공한다.
        if ("comedy".equals(play.getType())) {
            volumeCredits += Math.floor(perf.getAudience() / 5.);
        }
        return volumeCredits;
    }
}
