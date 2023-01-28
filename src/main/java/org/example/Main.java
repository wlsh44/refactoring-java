package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DecimalFormat;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
    }

    public String statement(Invoice invoice, Map<String, PlayInfo> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer()));
        DecimalFormat format = new DecimalFormat("$#,###.00");

        for (var perf : invoice.getPerformances()) {
            PlayInfo play = plays.get(perf.getPlayID());
            int thisAmount = 0;

            switch (play.getType()) {
                case "tragedy" -> { //비극
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                }
                case "comedy" -> { //희극
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                }
                default -> throw new IllegalArgumentException(String.format("알 수 없는 장르: %s", play.getType()));
            }

            //포인트를 접립한다.
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            //희극 관객 5명마다 추가 포인트를 제공한다.
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(perf.getAudience() / 5.);
            }

            //청구 내역을 출력한다.
            result.append(String.format("   %s: %s (%d석)\n", play.getName(), format.format(thisAmount / 100.), perf.getAudience()));
            totalAmount += thisAmount;
        }
        result.append(String.format("총액: %s\n", format.format(totalAmount / 100.)));
        result.append(String.format("적립 포인트: %s점\n", volumeCredits));
        return result.toString();
    }
}