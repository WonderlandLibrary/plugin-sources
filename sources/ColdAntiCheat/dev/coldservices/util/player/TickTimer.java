package dev.coldservices.util.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import dev.coldservices.data.PlayerData;

@Getter
@RequiredArgsConstructor
public class TickTimer {

    private final PlayerData data;

    private int tick = 0;

    public void reset() {
        this.tick = data.getTicks();
    }

    public boolean hasPassed(final int t) {
        return data.getTicks() - this.tick > t;
    }

    public boolean hasNotPassed(final int t) {
        return data.getTicks() - this.tick <= t;
    }
}