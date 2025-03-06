package com.elevatemc.anticheat.util.pair;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Pair<X, Y> {
    private X x;
    private Y y;
}
