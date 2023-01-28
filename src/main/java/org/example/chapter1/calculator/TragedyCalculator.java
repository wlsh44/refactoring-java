package org.example.chapter1.calculator;

import org.example.chapter1.Performance;
import org.example.chapter1.Play;

public class TragedyCalculator implements PerformanceCalculator {

    private final Performance performance;
    private final Play play;

    public TragedyCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }


    @Override
    public int amount() {
        int result = 40000;
        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }

    @Override
    public int volumeCredits() {
        return Math.max(performance.getAudience() - 30, 0);
    }
}
