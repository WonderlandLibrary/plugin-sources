package dev.coldservices.check.impl.simulation.strafe;

import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PositionCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.tracker.impl.PositionTracker;
import dev.coldservices.data.tracker.impl.RotationTracker;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.update.PositionUpdate;

@CheckManifest(name = "Strafe", type = "A", description = "Checks for invalid air movement")
public class StrafeA extends Check implements PositionCheck {

    // A REALLY BAD WAY OF STRAFE CHECK
    // HONESTLY I DONT KNOW WHAT I WAS THINKING WHILE MAKING IT
    // BUT IT ACTUALLY DETECTS XD

    // x

    // probably easy to bypass tho :(
    // made by rawrl

    public StrafeA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(PositionUpdate update) {
        boolean exempt = this.isExempt(ExemptType.JOIN, ExemptType.TELEPORTED_RECENTLY, ExemptType.ICE, ExemptType.VELOCITY,
                ExemptType.TELEPORT, ExemptType.CHUNK, ExemptType.WALL, ExemptType.BOAT, ExemptType.EXPLOSION);

        if(exempt) return;

        PositionTracker positionTracker = data.getPositionTracker();
        RotationTracker rotationTracker = data.getRotationTracker();

        double motionDirection = Math.atan2(positionTracker.getDeltaZ(), positionTracker.getDeltaX());
        double diffBetweenMotionAndYaw = Math.abs(wrapDegrees(rotationTracker.getYaw() - (float) Math.toDegrees(motionDirection)));

        if(!positionTracker.isOnGround() && diffBetweenMotionAndYaw >= 180) {
            this.failNoBan("diff: " + diffBetweenMotionAndYaw + " xz: " + (positionTracker.getDeltaXZ() - positionTracker.getLastDeltaXZ()));
        }
    }

    public static float wrapDegrees(float value)
    {
        value = value % 360.0F;

        if (value >= 180.0F)
        {
            value -= 360.0F;
        }

        if (value < -180.0F)
        {
            value += 360.0F;
        }

        return value;
    }
}
