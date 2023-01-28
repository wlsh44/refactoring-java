package org.example.chapter1;

import java.text.DecimalFormat;
import java.util.Map;

public class Statement {
    public String statement(Invoice invoice, Map<String, Play> plays) {
        StatementData statementData = new StatementData(invoice, plays);
        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("청구 내역 (고객명: %s)\n", data.getCustomer()));

        data.getPerformances()
            .forEach(perf -> result.append(String.format("   %s: %s (%d석)\n", perf.getPlay().getName(), usd(perf.getAmount()), perf.getAudience())));
        result.append(String.format("총액: %s\n", usd(data.totalAmount())));
        result.append(String.format("적립 포인트: %s점\n", data.totalVolumeCredits()));
        return result.toString();
    }

    private String usd(double aNumber) {
        DecimalFormat format = new DecimalFormat("$#,###.00");
        return format.format(aNumber / 100.);
    }
}