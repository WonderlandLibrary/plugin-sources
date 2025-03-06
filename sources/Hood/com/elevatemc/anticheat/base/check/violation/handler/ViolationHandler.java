package com.elevatemc.anticheat.base.check.violation.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ViolationHandler {
    private final int maxViolations;

    private final long maxViolationTimeLength;

    public static final ViolationHandler EXPERIMENTAL = new ViolationHandler(Integer.MAX_VALUE, 0L);
}