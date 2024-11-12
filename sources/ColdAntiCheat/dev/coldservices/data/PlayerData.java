package dev.coldservices.data;

import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.PacketAPI;
import lombok.Getter;
import lombok.Setter;
import dev.coldservices.CAC;
import dev.coldservices.alert.AlertManager;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.CheckManager;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.check.type.PositionCheck;
import dev.coldservices.check.type.RotationCheck;
import dev.coldservices.data.tracker.Tracker;
import dev.coldservices.data.tracker.impl.*;
import dev.coldservices.processor.impl.IncomingPacketProcessor;
import dev.coldservices.processor.impl.OutgoingPacketProcessor;
import dev.coldservices.update.PositionUpdate;
import dev.coldservices.update.RotationUpdate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public final class PlayerData {

    private final Player player;
    private final UUID uuid;

    private final IncomingPacketProcessor incomingPacketProcessor;
    private final OutgoingPacketProcessor outgoingPacketProcessor;

    private final Tracker[] trackers;

    private final PositionTracker positionTracker;
    private final RotationTracker rotationTracker;
    private final ActionTracker actionTracker;
    private final EntityTracker entityTracker;
    private final EmulationTracker emulationTracker;
    private final ConnectionTracker connectionTracker;
    private final AttributeTracker attributeTracker;
    private final VelocityTracker velocityTracker;
    private final GhostBlockTracker ghostBlockTracker;

    private final ExemptTracker exemptTracker;

    private final List<Check> checks;

    private final List<PacketCheck> packetChecks;
    private final List<PositionCheck> positionChecks;
    private final List<RotationCheck> rotationChecks;

    private PositionUpdate positionUpdate;
    private RotationUpdate rotationUpdate;

    private int ticks;

    public PlayerData(final Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();

        this.incomingPacketProcessor = new IncomingPacketProcessor(this);
        this.outgoingPacketProcessor = new OutgoingPacketProcessor(this);

        this.trackers = new Tracker[]{
                this.positionTracker = new PositionTracker(this),
                this.rotationTracker = new RotationTracker(this),
                this.actionTracker = new ActionTracker(this),
                this.connectionTracker = new ConnectionTracker(this),
                this.attributeTracker = new AttributeTracker(this),
                this.velocityTracker = new VelocityTracker(this),

                this.ghostBlockTracker = new GhostBlockTracker(this),
                this.entityTracker = new EntityTracker(this),
                this.emulationTracker = new EmulationTracker(this),
        };
        this.exemptTracker = new ExemptTracker(this);

        this.checks = CAC.get(CheckManager.class).loadChecks(this);

        this.packetChecks = this.checks.stream().filter(check -> check instanceof PacketCheck)
                .map(check -> ((PacketCheck) check)).collect(Collectors.toList());
        this.positionChecks = this.checks.stream().filter(check -> check instanceof PositionCheck)
                .map(check -> ((PositionCheck) check)).collect(Collectors.toList());
        this.rotationChecks = this.checks.stream().filter(check -> check instanceof RotationCheck)
                .map(check -> ((RotationCheck) check)).collect(Collectors.toList());

        if (this.player.hasPermission("coldac.alerts")) CAC.get(AlertManager.class).toggleAlerts(this);
    }

    public boolean blockCheck(Material material) {
        Location location = player.getLocation();
        World world = player.getWorld();

        double y = location.getY();

        for(double posY = y; posY < y + 1; posY--) {
            if(world.getBlockAt(location.getBlockX(), (int) posY, location.getBlockZ()).getType().equals(material)) {
                return true;
            }
        }

        return true;
    }

    public boolean isScaffolding() {
        World world = player.getWorld();

        Location xMinus1 = player.getLocation().add(-1, 0, 0);
        Location zMinus1 = player.getLocation().add(0, 0, -1);

        if(world.getBlockAt(xMinus1).getType() == Material.AIR && world.getBlockAt(zMinus1).getType() == Material.AIR) {
            return  true;
        }

        return false;
    }

    public void terminate() {
        CAC.get(AlertManager.class).getPlayers().remove(this);
    }

    public void updateTicks() {
        ++this.ticks;
    }

    public void haram() {
        this.haram("Timed out");
    }

    public void haram(final String reason) {
        Bukkit.getScheduler().runTask(CAC.get().getPlugin(), () -> this.player.kickPlayer(reason));
    }

    public void send(final GPacket packet) {
        try {
            PacketAPI.sendPacket(this.player, packet);
        } catch (final Throwable ignored) {
            // vodka coding LLC I feel funny
        }
    }
}
