package dev.coldservices.check.impl.aim;

import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.RotationCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.update.RotationUpdate;

@CheckManifest(name = "Aim", type = "B", description = "Checks if yaw moved but pitch is still same")
public class AimB extends Check implements RotationCheck {

    public AimB(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(RotationUpdate update) {
        float currentYaw = update.getYaw();
        float lastYaw = update.getLastYaw();

        float deltaYaw = update.getDeltaYaw();
        float deltaPitch = update.getDeltaPitch();

        if(currentYaw != lastYaw) {
            if(deltaYaw > 1.5 && deltaPitch == 0) {
                if(this.buffer.increase() > 25) {
                    this.failNoBan("deltaYaw: " + deltaYaw + " deltaPitch: " + deltaPitch);
                }
            } else {
                this.buffer.decrease();
            }
        } else {
            this.buffer.setBuffer(0);
        }
    }
}
