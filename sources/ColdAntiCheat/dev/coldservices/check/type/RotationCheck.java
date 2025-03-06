package dev.coldservices.check.type;

import dev.coldservices.update.RotationUpdate;

public interface RotationCheck {
    void handle(final RotationUpdate update);
}