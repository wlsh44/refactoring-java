package org.example.chapter1;

import lombok.Data;
import org.example.chapter1.calculator.CalculatorFactoryFactory;
import org.example.chapter1.calculator.PerformanceCalculator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class StatementData {

    private final String customer;
    private final Plays plays;
    private final List<EnrichedPerformance> performances;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.plays = new Plays(plays);
        this.customer = invoice.getCustomer();
        this.performances = invoice.getPerformances().stream()
                .map(this::enrichPerformance)
                .collect(Collectors.toList());
    }

    private EnrichedPerformance enrichPerformance(Performance performance) {
        Play play = playFor(performance);
        PerformanceCalculator calculator = CalculatorFactoryFactory.createPerformanceCalculator(performance, play);
        return new EnrichedPerformance(performance, play, calculator.amount(), calculator.volumeCredits());
    }

    private Play playFor(Performance performance) {
        return plays.get(performance.getPlayID());
    }

    public int totalVolumeCredits() {
        AtomicInteger volumeCredits = new AtomicInteger();
        performances.forEach(performance -> volumeCredits.addAndGet(performance.getVolumeCredits()));
        return volumeCredits.get();
    }

    public int totalAmount() {
        AtomicInteger totalAmount = new AtomicInteger();
        performances.forEach(performance -> totalAmount.addAndGet(performance.getAmount()));
        return totalAmount.get();
    }
}
