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

    private StatementData(String customer, Plays plays, List<EnrichedPerformance> performances) {
        this.customer = customer;
        this.plays = plays;
        this.performances = performances;
    }

    public static StatementData createStatementData(Invoice invoice, Map<String, Play> playIDPlayMap) {
        Plays plays = new Plays(playIDPlayMap);
        List<EnrichedPerformance> performances = invoice.getPerformances().stream()
                .map(performance -> enrichPerformance(performance, plays))
                .collect(Collectors.toList());
        return new StatementData(invoice.getCustomer(), plays, performances);
    }

    private static EnrichedPerformance enrichPerformance(Performance performance, Plays plays) {
        Play play = plays.playFor(performance);
        PerformanceCalculator calculator = CalculatorFactoryFactory.createPerformanceCalculator(performance, play);
        return new EnrichedPerformance(performance, play, calculator.amount(), calculator.volumeCredits());
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
