package me.frep.vulcan.spigot.check.impl.combat.velocity;

import org.bukkit.Location;
import me.frep.vulcan.spigot.util.type.Pair;
import me.frep.vulcan.spigot.data.processor.VelocityProcessor;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.util.nms.MathHelper;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Velocity", type = 'D', complexType = "Horizontal", description = "Horizontal velocity modifications.", experimental = true)
public class VelocityD extends AbstractCheck
{
    private final float LAND_MOVEMENT_FACTOR;
    private final double MINIMUM_MOVEMENT;
    private static final float SPEED_IN_AIR = 0.02f;
    private static final float FRICTION = 0.6f;
    private boolean hadPosition;
    private boolean hadHadPosition;
    private int attacks;
    
    public VelocityD(final PlayerData data) {
        super(data);
        this.LAND_MOVEMENT_FACTOR = (this.data.is1_13() ? 0.21600002f : 0.16277136f);
        this.MINIMUM_MOVEMENT = (this.data.is1_9() ? 0.003 : 0.005);
        this.hadPosition = false;
        this.hadHadPosition = false;
        this.attacks = 0;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying() && !this.teleporting()) {
            final boolean push = this.data.getActionProcessor().getSincePushTicks() < 20;
            if (push || this.data.getPositionProcessor().isNearBorder()) {
                return;
            }
            final WrappedPacketInFlying wrapper = this.data.getFlyingWrapper();
            final VelocityProcessor velocityProcessor = this.data.getVelocityProcessor();
            final int sinceVelocityTicks = velocityProcessor.getTransactionFlyingTicks();
            final boolean velocity = sinceVelocityTicks == 1;
            final boolean exempt = this.isExempt(ExemptType.SLOW_FALLING, ExemptType.LEVITATION, ExemptType.COLLIDING_VERTICALLY, ExemptType.COLLIDING_HORIZONTALLY, ExemptType.ELYTRA, ExemptType.GLIDING, ExemptType.RIPTIDE, ExemptType.ENTITY_COLLISION, ExemptType.SWEET_BERRIES, ExemptType.LIQUID, ExemptType.CLIMBABLE, ExemptType.ICE, ExemptType.SOUL_SAND, ExemptType.HONEY, ExemptType.ATTRIBUTE_MODIFIER, ExemptType.CAKE, ExemptType.NETHERITE_ARMOR, ExemptType.FROZEN, ExemptType.CHUNK, ExemptType.TRAPDOOR, ExemptType.DOOR, ExemptType.ILLEGAL_BLOCK, ExemptType.VEHICLE, ExemptType.DOLPHINS_GRACE, ExemptType.MYTHIC_MOB, ExemptType.PLACED_WEB, ExemptType.FULLY_STUCK, ExemptType.PARTIALLY_STUCK, ExemptType.VOID, ExemptType.CAULDRON, ExemptType.BUKKIT_VELOCITY, ExemptType.WEB, ExemptType.WATERLOGGED, ExemptType.ENDER_PEARL, ExemptType.FISHING_ROD, ExemptType.RESPAWN, ExemptType.DRAGON_DAMAGE, ExemptType.TELEPORT, ExemptType.DEATH, ExemptType.WORLD_CHANGE, ExemptType.FLIGHT);
            if (sinceVelocityTicks <= 6 && !exempt) {
                if (this.data.getPositionProcessor().getSinceCollidingVerticallyTicks() >= 3) {
                    final VelocityProcessor.VelocitySnapshot snapshot = this.data.getVelocityProcessor().getSnapshot();
                    if (snapshot == null) {
                        return;
                    }
                    if (snapshot.getVelocity().getX() >= 0.01 || snapshot.getVelocity().getY() >= 0.01 || snapshot.getVelocity().getZ() >= 0.01) {
                        float slipperiness = 0.91f;
                        float lastSlipperiness = 0.91f;
                        final boolean lastLastOnGround = this.data.getPositionProcessor().isLastLastClientOnGround();
                        final boolean lastOnGround = this.data.getPositionProcessor().isLastClientOnGround();
                        if (lastOnGround) {
                            slipperiness = 0.54600006f;
                        }
                        if (lastLastOnGround) {
                            lastSlipperiness = 0.54600006f;
                        }
                        double smallest = Double.MAX_VALUE;
                        for (int i = 0; i < 9; ++i) {
                            for (int sprintIter = 0; sprintIter < 2; ++sprintIter) {
                                for (int useItemIter = 0; useItemIter < 2; ++useItemIter) {
                                    for (int slowdownIter = 0; slowdownIter < 2; ++slowdownIter) {
                                        for (int jumpIter = 0; jumpIter < 2; ++jumpIter) {
                                            final boolean sprinting = sprintIter == 0;
                                            final boolean blocking = useItemIter == 0;
                                            final boolean hitSlowdown = slowdownIter == 0;
                                            final boolean jump = jumpIter == 0;
                                            final Pair<Float, Float> keyPresses = this.getKeyIndex(i);
                                            float forward = keyPresses.getX();
                                            float strafe = keyPresses.getY();
                                            if (blocking) {
                                                forward *= (float)0.2;
                                                strafe *= (float)0.2;
                                            }
                                            if (this.data.getActionProcessor().isSneaking()) {
                                                forward *= 0.3f;
                                                strafe *= 0.3f;
                                            }
                                            forward *= 0.98f;
                                            strafe *= 0.98f;
                                            double motionX = velocity ? velocityProcessor.getVelocityX() : this.data.getPositionProcessor().getLastDeltaX();
                                            double motionY = velocity ? velocityProcessor.getVelocityY() : this.data.getPositionProcessor().getLastDeltaY();
                                            double motionZ = velocity ? velocityProcessor.getVelocityZ() : this.data.getPositionProcessor().getLastDeltaZ();
                                            double moveSpeed = this.getAttributeSpeed(this.data);
                                            if (sprinting) {
                                                moveSpeed += moveSpeed * 0.30000001192092896;
                                            }
                                            final float moveSpeedMultiplier = this.LAND_MOVEMENT_FACTOR / (slipperiness * slipperiness * slipperiness);
                                            float moveFlyingFriction;
                                            if (lastOnGround) {
                                                moveFlyingFriction = (float)moveSpeed * moveSpeedMultiplier;
                                            }
                                            else {
                                                moveFlyingFriction = (float)(sprinting ? 0.025999999418854714 : 0.019999999552965164);
                                            }
                                            if (!this.data.is1_9() && this.attacks > 0 && hitSlowdown) {
                                                motionX *= 0.6;
                                                motionZ *= 0.6;
                                            }
                                            if (!velocity) {
                                                motionY -= 0.08;
                                                motionX *= lastSlipperiness;
                                                motionY *= 0.9800000190734863;
                                                motionZ *= lastSlipperiness;
                                            }
                                            if (Math.abs(motionX) < this.MINIMUM_MOVEMENT) {
                                                motionX = 0.0;
                                            }
                                            if (Math.abs(motionY) < this.MINIMUM_MOVEMENT) {
                                                motionY = 0.0;
                                            }
                                            if (Math.abs(motionZ) < this.MINIMUM_MOVEMENT) {
                                                motionZ = 0.0;
                                            }
                                            if (jump && lastOnGround) {
                                                motionY = 0.41999998688697815;
                                                if (this.data.getActionProcessor().isHasJumpBoost()) {
                                                    motionY += this.data.getActionProcessor().getJumpBoostAmplifier() * 0.1f;
                                                }
                                                if (sprinting) {
                                                    final float f = this.data.getRotationProcessor().getYaw() * 0.017453292f;
                                                    motionX -= MathHelper.sin(f) * 0.2f;
                                                    motionZ += MathHelper.cos(f) * 0.2f;
                                                }
                                            }
                                            float diagonal = strafe * strafe + forward * forward;
                                            double moveFlyingFactorX = 0.0;
                                            double moveFlyingFactorZ = 0.0;
                                            if (diagonal > 1.0E-4f) {
                                                diagonal = MathHelper.sqrt_float(diagonal);
                                                if (diagonal < 1.0f) {
                                                    diagonal = 1.0f;
                                                }
                                                diagonal = moveFlyingFriction / diagonal;
                                                final float flyingStrafe = strafe * diagonal;
                                                final float flyingForward = forward * diagonal;
                                                final float rotationYaw = this.data.getRotationProcessor().getYaw();
                                                final float f2 = MathHelper.sin(rotationYaw * 3.1415927f / 180.0f);
                                                final float f3 = MathHelper.cos(rotationYaw * 3.1415927f / 180.0f);
                                                final double factorX = flyingStrafe * f3 - flyingForward * f2;
                                                final double factorZ = flyingForward * f3 + flyingStrafe * f2;
                                                moveFlyingFactorX = factorX;
                                                moveFlyingFactorZ = factorZ;
                                            }
                                            motionX += moveFlyingFactorX;
                                            motionZ += moveFlyingFactorZ;
                                            final Location predicted = this.data.getPositionProcessor().getFrom().clone().add(motionX, 0.0, motionZ);
                                            predicted.setY(0.0);
                                            final Location to = this.data.getPositionProcessor().getTo().clone();
                                            to.setY(0.0);
                                            final double distance = to.distanceSquared(predicted);
                                            if (distance < smallest) {
                                                smallest = distance;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (smallest <= 10.0) {
                            double threshold = 5.0E-6 + ((this.hadPosition && this.hadHadPosition) ? 0.0 : 0.001);
                            if (this.data.is1_9()) {
                                threshold += 0.001;
                            }
                            if (smallest > threshold) {
                                if (this.increaseBuffer() > this.MAX_BUFFER) {
                                    this.fail("offset=" + smallest + " threshold=" + threshold + " tick=" + sinceVelocityTicks);
                                }
                            }
                            else {
                                this.decayBuffer();
                            }
                        }
                    }
                }
            }
            this.hadHadPosition = this.hadPosition;
            this.hadPosition = wrapper.isMoving();
            this.attacks = 0;
        }
        else if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper2 = this.data.getUseEntityWrapper();
            if (wrapper2.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                ++this.attacks;
            }
        }
    }
    
    private Pair<Float, Float> getKeyIndex(final int index) {
        float strafeForward = 0.0f;
        float strafeSideways = 0.0f;
        switch (index) {
            case 0: {
                strafeForward = 1.0f;
                break;
            }
            case 1: {
                strafeForward = 1.0f;
                strafeSideways = -1.0f;
                break;
            }
            case 2: {
                strafeSideways = -1.0f;
                break;
            }
            case 3: {
                strafeForward = -1.0f;
                strafeSideways = -1.0f;
                break;
            }
            case 4: {
                strafeForward = -1.0f;
                break;
            }
            case 5: {
                strafeForward = -1.0f;
                strafeSideways = 1.0f;
                break;
            }
            case 6: {
                strafeSideways = 1.0f;
                break;
            }
            case 7: {
                strafeForward = 1.0f;
                strafeSideways = 1.0f;
                break;
            }
            case 8: {
                strafeForward = 0.0f;
                strafeSideways = 0.0f;
                break;
            }
        }
        return new Pair<Float, Float>(strafeForward, strafeSideways);
    }
    
    public float getAttributeSpeed(final PlayerData data) {
        double attributeSpeed = data.getActionProcessor().getGenericMovementSpeed();
        attributeSpeed += data.getActionProcessor().getSpeedAmplifier() * 0.2 * attributeSpeed;
        return (float)attributeSpeed;
    }
}
