package dev.coldservices.check.impl.badpackets;

import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientBlockDig;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientBlockPlace;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import org.bukkit.Location;

import java.util.Objects;

@CheckManifest(name = "BadPackets", type = "F", description = "Checks if the use item releases are legit.")
public class BadPacketsF extends Check implements PacketCheck {

    public BadPacketsF(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientBlockDig) {
            GPacketPlayClientBlockDig wrapper = (GPacketPlayClientBlockDig) packet;

            if(wrapper.getType() == PlayerEnums.DigType.RELEASE_USE_ITEM) {
                boolean invalid = wrapper.getFace() != EnumDirection.DOWN || !Objects.equals(wrapper.getLocation(), new Location(wrapper.getLocation().getWorld(), 0, 0, 0));

                if(invalid) {
                    this.failNoBan("LOL GET CATCHED BOZO");
                }
            }
        }
    }
}
