package dev.coldservices.check.impl.autoclicker;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientArmAnimation;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientArmAnimation;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;

@CheckManifest(name = "AutoClicker", type = "B", description = "Detects if the clicks are always at the same timing")
public class AutoClickerB extends Check implements PacketCheck {

    private long lastClick;

    public AutoClickerB(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientArmAnimation) {
            if(packet.getTimestamp() - lastClick == 0) {
                if(this.buffer.increase() > 12) {
                    this.failNoBan("timestamp (difference): " + (packet.getTimestamp() - lastClick));
                }
            } else {
                this.buffer.decreaseBy(0.05);
            }

            lastClick = packet.getTimestamp();
        }
    }
}
