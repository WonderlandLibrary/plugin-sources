package dev.coldservices.check.impl.badpackets;

import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientUseEntity;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

@CheckManifest(name = "BadPackets", type = "E", description = "Checks if player attacked it self")
public class BadPacketsE extends Check implements PacketCheck {

    public BadPacketsE(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientUseEntity) {
            GPacketPlayClientUseEntity wrapper = (GPacketPlayClientUseEntity) packet;

            if(wrapper.getType() != PlayerEnums.UseType.ATTACK) return;

            Entity e = wrapper.getEntity();

            if(e instanceof Player) {
                Player p = (Player) e;

                if(p == data.getPlayer()) {
                    if(this.buffer.increase() > 1) {
                        this.failNoBan("??!");
                    }
                } else {
                    this.buffer.decrease();
                }
            }
        }
    }
}
