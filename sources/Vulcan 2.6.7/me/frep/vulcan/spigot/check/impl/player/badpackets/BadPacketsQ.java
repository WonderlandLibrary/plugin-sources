package me.frep.vulcan.spigot.check.impl.player.badpackets;

import io.github.retrooper.packetevents.packetwrappers.play.in.helditemslot.WrappedPacketInHeldItemSlot;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'Q', complexType = "Hotbar", description = "Too big HeldItemSlot packet.")
public class BadPacketsQ extends AbstractCheck
{
    public BadPacketsQ(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isHeldItemSlot()) {
            final WrappedPacketInHeldItemSlot wrapper = this.data.getHeldItemSlotWrapper();
            final int slot = wrapper.getCurrentSelectedSlot();
            if (slot > 8) {
                this.fail("slot=" + slot);
            }
        }
    }
}
