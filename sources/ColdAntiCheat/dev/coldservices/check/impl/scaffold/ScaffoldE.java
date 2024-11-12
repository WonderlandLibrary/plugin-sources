package dev.coldservices.check.impl.scaffold;

import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientBlockPlace;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.tracker.impl.ActionTracker;
import dev.coldservices.data.tracker.impl.PositionTracker;
import dev.coldservices.exempt.ExemptType;
import org.bukkit.GameMode;

@CheckManifest(name = "Scaffold", type = "E", description = "Detects keep-Y Scaffolds")
public class ScaffoldE extends Check implements PacketCheck {

    private int lastBlockY;

    public ScaffoldE(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientBlockPlace) {
            GPacketPlayClientBlockPlace wrapper = (GPacketPlayClientBlockPlace) packet;

            PositionTracker tracker = data.getPositionTracker();
            ActionTracker actionTracker = data.getActionTracker();

            boolean exempt = this.isExempt(ExemptType.WALL, ExemptType.FLIGHT, ExemptType.WEB) || data.getPlayer().getGameMode() == GameMode.CREATIVE
                    || wrapper.getPosition().getY() >= tracker.getY() || !data.isScaffolding();

            if(exempt) return;

            wrapper.getDirection().ifPresent(enumDirection -> {
                if(Math.abs(wrapper.getPosition().getY() - lastBlockY) == 0 && !data.getPlayer().isOnGround() && data.getPlayer().isSprinting()) {
                    if(this.buffer.increase() > 4) {
                        this.failNoBan("");
                        this.executeSetbackToPosition(tracker.getLastX(), wrapper.getPosition().getY(), tracker.getLastZ());
                    }
                } else {
                    this.buffer.decrease();
                }
            });

            lastBlockY = wrapper.getPosition().getY();
        }
    }
}
