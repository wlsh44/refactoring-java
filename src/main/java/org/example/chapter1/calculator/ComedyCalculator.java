package org.example.chapter1.calculator;

import org.example.chapter1.Performance;

public class ComedyCalculator implements PerformanceCalculator {

    private final Performance performance;

    public ComedyCalculator(Performance performance) {
        this.performance = performance;
    }

    @Override
    public int amount() {
        int result = 30000;
        if (performance.getAudience() > 20) {
            result += 10000 + 500 * (performance.getAudience() - 20);
        }
        result += 300 * performance.getAudience();
        return result;
    }

    @Override
    public int volumeCredits() {
        int volumeCredits = Math.max(performance.getAudience() - 30, 0);
        volumeCredits += Math.floor(performance.getAudience() / 5.);
        return volumeCredits;
    }
}
