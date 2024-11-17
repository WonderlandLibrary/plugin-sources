package me.frep.vulcan.spigot.check.impl.player.badpackets;

import io.github.retrooper.packetevents.packetwrappers.play.in.helditemslot.WrappedPacketInHeldItemSlot;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'G', complexType = "Hotbar", description = "Invalid HeldItemSlot packet.")
public class BadPacketsG extends AbstractCheck
{
    private int lastSlot;
    private int lastOutbound;
    
    public BadPacketsG(final PlayerData data) {
        super(data);
        this.lastSlot = -1;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isHeldItemSlot()) {
            final WrappedPacketInHeldItemSlot wrapper = this.data.getHeldItemSlotWrapper();
            final int slot = wrapper.getCurrentSelectedSlot();
            final boolean outbound = this.ticks() - this.lastOutbound < 100;
            if (slot == this.lastSlot && !outbound) {
                this.fail("slot=" + slot + " lastSlot=" + this.lastSlot);
            }
            this.lastSlot = slot;
        }
        else if (packet.isHeldItemSlotOut()) {
            this.lastOutbound = this.ticks();
        }
    }
}
