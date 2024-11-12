package dev.coldservices.alert.alert;

import dev.coldservices.check.Check;
import dev.coldservices.data.PlayerData;

import java.util.Set;

public interface AlertHandler {
    void handle(final Set<PlayerData> listeners, final String base, final Check check, final String information);
}
