package dev.coldservices.alert.punish.impl;

import dev.coldservices.CAC;
import dev.coldservices.alert.punish.PunishmentHandler;
import dev.coldservices.check.Check;
import org.bukkit.Bukkit;

public class ProductionPunishmentHandler implements PunishmentHandler {

    @Override
    public void punish(final Check check) {
        if (!check.isPunishing()) return;

        check.getPunishments().forEach(s -> {
            this.execute(s.replace("%player%", check.getData().getPlayer().getName()));;
        });
    }

    private void execute(final String command) {
        Bukkit.getScheduler().runTask(CAC.get().getPlugin(),
                () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
    }
}
