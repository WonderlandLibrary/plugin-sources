package dev.coldservices.check.impl.aim;

import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.RotationCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.update.RotationUpdate;

@CheckManifest(name = "Aim", type = "A", description = "Detects static speed aim assists")
public class AimA extends Check implements RotationCheck {

    private float lastYawSpeed, lastPitchSpeed, oldYawDist, oldPitchDist;

    public AimA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(RotationUpdate update) {
        if(this.isExempt(ExemptType.TELEPORT, ExemptType.JOIN, ExemptType.TELEPORTED_RECENTLY, ExemptType.BOAT,
                ExemptType.VEHICLE)) {
            return;
        }

        float currentYaw = update.getYaw();
        float currentPitch = update.getPitch();

        float yawDist = Math.abs(currentYaw - update.getLastYaw());
        float pitchDist = Math.abs(currentPitch - update.getLastPitch());

        float yawSpeed = Math.abs(yawDist - oldYawDist);
        float pitchSpeed = Math.abs(pitchDist - oldPitchDist);

        if(currentYaw != update.getLastYaw()) {
            if(yawDist > 1) {
                boolean invalid = yawSpeed == 0.0D || yawDist == oldYawDist || pitchDist == oldPitchDist && pitchDist > 1;
                if(invalid && this.buffer.increase() > 6) {
                    this.failNoBan("static speed: " + yawSpeed + " " + pitchSpeed);
                }
            }
        } else {
            this.buffer.decrease();
        }

        lastYawSpeed = yawSpeed;
        lastPitchSpeed = pitchSpeed;

        oldYawDist = yawDist;
        oldPitchDist = pitchDist;
    }
}
