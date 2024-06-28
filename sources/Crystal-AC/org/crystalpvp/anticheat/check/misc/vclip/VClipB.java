package org.crystalpvp.anticheat.check.misc.vclip;


import org.crystalpvp.anticheat.api.checks.PositionCheck;
import org.crystalpvp.anticheat.api.event.player.AlertType;
import org.crystalpvp.anticheat.api.manager.ConfigurationManager;
import org.crystalpvp.anticheat.api.update.PositionUpdate;
import org.crystalpvp.anticheat.api.util.BlockUtil;
import org.crystalpvp.anticheat.data.player.PlayerData;
import org.bukkit.entity.Player;

/**
 *  Coded by 4Remi#8652
 *   DO NOT REMOVE
 */

public class VClipB extends PositionCheck {

    public VClipB(PlayerData playerData) {
        super(playerData, "V-Clip (B)");
    }
    
    @Override
    public void handleCheck(final Player player, final PositionUpdate update) {
        if (ConfigurationManager.isEnabled("VClipB") == false) {
            return;
        }
        double vl = playerData.getCheckVl(this);

        final double difference = update.getTo().getY() - update.getFrom().getY();

        if (difference >= 2.0 && BlockUtil.isSlab(player)) {
            alert(AlertType.RELEASE, player, String.format("VL %.1f/%s.", ++vl , ConfigurationManager.alertVl(getClass().getSimpleName())), true);

            if (!playerData.isBanned() && ConfigurationManager.isAutoBanning("VClipB") && vl >= ConfigurationManager.banVL("VClipB")) {
                punish(player);
            }
        }
        playerData.setCheckVl(vl, this);
    }
}
