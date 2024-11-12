package dev.coldservices.alert.punish;

import dev.coldservices.check.Check;

public interface PunishmentHandler {
    void punish(final Check check);
}
