package me.frep.vulcan.spigot.check.impl.combat.killaura;

import me.frep.vulcan.spigot.util.PlayerUtil;
import io.github.retrooper.packetevents.packetwrappers.play.in.clientcommand.WrappedPacketInClientCommand;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Kill Aura", type = 'H', complexType = "Inventory", experimental = false, description = "Attacked while opening inventory.")
public class KillAuraH extends AbstractCheck
{
    private boolean attacked;
    
    public KillAuraH(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            this.attacked = false;
        }
        else if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                this.attacked = true;
            }
        }
        else if (packet.isClientCommand()) {
            final WrappedPacketInClientCommand wrapper2 = new WrappedPacketInClientCommand(packet.getRawPacket());
            if (wrapper2.getClientCommand() == WrappedPacketInClientCommand.ClientCommand.OPEN_INVENTORY_ACHIEVEMENT && this.attacked) {
                if (!PlayerUtil.isHigherThan1_9(this.data.getPlayer())) {
                    this.fail();
                }
            }
        }
    }
}
