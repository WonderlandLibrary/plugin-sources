package dev.coldservices.alert.punish;

import dev.coldservices.alert.punish.impl.ProductionPunishmentHandler;
import dev.coldservices.alert.punish.impl.TestingPunishmentHandler;
import dev.coldservices.util.Factory;

public class PunishmentHandlerFactory implements Factory<PunishmentHandler> {

    private boolean testing;

    public PunishmentHandlerFactory setTesting(final boolean testing) {
        this.testing = testing;

        return this;
    }

    @Override
    public PunishmentHandler build() {
        return this.testing ? new TestingPunishmentHandler() : new ProductionPunishmentHandler();
    }
}
