package org.example.chapter1;

import java.util.Map;

public class Plays {

    private final Map<String, Play> plays;

    public Plays(Map<String, Play> plays) {
        this.plays = plays;
    }

    public Play playFor(Performance performance) {
        return plays.get(performance.getPlayID());
    }
}
