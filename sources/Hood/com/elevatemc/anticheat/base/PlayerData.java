package com.elevatemc.anticheat.base;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.check.CheckData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.base.tracker.impl.*;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.protocol.player.User;
import lombok.Data;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.*;

@Data
public class PlayerData {
    private Map<UUID, String> debuggers = new HashMap<>();
    private final Set<Tracker> trackers = new HashSet<>();
    private final CheckData checkData = new CheckData();

    private TransactionTracker transactionTracker;
    private KeepAliveTracker keepAliveTracker;
    private CollisionTracker collisionTracker;
    private AttributeTracker attributeTracker;
    private PositionTracker positionTracker;
    private RotationTracker rotationTracker;
    private TeleportTracker teleportTracker;
    private VelocityTracker velocityTracker;
    private EntityTracker entityTracker;
    private ActionTracker actionTracker;
    private WorldTracker worldTracker;
    private SwingTracker swingTracker;
    private PredictionTracker predictionTracker;
    private int entityId = -1;

    private boolean banning, exempt;
    private String reason;

    private int ticksExisted;

    private ClientVersion clientVersion;

    public UUID uuid;
    private User user;
    private Player player;

    public long lastJoin = System.currentTimeMillis();
    private String clientBrand = null;

    public PlayerData(User user) {
        this.uuid = user.getUUID();
        this.user = user;

        clientVersion = user.getClientVersion();

        collisionTracker = new CollisionTracker(this);
        positionTracker = new PositionTracker(this);
        rotationTracker = new RotationTracker(this);
        teleportTracker = new TeleportTracker(this);
        worldTracker = new WorldTracker(this);
        transactionTracker = new TransactionTracker(this);
        keepAliveTracker = new KeepAliveTracker(this);
        entityTracker = new EntityTracker(this);
        attributeTracker = new AttributeTracker(this);
        velocityTracker = new VelocityTracker(this);
        actionTracker = new ActionTracker(this);
        swingTracker = new SwingTracker(this);
        predictionTracker = new PredictionTracker(this);
        trackers.add(collisionTracker);
        trackers.add(positionTracker);
        trackers.add(rotationTracker);
        trackers.add(teleportTracker);
        trackers.add(worldTracker);
        trackers.add(transactionTracker);
        trackers.add(keepAliveTracker);
        trackers.add(entityTracker);
        trackers.add(attributeTracker);
        trackers.add(velocityTracker);
        trackers.add(actionTracker);
        trackers.add(swingTracker);
        trackers.add(predictionTracker);
        checkData.enable(this);
    }

    public int getEntityId() {
        if (entityId != -1)
            return entityId;

        try {
            entityId = getPlayer().getEntityId();
        } catch (NullPointerException exception) {
            // The player hasn't been loaded yet, they just joined, shouldn't be an issue
            exception.fillInStackTrace();
        }

        return entityId;
    }

    public void initialize(Player player) {
        this.player = player;
    }

    public int getProtocolVersion() {
        return user.getClientVersion().getProtocolVersion();
    }

    public String clientVersion() {
        return clientVersion.toString().replaceAll("V_", "").replaceAll("_", ".");
    }

    public void handlePreFlush() {
        entityTracker.preFlush();
    }

    public void handleTickStart() {
        entityTracker.onTickStart();
    }

}