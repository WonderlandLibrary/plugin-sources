package dev.coldservices.check.impl.autoclicker;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientFlying;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientArmAnimation;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientFlying;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;

@CheckManifest(name = "AutoClicker", type = "A", description = "Detects cps over 20")
public class AutoClickerA extends Check implements PacketCheck {

    private int cps, flying;

    public AutoClickerA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientArmAnimation) {
            cps++;
        }

        if(packet instanceof PacketPlayClientFlying) {
            if(++flying > 20) {
                if(cps > 20) {
                    this.failNoBan("cps: " + cps);
                }

                flying = cps = 0;
            }
        }
    }
}
