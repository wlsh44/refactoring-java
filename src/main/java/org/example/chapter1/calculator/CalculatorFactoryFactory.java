package org.example.chapter1.calculator;

import org.example.chapter1.Performance;
import org.example.chapter1.Play;
import org.example.chapter1.PlayType;

public class CalculatorFactoryFactory {

    public static PerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        switch (PlayType.of(play.getType())) {
            case TRAGEDY -> {
                return new TragedyCalculator(performance, play);
            }
            case COMEDY -> {
                return new ComedyCalculator(performance);
            }
            default -> throw new IllegalArgumentException(String.format("알 수 없는 장르: %s", play.getType()));
        }
    }
}
