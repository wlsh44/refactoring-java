package org.example.chapter1;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class StatementData {

    private String customer;
    private Map<String, Play> plays;
    private List<EnrichedPerformance> performances;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.plays = plays;
        this.customer = invoice.getCustomer();
        this.performances = invoice.getPerformances().stream()
                .map(perf -> new EnrichedPerformance(perf, playFor(perf)))
                .collect(Collectors.toList());
    }

    public Play playFor(Performance performance) {
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
