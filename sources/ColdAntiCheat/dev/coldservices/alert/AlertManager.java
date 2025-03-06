package dev.coldservices.alert;

import lombok.Getter;
import dev.coldservices.CAC;
import dev.coldservices.alert.alert.AlertHandler;
import dev.coldservices.alert.alert.AlertHandlerFactory;
import dev.coldservices.alert.punish.PunishmentHandler;
import dev.coldservices.alert.punish.PunishmentHandlerFactory;
import dev.coldservices.check.Check;
import dev.coldservices.data.PlayerData;
import dev.coldservices.util.string.ChatUtil;

import java.util.HashSet;
import java.util.Set;

public class AlertManager {

    @Getter
    private final Set<PlayerData> players = new HashSet<>();

    private final String format = ChatUtil.translate(
            "&7[&b&lCold AC&7] &b%player% &ffailed &b%check% %type% &7[&3x%vl%%dev%&7]"
    );

    private final AlertHandler alertHandler = new AlertHandlerFactory().build();
    private final PunishmentHandler punishmentHandler = new PunishmentHandlerFactory()
            .setTesting(CAC.TEST_MODE)
            .build();

    public boolean toggleAlerts(final PlayerData data) {
        return this.players.contains(data)
                ? !this.players.remove(data)
                : this.players.add(data);
    }

    public void handleAlert(final Check check, final String debug) {
        this.alertHandler.handle(this.players, format, check, debug);
    }

    public void handlePunishment(final Check check) {
        this.punishmentHandler.punish(check);
    }

    public enum ToggleAlertType {
        ADD, REMOVE
    }
}
