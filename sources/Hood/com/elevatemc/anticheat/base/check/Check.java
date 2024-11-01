package com.elevatemc.anticheat.base.check;

import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.violation.base.AbstractPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.*;
import com.elevatemc.anticheat.manager.AlertManager;
import com.elevatemc.anticheat.util.chat.CC;
import lombok.Getter;
import lombok.Setter;
import org.atteo.classindex.IndexSubclasses;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@IndexSubclasses
public abstract class Check {
    protected final AlertManager alertManager = HoodPlugin.INSTANCE.getAlertManager();

    protected final TransactionTracker transactionTracker;
    protected final KeepAliveTracker keepAliveTracker;
    protected final CollisionTracker collisionTracker;
    protected final AttributeTracker attributeTracker;
    protected final PositionTracker positionTracker;
    protected final RotationTracker rotationTracker;
    protected final TeleportTracker teleportTracker;
    protected final VelocityTracker velocityTracker;
    protected final EntityTracker entityTracker;
    protected final ActionTracker actionTracker;
    protected final SwingTracker swingTracker;
    protected final PredictionTracker predictionTracker;
    protected final PlayerData playerData;
    private final String name, desc;

    private final ViolationHandler violationHandler;

    private final Category category;

    private final SubCategory subCategory;

    protected double vl = 0D;
    protected int falseProbability;


    private double buffer;

    protected Check(PlayerData playerData, String name, String desc, ViolationHandler violationHandler, Category category, SubCategory subCategory, int falseProbability) {
        this.playerData = playerData;
        this.name = name;
        this.desc = desc;
        this.violationHandler = violationHandler;
        this.category = category;
        this.subCategory = subCategory;
        this.falseProbability = falseProbability;

        transactionTracker = playerData.getTransactionTracker();
        keepAliveTracker = playerData.getKeepAliveTracker();
        collisionTracker = playerData.getCollisionTracker();
        attributeTracker = playerData.getAttributeTracker();
        positionTracker = playerData.getPositionTracker();
        rotationTracker = playerData.getRotationTracker();
        teleportTracker = playerData.getTeleportTracker();
        velocityTracker = playerData.getVelocityTracker();
        entityTracker = playerData.getEntityTracker();
        actionTracker = playerData.getActionTracker();
        swingTracker = playerData.getSwingTracker();
        predictionTracker = playerData.getPredictionTracker();
    }

    public void handleViolation(AbstractPlayerViolation violation) {
        alertManager.handleViolation(violation);
    }

    public void handleViolation() {
        alertManager.handleViolation(new DetailedPlayerViolation(this, ""));
    }

    public void staffAlert(AbstractPlayerViolation violation) {
        alertManager.staffAlert(violation);
    }

    public double increaseBuffer() {
        return buffer = Math.min(10000, buffer + 1);
    }

    public double increaseBufferBy(double increment) {
        return buffer = Math.min(10000, buffer + increment);
    }

    public void decreaseBuffer() {
        buffer = Math.max(0, buffer - 1);
    }

    public double decreaseBufferBy(final double amount) {
        return buffer = Math.max(0, buffer - amount);
    }

    public void multiplyBuffer(final double multiplier) {
        buffer *= multiplier;
    }


    public void resetBuffer() {
        buffer = 0;
    }

    public boolean isEnabled() {
        return HoodPlugin.INSTANCE.getConfigurationService().isEnabled(this.getClass().getSimpleName());
    }

    public String format(double input) {
        return new DecimalFormat("###.###").format(input);
    }

    public void debug(Object info) {
        if (!playerData.getDebuggers().isEmpty()) {
            for (Map.Entry<UUID, String> entry : playerData.getDebuggers().entrySet()) {
                String stringInfo = ChatColor.AQUA + "(" + playerData.getUser().getName() + ") " + this.getName() + ": " + ChatColor.GRAY + info.toString();

                if (stringInfo.toLowerCase().contains(entry.getValue())) {
                    Player bukkitPlayer = Bukkit.getPlayer(entry.getKey());
                    if (bukkitPlayer != null) {
                        bukkitPlayer.sendMessage(CC.translate(stringInfo));
                    }
                }
            }
        }
    }
}
