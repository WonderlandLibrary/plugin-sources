package dev.coldservices.alert.alert.impl;

import dev.coldservices.alert.alert.AlertHandler;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.enums.CheckState;
import dev.coldservices.data.PlayerData;
import dev.coldservices.util.string.ChatUtil;
import net.md_5.bungee.api.chat.*;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadedAlertHandler implements AlertHandler {

    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public void handle(final Set<PlayerData> listeners, final String base, final Check check, final String information) {
        this.executor.execute(() -> {
            final PlayerData data = check.getData();

            final String description = check.getDescription();
            final String state = check.getState().getDisplayName();
            final int ping = data.getConnectionTracker().getTransactionPing();
            final int max = check.getMaxVl();

            final String message = ChatUtil.translate(base
                    .replaceAll("%player%", data.getPlayer().getName())
                    .replaceAll("%check%", check.getName())
                    .replaceAll("%dev%", check.getState() != CheckState.STABLE ? "&7*" : "")
                    .replaceAll("%vl%", Integer.toString(check.getViolations()))
                    .replaceAll("%type%", check.getType()));

            final String hover = ChatUtil.translate(String.join("\n", Arrays.asList(
                    "&3" + description,
                    " ",
                    "&7" + information,
                    " ",
                    "&bPing: &7" + ping + "ms" + " &bMaxVl: &7" + max + " &bState: &7" + state)));

            final TextComponent alertMessage = new TextComponent(message);
            final BaseComponent[] baseComponents = new ComponentBuilder(hover).create();

            alertMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + data.getPlayer().getName()));
            alertMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, baseComponents));

            listeners.forEach(player -> player.getPlayer().spigot().sendMessage(alertMessage));
        });
    }
}
