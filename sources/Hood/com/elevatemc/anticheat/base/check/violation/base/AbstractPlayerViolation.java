package com.elevatemc.anticheat.base.check.violation.base;

import com.elevatemc.anticheat.base.check.Check;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractPlayerViolation implements Violation {
    private final Check check;
}