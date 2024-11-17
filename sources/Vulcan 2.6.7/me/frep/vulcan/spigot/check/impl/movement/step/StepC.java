package me.frep.vulcan.spigot.check.impl.movement.step;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Step", type = 'C', complexType = "Motion", description = "Reverse step.")
public class StepC extends AbstractCheck
{
    int nearGroundTicks;
    
    public StepC(final PlayerData data) {
        super(data);
        this.nearGroundTicks = 0;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            if (this.data.getActionProcessor().getPositionTicksExisted() < 30) {
                return;
            }
            final int airTicks = this.data.getPositionProcessor().getClientAirTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            if (this.data.getActionProcessor().getDistanceFromLastBreak() < 1.25 && this.data.getActionProcessor().getDistanceFromLastBreak() > 0.0) {
                return;
            }
            final float fallDistance = this.data.getPlayer().getFallDistance();
            final boolean exempt = this.isExempt(ExemptType.TELEPORT, ExemptType.WORLD_CHANGE, ExemptType.DOOR, ExemptType.DRAGON_DAMAGE, ExemptType.DEATH, ExemptType.JOINED, ExemptType.FLIGHT, ExemptType.CREATIVE, ExemptType.GLIDING, ExemptType.EXPLOSION, ExemptType.CANCELLED_PLACE, ExemptType.ELYTRA, ExemptType.LIQUID, ExemptType.VEHICLE, ExemptType.RIPTIDE, ExemptType.SLEEPING, ExemptType.BLOCK_PLACE, ExemptType.VELOCITY, ExemptType.FAST, ExemptType.GLIDING, ExemptType.BUKKIT_VELOCITY, ExemptType.SLIME, ExemptType.ENDER_PEARL, ExemptType.ANVIL, ExemptType.END_ROD, ExemptType.CHAIN, ExemptType.DRIPSTONE, ExemptType.SERVER_POSITION, ExemptType.PISTON, ExemptType.JUMP_BOOST, ExemptType.CLIMBABLE, ExemptType.SCAFFOLDING) || this.data.getPositionProcessor().isNearAmethyst() || this.data.getPositionProcessor().isNearDripstone() || this.data.getActionProcessor().getSinceCrystalDamageTicks() < 20;
            final boolean nearGround = this.data.getPositionProcessor().isNearGround();
            if (nearGround) {
                ++this.nearGroundTicks;
            }
            else {
                this.nearGroundTicks = 0;
            }
            if (!exempt) {
                switch (airTicks) {
                    case 0: {
                        if (deltaY >= -0.5 || this.nearGroundTicks <= 5) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance + " ng=" + this.nearGroundTicks);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (deltaY >= -0.07850000262260437) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (deltaY >= -0.15530000627040863) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (deltaY >= -0.2305999994277954) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 4: {
                        if (deltaY >= -0.3043999969959259) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 5: {
                        if (deltaY >= -0.376800000667572) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 6: {
                        if (deltaY >= -0.44749999046325684) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 7: {
                        if (deltaY >= -0.5170999765396118) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 8: {
                        if (deltaY >= -0.5860000252723694) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 9: {
                        if (deltaY >= -0.6520000100135803) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 10: {
                        if (deltaY >= -0.7171000242233276) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 11: {
                        if (deltaY >= -0.7811999917030334) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 12: {
                        if (deltaY >= -0.843999981880188) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 13: {
                        if (deltaY >= -0.9054999947547913) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 14: {
                        if (deltaY >= -0.9657999873161316) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 15: {
                        if (deltaY >= -1.024899959564209) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 16: {
                        if (deltaY >= -1.082800030708313) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 17: {
                        if (deltaY >= -1.1395000219345093) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 18: {
                        if (deltaY >= -1.195099949836731) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 19: {
                        if (deltaY >= -1.2496000528335571) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 20: {
                        if (deltaY >= -1.3029999732971191) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 21: {
                        if (deltaY >= -1.3559999465942383) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 22: {
                        if (deltaY >= -1.406999945640564) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 23: {
                        if (deltaY >= -1.4570000171661377) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 24: {
                        if (deltaY >= -1.5069999694824219) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                        }
                    }
                    case 25: {
                        if (deltaY >= -1.5549999475479126) {
                            this.decayBuffer();
                            break;
                        }
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("ticks=" + airTicks + " deltaY=" + deltaY + " distance=" + fallDistance);
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
}
