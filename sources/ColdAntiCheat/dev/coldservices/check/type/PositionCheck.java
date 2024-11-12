package dev.coldservices.check.type;

import dev.coldservices.update.PositionUpdate;

public interface PositionCheck {
    void handle(final PositionUpdate update);
}