package me.frep.vulcan.spigot.check.impl.movement.step;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Step", type = 'B', complexType = "Motion", description = "Invalid Step motion.")
public class StepB extends AbstractCheck
{
    private int ticks;
    private int lastAscension;
    
    public StepB(final PlayerData data) {
        super(data);
        this.ticks = 0;
        this.lastAscension = 0;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            if (this.data.getActionProcessor().isCrawling()) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final int groundTicks = this.data.getPositionProcessor().getClientGroundTicks();
            if (deltaY > 0.2) {
                ++this.ticks;
                this.lastAscension = this.ticks();
            }
            else if ((deltaY < 0.2 && deltaY > 0.0) || groundTicks > 16) {
                this.ticks = 0;
            }
            final boolean bed = this.data.getPositionProcessor().getSinceNearBedTicks() < 30;
            final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 100;
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.TELEPORT, ExemptType.FENCE, ExemptType.STAIRS, ExemptType.FLIGHT, ExemptType.CREATIVE, ExemptType.VELOCITY, ExemptType.COLLIDING_VERTICALLY, ExemptType.TRAPDOOR, ExemptType.SLAB, ExemptType.SLIME, ExemptType.WALL, ExemptType.GLIDING, ExemptType.RIPTIDE, ExemptType.VEHICLE, ExemptType.SEA_PICKLE, ExemptType.TURTLE_EGG, ExemptType.BOAT, ExemptType.SLEEPING, ExemptType.SNOW, ExemptType.ANVIL, ExemptType.POWDER_SNOW, ExemptType.END_ROD, ExemptType.CHAIN, ExemptType.PISTON, ExemptType.JUMP_BOOST, ExemptType.CAULDRON, ExemptType.HOPPER, ExemptType.CANCELLED_PLACE, ExemptType.ENDER_PEARL, ExemptType.SERVER_POSITION_FAST, ExemptType.FULLY_STUCK, ExemptType.ELYTRA, ExemptType.GLIDING, ExemptType.LEVITATION, ExemptType.FISHING_ROD, ExemptType.SWEET_BERRIES, ExemptType.ILLEGAL_BLOCK, ExemptType.GLITCHED_BLOCKS_ABOVE, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.CAKE, ExemptType.FLOWER_POT, ExemptType.TURTLE_EGG) || this.data.getPositionProcessor().isNearEnchantmentTable();
            final boolean bell = this.data.getPositionProcessor().isNearBell();
            final boolean lantern = this.data.getPositionProcessor().isNearLantern();
            final boolean amethyst = this.data.getPositionProcessor().isNearAmethyst();
            final boolean reset = this.ticks() - this.lastAscension > 20;
            if (exempt || reset || bed || explosion || bell || lantern || amethyst) {
                this.resetBuffer();
                this.ticks = 0;
                return;
            }
            if (this.ticks > 3) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("ticks=" + this.ticks + " deltaY=" + deltaY);
                }
            }
            else {
                this.decayBuffer();
            }
        }
        else if (packet.isTeleport()) {
            this.ticks = 0;
        }
    }
}
