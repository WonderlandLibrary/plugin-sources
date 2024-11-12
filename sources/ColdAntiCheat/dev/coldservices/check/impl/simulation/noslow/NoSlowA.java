package dev.coldservices.check.impl.simulation.noslow;

import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PositionCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.update.PositionUpdate;

@CheckManifest(name = "NoSlow", type = "A", description = "Detects moving while blocking, eating")
public class NoSlowA extends Check implements PositionCheck {

    private double motionX, motionZ;

    public NoSlowA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(PositionUpdate update) {
        boolean exempt = isExempt(ExemptType.TELEPORT, ExemptType.CHUNK, ExemptType.SLIME,
                ExemptType.LIQUID, ExemptType.BOAT, ExemptType.VEHICLE, ExemptType.EXPLOSION,
                ExemptType.JOIN);

        if(exempt) return;

        if(data.getActionTracker().isBlocking() || data.getActionTracker().isEating() || data.getEmulationTracker().isUsing()) {
            double fallDistance = data.getPlayer().getFallDistance();

            if(data.getActionTracker().isSprinting() && fallDistance < 1) {
                if(this.buffer.increase() > 3) {
                    this.failNoBan("");
                    this.executeSetback(true, false);
                }
            } else {
                this.buffer.decrease();
            }
        }
    }
}
