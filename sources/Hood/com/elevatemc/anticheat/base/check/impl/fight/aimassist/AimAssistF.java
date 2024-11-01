package com.elevatemc.anticheat.base.check.impl.fight.aimassist;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;
import com.elevatemc.anticheat.util.math.MathUtil;
public class AimAssistF extends RotationCheck {
    public AimAssistF(PlayerData playerData) {
        super(playerData, "Aim Assist F", "Invalid aim prediction", new ViolationHandler(7, 300L),
                Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 6 || rotationTracker.isZooming()) {
            return;
        }
        float yaw = rotationTracker.getYaw();
        float pitch = rotationTracker.getPitch();

        float deltaYaw = rotationTracker.getDeltaYaw();

        float deltaPitch = rotationTracker.getDeltaPitch();
        float lastDeltaPitch = rotationTracker.getLastDeltaPitch();

        double divisor = MathUtil.getGcd(deltaPitch, lastDeltaPitch);

        if (divisor < 0.0078125F) return;

        double deltaX = deltaYaw / divisor;
        double deltaY = deltaPitch / divisor;

        boolean properX = Math.abs(Math.round(deltaX) - deltaX) < 0.0001D;
        boolean properY = Math.abs(Math.round(deltaY) - deltaY) < 0.0001D;

        if (!properX || !properY) return;

        double diffX = Math.abs(yaw - (yaw - (yaw % divisor)));
        double diffY = Math.abs(pitch - (pitch - (pitch % divisor)));

        if (diffX < 1e-4 && diffY < 1e-4 && teleportTracker.getSinceTeleportTicks() > 10) {
            if (increaseBuffer() > 12) {
                handleViolation(new DetailedPlayerViolation(this, "X " + format(diffX) + " Y " + format(diffY)));
                if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
            }
        } else {
            decreaseBufferBy(1.25);
        }
    }
}