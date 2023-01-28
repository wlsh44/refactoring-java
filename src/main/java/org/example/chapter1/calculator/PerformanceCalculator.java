package org.example.chapter1.calculator;

public interface PerformanceCalculator {
    int amount();
    int volumeCredits();

//    public int amount() {
//        int result = 0;
//
//        switch (play.getType()) {
//            case "tragedy" -> { //비극
//                result = 40000;
//                if (performance.getAudience() > 30) {
//                    result += 1000 * (performance.getAudience() - 30);
//                }
//            }
//            case "comedy" -> { //희극
//                result = 30000;
//                if (performance.getAudience() > 20) {
//                    result += 10000 + 500 * (performance.getAudience() - 20);
//                }
//                result += 300 * performance.getAudience();
//            }
//            default -> throw new IllegalArgumentException(String.format("알 수 없는 장르: %s", play.getType()));
//        }
//        return result;
//    }
//
//    int volumeCredits() {
//        int volumeCredits = Math.max(performance.getAudience() - 30, 0);
//        //희극 관객 5명마다 추가 포인트를 제공한다.
//        if ("comedy".equals(play.getType())) {
//            volumeCredits += Math.floor(performance.getAudience() / 5.);
//        }
//        return volumeCredits;
//    }
}
