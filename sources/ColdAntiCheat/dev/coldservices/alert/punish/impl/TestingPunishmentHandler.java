package dev.coldservices.alert.punish.impl;

import dev.coldservices.alert.punish.PunishmentHandler;
import dev.coldservices.check.Check;

public class TestingPunishmentHandler implements PunishmentHandler {

    @Override
    public void punish(final Check check) {
        check.getData().getPlayer().sendMessage(
                "You would have been punished for " + check.getName() + " " + check.getType() + "."
        );
    }
}
