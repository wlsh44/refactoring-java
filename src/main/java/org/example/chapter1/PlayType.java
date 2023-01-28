package org.example.chapter1;

import java.util.Arrays;

public enum PlayType {
    TRAGEDY("tragedy"),
    COMEDY("comedy");

    private final String type;

    PlayType(String type) {
        this.type = type;
    }

    public static PlayType of(String type) {
        return Arrays.stream(PlayType.values())
                .filter(playType -> playType.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("알 수 없는 장르: %s", type)));
    }
}
