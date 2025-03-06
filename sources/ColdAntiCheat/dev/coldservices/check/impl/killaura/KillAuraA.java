package dev.coldservices.check.impl.killaura;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientFlying;
import ac.artemis.packet.wrapper.client.PacketPlayClientUseEntity;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientUseEntity;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;

@CheckManifest(name = "KillAura", type = "A", description = "Detects if player attacked 2 entities in 1 tick")
public class KillAuraA extends Check implements PacketCheck {

    private int entities;

    public KillAuraA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientUseEntity) {
            GPacketPlayClientUseEntity wrapper = (GPacketPlayClientUseEntity) packet;

            if(wrapper.getType() != PlayerEnums.UseType.ATTACK) return;

            entities++;

            if(entities >= 2) {
                if(this.buffer.increase() > 1) {
                    this.failNoBan("multi attack");
                }
            } else {
                this.buffer.setBuffer(0);
            }
        }

        if(packet instanceof PacketPlayClientFlying) {
            entities = 0;
        }
    }
}
