package dev.coldservices.check.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CheckState {
    STABLE("Stable"),
    EXPERIMENTAL("Experimental"),
    DEVELOPMENT("Development");

    private final String displayName;
}