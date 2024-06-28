package org.crystalpvp.anticheat.check.combat.autoclicker;

import org.crystalpvp.anticheat.api.checks.PacketCheck;
import org.crystalpvp.anticheat.api.event.player.AlertType;
import org.crystalpvp.anticheat.api.manager.ConfigurationManager;
import org.crystalpvp.anticheat.data.player.PlayerData;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import org.bukkit.entity.Player;

/**
 *  Coded by 4Remi#8652
 *   DO NOT REMOVE
 */

public class AutoClickerI extends PacketCheck {
    private int stage;

    public AutoClickerI(PlayerData playerData) {
        super(playerData, "Auto-Clicker (I)");
    }

    @Override
    public void handleCheck(Player player, Packet packet) {
        if (ConfigurationManager.isEnabled("AutoClickerI") == false) {
            return;
        }
        double vl = playerData.getCheckVl(this);


        if (packet instanceof PacketPlayInBlockDig || packet instanceof PacketPlayInArmAnimation) {
            if (this.stage == 0) {
                if (packet instanceof PacketPlayInArmAnimation) {
                    ++this.stage;
                }
            } else if (packet instanceof PacketPlayInBlockDig) {
                if (playerData.isPlacing() || playerData.isDigging()) return;

                if (this.playerData.getFakeBlocks().contains(((PacketPlayInBlockDig) packet).a())) {
                	return;
                }

                if (((PacketPlayInBlockDig) packet).c() == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                    if (this.stage == 1) {
                        ++this.stage;
                    } else {
                        this.stage = 0;
                    }
                } else if (((PacketPlayInBlockDig) packet).c() == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                    if (this.stage == 2) {
                        if ((vl += 1.4) >= 15.0) {

                            alert(AlertType.RELEASE, player, String.format("VL %.1f/%s.", ++vl , ConfigurationManager.alertVl(getClass().getSimpleName())), true);

                            if (!playerData.isBanned() && ConfigurationManager.isAutoBanning("AutoClickerI") && vl >= ConfigurationManager.banVL("AutoClickerI")) {
                                punish(player);
                            }

                        }
                    } else {
                        this.stage = 0;
                        vl -= 0.25;
                    }
                } else {
                    this.stage = 0;
                }
            } else {
                this.stage = 0;
            }
        }

        playerData.setCheckVl(vl, this);
    }

}
