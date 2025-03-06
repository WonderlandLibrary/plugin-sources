package me.frep.vulcan.spigot.data.processor;

import org.bukkit.potion.PotionEffectType;
import io.github.retrooper.packetevents.utils.vector.Vector3f;
import io.github.retrooper.packetevents.packetwrappers.play.out.explosion.WrappedPacketOutExplosion;
import io.github.retrooper.packetevents.packetwrappers.play.out.entitymetadata.WrappedWatchableObject;
import io.github.retrooper.packetevents.packetwrappers.play.out.entitymetadata.WrappedPacketOutEntityMetadata;
import org.bukkit.event.block.BlockPlaceEvent;
import io.github.retrooper.packetevents.packetwrappers.play.out.abilities.WrappedPacketOutAbilities;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.bukkit.enchantments.Enchantment;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import org.bukkit.event.entity.EntityDamageEvent;
import io.github.retrooper.packetevents.packetwrappers.play.in.pong.WrappedPacketInPong;
import io.github.retrooper.packetevents.utils.vector.Vector3d;
import io.github.retrooper.packetevents.packetwrappers.play.in.transaction.WrappedPacketInTransaction;
import me.frep.vulcan.spigot.util.MathUtil;
import io.github.retrooper.packetevents.packetwrappers.play.in.clientcommand.WrappedPacketInClientCommand;
import me.frep.vulcan.spigot.util.ServerUtil;
import org.bukkit.entity.EntityType;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;
import me.frep.vulcan.spigot.Vulcan;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;
import java.util.Iterator;
import java.util.List;
import io.github.retrooper.packetevents.utils.attributesnapshot.AttributeSnapshotWrapper;
import io.github.retrooper.packetevents.packetwrappers.play.out.updateattributes.WrappedPacketOutUpdateAttributes;
import java.util.HashMap;
import org.bukkit.util.Vector;
import me.frep.vulcan.spigot.util.type.EvictingList;
import org.bukkit.inventory.ItemStack;
import io.github.retrooper.packetevents.packetwrappers.play.out.position.WrappedPacketOutPosition;
import io.github.retrooper.packetevents.packetwrappers.play.out.gamestatechange.WrappedPacketOutGameStateChange;
import io.github.retrooper.packetevents.packetwrappers.play.out.removeentityeffect.WrappedPacketOutRemoveEntityEffect;
import io.github.retrooper.packetevents.packetwrappers.play.out.entityeffect.WrappedPacketOutEntityEffect;
import java.util.Map;
import org.bukkit.GameMode;
import me.frep.vulcan.spigot.data.PlayerData;

public class ActionProcessor
{
    private final PlayerData data;
    private boolean sprinting;
    private boolean sneaking;
    private boolean sendingAction;
    private boolean placing;
    private boolean digging;
    private boolean blocking;
    private boolean inventory;
    private boolean sendingDig;
    private boolean teleporting;
    private boolean hasSpeed;
    private boolean hasJumpBoost;
    private boolean hasLevitation;
    private boolean hasSlowFalling;
    private boolean hasDolphinsGrace;
    private boolean sitting;
    private boolean hasConduitsPower;
    private boolean crawling;
    private boolean hasSlowness;
    private boolean wearingDepthStrider;
    private boolean wearingElytra;
    private boolean bukkitGliding;
    private boolean packetGliding;
    private boolean packetSwimming;
    private boolean updateSwim;
    private double genericMovementSpeed;
    private boolean berserking;
    private int lastBukkitDiggingTick;
    private int lastDropItemTick;
    private int lastDiggingTick;
    private int sinceSprintingTicks;
    private int positionTicksExisted;
    private int lastWindowClick;
    private int amount;
    private int lastAmount;
    private int slot;
    private int lastSlot;
    private int sinceTeleportTicks;
    private int sinceExplosionTicks;
    private int lastBedEnter;
    private int lastBedLeave;
    private int lastItemPickup;
    private int lastCancelledBreak;
    private int lastChorusFruitTeleport;
    private int lastDamage;
    private int sinceDragonDamageTicks;
    private int sprintingTicks;
    private int lastRespawn;
    private int lastLowHealth;
    private int lastProjectileThrow;
    private int lastFishEvent;
    private int sinceFireDamageTicks;
    private int sinceAttackDamageTicks;
    private int sinceFallDamageTicks;
    private int sinceMagicDamageTicks;
    private int sincePoisonDamageTicks;
    private int sinceContactDamageTicks;
    private int sinceProjectileDamageTicks;
    private int lastLavaDamage;
    private int sinceLavaDamageTicks;
    private int sinceExplosionDamageTicks;
    private int sinceBucketEmptyTicks;
    private int sinceBlockPlaceTicks;
    private int sinceClimbablePlaceTicks;
    private int sinceSlimePlaceTicks;
    private int sinceCancelledPlaceTicks;
    private int sinceWorldChangeTicks;
    private int sinceRessurectTicks;
    private int sinceBukkitTeleportTicks;
    private int sinceDeathTicks;
    private int sinceNonFallDamageTicks;
    private int sinceIronGolemDamageTicks;
    private int lastScaffoldPlaceX;
    private int lastScaffoldPlaceY;
    private int lastScaffoldPlaceZ;
    private int sinceBerserkTicks;
    private int sinceCrackshotDamageTicks;
    private int sinceGlassBottleFillTicks;
    private int sinceFireballDamageTicks;
    private int speedAmplifier;
    private int jumpBoostAmplifier;
    private int levitationAmplifier;
    private int slowFallingAmplifier;
    private int dolphinsGraceAmplifier;
    private int sinceChorusFruitTeleportTicks;
    private int sinceWebPlaceTicks;
    private int sinceEnderPearlTicks;
    private int sinceBukkitVelocityTicks;
    private int sinceDamageTicks;
    private int sinceStartGlidingTicks;
    private int sinceIcePlaceTicks;
    private int sinceBlockBreakTicks;
    private int ticksExisted;
    private int sinceHoglinDamageTicks;
    private int sinceAbilitiesTicks;
    private int sincePushTicks;
    private int sinceMythicMobTicks;
    private int sinceCancelledMoveTicks;
    private int sinceRavagerDamageTicks;
    private int sinceWitherDamageTicks;
    private int sinceCrystalDamageTicks;
    private int sinceSuffociationDamageTicks;
    private int sinceStupidBucketEmptyTicks;
    private int sinceNetherrackBreakTicks;
    private int sinceBowBoostTicks;
    private double lastAbortX;
    private double lastAbortZ;
    private double distanceFromLastAbort;
    private double lastBreakX;
    private double lastBreakY;
    private double lastBreakZ;
    private double distanceFromLastBreak;
    private double health;
    private double distanceFromLastScaffoldPlace;
    private double explosionX;
    private double explosionY;
    private double explosionZ;
    private double lastTeleportX;
    private double lastTeleportY;
    private double lastTeleportZ;
    private double distanceFromLastTeleport;
    private double lastGlidingDeltaXZ;
    private long lastStartDestroy;
    private long lastStopDestroy;
    private long lastAbort;
    private GameMode gameMode;
    private short positionTransactionId;
    private short entityEffectTransactionId;
    private short removeEntityEffectTransactionId;
    private short gameStateChangeTransactionId;
    private short positionPingId;
    private short entityEffectPingId;
    private short removeEntityEffectPingId;
    private short gameStateChangePingId;
    private final Map<Short, WrappedPacketOutEntityEffect> queuedEntityEffects;
    private final Map<Short, WrappedPacketOutRemoveEntityEffect> queuedRemoveEntityEffects;
    private final Map<Short, WrappedPacketOutGameStateChange> queuedGameStateChanges;
    private final Map<Short, WrappedPacketOutPosition> queuedTeleports;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack itemInMainHand;
    private ItemStack itemInOffHand;
    private static final String GENERIC_MOVEMENT_SPEED;
    final EvictingList<Vector> teleports;
    
    public ActionProcessor(final PlayerData data) {
        this.genericMovementSpeed = 0.0;
        this.positionTicksExisted = 0;
        this.sinceTeleportTicks = 100;
        this.sinceExplosionTicks = 1000;
        this.sinceDragonDamageTicks = 100;
        this.sinceFireDamageTicks = 100;
        this.sinceAttackDamageTicks = 100;
        this.sinceFallDamageTicks = 100;
        this.sinceExplosionDamageTicks = 100;
        this.sinceBucketEmptyTicks = 100;
        this.sinceBlockPlaceTicks = 100;
        this.sinceClimbablePlaceTicks = 100;
        this.sinceSlimePlaceTicks = 150;
        this.sinceCancelledPlaceTicks = 100;
        this.sinceWorldChangeTicks = 100;
        this.sinceBukkitTeleportTicks = 100;
        this.sinceDeathTicks = 100;
        this.sinceNonFallDamageTicks = 100;
        this.sinceGlassBottleFillTicks = 100;
        this.sinceFireballDamageTicks = 100;
        this.speedAmplifier = 0;
        this.jumpBoostAmplifier = 0;
        this.levitationAmplifier = 0;
        this.slowFallingAmplifier = 0;
        this.dolphinsGraceAmplifier = 0;
        this.sinceChorusFruitTeleportTicks = 100;
        this.sinceWebPlaceTicks = 100;
        this.sinceEnderPearlTicks = 100;
        this.sinceBukkitVelocityTicks = 100;
        this.sinceDamageTicks = 100;
        this.sinceStartGlidingTicks = 100;
        this.sinceIcePlaceTicks = 100;
        this.sinceBlockBreakTicks = 100;
        this.ticksExisted = 0;
        this.sinceAbilitiesTicks = 0;
        this.sincePushTicks = 100;
        this.sinceMythicMobTicks = 500;
        this.sinceCancelledMoveTicks = 1000;
        this.sinceRavagerDamageTicks = 1000;
        this.sinceWitherDamageTicks = 100;
        this.sinceCrystalDamageTicks = 100;
        this.sinceSuffociationDamageTicks = 100;
        this.sinceStupidBucketEmptyTicks = 100;
        this.sinceNetherrackBreakTicks = 100;
        this.sinceBowBoostTicks = 100;
        this.gameMode = GameMode.SURVIVAL;
        this.positionTransactionId = -29768;
        this.entityEffectTransactionId = -28768;
        this.removeEntityEffectTransactionId = -27768;
        this.gameStateChangeTransactionId = -26768;
        this.positionPingId = -29768;
        this.entityEffectPingId = -28768;
        this.removeEntityEffectPingId = -27768;
        this.gameStateChangePingId = -26768;
        this.queuedEntityEffects = new HashMap<Short, WrappedPacketOutEntityEffect>();
        this.queuedRemoveEntityEffects = new HashMap<Short, WrappedPacketOutRemoveEntityEffect>();
        this.queuedGameStateChanges = new HashMap<Short, WrappedPacketOutGameStateChange>();
        this.queuedTeleports = new HashMap<Short, WrappedPacketOutPosition>();
        this.teleports = new EvictingList<Vector>(15);
        this.data = data;
    }
    
    public void handleUpdateAttributes(final WrappedPacketOutUpdateAttributes wrapper) {
        final List<AttributeSnapshotWrapper> attributes = wrapper.getProperties();
        for (final AttributeSnapshotWrapper attribute : attributes) {
            if (attribute.getKey().equals(ActionProcessor.GENERIC_MOVEMENT_SPEED)) {
                this.genericMovementSpeed = attribute.getValue();
            }
        }
    }
    
    public void handleEntityAction(final WrappedPacketInEntityAction wrapper) {
        this.sendingAction = true;
        switch (wrapper.getAction()) {
            case START_SPRINTING: {
                this.sprinting = true;
                break;
            }
            case STOP_SPRINTING: {
                this.sprinting = false;
                break;
            }
            case START_SNEAKING: {
                this.sneaking = true;
                break;
            }
            case STOP_SNEAKING: {
                this.sneaking = false;
            }
            case START_FALL_FLYING: {
                this.sinceStartGlidingTicks = 0;
                break;
            }
        }
    }
    
    public void handleItemPickup() {
        this.lastItemPickup = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleBukkitDig() {
        this.lastBukkitDiggingTick = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleBlockDig(final WrappedPacketInBlockDig wrapper) {
        this.sendingDig = true;
        switch (wrapper.getDigType()) {
            case START_DESTROY_BLOCK: {
                this.digging = true;
                this.lastStartDestroy = System.currentTimeMillis();
                break;
            }
            case STOP_DESTROY_BLOCK: {
                this.handleBlockBreak(wrapper.getBlockPosition().getX(), wrapper.getBlockPosition().getY(), wrapper.getBlockPosition().getZ());
                this.lastStopDestroy = System.currentTimeMillis();
                this.digging = false;
                break;
            }
            case ABORT_DESTROY_BLOCK: {
                this.lastAbort = System.currentTimeMillis();
                this.digging = false;
                break;
            }
            case RELEASE_USE_ITEM: {
                this.blocking = true;
                break;
            }
            case DROP_ITEM:
            case DROP_ALL_ITEMS: {
                this.lastDropItemTick = Vulcan.INSTANCE.getTickManager().getTicks();
                break;
            }
        }
        if (wrapper.getDigType() == WrappedPacketInBlockDig.PlayerDigType.ABORT_DESTROY_BLOCK) {
            this.lastAbortX = wrapper.getBlockPosition().getX();
            this.lastAbortZ = wrapper.getBlockPosition().getZ();
        }
    }
    
    public void handleBukkitAttackDamage(final EntityType entityType) {
        switch (entityType) {
            case ENDER_DRAGON: {
                this.sinceDragonDamageTicks = 0;
                break;
            }
            case IRON_GOLEM: {
                this.sinceIronGolemDamageTicks = 0;
                break;
            }
            case FIREBALL:
            case SMALL_FIREBALL:
            case DRAGON_FIREBALL: {
                this.sinceFireballDamageTicks = 0;
                break;
            }
            case WITHER:
            case WITHER_SKULL: {
                this.sinceWitherDamageTicks = 0;
                break;
            }
            case ENDER_CRYSTAL: {
                this.sinceCrystalDamageTicks = 0;
                break;
            }
        }
        if (ServerUtil.isHigherThan1_16() && entityType == EntityType.HOGLIN) {
            this.sinceHoglinDamageTicks = 0;
        }
        if (ServerUtil.isHigherThan1_14() && entityType == EntityType.RAVAGER) {
            this.sinceRavagerDamageTicks = 0;
        }
    }
    
    public void handleChorusTeleport() {
        this.lastChorusFruitTeleport = Vulcan.INSTANCE.getTickManager().getTicks();
        this.sinceChorusFruitTeleportTicks = 0;
    }
    
    public void handleCancelledBreak() {
        this.lastCancelledBreak = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleClientCommand(final WrappedPacketInClientCommand wrapper) {
        switch (wrapper.getClientCommand()) {
            case OPEN_INVENTORY_ACHIEVEMENT: {
                this.inventory = true;
                break;
            }
            case PERFORM_RESPAWN: {
                this.lastRespawn = Vulcan.INSTANCE.getTickManager().getTicks();
                break;
            }
        }
    }
    
    public void handleBlockPlace() {
        this.placing = true;
        if (ServerUtil.isHigherThan1_9() && (this.data.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("FIREWORK") || this.data.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("FIREWORK"))) {
            this.data.getPositionProcessor().setSinceFireworkTicks(0);
        }
    }
    
    public void handleCloseWindow() {
        this.inventory = false;
    }
    
    public void handleWindowClick() {
        this.lastWindowClick = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleArmAnimation() {
        if (this.digging) {
            this.lastDiggingTick = Vulcan.INSTANCE.getTickManager().getTicks();
        }
        else if (this.lastAbortX != 0.0 && this.lastAbortZ != 0.0) {
            final double locationX = this.data.getPositionProcessor().getX();
            final double locationZ = this.data.getPositionProcessor().getZ();
            this.distanceFromLastAbort = MathUtil.hypot(Math.abs(locationX - this.lastAbortX), Math.abs(locationZ - this.lastAbortZ));
            if (this.distanceFromLastAbort > 12.0) {
                final double lastAbortX = 0.0;
                this.distanceFromLastAbort = lastAbortX;
                this.lastAbortZ = lastAbortX;
                this.lastAbortX = lastAbortX;
            }
        }
    }
    
    public void handleBukkitPlace() {
        this.sinceBlockPlaceTicks = 0;
        this.handleCancelledBlockPlace();
    }
    
    private void handleCancelledBlockPlace() {
        this.amount = this.data.getPlayer().getItemInHand().getAmount();
        this.slot = this.data.getPlayer().getInventory().getHeldItemSlot();
        if (this.amount == this.lastAmount && this.slot == this.lastSlot) {
            this.sinceCancelledPlaceTicks = 0;
        }
        this.lastSlot = this.slot;
        this.lastAmount = this.amount;
    }
    
    public void handleBlockBreak(final double x, final double y, final double z) {
        this.sinceBlockBreakTicks = 0;
        this.lastBreakX = x;
        this.lastBreakY = y;
        this.lastBreakZ = z;
    }
    
    public void handleTeleport() {
        this.sinceBukkitTeleportTicks = 0;
    }
    
    public void handleServerPosition(final WrappedPacketOutPosition wrapper) {
        ++this.positionTransactionId;
        ++this.positionPingId;
        if (this.positionTransactionId > -28769) {
            this.positionTransactionId = -29768;
        }
        if (this.positionPingId > -28769) {
            this.positionPingId = -29768;
        }
        this.sinceTeleportTicks = 0;
        final Vector teleport = new Vector(wrapper.getPosition().getX(), wrapper.getPosition().getY(), wrapper.getPosition().getZ());
        this.teleports.add(teleport);
        if (ServerUtil.isHigherThan1_17()) {
            this.data.sendPing(this.positionPingId);
        }
        else {
            this.data.sendTransaction(this.positionTransactionId);
        }
        this.queuedTeleports.put(this.positionTransactionId, wrapper);
    }
    
    public void handleEntityEffect(final WrappedPacketOutEntityEffect wrapper) {
        ++this.entityEffectTransactionId;
        ++this.entityEffectPingId;
        if (this.entityEffectTransactionId > -27769) {
            this.entityEffectTransactionId = -28768;
        }
        if (this.entityEffectPingId > -27769) {
            this.entityEffectPingId = -28768;
        }
        if (ServerUtil.isHigherThan1_17()) {
            this.data.sendPing(this.entityEffectPingId);
        }
        else {
            this.data.sendTransaction(this.entityEffectTransactionId);
        }
        this.queuedEntityEffects.put(this.entityEffectTransactionId, wrapper);
    }
    
    public void handleRemoveEntityEffect(final WrappedPacketOutRemoveEntityEffect wrapper) {
        ++this.removeEntityEffectTransactionId;
        ++this.removeEntityEffectPingId;
        if (this.removeEntityEffectTransactionId > -26769) {
            this.removeEntityEffectTransactionId = -27768;
        }
        if (this.removeEntityEffectPingId > -26769) {
            this.removeEntityEffectPingId = -27768;
        }
        if (ServerUtil.isHigherThan1_17()) {
            this.data.sendPing(this.removeEntityEffectPingId);
        }
        else {
            this.data.sendTransaction(this.removeEntityEffectTransactionId);
        }
        this.queuedRemoveEntityEffects.put(this.removeEntityEffectTransactionId, wrapper);
    }
    
    public void handleGameStateChange(final WrappedPacketOutGameStateChange wrapper) {
        ++this.gameStateChangeTransactionId;
        ++this.gameStateChangePingId;
        if (this.gameStateChangeTransactionId > -25769) {
            this.gameStateChangeTransactionId = -26768;
        }
        if (this.gameStateChangePingId > -25769) {
            this.gameStateChangePingId = -26768;
        }
        if (ServerUtil.isHigherThan1_17()) {
            this.data.sendPing(this.gameStateChangePingId);
        }
        else {
            this.data.sendTransaction(this.gameStateChangeTransactionId);
        }
        this.queuedGameStateChanges.put(this.gameStateChangeTransactionId, wrapper);
    }
    
    private void confirmPosition(final WrappedPacketInTransaction wrapper) {
        if (this.queuedTeleports.containsKey(wrapper.getActionNumber())) {
            final WrappedPacketOutPosition position = this.queuedTeleports.get(wrapper.getActionNumber());
            final Vector3d vector = position.getPosition();
            this.lastTeleportX = vector.getX();
            this.lastTeleportY = vector.getY();
            this.lastTeleportZ = vector.getZ();
            this.sinceTeleportTicks = 0;
            this.queuedTeleports.remove(wrapper.getActionNumber());
        }
    }
    
    public void confirmPositionPing(final WrappedPacketInPong wrapper) {
        final short id = (short)wrapper.getId();
        if (this.queuedTeleports.containsKey(id)) {
            final WrappedPacketOutPosition position = this.queuedTeleports.get(id);
            final Vector3d vector = position.getPosition();
            this.lastTeleportX = vector.getX();
            this.lastTeleportY = vector.getY();
            this.lastTeleportZ = vector.getZ();
            this.sinceTeleportTicks = 0;
            this.queuedTeleports.remove(id);
        }
    }
    
    private void confirmEntityEffect(final WrappedPacketInTransaction wrapper) {
        if (this.queuedEntityEffects.containsKey(wrapper.getActionNumber())) {
            final WrappedPacketOutEntityEffect confirmation = this.queuedEntityEffects.get(wrapper.getActionNumber());
            if (confirmation == null) {
                return;
            }
            final int amplifier = confirmation.getAmplifier() + 1;
            switch (confirmation.getEffectId()) {
                case 1: {
                    this.speedAmplifier = amplifier;
                    this.hasSpeed = true;
                    break;
                }
                case 2: {
                    this.hasSlowness = true;
                    break;
                }
                case 8: {
                    this.jumpBoostAmplifier = amplifier;
                    this.hasJumpBoost = true;
                    break;
                }
                case 25: {
                    this.levitationAmplifier = amplifier;
                    this.hasLevitation = true;
                    break;
                }
                case 28: {
                    this.slowFallingAmplifier = amplifier;
                    this.hasSlowFalling = true;
                    break;
                }
                case 29: {
                    this.hasConduitsPower = true;
                    break;
                }
                case 30: {
                    this.dolphinsGraceAmplifier = amplifier;
                    this.hasDolphinsGrace = true;
                    break;
                }
            }
            this.queuedEntityEffects.remove(wrapper.getActionNumber());
        }
    }
    
    private void confirmEntityEffectPing(final WrappedPacketInPong wrapper) {
        final short id = (short)wrapper.getId();
        if (this.queuedEntityEffects.containsKey(id)) {
            final WrappedPacketOutEntityEffect confirmation = this.queuedEntityEffects.get(id);
            final int amplifier = confirmation.getAmplifier() + 1;
            switch (confirmation.getEffectId()) {
                case 1: {
                    this.speedAmplifier = amplifier;
                    this.hasSpeed = true;
                    break;
                }
                case 8: {
                    this.jumpBoostAmplifier = amplifier;
                    this.hasJumpBoost = true;
                    break;
                }
                case 25: {
                    this.levitationAmplifier = amplifier;
                    this.hasLevitation = true;
                    break;
                }
                case 28: {
                    this.slowFallingAmplifier = amplifier;
                    this.hasSlowFalling = true;
                    break;
                }
                case 29: {
                    this.hasConduitsPower = true;
                    break;
                }
                case 30: {
                    this.dolphinsGraceAmplifier = amplifier;
                    this.hasDolphinsGrace = true;
                    break;
                }
            }
            this.queuedEntityEffects.remove(id);
        }
    }
    
    private void confirmRemoveEntityEffectPing(final WrappedPacketInPong wrapper) {
        final short id = (short)wrapper.getId();
        if (this.queuedRemoveEntityEffects.containsKey(id)) {
            final WrappedPacketOutRemoveEntityEffect confirmation = this.queuedRemoveEntityEffects.get(id);
            if (confirmation == null) {
                return;
            }
            switch (confirmation.getEffectId()) {
                case 1: {
                    this.speedAmplifier = 0;
                    this.hasSpeed = false;
                    this.data.getPositionProcessor().setSinceSpeedTicks(0);
                    break;
                }
                case 2: {
                    this.hasSlowness = false;
                    break;
                }
                case 8: {
                    this.jumpBoostAmplifier = 0;
                    this.hasJumpBoost = false;
                    break;
                }
                case 25: {
                    this.levitationAmplifier = 0;
                    this.hasLevitation = false;
                    break;
                }
                case 28: {
                    this.slowFallingAmplifier = 0;
                    this.hasSlowFalling = false;
                    break;
                }
                case 29: {
                    this.hasConduitsPower = false;
                    break;
                }
                case 30: {
                    this.dolphinsGraceAmplifier = 0;
                    this.hasDolphinsGrace = false;
                    break;
                }
            }
            this.queuedRemoveEntityEffects.remove(id);
        }
    }
    
    private void confirmRemoveEntityEffect(final WrappedPacketInTransaction wrapper) {
        if (this.queuedRemoveEntityEffects.containsKey(wrapper.getActionNumber())) {
            final WrappedPacketOutRemoveEntityEffect confirmation = this.queuedRemoveEntityEffects.get(wrapper.getActionNumber());
            if (confirmation == null) {
                return;
            }
            switch (confirmation.getEffectId()) {
                case 1: {
                    this.speedAmplifier = 0;
                    this.hasSpeed = false;
                    this.data.getPositionProcessor().setSinceSpeedTicks(0);
                    break;
                }
                case 8: {
                    this.jumpBoostAmplifier = 0;
                    this.hasJumpBoost = false;
                    break;
                }
                case 25: {
                    this.levitationAmplifier = 0;
                    this.hasLevitation = false;
                    break;
                }
                case 28: {
                    this.slowFallingAmplifier = 0;
                    this.hasSlowFalling = false;
                    break;
                }
                case 29: {
                    this.hasConduitsPower = false;
                    break;
                }
                case 30: {
                    this.dolphinsGraceAmplifier = 0;
                    this.hasDolphinsGrace = false;
                    break;
                }
            }
            this.queuedRemoveEntityEffects.remove(wrapper.getActionNumber());
        }
    }
    
    private void confirmGameStateChangePing(final WrappedPacketInPong wrapper) {
        final short id = (short)wrapper.getId();
        if (this.queuedGameStateChanges.containsKey(id)) {
            final WrappedPacketOutGameStateChange confirmation = this.queuedGameStateChanges.get(id);
            if (confirmation == null) {
                return;
            }
            if (confirmation.getReason() == 3) {
                switch ((int)confirmation.getValue()) {
                    case 0: {
                        this.gameMode = GameMode.SURVIVAL;
                        break;
                    }
                    case 1: {
                        this.gameMode = GameMode.CREATIVE;
                        break;
                    }
                    case 2: {
                        this.gameMode = GameMode.ADVENTURE;
                        break;
                    }
                    case 3: {
                        if (ServerUtil.isHigherThan1_7()) {
                            this.gameMode = GameMode.SPECTATOR;
                            break;
                        }
                        break;
                    }
                }
            }
            this.queuedGameStateChanges.remove(id);
        }
    }
    
    private void confirmGameStateChange(final WrappedPacketInTransaction wrapper) {
        if (this.queuedGameStateChanges.containsKey(wrapper.getActionNumber())) {
            final WrappedPacketOutGameStateChange confirmation = this.queuedGameStateChanges.get(wrapper.getActionNumber());
            if (confirmation == null) {
                return;
            }
            if (confirmation.getReason() == 3) {
                switch ((int)confirmation.getValue()) {
                    case 0: {
                        this.gameMode = GameMode.SURVIVAL;
                        break;
                    }
                    case 1: {
                        this.gameMode = GameMode.CREATIVE;
                        break;
                    }
                    case 2: {
                        this.gameMode = GameMode.ADVENTURE;
                        break;
                    }
                    case 3: {
                        if (ServerUtil.isHigherThan1_7()) {
                            this.gameMode = GameMode.SPECTATOR;
                            break;
                        }
                        break;
                    }
                }
            }
            this.queuedGameStateChanges.remove(wrapper.getActionNumber());
        }
    }
    
    public void handlePong(final WrappedPacketInPong wrapper) {
        this.confirmPositionPing(wrapper);
        this.confirmEntityEffectPing(wrapper);
        this.confirmGameStateChangePing(wrapper);
        this.confirmRemoveEntityEffectPing(wrapper);
    }
    
    public void handleTransaction(final WrappedPacketInTransaction wrapper) {
        this.confirmPosition(wrapper);
        this.confirmEntityEffect(wrapper);
        this.confirmGameStateChange(wrapper);
        this.confirmRemoveEntityEffect(wrapper);
    }
    
    public void handleWorldChange() {
        this.sinceWorldChangeTicks = 0;
        this.data.getPositionProcessor().setServerAirTicks(0);
    }
    
    public void handleDeath() {
        this.sinceDeathTicks = 0;
    }
    
    public void handleDamage(final EntityDamageEvent.DamageCause cause) {
        this.lastDamage = Vulcan.INSTANCE.getTickManager().getTicks();
        this.sinceDamageTicks = 0;
        if (cause != EntityDamageEvent.DamageCause.FALL && cause != EntityDamageEvent.DamageCause.SUFFOCATION) {
            this.sinceNonFallDamageTicks = 0;
        }
        switch (cause) {
            case SUFFOCATION: {
                this.sinceSuffociationDamageTicks = 0;
                break;
            }
            case ENTITY_EXPLOSION:
            case BLOCK_EXPLOSION: {
                this.sinceExplosionDamageTicks = 0;
                break;
            }
            case ENTITY_ATTACK: {
                this.sinceAttackDamageTicks = 0;
                break;
            }
            case FIRE:
            case FIRE_TICK: {
                this.sinceFireDamageTicks = 0;
                break;
            }
            case FALL: {
                this.sinceFallDamageTicks = 0;
                break;
            }
            case MAGIC: {
                this.sinceMagicDamageTicks = 0;
                break;
            }
            case POISON: {
                this.sincePoisonDamageTicks = 0;
                break;
            }
            case CONTACT: {
                this.sinceContactDamageTicks = 0;
                break;
            }
            case PROJECTILE: {
                this.sinceProjectileDamageTicks = 0;
                break;
            }
            case LAVA: {
                this.sinceLavaDamageTicks = 0;
                break;
            }
        }
    }
    
    public void handleEnderPearl() {
        this.sinceEnderPearlTicks = 0;
    }
    
    public void handleBedEnter() {
        this.lastBedEnter = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleBedLeave() {
        this.lastBedLeave = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleFlying(final WrappedPacketInFlying wrapper) {
        this.blocking = false;
        this.sendingDig = false;
        this.sendingAction = false;
        this.placing = false;
        ++this.ticksExisted;
        ++this.sinceFireDamageTicks;
        ++this.sinceMagicDamageTicks;
        ++this.sinceFallDamageTicks;
        ++this.sinceExplosionTicks;
        ++this.sinceAttackDamageTicks;
        ++this.sinceNonFallDamageTicks;
        ++this.sinceContactDamageTicks;
        ++this.sinceWitherDamageTicks;
        ++this.sincePoisonDamageTicks;
        ++this.sinceSuffociationDamageTicks;
        ++this.sincePushTicks;
        ++this.sinceProjectileDamageTicks;
        ++this.sinceLavaDamageTicks;
        ++this.sinceDragonDamageTicks;
        ++this.sinceHoglinDamageTicks;
        ++this.sinceStupidBucketEmptyTicks;
        ++this.sinceExplosionDamageTicks;
        ++this.sinceMythicMobTicks;
        ++this.sinceBucketEmptyTicks;
        ++this.sinceBlockPlaceTicks;
        ++this.sinceIronGolemDamageTicks;
        ++this.sinceGlassBottleFillTicks;
        ++this.sinceClimbablePlaceTicks;
        ++this.sinceSlimePlaceTicks;
        ++this.sinceCrystalDamageTicks;
        ++this.sinceWorldChangeTicks;
        ++this.sinceRessurectTicks;
        ++this.sinceBukkitTeleportTicks;
        ++this.sinceDeathTicks;
        ++this.sinceBerserkTicks;
        ++this.sinceCrackshotDamageTicks;
        ++this.sinceFireballDamageTicks;
        ++this.sinceWebPlaceTicks;
        ++this.sinceBukkitVelocityTicks;
        ++this.sinceChorusFruitTeleportTicks;
        ++this.sinceBowBoostTicks;
        ++this.sinceEnderPearlTicks;
        ++this.sinceCancelledPlaceTicks;
        ++this.sinceAbilitiesTicks;
        ++this.sinceDamageTicks;
        ++this.sinceStartGlidingTicks;
        ++this.sinceIcePlaceTicks;
        ++this.sinceBlockBreakTicks;
        ++this.sinceRavagerDamageTicks;
        ++this.sinceNetherrackBreakTicks;
        ++this.sinceTeleportTicks;
        this.health = this.data.getPlayer().getHealth();
        if (wrapper.isMoving()) {
            ++this.positionTicksExisted;
            this.helmet = this.data.getPlayer().getInventory().getHelmet();
            this.chestplate = this.data.getPlayer().getInventory().getChestplate();
            this.leggings = this.data.getPlayer().getInventory().getLeggings();
            this.boots = this.data.getPlayer().getInventory().getBoots();
            this.itemInMainHand = this.data.getPlayer().getInventory().getItemInHand();
            if (ServerUtil.isHigherThan1_9()) {
                this.itemInOffHand = this.data.getPlayer().getInventory().getItemInOffHand();
            }
            if (ServerUtil.isHigherThan1_13()) {
                if (this.helmet != null && this.helmet.hasItemMeta() && this.helmet.getItemMeta() != null && this.helmet.getItemMeta().hasAttributeModifiers()) {
                    this.data.getPositionProcessor().setSinceAttributeModifierTicks(0);
                }
                if (this.chestplate != null && this.chestplate.hasItemMeta() && this.chestplate.getItemMeta() != null && this.chestplate.getItemMeta().hasAttributeModifiers()) {
                    this.data.getPositionProcessor().setSinceAttributeModifierTicks(0);
                }
                if (this.leggings != null && this.leggings.hasItemMeta() && this.leggings.getItemMeta() != null && this.leggings.getItemMeta().hasAttributeModifiers()) {
                    this.data.getPositionProcessor().setSinceAttributeModifierTicks(0);
                }
                if (this.boots != null && this.boots.hasItemMeta() && this.boots.getItemMeta() != null && this.boots.getItemMeta().hasAttributeModifiers()) {
                    this.data.getPositionProcessor().setSinceAttributeModifierTicks(0);
                }
                if (this.itemInOffHand != null && this.itemInOffHand.hasItemMeta() && this.itemInOffHand.getItemMeta() != null && this.itemInOffHand.getItemMeta().hasAttributeModifiers()) {
                    this.data.getPositionProcessor().setSinceAttributeModifierTicks(0);
                }
                if (this.itemInMainHand != null && this.itemInMainHand.hasItemMeta() && this.itemInMainHand.getItemMeta() != null && this.itemInMainHand.getItemMeta().hasAttributeModifiers()) {
                    this.data.getPositionProcessor().setSinceAttributeModifierTicks(0);
                }
            }
            this.wearingDepthStrider = (ServerUtil.isHigherThan1_7() && this.boots != null && this.boots.containsEnchantment(Enchantment.DEPTH_STRIDER));
            this.wearingElytra = (ServerUtil.isHigherThan1_9() && this.chestplate != null && this.chestplate.getType().toString().equals("ELYTRA"));
            this.bukkitGliding = PlayerUtil.isGliding(this.data.getPlayer());
            if (this.bukkitGliding) {
                this.lastGlidingDeltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            }
            this.teleporting = false;
            final double x = wrapper.getX();
            final double y = wrapper.getY();
            final double z = wrapper.getZ();
            if (this.sprinting) {
                ++this.sprintingTicks;
            }
            else {
                this.sprintingTicks = 0;
            }
            if (!this.sprinting) {
                ++this.sinceSprintingTicks;
            }
            else {
                this.sinceSprintingTicks = 0;
            }
            if (this.lastBreakX != 0.0 && this.lastBreakZ != 0.0) {
                this.distanceFromLastBreak = MathUtil.magnitude(Math.abs(x - this.lastBreakX), Math.abs(y - this.lastBreakY), Math.abs(z - this.lastBreakZ));
                if (this.distanceFromLastBreak > 8.0) {
                    final double lastBreakX = 0.0;
                    this.distanceFromLastBreak = lastBreakX;
                    this.lastBreakZ = lastBreakX;
                    this.lastBreakX = lastBreakX;
                }
            }
            if (this.lastScaffoldPlaceX != 0 && this.lastScaffoldPlaceY != 0 && this.lastScaffoldPlaceZ != 0) {
                this.distanceFromLastScaffoldPlace = MathUtil.magnitude(Math.abs(x - this.lastScaffoldPlaceX), Math.abs(y - this.lastScaffoldPlaceY), Math.abs(z - this.lastScaffoldPlaceZ));
                if (this.distanceFromLastScaffoldPlace > 12.0) {
                    final int lastScaffoldPlaceX = 0;
                    this.lastScaffoldPlaceZ = lastScaffoldPlaceX;
                    this.lastScaffoldPlaceY = lastScaffoldPlaceX;
                    this.lastScaffoldPlaceX = lastScaffoldPlaceX;
                }
            }
            for (final Vector teleport : this.teleports) {
                final double deltaX = Math.abs(teleport.getX() - x);
                final double deltaY = Math.abs(teleport.getY() - y);
                final double deltaZ = Math.abs(teleport.getZ() - z);
                if (deltaX <= 0.03 && deltaY <= 0.03 && deltaZ <= 0.03) {
                    this.teleporting = true;
                }
            }
        }
    }
    
    public void handleSlimePlace() {
        this.sinceSlimePlaceTicks = 0;
    }
    
    public void handleCancelledPlace() {
        this.sinceCancelledPlaceTicks = 0;
    }
    
    public void handleIcePlace() {
        this.sinceIcePlaceTicks = 0;
    }
    
    public void handleBukkitVelocity() {
        this.sinceBukkitVelocityTicks = 0;
    }
    
    public void handleClimbablePlace() {
        this.sinceClimbablePlaceTicks = 0;
    }
    
    public void handleWebPlace() {
        this.sinceWebPlaceTicks = 0;
    }
    
    public void handleProjectileThrow() {
        this.lastProjectileThrow = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleFishEvent() {
        this.lastFishEvent = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public void handleAbilities(final WrappedPacketOutAbilities wrapper) {
        this.sinceAbilitiesTicks = 0;
    }
    
    public void handleResurrect() {
        this.sinceRessurectTicks = 0;
    }
    
    public void handleScaffoldingPlace(final BlockPlaceEvent event) {
        this.lastScaffoldPlaceX = event.getBlockPlaced().getX();
        this.lastScaffoldPlaceY = event.getBlockPlaced().getY();
        this.lastScaffoldPlaceZ = event.getBlockPlaced().getZ();
    }
    
    public void handleCrackshotDamage() {
        this.sinceCrackshotDamageTicks = 0;
    }
    
    public void handleChorusEat() {
        this.sinceChorusFruitTeleportTicks = 0;
    }
    
    public void handleMetaData(final WrappedPacketOutEntityMetadata wrapper) {
        final WrappedWatchableObject watchable = this.getIndex(wrapper.getWatchableObjects(), 0);
        if (watchable != null) {
            final Object zeroBitField = watchable.getRawValue();
            if (zeroBitField instanceof Byte) {
                final byte field = (byte)zeroBitField;
                this.packetGliding = (PlayerUtil.isHigherThan1_9(this.data.getPlayer()) && (field & 0x80) == 0x80);
                this.packetSwimming = ((field & 0x10) == 0x10);
            }
        }
    }
    
    private WrappedWatchableObject getIndex(final List<WrappedWatchableObject> objects, final int index) {
        for (final WrappedWatchableObject object : objects) {
            if (object.getIndex() == index) {
                return object;
            }
        }
        return null;
    }
    
    public void handleExplosion(final WrappedPacketOutExplosion wrapper) {
        final Vector3f velocity = wrapper.getPlayerVelocity();
        this.explosionX = velocity.getX();
        this.explosionY = velocity.getY();
        this.explosionZ = velocity.getZ();
        this.sinceExplosionTicks = 0;
    }
    
    public void onJoin() {
        this.hasSpeed = this.data.getPlayer().hasPotionEffect(PotionEffectType.SPEED);
        if (this.hasSpeed) {
            if (ServerUtil.isHigherThan1_13()) {
                this.speedAmplifier = this.data.getPlayer().getPotionEffect(PotionEffectType.SPEED).getAmplifier() + 1;
            }
            else {
                this.speedAmplifier = PlayerUtil.getPotionLevel(this.data.getPlayer(), PotionEffectType.SPEED);
            }
        }
        this.hasSlowness = this.data.getPlayer().hasPotionEffect(PotionEffectType.SLOW);
        this.hasJumpBoost = this.data.getPlayer().hasPotionEffect(PotionEffectType.JUMP);
        if (this.hasJumpBoost) {
            if (ServerUtil.isHigherThan1_13()) {
                this.jumpBoostAmplifier = this.data.getPlayer().getPotionEffect(PotionEffectType.JUMP).getAmplifier() + 1;
            }
            else {
                this.jumpBoostAmplifier = PlayerUtil.getPotionLevel(this.data.getPlayer(), PotionEffectType.JUMP);
            }
        }
        if (ServerUtil.isHigherThan1_11()) {
            this.hasLevitation = this.data.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION);
            if (this.hasLevitation) {
                if (ServerUtil.isHigherThan1_13()) {
                    this.levitationAmplifier = this.data.getPlayer().getPotionEffect(PotionEffectType.LEVITATION).getAmplifier() + 1;
                }
                else {
                    this.levitationAmplifier = PlayerUtil.getPotionLevel(this.data.getPlayer(), PotionEffectType.LEVITATION);
                }
            }
        }
        if (ServerUtil.isHigherThan1_13()) {
            this.hasDolphinsGrace = this.data.getPlayer().hasPotionEffect(PotionEffectType.DOLPHINS_GRACE);
            if (this.hasDolphinsGrace) {
                this.dolphinsGraceAmplifier = this.data.getPlayer().getPotionEffect(PotionEffectType.DOLPHINS_GRACE).getAmplifier() + 1;
            }
            this.hasConduitsPower = this.data.getPlayer().hasPotionEffect(PotionEffectType.CONDUIT_POWER);
            this.hasSlowFalling = this.data.getPlayer().hasPotionEffect(PotionEffectType.SLOW_FALLING);
            if (this.hasSlowFalling) {
                this.slowFallingAmplifier = this.data.getPlayer().getPotionEffect(PotionEffectType.SLOW_FALLING).getAmplifier() + 1;
            }
        }
    }
    
    public PlayerData getData() {
        return this.data;
    }
    
    public boolean isSprinting() {
        return this.sprinting;
    }
    
    public boolean isSneaking() {
        return this.sneaking;
    }
    
    public boolean isSendingAction() {
        return this.sendingAction;
    }
    
    public boolean isPlacing() {
        return this.placing;
    }
    
    public boolean isDigging() {
        return this.digging;
    }
    
    public boolean isBlocking() {
        return this.blocking;
    }
    
    public boolean isInventory() {
        return this.inventory;
    }
    
    public boolean isSendingDig() {
        return this.sendingDig;
    }
    
    public boolean isTeleporting() {
        return this.teleporting;
    }
    
    public boolean isHasSpeed() {
        return this.hasSpeed;
    }
    
    public boolean isHasJumpBoost() {
        return this.hasJumpBoost;
    }
    
    public boolean isHasLevitation() {
        return this.hasLevitation;
    }
    
    public boolean isHasSlowFalling() {
        return this.hasSlowFalling;
    }
    
    public boolean isHasDolphinsGrace() {
        return this.hasDolphinsGrace;
    }
    
    public boolean isSitting() {
        return this.sitting;
    }
    
    public boolean isHasConduitsPower() {
        return this.hasConduitsPower;
    }
    
    public boolean isCrawling() {
        return this.crawling;
    }
    
    public boolean isHasSlowness() {
        return this.hasSlowness;
    }
    
    public boolean isWearingDepthStrider() {
        return this.wearingDepthStrider;
    }
    
    public boolean isWearingElytra() {
        return this.wearingElytra;
    }
    
    public boolean isBukkitGliding() {
        return this.bukkitGliding;
    }
    
    public boolean isPacketGliding() {
        return this.packetGliding;
    }
    
    public boolean isPacketSwimming() {
        return this.packetSwimming;
    }
    
    public boolean isUpdateSwim() {
        return this.updateSwim;
    }
    
    public double getGenericMovementSpeed() {
        return this.genericMovementSpeed;
    }
    
    public boolean isBerserking() {
        return this.berserking;
    }
    
    public int getLastBukkitDiggingTick() {
        return this.lastBukkitDiggingTick;
    }
    
    public int getLastDropItemTick() {
        return this.lastDropItemTick;
    }
    
    public int getLastDiggingTick() {
        return this.lastDiggingTick;
    }
    
    public int getSinceSprintingTicks() {
        return this.sinceSprintingTicks;
    }
    
    public int getPositionTicksExisted() {
        return this.positionTicksExisted;
    }
    
    public int getLastWindowClick() {
        return this.lastWindowClick;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public int getLastAmount() {
        return this.lastAmount;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public int getLastSlot() {
        return this.lastSlot;
    }
    
    public int getSinceTeleportTicks() {
        return this.sinceTeleportTicks;
    }
    
    public int getSinceExplosionTicks() {
        return this.sinceExplosionTicks;
    }
    
    public int getLastBedEnter() {
        return this.lastBedEnter;
    }
    
    public int getLastBedLeave() {
        return this.lastBedLeave;
    }
    
    public int getLastItemPickup() {
        return this.lastItemPickup;
    }
    
    public int getLastCancelledBreak() {
        return this.lastCancelledBreak;
    }
    
    public int getLastChorusFruitTeleport() {
        return this.lastChorusFruitTeleport;
    }
    
    public int getLastDamage() {
        return this.lastDamage;
    }
    
    public int getSinceDragonDamageTicks() {
        return this.sinceDragonDamageTicks;
    }
    
    public int getSprintingTicks() {
        return this.sprintingTicks;
    }
    
    public int getLastRespawn() {
        return this.lastRespawn;
    }
    
    public int getLastLowHealth() {
        return this.lastLowHealth;
    }
    
    public int getLastProjectileThrow() {
        return this.lastProjectileThrow;
    }
    
    public int getLastFishEvent() {
        return this.lastFishEvent;
    }
    
    public int getSinceFireDamageTicks() {
        return this.sinceFireDamageTicks;
    }
    
    public int getSinceAttackDamageTicks() {
        return this.sinceAttackDamageTicks;
    }
    
    public int getSinceFallDamageTicks() {
        return this.sinceFallDamageTicks;
    }
    
    public int getSinceMagicDamageTicks() {
        return this.sinceMagicDamageTicks;
    }
    
    public int getSincePoisonDamageTicks() {
        return this.sincePoisonDamageTicks;
    }
    
    public int getSinceContactDamageTicks() {
        return this.sinceContactDamageTicks;
    }
    
    public int getSinceProjectileDamageTicks() {
        return this.sinceProjectileDamageTicks;
    }
    
    public int getLastLavaDamage() {
        return this.lastLavaDamage;
    }
    
    public int getSinceLavaDamageTicks() {
        return this.sinceLavaDamageTicks;
    }
    
    public int getSinceExplosionDamageTicks() {
        return this.sinceExplosionDamageTicks;
    }
    
    public int getSinceBucketEmptyTicks() {
        return this.sinceBucketEmptyTicks;
    }
    
    public int getSinceBlockPlaceTicks() {
        return this.sinceBlockPlaceTicks;
    }
    
    public int getSinceClimbablePlaceTicks() {
        return this.sinceClimbablePlaceTicks;
    }
    
    public int getSinceSlimePlaceTicks() {
        return this.sinceSlimePlaceTicks;
    }
    
    public int getSinceCancelledPlaceTicks() {
        return this.sinceCancelledPlaceTicks;
    }
    
    public int getSinceWorldChangeTicks() {
        return this.sinceWorldChangeTicks;
    }
    
    public int getSinceRessurectTicks() {
        return this.sinceRessurectTicks;
    }
    
    public int getSinceBukkitTeleportTicks() {
        return this.sinceBukkitTeleportTicks;
    }
    
    public int getSinceDeathTicks() {
        return this.sinceDeathTicks;
    }
    
    public int getSinceNonFallDamageTicks() {
        return this.sinceNonFallDamageTicks;
    }
    
    public int getSinceIronGolemDamageTicks() {
        return this.sinceIronGolemDamageTicks;
    }
    
    public int getLastScaffoldPlaceX() {
        return this.lastScaffoldPlaceX;
    }
    
    public int getLastScaffoldPlaceY() {
        return this.lastScaffoldPlaceY;
    }
    
    public int getLastScaffoldPlaceZ() {
        return this.lastScaffoldPlaceZ;
    }
    
    public int getSinceBerserkTicks() {
        return this.sinceBerserkTicks;
    }
    
    public int getSinceCrackshotDamageTicks() {
        return this.sinceCrackshotDamageTicks;
    }
    
    public int getSinceGlassBottleFillTicks() {
        return this.sinceGlassBottleFillTicks;
    }
    
    public int getSinceFireballDamageTicks() {
        return this.sinceFireballDamageTicks;
    }
    
    public int getSpeedAmplifier() {
        return this.speedAmplifier;
    }
    
    public int getJumpBoostAmplifier() {
        return this.jumpBoostAmplifier;
    }
    
    public int getLevitationAmplifier() {
        return this.levitationAmplifier;
    }
    
    public int getSlowFallingAmplifier() {
        return this.slowFallingAmplifier;
    }
    
    public int getDolphinsGraceAmplifier() {
        return this.dolphinsGraceAmplifier;
    }
    
    public int getSinceChorusFruitTeleportTicks() {
        return this.sinceChorusFruitTeleportTicks;
    }
    
    public int getSinceWebPlaceTicks() {
        return this.sinceWebPlaceTicks;
    }
    
    public int getSinceEnderPearlTicks() {
        return this.sinceEnderPearlTicks;
    }
    
    public int getSinceBukkitVelocityTicks() {
        return this.sinceBukkitVelocityTicks;
    }
    
    public int getSinceDamageTicks() {
        return this.sinceDamageTicks;
    }
    
    public int getSinceStartGlidingTicks() {
        return this.sinceStartGlidingTicks;
    }
    
    public int getSinceIcePlaceTicks() {
        return this.sinceIcePlaceTicks;
    }
    
    public int getSinceBlockBreakTicks() {
        return this.sinceBlockBreakTicks;
    }
    
    public int getTicksExisted() {
        return this.ticksExisted;
    }
    
    public int getSinceHoglinDamageTicks() {
        return this.sinceHoglinDamageTicks;
    }
    
    public int getSinceAbilitiesTicks() {
        return this.sinceAbilitiesTicks;
    }
    
    public int getSincePushTicks() {
        return this.sincePushTicks;
    }
    
    public int getSinceMythicMobTicks() {
        return this.sinceMythicMobTicks;
    }
    
    public int getSinceCancelledMoveTicks() {
        return this.sinceCancelledMoveTicks;
    }
    
    public int getSinceRavagerDamageTicks() {
        return this.sinceRavagerDamageTicks;
    }
    
    public int getSinceWitherDamageTicks() {
        return this.sinceWitherDamageTicks;
    }
    
    public int getSinceCrystalDamageTicks() {
        return this.sinceCrystalDamageTicks;
    }
    
    public int getSinceSuffociationDamageTicks() {
        return this.sinceSuffociationDamageTicks;
    }
    
    public int getSinceStupidBucketEmptyTicks() {
        return this.sinceStupidBucketEmptyTicks;
    }
    
    public int getSinceNetherrackBreakTicks() {
        return this.sinceNetherrackBreakTicks;
    }
    
    public int getSinceBowBoostTicks() {
        return this.sinceBowBoostTicks;
    }
    
    public double getLastAbortX() {
        return this.lastAbortX;
    }
    
    public double getLastAbortZ() {
        return this.lastAbortZ;
    }
    
    public double getDistanceFromLastAbort() {
        return this.distanceFromLastAbort;
    }
    
    public double getLastBreakX() {
        return this.lastBreakX;
    }
    
    public double getLastBreakY() {
        return this.lastBreakY;
    }
    
    public double getLastBreakZ() {
        return this.lastBreakZ;
    }
    
    public double getDistanceFromLastBreak() {
        return this.distanceFromLastBreak;
    }
    
    public double getHealth() {
        return this.health;
    }
    
    public double getDistanceFromLastScaffoldPlace() {
        return this.distanceFromLastScaffoldPlace;
    }
    
    public double getExplosionX() {
        return this.explosionX;
    }
    
    public double getExplosionY() {
        return this.explosionY;
    }
    
    public double getExplosionZ() {
        return this.explosionZ;
    }
    
    public double getLastTeleportX() {
        return this.lastTeleportX;
    }
    
    public double getLastTeleportY() {
        return this.lastTeleportY;
    }
    
    public double getLastTeleportZ() {
        return this.lastTeleportZ;
    }
    
    public double getDistanceFromLastTeleport() {
        return this.distanceFromLastTeleport;
    }
    
    public double getLastGlidingDeltaXZ() {
        return this.lastGlidingDeltaXZ;
    }
    
    public long getLastStartDestroy() {
        return this.lastStartDestroy;
    }
    
    public long getLastStopDestroy() {
        return this.lastStopDestroy;
    }
    
    public long getLastAbort() {
        return this.lastAbort;
    }
    
    public GameMode getGameMode() {
        return this.gameMode;
    }
    
    public short getPositionTransactionId() {
        return this.positionTransactionId;
    }
    
    public short getEntityEffectTransactionId() {
        return this.entityEffectTransactionId;
    }
    
    public short getRemoveEntityEffectTransactionId() {
        return this.removeEntityEffectTransactionId;
    }
    
    public short getGameStateChangeTransactionId() {
        return this.gameStateChangeTransactionId;
    }
    
    public short getPositionPingId() {
        return this.positionPingId;
    }
    
    public short getEntityEffectPingId() {
        return this.entityEffectPingId;
    }
    
    public short getRemoveEntityEffectPingId() {
        return this.removeEntityEffectPingId;
    }
    
    public short getGameStateChangePingId() {
        return this.gameStateChangePingId;
    }
    
    public Map<Short, WrappedPacketOutEntityEffect> getQueuedEntityEffects() {
        return this.queuedEntityEffects;
    }
    
    public Map<Short, WrappedPacketOutRemoveEntityEffect> getQueuedRemoveEntityEffects() {
        return this.queuedRemoveEntityEffects;
    }
    
    public Map<Short, WrappedPacketOutGameStateChange> getQueuedGameStateChanges() {
        return this.queuedGameStateChanges;
    }
    
    public Map<Short, WrappedPacketOutPosition> getQueuedTeleports() {
        return this.queuedTeleports;
    }
    
    public ItemStack getHelmet() {
        return this.helmet;
    }
    
    public ItemStack getChestplate() {
        return this.chestplate;
    }
    
    public ItemStack getLeggings() {
        return this.leggings;
    }
    
    public ItemStack getBoots() {
        return this.boots;
    }
    
    public ItemStack getItemInMainHand() {
        return this.itemInMainHand;
    }
    
    public ItemStack getItemInOffHand() {
        return this.itemInOffHand;
    }
    
    public EvictingList<Vector> getTeleports() {
        return this.teleports;
    }
    
    public void setSprinting(final boolean sprinting) {
        this.sprinting = sprinting;
    }
    
    public void setSneaking(final boolean sneaking) {
        this.sneaking = sneaking;
    }
    
    public void setSendingAction(final boolean sendingAction) {
        this.sendingAction = sendingAction;
    }
    
    public void setPlacing(final boolean placing) {
        this.placing = placing;
    }
    
    public void setDigging(final boolean digging) {
        this.digging = digging;
    }
    
    public void setBlocking(final boolean blocking) {
        this.blocking = blocking;
    }
    
    public void setInventory(final boolean inventory) {
        this.inventory = inventory;
    }
    
    public void setSendingDig(final boolean sendingDig) {
        this.sendingDig = sendingDig;
    }
    
    public void setTeleporting(final boolean teleporting) {
        this.teleporting = teleporting;
    }
    
    public void setHasSpeed(final boolean hasSpeed) {
        this.hasSpeed = hasSpeed;
    }
    
    public void setHasJumpBoost(final boolean hasJumpBoost) {
        this.hasJumpBoost = hasJumpBoost;
    }
    
    public void setHasLevitation(final boolean hasLevitation) {
        this.hasLevitation = hasLevitation;
    }
    
    public void setHasSlowFalling(final boolean hasSlowFalling) {
        this.hasSlowFalling = hasSlowFalling;
    }
    
    public void setHasDolphinsGrace(final boolean hasDolphinsGrace) {
        this.hasDolphinsGrace = hasDolphinsGrace;
    }
    
    public void setSitting(final boolean sitting) {
        this.sitting = sitting;
    }
    
    public void setHasConduitsPower(final boolean hasConduitsPower) {
        this.hasConduitsPower = hasConduitsPower;
    }
    
    public void setCrawling(final boolean crawling) {
        this.crawling = crawling;
    }
    
    public void setHasSlowness(final boolean hasSlowness) {
        this.hasSlowness = hasSlowness;
    }
    
    public void setWearingDepthStrider(final boolean wearingDepthStrider) {
        this.wearingDepthStrider = wearingDepthStrider;
    }
    
    public void setWearingElytra(final boolean wearingElytra) {
        this.wearingElytra = wearingElytra;
    }
    
    public void setBukkitGliding(final boolean bukkitGliding) {
        this.bukkitGliding = bukkitGliding;
    }
    
    public void setPacketGliding(final boolean packetGliding) {
        this.packetGliding = packetGliding;
    }
    
    public void setPacketSwimming(final boolean packetSwimming) {
        this.packetSwimming = packetSwimming;
    }
    
    public void setUpdateSwim(final boolean updateSwim) {
        this.updateSwim = updateSwim;
    }
    
    public void setGenericMovementSpeed(final double genericMovementSpeed) {
        this.genericMovementSpeed = genericMovementSpeed;
    }
    
    public void setLastBukkitDiggingTick(final int lastBukkitDiggingTick) {
        this.lastBukkitDiggingTick = lastBukkitDiggingTick;
    }
    
    public void setLastDropItemTick(final int lastDropItemTick) {
        this.lastDropItemTick = lastDropItemTick;
    }
    
    public void setLastDiggingTick(final int lastDiggingTick) {
        this.lastDiggingTick = lastDiggingTick;
    }
    
    public void setSinceSprintingTicks(final int sinceSprintingTicks) {
        this.sinceSprintingTicks = sinceSprintingTicks;
    }
    
    public void setPositionTicksExisted(final int positionTicksExisted) {
        this.positionTicksExisted = positionTicksExisted;
    }
    
    public void setLastWindowClick(final int lastWindowClick) {
        this.lastWindowClick = lastWindowClick;
    }
    
    public void setAmount(final int amount) {
        this.amount = amount;
    }
    
    public void setLastAmount(final int lastAmount) {
        this.lastAmount = lastAmount;
    }
    
    public void setSlot(final int slot) {
        this.slot = slot;
    }
    
    public void setLastSlot(final int lastSlot) {
        this.lastSlot = lastSlot;
    }
    
    public void setSinceTeleportTicks(final int sinceTeleportTicks) {
        this.sinceTeleportTicks = sinceTeleportTicks;
    }
    
    public void setSinceExplosionTicks(final int sinceExplosionTicks) {
        this.sinceExplosionTicks = sinceExplosionTicks;
    }
    
    public void setLastBedEnter(final int lastBedEnter) {
        this.lastBedEnter = lastBedEnter;
    }
    
    public void setLastBedLeave(final int lastBedLeave) {
        this.lastBedLeave = lastBedLeave;
    }
    
    public void setLastItemPickup(final int lastItemPickup) {
        this.lastItemPickup = lastItemPickup;
    }
    
    public void setLastCancelledBreak(final int lastCancelledBreak) {
        this.lastCancelledBreak = lastCancelledBreak;
    }
    
    public void setLastChorusFruitTeleport(final int lastChorusFruitTeleport) {
        this.lastChorusFruitTeleport = lastChorusFruitTeleport;
    }
    
    public void setLastDamage(final int lastDamage) {
        this.lastDamage = lastDamage;
    }
    
    public void setSinceDragonDamageTicks(final int sinceDragonDamageTicks) {
        this.sinceDragonDamageTicks = sinceDragonDamageTicks;
    }
    
    public void setSprintingTicks(final int sprintingTicks) {
        this.sprintingTicks = sprintingTicks;
    }
    
    public void setLastRespawn(final int lastRespawn) {
        this.lastRespawn = lastRespawn;
    }
    
    public void setLastLowHealth(final int lastLowHealth) {
        this.lastLowHealth = lastLowHealth;
    }
    
    public void setLastProjectileThrow(final int lastProjectileThrow) {
        this.lastProjectileThrow = lastProjectileThrow;
    }
    
    public void setLastFishEvent(final int lastFishEvent) {
        this.lastFishEvent = lastFishEvent;
    }
    
    public void setSinceFireDamageTicks(final int sinceFireDamageTicks) {
        this.sinceFireDamageTicks = sinceFireDamageTicks;
    }
    
    public void setSinceAttackDamageTicks(final int sinceAttackDamageTicks) {
        this.sinceAttackDamageTicks = sinceAttackDamageTicks;
    }
    
    public void setSinceFallDamageTicks(final int sinceFallDamageTicks) {
        this.sinceFallDamageTicks = sinceFallDamageTicks;
    }
    
    public void setSinceMagicDamageTicks(final int sinceMagicDamageTicks) {
        this.sinceMagicDamageTicks = sinceMagicDamageTicks;
    }
    
    public void setSincePoisonDamageTicks(final int sincePoisonDamageTicks) {
        this.sincePoisonDamageTicks = sincePoisonDamageTicks;
    }
    
    public void setSinceContactDamageTicks(final int sinceContactDamageTicks) {
        this.sinceContactDamageTicks = sinceContactDamageTicks;
    }
    
    public void setSinceProjectileDamageTicks(final int sinceProjectileDamageTicks) {
        this.sinceProjectileDamageTicks = sinceProjectileDamageTicks;
    }
    
    public void setLastLavaDamage(final int lastLavaDamage) {
        this.lastLavaDamage = lastLavaDamage;
    }
    
    public void setSinceLavaDamageTicks(final int sinceLavaDamageTicks) {
        this.sinceLavaDamageTicks = sinceLavaDamageTicks;
    }
    
    public void setSinceExplosionDamageTicks(final int sinceExplosionDamageTicks) {
        this.sinceExplosionDamageTicks = sinceExplosionDamageTicks;
    }
    
    public void setSinceBucketEmptyTicks(final int sinceBucketEmptyTicks) {
        this.sinceBucketEmptyTicks = sinceBucketEmptyTicks;
    }
    
    public void setSinceBlockPlaceTicks(final int sinceBlockPlaceTicks) {
        this.sinceBlockPlaceTicks = sinceBlockPlaceTicks;
    }
    
    public void setSinceClimbablePlaceTicks(final int sinceClimbablePlaceTicks) {
        this.sinceClimbablePlaceTicks = sinceClimbablePlaceTicks;
    }
    
    public void setSinceSlimePlaceTicks(final int sinceSlimePlaceTicks) {
        this.sinceSlimePlaceTicks = sinceSlimePlaceTicks;
    }
    
    public void setSinceCancelledPlaceTicks(final int sinceCancelledPlaceTicks) {
        this.sinceCancelledPlaceTicks = sinceCancelledPlaceTicks;
    }
    
    public void setSinceWorldChangeTicks(final int sinceWorldChangeTicks) {
        this.sinceWorldChangeTicks = sinceWorldChangeTicks;
    }
    
    public void setSinceRessurectTicks(final int sinceRessurectTicks) {
        this.sinceRessurectTicks = sinceRessurectTicks;
    }
    
    public void setSinceBukkitTeleportTicks(final int sinceBukkitTeleportTicks) {
        this.sinceBukkitTeleportTicks = sinceBukkitTeleportTicks;
    }
    
    public void setSinceDeathTicks(final int sinceDeathTicks) {
        this.sinceDeathTicks = sinceDeathTicks;
    }
    
    public void setSinceNonFallDamageTicks(final int sinceNonFallDamageTicks) {
        this.sinceNonFallDamageTicks = sinceNonFallDamageTicks;
    }
    
    public void setSinceIronGolemDamageTicks(final int sinceIronGolemDamageTicks) {
        this.sinceIronGolemDamageTicks = sinceIronGolemDamageTicks;
    }
    
    public void setLastScaffoldPlaceX(final int lastScaffoldPlaceX) {
        this.lastScaffoldPlaceX = lastScaffoldPlaceX;
    }
    
    public void setLastScaffoldPlaceY(final int lastScaffoldPlaceY) {
        this.lastScaffoldPlaceY = lastScaffoldPlaceY;
    }
    
    public void setLastScaffoldPlaceZ(final int lastScaffoldPlaceZ) {
        this.lastScaffoldPlaceZ = lastScaffoldPlaceZ;
    }
    
    public void setSinceBerserkTicks(final int sinceBerserkTicks) {
        this.sinceBerserkTicks = sinceBerserkTicks;
    }
    
    public void setSinceCrackshotDamageTicks(final int sinceCrackshotDamageTicks) {
        this.sinceCrackshotDamageTicks = sinceCrackshotDamageTicks;
    }
    
    public void setSinceGlassBottleFillTicks(final int sinceGlassBottleFillTicks) {
        this.sinceGlassBottleFillTicks = sinceGlassBottleFillTicks;
    }
    
    public void setSinceFireballDamageTicks(final int sinceFireballDamageTicks) {
        this.sinceFireballDamageTicks = sinceFireballDamageTicks;
    }
    
    public void setSpeedAmplifier(final int speedAmplifier) {
        this.speedAmplifier = speedAmplifier;
    }
    
    public void setJumpBoostAmplifier(final int jumpBoostAmplifier) {
        this.jumpBoostAmplifier = jumpBoostAmplifier;
    }
    
    public void setLevitationAmplifier(final int levitationAmplifier) {
        this.levitationAmplifier = levitationAmplifier;
    }
    
    public void setSlowFallingAmplifier(final int slowFallingAmplifier) {
        this.slowFallingAmplifier = slowFallingAmplifier;
    }
    
    public void setDolphinsGraceAmplifier(final int dolphinsGraceAmplifier) {
        this.dolphinsGraceAmplifier = dolphinsGraceAmplifier;
    }
    
    public void setSinceChorusFruitTeleportTicks(final int sinceChorusFruitTeleportTicks) {
        this.sinceChorusFruitTeleportTicks = sinceChorusFruitTeleportTicks;
    }
    
    public void setSinceWebPlaceTicks(final int sinceWebPlaceTicks) {
        this.sinceWebPlaceTicks = sinceWebPlaceTicks;
    }
    
    public void setSinceEnderPearlTicks(final int sinceEnderPearlTicks) {
        this.sinceEnderPearlTicks = sinceEnderPearlTicks;
    }
    
    public void setSinceBukkitVelocityTicks(final int sinceBukkitVelocityTicks) {
        this.sinceBukkitVelocityTicks = sinceBukkitVelocityTicks;
    }
    
    public void setSinceDamageTicks(final int sinceDamageTicks) {
        this.sinceDamageTicks = sinceDamageTicks;
    }
    
    public void setSinceStartGlidingTicks(final int sinceStartGlidingTicks) {
        this.sinceStartGlidingTicks = sinceStartGlidingTicks;
    }
    
    public void setSinceIcePlaceTicks(final int sinceIcePlaceTicks) {
        this.sinceIcePlaceTicks = sinceIcePlaceTicks;
    }
    
    public void setSinceBlockBreakTicks(final int sinceBlockBreakTicks) {
        this.sinceBlockBreakTicks = sinceBlockBreakTicks;
    }
    
    public void setTicksExisted(final int ticksExisted) {
        this.ticksExisted = ticksExisted;
    }
    
    public void setSinceHoglinDamageTicks(final int sinceHoglinDamageTicks) {
        this.sinceHoglinDamageTicks = sinceHoglinDamageTicks;
    }
    
    public void setSinceAbilitiesTicks(final int sinceAbilitiesTicks) {
        this.sinceAbilitiesTicks = sinceAbilitiesTicks;
    }
    
    public void setSincePushTicks(final int sincePushTicks) {
        this.sincePushTicks = sincePushTicks;
    }
    
    public void setSinceMythicMobTicks(final int sinceMythicMobTicks) {
        this.sinceMythicMobTicks = sinceMythicMobTicks;
    }
    
    public void setSinceCancelledMoveTicks(final int sinceCancelledMoveTicks) {
        this.sinceCancelledMoveTicks = sinceCancelledMoveTicks;
    }
    
    public void setSinceRavagerDamageTicks(final int sinceRavagerDamageTicks) {
        this.sinceRavagerDamageTicks = sinceRavagerDamageTicks;
    }
    
    public void setSinceWitherDamageTicks(final int sinceWitherDamageTicks) {
        this.sinceWitherDamageTicks = sinceWitherDamageTicks;
    }
    
    public void setSinceCrystalDamageTicks(final int sinceCrystalDamageTicks) {
        this.sinceCrystalDamageTicks = sinceCrystalDamageTicks;
    }
    
    public void setSinceSuffociationDamageTicks(final int sinceSuffociationDamageTicks) {
        this.sinceSuffociationDamageTicks = sinceSuffociationDamageTicks;
    }
    
    public void setSinceStupidBucketEmptyTicks(final int sinceStupidBucketEmptyTicks) {
        this.sinceStupidBucketEmptyTicks = sinceStupidBucketEmptyTicks;
    }
    
    public void setSinceNetherrackBreakTicks(final int sinceNetherrackBreakTicks) {
        this.sinceNetherrackBreakTicks = sinceNetherrackBreakTicks;
    }
    
    public void setSinceBowBoostTicks(final int sinceBowBoostTicks) {
        this.sinceBowBoostTicks = sinceBowBoostTicks;
    }
    
    public void setLastAbortX(final double lastAbortX) {
        this.lastAbortX = lastAbortX;
    }
    
    public void setLastAbortZ(final double lastAbortZ) {
        this.lastAbortZ = lastAbortZ;
    }
    
    public void setDistanceFromLastAbort(final double distanceFromLastAbort) {
        this.distanceFromLastAbort = distanceFromLastAbort;
    }
    
    public void setLastBreakX(final double lastBreakX) {
        this.lastBreakX = lastBreakX;
    }
    
    public void setLastBreakY(final double lastBreakY) {
        this.lastBreakY = lastBreakY;
    }
    
    public void setLastBreakZ(final double lastBreakZ) {
        this.lastBreakZ = lastBreakZ;
    }
    
    public void setDistanceFromLastBreak(final double distanceFromLastBreak) {
        this.distanceFromLastBreak = distanceFromLastBreak;
    }
    
    public void setHealth(final double health) {
        this.health = health;
    }
    
    public void setDistanceFromLastScaffoldPlace(final double distanceFromLastScaffoldPlace) {
        this.distanceFromLastScaffoldPlace = distanceFromLastScaffoldPlace;
    }
    
    public void setExplosionX(final double explosionX) {
        this.explosionX = explosionX;
    }
    
    public void setExplosionY(final double explosionY) {
        this.explosionY = explosionY;
    }
    
    public void setExplosionZ(final double explosionZ) {
        this.explosionZ = explosionZ;
    }
    
    public void setLastTeleportX(final double lastTeleportX) {
        this.lastTeleportX = lastTeleportX;
    }
    
    public void setLastTeleportY(final double lastTeleportY) {
        this.lastTeleportY = lastTeleportY;
    }
    
    public void setLastTeleportZ(final double lastTeleportZ) {
        this.lastTeleportZ = lastTeleportZ;
    }
    
    public void setDistanceFromLastTeleport(final double distanceFromLastTeleport) {
        this.distanceFromLastTeleport = distanceFromLastTeleport;
    }
    
    public void setLastGlidingDeltaXZ(final double lastGlidingDeltaXZ) {
        this.lastGlidingDeltaXZ = lastGlidingDeltaXZ;
    }
    
    public void setLastStartDestroy(final long lastStartDestroy) {
        this.lastStartDestroy = lastStartDestroy;
    }
    
    public void setLastStopDestroy(final long lastStopDestroy) {
        this.lastStopDestroy = lastStopDestroy;
    }
    
    public void setLastAbort(final long lastAbort) {
        this.lastAbort = lastAbort;
    }
    
    public void setGameMode(final GameMode gameMode) {
        this.gameMode = gameMode;
    }
    
    public void setPositionTransactionId(final short positionTransactionId) {
        this.positionTransactionId = positionTransactionId;
    }
    
    public void setEntityEffectTransactionId(final short entityEffectTransactionId) {
        this.entityEffectTransactionId = entityEffectTransactionId;
    }
    
    public void setRemoveEntityEffectTransactionId(final short removeEntityEffectTransactionId) {
        this.removeEntityEffectTransactionId = removeEntityEffectTransactionId;
    }
    
    public void setGameStateChangeTransactionId(final short gameStateChangeTransactionId) {
        this.gameStateChangeTransactionId = gameStateChangeTransactionId;
    }
    
    public void setPositionPingId(final short positionPingId) {
        this.positionPingId = positionPingId;
    }
    
    public void setEntityEffectPingId(final short entityEffectPingId) {
        this.entityEffectPingId = entityEffectPingId;
    }
    
    public void setRemoveEntityEffectPingId(final short removeEntityEffectPingId) {
        this.removeEntityEffectPingId = removeEntityEffectPingId;
    }
    
    public void setGameStateChangePingId(final short gameStateChangePingId) {
        this.gameStateChangePingId = gameStateChangePingId;
    }
    
    public void setHelmet(final ItemStack helmet) {
        this.helmet = helmet;
    }
    
    public void setChestplate(final ItemStack chestplate) {
        this.chestplate = chestplate;
    }
    
    public void setLeggings(final ItemStack leggings) {
        this.leggings = leggings;
    }
    
    public void setBoots(final ItemStack boots) {
        this.boots = boots;
    }
    
    public void setItemInMainHand(final ItemStack itemInMainHand) {
        this.itemInMainHand = itemInMainHand;
    }
    
    public void setItemInOffHand(final ItemStack itemInOffHand) {
        this.itemInOffHand = itemInOffHand;
    }
    
    public void setBerserking(final boolean berserking) {
        this.berserking = berserking;
    }
    
    static {
        GENERIC_MOVEMENT_SPEED = (ServerUtil.isHigherThan1_16() ? "attribute.name.generic.movement_speed" : "generic.movementSpeed");
    }
}
