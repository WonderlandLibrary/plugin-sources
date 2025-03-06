package me.frep.vulcan.spigot.check.impl.combat.fastbow;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.ServerUtil;
import org.bukkit.entity.Arrow;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Fast Bow", type = 'A', complexType = "Limit", description = "Shooting a bow too quickly")
public class FastBowA extends AbstractCheck
{
    private long lastShot;
    
    public FastBowA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isProjectileLaunchEvent()) {
            final ProjectileLaunchEvent event = (ProjectileLaunchEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.getEntity() instanceof Arrow) {
                final Arrow arrow = (Arrow)event.getEntity();
                final boolean crossbow = this.data.getPlayer().getItemInHand().getType().toString().contains("CROSS") || (ServerUtil.isHigherThan1_9() && this.data.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("CROSS"));
                final boolean holdingBow = this.data.getPlayer().getItemInHand().getType().toString().contains("BOW") || (ServerUtil.isHigherThan1_9() && this.data.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("BOW"));
                if (arrow.getVelocity().length() > 2.0 && !crossbow && holdingBow) {
                    final long delay = System.currentTimeMillis() - this.lastShot;
                    final boolean exempt = this.isExempt(ExemptType.FAST);
                    if (delay < 750L && !exempt) {
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("length=" + arrow.getVelocity().length() + " delay=" + delay);
                        }
                    }
                    else {
                        this.decayBuffer();
                    }
                    this.lastShot = System.currentTimeMillis();
                }
            }
        }
    }
}
