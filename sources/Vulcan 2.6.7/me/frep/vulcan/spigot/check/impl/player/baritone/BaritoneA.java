package me.frep.vulcan.spigot.check.impl.player.baritone;

import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Baritone", type = 'A', complexType = "Rotations", description = "Checks for Baritone like rotations.")
public class BaritoneA extends AbstractCheck
{
    private int breakTicks;
    
    public BaritoneA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && ++this.breakTicks < 10) {
            final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
            final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
            final boolean cinematic = this.data.getRotationProcessor().isCinematic();
            final boolean invalid = deltaYaw < 0.005 && deltaPitch < 0.005 && deltaYaw > 0.0 && deltaPitch > 0.0 && !cinematic;
            if (invalid) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaYaw=" + deltaYaw + " deltaPitch=" + deltaPitch);
                }
            }
            else {
                this.decayBuffer();
            }
        }
        else if (packet.isBlockDig()) {
            final WrappedPacketInBlockDig wrapper = this.data.getBlockDigWrapper();
            if (wrapper.getDigType() == WrappedPacketInBlockDig.PlayerDigType.STOP_DESTROY_BLOCK) {
                this.breakTicks = 0;
            }
        }
    }
}
