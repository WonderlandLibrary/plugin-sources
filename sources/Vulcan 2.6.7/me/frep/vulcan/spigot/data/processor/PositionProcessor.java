package me.frep.vulcan.spigot.data.processor;

import io.github.retrooper.packetevents.packetwrappers.play.in.pong.WrappedPacketInPong;
import io.github.retrooper.packetevents.packetwrappers.play.in.transaction.WrappedPacketInTransaction;
import java.util.logging.Level;
import me.frep.vulcan.spigot.util.ColorUtil;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import io.github.retrooper.packetevents.packetwrappers.api.SendableWrapper;
import io.github.retrooper.packetevents.packetwrappers.play.out.blockchange.WrappedPacketOutBlockChange;
import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import me.frep.vulcan.api.event.VulcanGhostBlockEvent;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.WeatherType;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.GameMode;
import org.bukkit.entity.Boat;
import io.github.retrooper.packetevents.packetwrappers.play.in.vehiclemove.WrappedPacketInVehicleMove;
import org.bukkit.entity.TNTPrimed;
import java.util.Iterator;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import me.frep.vulcan.spigot.util.EntityUtil;
import java.util.Collection;
import me.frep.vulcan.spigot.util.StreamUtil;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.bukkit.util.NumberConversions;
import me.frep.vulcan.spigot.exempt.ExemptProcessor;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import org.bukkit.WorldBorder;
import java.util.Arrays;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.util.BlockUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.util.MathUtil;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import java.util.HashMap;
import org.bukkit.World;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import java.util.List;
import io.github.retrooper.packetevents.packetwrappers.play.out.abilities.WrappedPacketOutAbilities;
import java.util.Map;
import org.bukkit.Location;
import me.frep.vulcan.spigot.data.PlayerData;

public class PositionProcessor
{
    private final PlayerData data;
    private boolean nearFence;
    private boolean nearLiquid;
    private boolean nearSlime;
    private boolean nearSoulSand;
    private boolean nearClimbable;
    private boolean nearWeb;
    private boolean nearLilyPad;
    private boolean nearDaylightSensor;
    private boolean nearBrewingStand;
    private boolean nearWall;
    private boolean nearStair;
    private boolean nearSlab;
    private boolean nearHoney;
    private boolean nearScaffolding;
    private boolean nearTrapdoor;
    private boolean nearSkull;
    private boolean nearPortalFrame;
    private boolean nearCampfire;
    private boolean nearSweetBerries;
    private boolean nearShulkerBox;
    private boolean vehicleNearBubbleColumn;
    private boolean nearSnow;
    private boolean nearAnvil;
    private boolean nearEndRod;
    private boolean nearChain;
    private boolean nearPiston;
    private boolean nearDoor;
    private boolean touchingAir;
    private boolean nearCauldron;
    private boolean nearLava;
    private boolean nearHopper;
    private boolean nearFenceGate;
    private boolean nearFlowerPot;
    private boolean onClimbable;
    private boolean inWeb;
    private boolean inBubbleColumn;
    private boolean fullySubmerged;
    private boolean inLiquid;
    private boolean collidingVertically;
    private boolean nearKelp;
    private boolean fullyStuck;
    private boolean partiallyStuck;
    private boolean onIce;
    private boolean clientOnGround;
    private boolean mathematicallyOnGround;
    private boolean nearShulker;
    private boolean nearBoat;
    private boolean nearBell;
    private boolean nearBed;
    private boolean nearCarpet;
    private boolean nearLectern;
    private boolean nearTurtleEgg;
    private boolean nearSeaPickle;
    private boolean nearIce;
    private boolean nearConduit;
    private boolean collidingHorizontally;
    private boolean nearRepeater;
    private boolean nearSolid;
    private boolean nearPane;
    private boolean vehicleNearIce;
    private boolean vehicleNearLiquid;
    private boolean vehicleNearSlime;
    private boolean vehicleInAir;
    private boolean moving;
    private boolean nearFrostedIce;
    private boolean nearBubbleColumn;
    private boolean nearFarmland;
    private boolean lastClientOnGround;
    private boolean allowFlight;
    private boolean nearSeaGrass;
    private boolean nearChest;
    private boolean vehicleNearPiston;
    private boolean nearCake;
    private boolean vehicleNearBed;
    private boolean nearAmethyst;
    private boolean nearDripstone;
    private boolean nearPowderSnow;
    private boolean setbackGround;
    private boolean collidingEntity;
    private boolean lastLastClientOnGround;
    private boolean vehicleNearEntity;
    private boolean nearPath;
    private boolean nearLantern;
    private boolean nearBorder;
    private boolean nearRail;
    private boolean nearSign;
    private boolean nearBamboo;
    private boolean nearPressurePlate;
    private boolean fuckedPosition;
    private boolean nearEnchantmentTable;
    private Location from;
    private Location to;
    private short abilitiesTransactionId;
    private short abilitiesPingId;
    private final Map<Short, WrappedPacketOutAbilities> queuedAbilities;
    public boolean frozen;
    public List<Entity> nearbyEntities;
    private Material blockBelow;
    private Material blockBelow2;
    private Material blockBelow3;
    private List<Material> nearbyBlocks;
    private List<Material> blocksBelow;
    private List<Material> blocksAbove;
    private List<Material> blocksAround;
    private List<Material> glitchedBlocksAbove;
    public List<Material> nearbyBlocksModern;
    public List<Material> vehicleBlocks;
    public List<Material> blocksUnderModern;
    public Material blockBelowModern;
    public Material blockBelow2Modern;
    public Material blockBelow3Modern;
    private double x;
    private double y;
    private double z;
    private double lastX;
    private double lastY;
    private double lastZ;
    private double deltaX;
    private double deltaY;
    private double deltaZ;
    private double deltaXZ;
    private double deltaXYZ;
    private double lastDeltaX;
    private double lastDeltaZ;
    private double lastDeltaY;
    private double lastDeltaXZ;
    private double vehicleX;
    private double vehicleY;
    private double vehicleZ;
    private double lastVehicleX;
    private double lastVehicleY;
    private double lastVehicleZ;
    private double lastVehicleDeltaX;
    private double lastVehicleDeltaY;
    private double lastVehicleDeltaZ;
    private double vehicleDeltaX;
    private double vehicleDeltaY;
    private double vehicleDeltaZ;
    private double vehicleDeltaXZ;
    private double lastOnGroundX;
    private double lastOnGroundY;
    private double lastOnGroundZ;
    private double setbackX;
    private double setbackY;
    private double setbackZ;
    private double ghostBlockBuffer;
    private double newSetbackX;
    private double newSetbackY;
    private double newSetbackZ;
    private double ghostBlockSetbackX;
    private double ghostBlockSetbackY;
    private double ghostBlockSetbackZ;
    private double lastLegitX;
    private double lastLegitY;
    private double lastLegitZ;
    private double lastLastX;
    private double lastLastY;
    private double lastLastZ;
    private double firstJoinX;
    private double firstJoinY;
    private double firstJoinZ;
    private double unloadedChunkBuffer;
    private int sinceVehicleTicks;
    private int sinceFlyingTicks;
    private int sinceTrapdoorTicks;
    private int clientAirTicks;
    private int clientGroundTicks;
    private int sinceSwimmingTicks;
    private int sinceRiptideTicks;
    private int sinceGlidingTicks;
    private int sinceIceTicks;
    private int sinceDolphinsGraceTicks;
    private int sinceLevitationTicks;
    private int sinceBubbleColumnTicks;
    private int glidingTicks;
    private int sinceSlowFallingTicks;
    private int serverAirTicks;
    private int serverGroundTicks;
    private int sinceWebTicks;
    private int sinceLiquidTicks;
    private int climbableTicks;
    private int sinceJumpBoostTicks;
    private int sinceNearSlimeTicks;
    private int sinceHoneyTicks;
    private int sinceCollidingVerticallyTicks;
    private int sinceNearFenceTicks;
    private int sinceSoulSpeedTicks;
    private int sinceNearBedTicks;
    private int lastPulledByFishingRod;
    private int vehicleTicks;
    private int sinceFireworkTicks;
    private int sinceNearIceTicks;
    private int sinceNearPistonTicks;
    private int lastWaterLogged;
    private int sinceSpeedTicks;
    private int lastAttributeModifier;
    private int sinceAroundSlimeTicks;
    private int sinceAroundPistonTicks;
    private int sinceVehicleNearIceTicks;
    private int sinceVehicleNearLiquidTicks;
    private int sinceVehicleNearSlimeTicks;
    private int sinceVehicleNearBubbleColumnTicks;
    private int blockX;
    private int blockY;
    private int blockZ;
    private int sinceNearFrostedIceTicks;
    private int sinceNearScaffoldingTicks;
    private int ticksSinceGhostBlockSetback;
    private int lastServerAbilities;
    private int vehicleAirTicks;
    private int sinceNearShulkerBoxTicks;
    private int sinceElytraTicks;
    private int lastGhostBlockSetback;
    private int sinceNearFarmlandTicks;
    private int sinceNearStairTicks;
    private int sinceNearSlabTicks;
    private int sinceEntityCollisionTicks;
    private int sinceVehicleNearPistonTicks;
    private int sinceFishingRodTicks;
    private int sinceAttributeModifierTicks;
    private int sinceHighFlySpeedTicks;
    private int sinceAroundSlabTicks;
    private int sinceVehicleNearBedTicks;
    private int sinceWaterLogTicks;
    private int sinceGroundSpeedFailTicks;
    private int sinceNearClimbableTicks;
    private int boatsAround;
    private int sinceSetbackTicks;
    private int sinceCollidingHorizontallyTicks;
    private int sinceFlagTicks;
    private int sinceFuckingEntityTicks;
    private int sinceSpectatorTicks;
    private World world;
    private Entity bukkitVehicle;
    private Entity lastBukkitVehicle;
    private float moveForward;
    private float moveStrafing;
    private float walkSpeed;
    private float flySpeed;
    private boolean exemptFlight;
    private boolean exemptCreative;
    private boolean exemptJoined;
    private boolean exemptLiquid;
    private boolean exemptLevitation;
    private boolean exemptSlowFalling;
    private boolean exemptRiptide;
    private boolean exemptVehicle;
    private boolean exemptLenientScaffolding;
    private boolean exemptBukkitVelocity;
    private boolean exemptGliding;
    private boolean exemptElytra;
    private boolean exemptTeleport;
    private boolean exemptEnderPearl;
    private boolean exemptChunk;
    private boolean exemptComboMode;
    private boolean exemptMythicMob;
    private boolean exemptClimbable;
    
    public PositionProcessor(final PlayerData data) {
        this.abilitiesTransactionId = -30768;
        this.abilitiesPingId = -30768;
        this.queuedAbilities = new HashMap<Short, WrappedPacketOutAbilities>();
        this.sinceVehicleTicks = 100;
        this.sinceFlyingTicks = 100;
        this.sinceTrapdoorTicks = 100;
        this.sinceSwimmingTicks = 100;
        this.sinceRiptideTicks = 200;
        this.sinceGlidingTicks = 100;
        this.sinceDolphinsGraceTicks = 100;
        this.sinceLevitationTicks = 100;
        this.sinceBubbleColumnTicks = 100;
        this.sinceSlowFallingTicks = 100;
        this.sinceWebTicks = 100;
        this.sinceLiquidTicks = 100;
        this.sinceJumpBoostTicks = 100;
        this.sinceNearSlimeTicks = 100;
        this.sinceHoneyTicks = 100;
        this.sinceCollidingVerticallyTicks = 100;
        this.sinceNearFenceTicks = 100;
        this.sinceSoulSpeedTicks = 100;
        this.sinceNearBedTicks = 100;
        this.sinceFireworkTicks = 200;
        this.sinceNearIceTicks = 100;
        this.sinceNearPistonTicks = 100;
        this.sinceSpeedTicks = 100;
        this.sinceAroundSlimeTicks = 500;
        this.sinceAroundPistonTicks = 500;
        this.sinceVehicleNearIceTicks = 500;
        this.sinceVehicleNearLiquidTicks = 150;
        this.sinceVehicleNearSlimeTicks = 150;
        this.sinceVehicleNearBubbleColumnTicks = 150;
        this.sinceNearScaffoldingTicks = 100;
        this.ticksSinceGhostBlockSetback = 500;
        this.sinceNearShulkerBoxTicks = 100;
        this.sinceElytraTicks = 100;
        this.sinceNearFarmlandTicks = 100;
        this.sinceEntityCollisionTicks = 100;
        this.sinceVehicleNearPistonTicks = 100;
        this.sinceFishingRodTicks = 1000;
        this.sinceAttributeModifierTicks = 100;
        this.sinceHighFlySpeedTicks = 200;
        this.sinceAroundSlabTicks = 100;
        this.sinceVehicleNearBedTicks = 1000;
        this.sinceWaterLogTicks = 1000;
        this.sinceGroundSpeedFailTicks = 1000;
        this.sinceNearClimbableTicks = 1000;
        this.boatsAround = 0;
        this.sinceSetbackTicks = 1000;
        this.sinceCollidingHorizontallyTicks = 100;
        this.sinceFlagTicks = 1000;
        this.sinceFuckingEntityTicks = 100;
        this.sinceSpectatorTicks = 100;
        this.moveForward = 0.0f;
        this.moveStrafing = 0.0f;
        this.walkSpeed = 0.2f;
        this.flySpeed = 0.2f;
        this.data = data;
    }
    
    public void onJoin() {
        this.walkSpeed = this.data.getPlayer().getWalkSpeed();
        final Location location = this.data.getPlayer().getLocation();
        this.firstJoinX = location.getX();
        this.firstJoinY = location.getY();
        this.firstJoinZ = location.getZ();
        this.setbackX = location.getX();
        this.setbackY = location.getY();
        this.setbackZ = location.getZ();
    }
    
    public void handleFlying(final WrappedPacketInFlying wrapper) {
        this.lastLastClientOnGround = this.lastClientOnGround;
        this.lastClientOnGround = this.clientOnGround;
        this.clientOnGround = wrapper.isOnGround();
        this.handleFlyingTicks();
        if (wrapper.isPosition()) {
            this.fuckedPosition = (this.blockX == 0 && this.blockY == 0 && this.blockZ == 0);
            this.world = this.data.getPlayer().getWorld();
            this.lastLastX = this.lastX;
            this.lastLastY = this.lastY;
            this.lastLastZ = this.lastZ;
            this.lastX = this.x;
            this.lastY = this.y;
            this.lastZ = this.z;
            this.x = wrapper.getX();
            this.y = wrapper.getY();
            this.z = wrapper.getZ();
            this.from = ((this.to == null) ? new Location(this.world, this.x, this.y, this.z) : this.to);
            this.to = new Location(this.world, this.x, this.y, this.z);
            this.lastDeltaX = this.deltaX;
            this.lastDeltaY = this.deltaY;
            this.lastDeltaZ = this.deltaZ;
            this.lastDeltaXZ = this.deltaXZ;
            this.deltaX = this.x - this.lastX;
            this.deltaY = this.y - this.lastY;
            this.deltaZ = this.z - this.lastZ;
            this.deltaXZ = MathUtil.hypot(this.deltaX, this.deltaZ);
            this.deltaXYZ = MathUtil.magnitude(this.deltaX, this.deltaY, this.deltaZ);
            this.moving = (Math.abs(this.deltaX) > 0.0 || Math.abs(this.deltaZ) > 0.0 || Math.abs(this.deltaY) > 0.0);
            this.mathematicallyOnGround = (this.y % 0.015625 == 0.0);
            if (this.data.getPlayer().getVehicle() == null) {
                this.vehicleTicks = 0;
            }
            if (ServerUtil.isHigherThan1_13()) {
                this.cacheBlocksModern();
            }
            else {
                this.cacheBlocksLegacy();
            }
            this.handleGhostBlock();
            this.handleUnloadedChunk();
            if (ServerUtil.isHigherThan1_13() && !this.frozen) {
                if (this.blockBelowModern != null && this.blockBelowModern != Material.AIR && this.blockBelowModern.isBlock() && this.mathematicallyOnGround && !BlockUtil.isLiquid(this.blockBelowModern) && this.sinceSetbackTicks > 5 && this.deltaXZ < 2.0 && !this.data.getActionProcessor().isTeleporting() && this.ticksSinceGhostBlockSetback > 10) {
                    this.setbackX = this.x;
                    this.setbackY = this.y;
                    this.setbackZ = this.z;
                }
            }
            else if (this.blockBelow != null && this.blockBelow.isSolid() && this.mathematicallyOnGround && !BlockUtil.isLiquid(this.blockBelow) && !this.frozen && this.deltaXZ < 2.0 && this.sinceSetbackTicks > 5 && !this.data.getActionProcessor().isTeleporting() && this.ticksSinceGhostBlockSetback > 10) {
                this.setbackX = this.x;
                this.setbackY = this.y;
                this.setbackZ = this.z;
            }
            this.handlePositionTicks();
            this.parseForwardAndStrafe();
            if (this.data.getActionProcessor().getGenericMovementSpeed() > 0.11) {
                this.sinceAttributeModifierTicks = 0;
            }
            this.cacheExemptions();
            if (Config.VELOCITY_WORLD_BORDER && ServerUtil.isHigherThan1_8()) {
                final WorldBorder worldBorder = this.world.getWorldBorder();
                final double centerX = worldBorder.getCenter().getX();
                final double centerZ = worldBorder.getCenter().getZ();
                final double size = worldBorder.getSize() / 2.0;
                double dx1 = this.x - centerX - size;
                double dx2 = this.x - centerX + size;
                double dx3 = this.z - centerZ - size;
                double dx4 = this.z - centerZ + size;
                if (dx1 < 0.0) {
                    dx1 *= -1.0;
                }
                if (dx2 < 0.0) {
                    dx2 *= -1.0;
                }
                if (dx3 < 0.0) {
                    dx3 *= -1.0;
                }
                if (dx4 < 0.0) {
                    dx4 *= -1.0;
                }
                final double[] distances = { dx1, dx2, dx3, dx4 };
                Arrays.sort(distances);
                this.nearBorder = (distances[0] < 1.0);
            }
        }
    }
    
    private void cacheExemptions() {
        final ExemptProcessor exemptProcessor = this.data.getExemptProcessor();
        this.exemptFlight = exemptProcessor.isExempt(ExemptType.FLIGHT);
        this.exemptCreative = exemptProcessor.isExempt(ExemptType.CREATIVE);
        this.exemptJoined = exemptProcessor.isExempt(ExemptType.JOINED);
        this.exemptLiquid = exemptProcessor.isExempt(ExemptType.LIQUID);
        this.exemptLevitation = exemptProcessor.isExempt(ExemptType.LEVITATION);
        this.exemptSlowFalling = exemptProcessor.isExempt(ExemptType.SLOW_FALLING);
        this.exemptRiptide = exemptProcessor.isExempt(ExemptType.RIPTIDE);
        this.exemptVehicle = exemptProcessor.isExempt(ExemptType.VEHICLE);
        this.exemptLenientScaffolding = exemptProcessor.isExempt(ExemptType.LENIENT_SCAFFOLDING);
        this.exemptBukkitVelocity = exemptProcessor.isExempt(ExemptType.BUKKIT_VELOCITY);
        this.exemptGliding = exemptProcessor.isExempt(ExemptType.GLIDING);
        this.exemptElytra = exemptProcessor.isExempt(ExemptType.ELYTRA);
        this.exemptTeleport = exemptProcessor.isExempt(ExemptType.TELEPORT);
        this.exemptEnderPearl = exemptProcessor.isExempt(ExemptType.ENDER_PEARL);
        this.exemptChunk = exemptProcessor.isExempt(ExemptType.CHUNK);
        this.exemptComboMode = exemptProcessor.isExempt(ExemptType.COMBO_MODE);
        this.exemptMythicMob = exemptProcessor.isExempt(ExemptType.MYTHIC_MOB);
        this.exemptClimbable = exemptProcessor.isExempt(ExemptType.CLIMBABLE);
    }
    
    private void cacheBlocksModern() {
        if (this.nearbyBlocksModern == null || this.blockBelowModern == null || this.blockBelow2Modern == null || this.blockBelow3Modern == null) {
            return;
        }
        this.blockX = NumberConversions.floor(this.x);
        this.blockY = NumberConversions.floor(this.y);
        this.blockZ = NumberConversions.floor(this.z);
        this.blocksBelow = PlayerUtil.getBlocksBelowModern(this.data);
        this.blocksAbove = PlayerUtil.getBlocksAboveModern(this.data);
        this.blocksAround = PlayerUtil.getBlocksAroundModern(this.data);
        this.glitchedBlocksAbove = PlayerUtil.getBlocksAboveGlitchedModern(this.data);
        this.onIce = PlayerUtil.isOnIceModern(this.data);
        this.fullyStuck = PlayerUtil.isFullyStuckModern(this.data);
        this.partiallyStuck = PlayerUtil.isFullyStuckModern(this.data);
        this.collidingVertically = !StreamUtil.allMatch(this.blocksAbove, BlockUtil::isAir);
        this.collidingHorizontally = !StreamUtil.allMatch(this.blocksAround, BlockUtil::isAir);
        this.nearSolid = StreamUtil.anyMatch(this.nearbyBlocksModern, BlockUtil::isSolid);
        this.touchingAir = (StreamUtil.allMatch(this.blocksAround, BlockUtil::isAir) && StreamUtil.allMatch(this.blocksBelow, BlockUtil::isAir));
        this.handleCollisionsModern();
    }
    
    private void handleCollisionsModern() {
        if (this.nearbyBlocksModern != null && this.nearbyEntities != null) {
            final boolean b = false;
            this.nearPowderSnow = b;
            this.nearDripstone = b;
            this.nearCake = b;
            this.nearChest = b;
            this.nearSeaGrass = b;
            this.nearLava = b;
            this.nearFarmland = b;
            this.nearBubbleColumn = b;
            this.nearFrostedIce = b;
            this.nearSign = b;
            this.nearPressurePlate = b;
            this.nearEnchantmentTable = b;
            this.nearAmethyst = b;
            this.nearHoney = b;
            this.nearScaffolding = b;
            this.nearChain = b;
            this.nearEndRod = b;
            this.nearTurtleEgg = b;
            this.nearKelp = b;
            this.nearShulkerBox = b;
            this.nearSeaPickle = b;
            this.nearBell = b;
            this.nearLectern = b;
            this.nearBamboo = b;
            this.nearLantern = b;
            this.nearConduit = b;
            this.nearSweetBerries = b;
            this.nearCampfire = b;
            this.nearBed = b;
            this.nearSkull = b;
            this.nearPane = b;
            this.nearPiston = b;
            this.nearLilyPad = b;
            this.nearDoor = b;
            this.nearHopper = b;
            this.nearSlab = b;
            this.nearRail = b;
            this.nearRepeater = b;
            this.nearAnvil = b;
            this.nearSnow = b;
            this.nearFenceGate = b;
            this.nearIce = b;
            this.nearStair = b;
            this.nearCauldron = b;
            this.nearWall = b;
            this.nearBrewingStand = b;
            this.nearDaylightSensor = b;
            this.nearPath = b;
            this.nearPortalFrame = b;
            this.nearTrapdoor = b;
            this.nearFlowerPot = b;
            this.nearWeb = b;
            this.nearClimbable = b;
            this.nearSoulSand = b;
            this.nearCarpet = b;
            this.nearLiquid = b;
            this.nearFence = b;
            this.nearSlime = b;
            for (final Material material : this.nearbyBlocksModern) {
                this.nearSlime |= (material == Material.SLIME_BLOCK);
                this.nearLiquid |= (material == Material.WATER || material == Material.LAVA);
                this.nearWeb |= (material == Material.COBWEB);
                this.nearFlowerPot |= (material == Material.FLOWER_POT);
                this.nearPortalFrame |= (material == Material.END_PORTAL_FRAME);
                this.nearDaylightSensor |= (material == Material.DAYLIGHT_DETECTOR);
                this.nearBrewingStand |= (material == Material.BREWING_STAND);
                this.nearIce |= (material == Material.ICE || material == Material.PACKED_ICE || material == Material.FROSTED_ICE);
                this.nearSnow |= (material == Material.SNOW);
                this.nearAnvil |= (material == Material.ANVIL || material == Material.CHIPPED_ANVIL || material == Material.DAMAGED_ANVIL);
                this.nearRepeater |= (material == Material.REPEATER);
                this.nearHopper |= (material == Material.HOPPER);
                this.nearLilyPad |= (material == Material.LILY_PAD);
                this.nearPiston |= (material == Material.PISTON || material == Material.PISTON_HEAD || material == Material.MOVING_PISTON || material == Material.STICKY_PISTON);
                this.nearConduit |= (material == Material.CONDUIT);
                this.nearSeaPickle |= (material == Material.SEA_PICKLE);
                this.nearKelp |= (material == Material.KELP || material == Material.KELP_PLANT);
                this.nearTurtleEgg |= (material == Material.TURTLE_EGG);
                this.nearEndRod |= (material == Material.END_ROD);
                this.nearScaffolding |= BlockUtil.isScaffolding(material);
                this.nearHoney |= BlockUtil.isHoney(material);
                this.nearCauldron |= (material == Material.CAULDRON);
                this.nearCake |= (material == Material.CAKE);
                this.nearEnchantmentTable |= BlockUtil.isEnchantmentTable(material);
                this.nearAmethyst |= BlockUtil.isAmethyst(material);
                this.nearRail |= BlockUtil.isRail(material);
                this.nearLantern |= BlockUtil.isLantern(material);
                this.nearPowderSnow |= BlockUtil.isPowderSnow(material);
                this.nearDripstone |= BlockUtil.isDripstone(material);
                this.nearChest |= BlockUtil.isChest(material);
                this.nearSeaGrass |= BlockUtil.isSeaGrass(material);
                this.nearBell |= BlockUtil.isBell(material);
                this.nearChain |= BlockUtil.isChain(material);
                this.nearLectern |= BlockUtil.isLectern(material);
                this.nearSweetBerries |= BlockUtil.isSweetBerries(material);
                this.nearBubbleColumn |= BlockUtil.isBubbleColumn(material);
                this.nearFrostedIce |= BlockUtil.isFrostedIce(material);
                this.nearSoulSand |= BlockUtil.isSoulSand(material);
                this.nearCarpet |= BlockUtil.isCarpet(material);
                this.nearTrapdoor |= BlockUtil.isTrapdoor(material);
                this.nearWall |= BlockUtil.isWall(material);
                this.nearFarmland |= BlockUtil.isFarmland(material);
                this.nearStair |= BlockUtil.isStair(material);
                this.nearPressurePlate |= BlockUtil.isPressurePlate(material);
                this.nearFence |= BlockUtil.isFence(material);
                this.nearClimbable |= BlockUtil.isClimbable(material);
                this.nearFenceGate |= BlockUtil.isFenceGate(material);
                this.nearSlab |= BlockUtil.isSlab(material);
                this.nearDoor |= BlockUtil.isDoor(material);
                this.nearPane |= BlockUtil.isPane(material);
                this.nearSkull |= BlockUtil.isSkull(material);
                this.nearBed |= BlockUtil.isBed(material);
                this.nearCampfire |= BlockUtil.isCampfire(material);
                this.nearShulkerBox |= BlockUtil.isShulkerBox(material);
                this.nearPath |= BlockUtil.isPath(material);
                this.nearSign |= BlockUtil.isSign(material);
                this.nearBamboo |= BlockUtil.isBamboo(material);
            }
            final boolean nearBoat = false;
            this.collidingEntity = nearBoat;
            this.nearShulker = nearBoat;
            this.nearBoat = nearBoat;
            for (final Entity entity : this.nearbyEntities) {
                this.nearBoat |= EntityUtil.isBoat(entity);
                this.nearShulker |= EntityUtil.isShulker(entity);
                if (entity instanceof LivingEntity) {
                    this.collidingEntity = true;
                }
                if (entity instanceof Firework) {
                    this.sinceFireworkTicks = 0;
                }
                if (entity instanceof EnderDragon || entity instanceof EnderDragonPart) {
                    this.data.getActionProcessor().setSinceDragonDamageTicks(0);
                }
            }
            if (this.nearbyEntities.size() > Config.ENTITY_CRAM_FIX_AMOUNT) {
                this.sinceFuckingEntityTicks = 0;
            }
            this.inBubbleColumn = PlayerUtil.isInBubbleColumnModern(this.data);
            this.onClimbable = PlayerUtil.isOnClimbableModern(this.data);
            this.inWeb = PlayerUtil.isInWebModern(this.data);
            this.inLiquid = PlayerUtil.isInLiquidModern(this.data);
            this.fullySubmerged = PlayerUtil.isFullySubmergedModern(this.data);
        }
        if (this.nearBoat || this.nearShulker || this.nearTrapdoor || this.nearCarpet || this.nearCampfire || this.nearBrewingStand || this.nearRepeater || this.nearDaylightSensor || this.nearSkull || this.nearLilyPad || this.nearShulkerBox || this.nearFlowerPot) {
            this.serverAirTicks = 0;
        }
    }
    
    private void cacheBlocksLegacy() {
        this.blockX = NumberConversions.floor(this.x);
        this.blockY = NumberConversions.floor(this.y);
        this.blockZ = NumberConversions.floor(this.z);
        this.nearbyBlocks = BlockUtil.getNearbyBlocksAsync(this.world, this.blockX, this.blockY, this.blockZ, 1);
        this.blocksBelow = PlayerUtil.getBlocksBelow(this.data);
        this.blocksAbove = PlayerUtil.getBlocksAbove(this.data);
        this.blocksAround = PlayerUtil.getBlocksAround(this.data);
        this.glitchedBlocksAbove = PlayerUtil.getBlocksAboveGlitchedLegacy(this.data);
        this.blockBelow = BlockUtil.getBlockTypeASync(this.world, this.blockX, NumberConversions.floor(this.y - 1.0), this.blockZ);
        this.blockBelow2 = BlockUtil.getBlockTypeASync(this.world, this.blockX, NumberConversions.floor(this.y - 2.0), this.blockZ);
        this.blockBelow3 = BlockUtil.getBlockTypeASync(this.world, this.blockX, NumberConversions.floor(this.y - 3.0), this.blockZ);
        this.onIce = PlayerUtil.isOnIce(this.data);
        this.fullyStuck = PlayerUtil.isFullyStuck(this.data);
        this.partiallyStuck = PlayerUtil.isPartiallyStuck(this.data);
        this.collidingVertically = !StreamUtil.allMatch(this.blocksAbove, BlockUtil::isAir);
        this.collidingHorizontally = !StreamUtil.allMatch(this.blocksAround, BlockUtil::isAir);
        this.nearSolid = StreamUtil.anyMatch(this.nearbyBlocks, BlockUtil::isSolid);
        this.touchingAir = (StreamUtil.allMatch(this.blocksAround, BlockUtil::isAir) && StreamUtil.allMatch(this.blocksBelow, BlockUtil::isAir));
        this.handleCollisionsLegacy();
    }
    
    private void handleCollisionsLegacy() {
        final boolean nearSlime = false;
        this.nearAmethyst = nearSlime;
        this.nearEnchantmentTable = nearSlime;
        this.nearPressurePlate = nearSlime;
        this.nearBamboo = nearSlime;
        this.nearSign = nearSlime;
        this.nearCake = nearSlime;
        this.nearChest = nearSlime;
        this.nearLava = nearSlime;
        this.nearFarmland = nearSlime;
        this.nearBed = nearSlime;
        this.nearSkull = nearSlime;
        this.nearPane = nearSlime;
        this.nearPiston = nearSlime;
        this.nearLilyPad = nearSlime;
        this.nearDoor = nearSlime;
        this.nearHopper = nearSlime;
        this.nearSlab = nearSlime;
        this.nearRail = nearSlime;
        this.nearBoat = nearSlime;
        this.nearRepeater = nearSlime;
        this.nearAnvil = nearSlime;
        this.nearSnow = nearSlime;
        this.nearFenceGate = nearSlime;
        this.nearIce = nearSlime;
        this.nearStair = nearSlime;
        this.nearCauldron = nearSlime;
        this.nearWall = nearSlime;
        this.nearBrewingStand = nearSlime;
        this.nearDaylightSensor = nearSlime;
        this.nearLantern = nearSlime;
        this.nearPath = nearSlime;
        this.nearPortalFrame = nearSlime;
        this.nearTrapdoor = nearSlime;
        this.nearFlowerPot = nearSlime;
        this.nearWeb = nearSlime;
        this.nearClimbable = nearSlime;
        this.nearSoulSand = nearSlime;
        this.nearCarpet = nearSlime;
        this.nearLiquid = nearSlime;
        this.nearFence = nearSlime;
        this.nearSlime = nearSlime;
        if (this.nearbyBlocks != null && this.nearbyEntities != null) {
            for (final Material material : this.nearbyBlocks) {
                this.nearSlime |= BlockUtil.isSlime(material);
                this.nearFence |= BlockUtil.isFence(material);
                this.nearLiquid |= BlockUtil.isLiquid(material);
                this.nearCarpet |= BlockUtil.isCarpet(material);
                this.nearSoulSand |= BlockUtil.isSoulSand(material);
                this.nearClimbable |= BlockUtil.isClimbable(material);
                this.nearWeb |= BlockUtil.isWeb(material);
                this.nearFlowerPot |= BlockUtil.isFlowerPot(material);
                this.nearTrapdoor |= BlockUtil.isTrapdoor(material);
                this.nearPortalFrame |= BlockUtil.isPortalFrame(material);
                this.nearDaylightSensor |= BlockUtil.isDaylightSensor(material);
                this.nearBrewingStand |= BlockUtil.isBrewingStand(material);
                this.nearWall |= BlockUtil.isWall(material);
                this.nearCauldron |= BlockUtil.isCauldron(material);
                this.nearStair |= BlockUtil.isStair(material);
                this.nearIce |= BlockUtil.isIce(material);
                this.nearFenceGate |= BlockUtil.isFenceGate(material);
                this.nearSnow |= BlockUtil.isSnow(material);
                this.nearAnvil |= BlockUtil.isAnvil(material);
                this.nearRepeater |= BlockUtil.isRepeater(material);
                this.nearSlab |= BlockUtil.isSlab(material);
                this.nearHopper |= BlockUtil.isHopper(material);
                this.nearDoor |= BlockUtil.isDoor(material);
                this.nearLilyPad |= BlockUtil.isLilyPad(material);
                this.nearPiston |= BlockUtil.isPiston(material);
                this.nearPane |= BlockUtil.isPane(material);
                this.nearSkull |= BlockUtil.isSkull(material);
                this.nearBed |= BlockUtil.isBed(material);
                this.nearFarmland |= BlockUtil.isFarmland(material);
                this.nearChest |= BlockUtil.isChest(material);
                this.nearCake |= BlockUtil.isCake(material);
                this.nearPath |= BlockUtil.isPath(material);
                this.nearLantern |= BlockUtil.isLantern(material);
                this.nearRail |= BlockUtil.isRail(material);
                this.nearSign |= BlockUtil.isSign(material);
                this.nearBamboo |= BlockUtil.isBamboo(material);
                this.nearPressurePlate |= BlockUtil.isPressurePlate(material);
                this.nearEnchantmentTable |= BlockUtil.isEnchantmentTable(material);
                this.nearAmethyst |= BlockUtil.isAmethyst(material);
            }
            for (final Entity entity : this.nearbyEntities) {
                this.nearBoat |= EntityUtil.isBoat(entity);
                if (entity instanceof EnderDragon || entity instanceof EnderDragonPart) {
                    this.data.getActionProcessor().setSinceDragonDamageTicks(0);
                }
                if (entity instanceof TNTPrimed) {
                    this.data.getActionProcessor().setSinceExplosionTicks(0);
                }
            }
            this.onClimbable = PlayerUtil.isOnClimbable(this.data);
            this.inWeb = PlayerUtil.isInWeb(this.data);
            this.inLiquid = PlayerUtil.isInLiquid(this.data);
            this.fullySubmerged = PlayerUtil.isFullySubmerged(this.data);
        }
        if (this.nearBoat || this.nearShulker || this.nearTrapdoor || this.nearCarpet || this.nearBrewingStand || this.nearRepeater || this.nearDaylightSensor || this.nearSkull || this.nearLilyPad || this.nearFlowerPot) {
            this.serverAirTicks = 0;
        }
    }
    
    public void handleVehicleMove(final WrappedPacketInVehicleMove wrapper) {
        ++this.vehicleTicks;
        this.lastBukkitVehicle = this.bukkitVehicle;
        this.bukkitVehicle = this.data.getPlayer().getVehicle();
        if (this.lastBukkitVehicle != this.bukkitVehicle) {
            this.vehicleTicks = 0;
        }
        this.vehicleX = wrapper.getX();
        this.vehicleY = wrapper.getY();
        this.vehicleZ = wrapper.getZ();
        this.lastVehicleDeltaX = this.vehicleDeltaX;
        this.lastVehicleDeltaY = this.vehicleDeltaY;
        this.lastVehicleDeltaZ = this.vehicleDeltaZ;
        this.vehicleDeltaX = this.vehicleX - this.lastVehicleX;
        this.vehicleDeltaY = this.vehicleY - this.lastVehicleY;
        this.vehicleDeltaZ = this.vehicleZ - this.lastVehicleZ;
        this.vehicleDeltaXZ = MathUtil.hypot(this.vehicleDeltaX, this.vehicleDeltaZ);
        if (ServerUtil.isLowerThan1_13() && ServerUtil.isHigherThan1_9()) {
            this.vehicleBlocks = BlockUtil.getNearbyBlocksAsync(this.world, NumberConversions.floor(this.vehicleX), NumberConversions.floor(this.vehicleY), NumberConversions.floor(this.vehicleZ), 1);
        }
        if (this.vehicleBlocks != null) {
            final boolean b = false;
            this.vehicleNearBed = b;
            this.vehicleNearBubbleColumn = b;
            this.vehicleNearSlime = b;
            this.vehicleNearPiston = b;
            this.vehicleNearLiquid = b;
            this.vehicleNearIce = b;
            for (final Material material : this.vehicleBlocks) {
                this.vehicleNearIce |= BlockUtil.isIce(material);
                this.vehicleNearLiquid |= BlockUtil.isLiquid(material);
                this.vehicleNearPiston |= BlockUtil.isPiston(material);
                this.vehicleNearSlime |= BlockUtil.isSlime(material);
                this.vehicleNearBubbleColumn |= BlockUtil.isBubbleColumn(material);
                this.vehicleNearBed |= BlockUtil.isBed(material);
            }
            this.vehicleInAir = StreamUtil.allMatch(this.vehicleBlocks, BlockUtil::isAir);
        }
        this.boatsAround = 0;
        this.vehicleNearEntity = false;
        if (this.nearbyEntities != null) {
            for (final Entity entity : this.nearbyEntities) {
                if (entity instanceof Boat) {
                    ++this.boatsAround;
                }
                if (entity instanceof LivingEntity) {
                    this.vehicleNearEntity = true;
                }
            }
        }
        if (this.vehicleNearIce) {
            this.sinceVehicleNearIceTicks = 0;
        }
        else {
            ++this.sinceVehicleNearIceTicks;
        }
        if (this.vehicleNearPiston) {
            this.sinceVehicleNearPistonTicks = 0;
        }
        else {
            ++this.sinceVehicleNearPistonTicks;
        }
        if (this.vehicleNearBed) {
            this.sinceVehicleNearBedTicks = 0;
        }
        else {
            ++this.sinceVehicleNearBedTicks;
        }
        if (this.vehicleNearLiquid) {
            this.sinceVehicleNearLiquidTicks = 0;
        }
        else {
            ++this.sinceVehicleNearLiquidTicks;
        }
        if (this.vehicleNearSlime) {
            this.sinceVehicleNearSlimeTicks = 0;
        }
        else {
            ++this.sinceVehicleNearSlimeTicks;
        }
        if (this.vehicleNearBubbleColumn) {
            this.sinceVehicleNearBubbleColumnTicks = 0;
        }
        else {
            ++this.sinceVehicleNearBubbleColumnTicks;
        }
        if (this.vehicleInAir) {
            ++this.vehicleAirTicks;
        }
        else {
            this.vehicleAirTicks = 0;
        }
        this.lastVehicleX = this.vehicleX;
        this.lastVehicleY = this.vehicleY;
        this.lastVehicleZ = this.vehicleZ;
    }
    
    public void handleFlyingTicks() {
        ++this.sinceGroundSpeedFailTicks;
        ++this.ticksSinceGhostBlockSetback;
        ++this.sinceFishingRodTicks;
        ++this.sinceAttributeModifierTicks;
        ++this.sinceSetbackTicks;
        if (this.clientOnGround) {
            ++this.clientGroundTicks;
        }
        else {
            this.clientGroundTicks = 0;
        }
        if (!this.clientOnGround) {
            ++this.clientAirTicks;
        }
        else {
            this.clientAirTicks = 0;
        }
    }
    
    public void handlePositionTicks() {
        ++this.sinceFlagTicks;
        ++this.sinceFuckingEntityTicks;
        if (this.touchingAir) {
            ++this.serverAirTicks;
        }
        else {
            this.serverAirTicks = 0;
        }
        if (this.mathematicallyOnGround) {
            ++this.serverGroundTicks;
        }
        else {
            this.serverGroundTicks = 0;
        }
        if (this.flySpeed > 0.11f) {
            this.sinceHighFlySpeedTicks = 0;
        }
        else {
            ++this.sinceHighFlySpeedTicks;
        }
        if (this.nearSlime || (this.blockBelow != null && BlockUtil.isSlime(this.blockBelow)) || (this.blockBelow2 != null && BlockUtil.isSlime(this.blockBelow2)) || (this.blockBelow3 != null && BlockUtil.isSlime(this.blockBelow3)) || (this.blockBelowModern != null && BlockUtil.isSlime(this.blockBelowModern)) || (this.blockBelow2Modern != null && BlockUtil.isSlime(this.blockBelow2Modern)) || (this.blockBelow3Modern != null && BlockUtil.isSlime(this.blockBelow3Modern))) {
            this.sinceNearSlimeTicks = 0;
        }
        else {
            ++this.sinceNearSlimeTicks;
        }
        ++this.sinceWaterLogTicks;
        if (this.nearIce) {
            this.sinceNearIceTicks = 0;
        }
        else {
            ++this.sinceNearIceTicks;
        }
        if (this.data.getActionProcessor().isHasSpeed()) {
            this.sinceSpeedTicks = 0;
        }
        else {
            ++this.sinceSpeedTicks;
        }
        if (ServerUtil.isHigherThan1_8() && this.data.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            this.sinceSpectatorTicks = 0;
        }
        else {
            ++this.sinceSpectatorTicks;
        }
        if (this.nearBed) {
            this.sinceNearBedTicks = 0;
        }
        else {
            ++this.sinceNearBedTicks;
        }
        if (this.nearFarmland) {
            this.sinceNearFarmlandTicks = 0;
        }
        else {
            ++this.sinceNearFarmlandTicks;
        }
        if (this.nearSlab) {
            this.sinceNearSlabTicks = 0;
        }
        else {
            ++this.sinceNearSlabTicks;
        }
        if (this.nearPiston) {
            this.sinceNearPistonTicks = 0;
        }
        else {
            ++this.sinceNearPistonTicks;
        }
        if (this.collidingEntity) {
            this.sinceEntityCollisionTicks = 0;
        }
        else {
            ++this.sinceEntityCollisionTicks;
        }
        if (this.data.getActionProcessor().isHasJumpBoost()) {
            this.sinceJumpBoostTicks = 0;
        }
        else {
            ++this.sinceJumpBoostTicks;
        }
        if (this.collidingVertically) {
            this.sinceCollidingVerticallyTicks = 0;
        }
        else {
            ++this.sinceCollidingVerticallyTicks;
        }
        if (this.collidingHorizontally) {
            this.sinceCollidingHorizontallyTicks = 0;
        }
        else {
            ++this.sinceCollidingHorizontallyTicks;
        }
        if (this.nearFrostedIce) {
            this.sinceNearFrostedIceTicks = 0;
        }
        else {
            ++this.sinceNearFrostedIceTicks;
        }
        if (this.nearFence) {
            this.sinceNearFenceTicks = 0;
        }
        else {
            ++this.sinceNearFenceTicks;
        }
        if (this.onIce) {
            this.sinceIceTicks = 0;
        }
        else {
            ++this.sinceIceTicks;
        }
        if (this.nearTrapdoor) {
            this.sinceTrapdoorTicks = 0;
        }
        else {
            ++this.sinceTrapdoorTicks;
        }
        if (this.nearWeb) {
            this.sinceWebTicks = 0;
        }
        else {
            ++this.sinceWebTicks;
        }
        if (this.nearClimbable) {
            this.sinceNearClimbableTicks = 0;
        }
        else {
            ++this.sinceNearClimbableTicks;
        }
        if (this.nearShulker || this.nearShulkerBox) {
            this.sinceNearShulkerBoxTicks = 0;
        }
        else {
            ++this.sinceNearShulkerBoxTicks;
        }
        if (this.nearScaffolding) {
            this.sinceNearScaffoldingTicks = 0;
        }
        else {
            ++this.sinceNearScaffoldingTicks;
        }
        if (this.inLiquid || this.nearLiquid) {
            this.sinceLiquidTicks = 0;
        }
        else {
            ++this.sinceLiquidTicks;
        }
        if (this.onClimbable) {
            ++this.climbableTicks;
        }
        else {
            this.climbableTicks = 0;
        }
        if (this.nearStair) {
            this.sinceNearStairTicks = 0;
        }
        else {
            ++this.sinceNearStairTicks;
        }
        if (this.blocksAround != null && StreamUtil.anyMatch(this.blocksAround, BlockUtil::isSlime)) {
            this.sinceAroundSlimeTicks = 0;
        }
        else {
            ++this.sinceAroundSlimeTicks;
        }
        if (this.blocksAround != null && StreamUtil.anyMatch(this.blocksAround, BlockUtil::isPiston)) {
            this.sinceAroundPistonTicks = 0;
        }
        else {
            ++this.sinceAroundPistonTicks;
        }
        if (this.blocksAround != null && StreamUtil.anyMatch(this.blocksAround, BlockUtil::isSlab)) {
            this.sinceAroundSlabTicks = 0;
        }
        else {
            ++this.sinceAroundSlabTicks;
        }
        if (this.data.getPlayer().isInsideVehicle() || this.data.getPlayer().getVehicle() != null) {
            this.sinceVehicleTicks = 0;
        }
        else {
            ++this.sinceVehicleTicks;
        }
        ++this.sinceFireworkTicks;
        if (this.data.getPlayer().getAllowFlight() || this.allowFlight) {
            this.sinceFlyingTicks = 0;
        }
        else {
            ++this.sinceFlyingTicks;
        }
        if (ServerUtil.isHigherThan1_9()) {
            if (this.data.getActionProcessor().isHasSlowFalling()) {
                this.sinceSlowFallingTicks = 0;
            }
            else {
                ++this.sinceSlowFallingTicks;
            }
            if (PlayerUtil.isSwimming(this.data.getPlayer()) || this.data.getActionProcessor().isPacketSwimming()) {
                this.sinceSwimmingTicks = 0;
            }
            else {
                ++this.sinceSwimmingTicks;
            }
            if (this.data.getActionProcessor().isBukkitGliding() || this.data.getActionProcessor().isPacketGliding()) {
                this.sinceGlidingTicks = 0;
            }
            else {
                ++this.sinceGlidingTicks;
            }
            if (PlayerUtil.isGliding(this.data.getPlayer())) {
                ++this.glidingTicks;
            }
            else {
                this.glidingTicks = 0;
            }
            if (ServerUtil.isHigherThan1_16() && this.nearSoulSand && this.data.getActionProcessor().getBoots() != null && this.data.getActionProcessor().getBoots().getEnchantmentLevel(Enchantment.SOUL_SPEED) > 0) {
                this.sinceSoulSpeedTicks = 0;
            }
            else {
                ++this.sinceSoulSpeedTicks;
            }
            if (this.data.getActionProcessor().isWearingElytra()) {
                this.sinceElytraTicks = 0;
            }
            else {
                ++this.sinceElytraTicks;
            }
            if (PlayerUtil.isRiptiding(this.data.getPlayer())) {
                this.sinceRiptideTicks = 0;
            }
            else {
                ++this.sinceRiptideTicks;
            }
            if (this.nearHoney) {
                this.sinceHoneyTicks = 0;
            }
            else {
                ++this.sinceHoneyTicks;
            }
            if (this.data.getActionProcessor().isHasLevitation()) {
                this.sinceLevitationTicks = 0;
            }
            else {
                ++this.sinceLevitationTicks;
            }
            if (PlayerUtil.hasDolphinsGrace(this.data.getPlayer())) {
                this.sinceDolphinsGraceTicks = 0;
            }
            else {
                ++this.sinceDolphinsGraceTicks;
            }
            if (this.inBubbleColumn || this.nearBubbleColumn) {
                this.sinceBubbleColumnTicks = 0;
            }
            else {
                ++this.sinceBubbleColumnTicks;
            }
        }
    }
    
    public void handleBlockDig(final WrappedPacketInBlockDig wrapper) {
        if (ServerUtil.isHigherThan1_9() && wrapper.getDigType() == WrappedPacketInBlockDig.PlayerDigType.RELEASE_USE_ITEM) {
            final boolean liquid = this.sinceLiquidTicks < 30 || this.sinceWaterLogTicks < 30;
            final boolean playerWeather = this.data.getPlayer().getPlayerWeather() == WeatherType.DOWNFALL;
            final boolean rain = this.data.getPlayer().getWorld().hasStorm();
            if ((liquid || rain || playerWeather) && PlayerUtil.isHoldingTridentWithRiptide(this.data.getPlayer())) {
                this.sinceRiptideTicks = 0;
            }
        }
    }
    
    public boolean isNearGround() {
        if (ServerUtil.isHigherThan1_13()) {
            if (this.blockBelowModern != null && this.blockBelow2Modern != null) {
                return !BlockUtil.isAir(this.blockBelowModern) || !BlockUtil.isAir(this.blockBelow2Modern);
            }
        }
        else if (this.blockBelow != null && this.blockBelow2 != null) {
            return !BlockUtil.isAir(this.blockBelow) || !BlockUtil.isAir(this.blockBelow2);
        }
        return false;
    }
    
    public void handleArmAnimation() {
        if (ServerUtil.isHigherThan1_9()) {
            if (this.data.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("FIREWORK")) {
                this.sinceFireworkTicks = 0;
            }
            if (this.data.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("FIREWORK")) {
                this.sinceFireworkTicks = 0;
            }
        }
    }
    
    public boolean isNearGroundModern() {
        return this.blockBelowModern != null && this.blockBelow2Modern != null && this.blockBelow3Modern != null && (!BlockUtil.isAir(this.blockBelowModern) || !BlockUtil.isAir(this.blockBelow2Modern) || !BlockUtil.isAir(this.blockBelow3Modern));
    }
    
    public boolean isOnLiquid() {
        return this.blocksBelow != null && StreamUtil.allMatch(this.blocksBelow, BlockUtil::isLiquid);
    }
    
    public boolean isAirBelow() {
        if (ServerUtil.isHigherThan1_13()) {
            return this.blocksBelow != null && this.blocksUnderModern != null && StreamUtil.allMatch(this.blocksBelow, BlockUtil::isAir) && StreamUtil.allMatch(this.blocksUnderModern, BlockUtil::isAir);
        }
        return this.blocksBelow != null && StreamUtil.allMatch(this.blocksBelow, BlockUtil::isAir);
    }
    
    public double getAcceleration() {
        return Math.abs(this.deltaXZ - this.lastDeltaXZ);
    }
    
    public void handleFishingRod() {
        this.lastPulledByFishingRod = Vulcan.INSTANCE.getTickManager().getTicks();
        this.sinceFishingRodTicks = 0;
    }
    
    public void parseForwardAndStrafe() {
        final double angle = MathUtil.angleOf(this.lastZ, this.lastX, this.z, this.x);
        final float rawYaw = this.data.getRotationProcessor().getYaw() % 360.0f;
        final double yaw = (rawYaw >= 0.0f) ? rawYaw : ((double)(rawYaw + 360.0f));
        final double angleDeltaRaw = (double)(Math.round(MathUtil.getDistanceBetweenAngles360Raw(yaw, angle) / 5.0) * 5L);
        final double angleDelta = (double)(Math.round(MathUtil.getDistanceBetweenAngles360(yaw, angle) / 5.0) * 5L);
        float moveStrafing = 0.0f;
        float moveForward = 0.0f;
        if (angleDelta >= 0.0 && angleDelta < 90.0) {
            ++moveForward;
        }
        else if (angleDelta > 90.0 && angleDelta <= 180.0) {
            --moveForward;
        }
        if (angleDeltaRaw > 0.0 && angleDeltaRaw < 180.0) {
            --moveStrafing;
        }
        else if (angleDeltaRaw > 180.0 && angleDeltaRaw < 360.0) {
            ++moveStrafing;
        }
        this.moveForward = moveForward;
        this.moveStrafing = moveStrafing;
    }
    
    public void handleWaterlogged() {
        this.sinceWaterLogTicks = 0;
        this.lastWaterLogged = Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    private void handleGhostBlock() {
        if (!Config.GHOST_BLOCK_FIX || ServerUtil.getTPS() < Config.GHOST_BLOCK_MIN_TPS) {
            return;
        }
        final boolean exempt = this.data.getExemptProcessor().isExempt(ExemptType.LAGGED_NEAR_GROUND, ExemptType.BLOCK_PLACE_FAST, ExemptType.LAGGED_NEAR_GROUND_MODERN, ExemptType.FLIGHT, ExemptType.TELEPORT, ExemptType.SWIMMING, ExemptType.DEATH);
        final boolean geyser = (Config.IGNORE_GEYSER_CLIENT_BRAND && this.data.getClientBrand().toLowerCase().contains("geyser")) || (Config.IGNORE_GEYSER_PREFIXES && this.data.getPlayer().getName().startsWith(Config.IGNORE_GEYSER_PREFIX)) || this.data.getPlayer().getUniqueId().toString().startsWith("00000");
        final boolean ghostBlock = this.serverAirTicks > Config.GHOST_BLOCK_MIN_TICKS && this.clientGroundTicks >= 1 && this.serverGroundTicks >= 1;
        if (ghostBlock && !exempt && !geyser) {
            final double ghostBlockBuffer = this.ghostBlockBuffer + 1.0;
            this.ghostBlockBuffer = ghostBlockBuffer;
            if (ghostBlockBuffer > Config.GHOST_BLOCK_MAX_BUFFER) {
                if (Config.ENABLE_API) {
                    final VulcanGhostBlockEvent event = new VulcanGhostBlockEvent(this.data.getPlayer());
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) {
                        return;
                    }
                }
                if (Config.GHOST_BLOCK_FIX_UPDATE_BLOCKS) {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> {
                        final WrappedPacketOutBlockChange wrapper = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX, this.blockY - 1, this.blockZ), this.world.getBlockAt(this.blockX, this.blockY - 1, this.blockZ).getType());
                        final WrappedPacketOutBlockChange wrapper2 = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX + 1, this.blockY - 1, this.blockZ), this.world.getBlockAt(this.blockX + 1, this.blockY - 1, this.blockZ).getType());
                        final WrappedPacketOutBlockChange wrapper3 = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX - 1, this.blockY - 1, this.blockZ), this.world.getBlockAt(this.blockX - 1, this.blockY - 1, this.blockZ).getType());
                        final WrappedPacketOutBlockChange wrapper4 = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX, this.blockY - 1, this.blockZ + 1), this.world.getBlockAt(this.blockX, this.blockY - 1, this.blockZ + 1).getType());
                        final WrappedPacketOutBlockChange wrapper5 = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX, this.blockY - 1, this.blockZ - 1), this.world.getBlockAt(this.blockX, this.blockY - 1, this.blockZ - 1).getType());
                        final WrappedPacketOutBlockChange wrapper6 = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX - 1, this.blockY - 1, this.blockZ - 1), this.world.getBlockAt(this.blockX - 1, this.blockY - 1, this.blockZ - 1).getType());
                        final WrappedPacketOutBlockChange wrapper7 = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX - 1, this.blockY - 1, this.blockZ + 1), this.world.getBlockAt(this.blockX - 1, this.blockY - 1, this.blockZ + 1).getType());
                        final WrappedPacketOutBlockChange wrapper8 = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX + 1, this.blockY - 1, this.blockZ + 1), this.world.getBlockAt(this.blockX - 1, this.blockY - 1, this.blockZ + 1).getType());
                        final WrappedPacketOutBlockChange wrapper9 = new WrappedPacketOutBlockChange(new Location(this.world, this.blockX + 1, this.blockY - 1, this.blockZ - 1), this.world.getBlockAt(this.blockX + 1, this.blockY - 1, this.blockZ - 1).getType());
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper);
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper2);
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper3);
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper4);
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper5);
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper6);
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper7);
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper8);
                        PlayerUtil.sendPacket(this.data.getPlayer(), wrapper9);
                        return;
                    });
                }
                if (Config.GHOST_BLOCK_SETBACK) {
                    final float yaw = this.data.getRotationProcessor().getYaw();
                    final float pitch = this.data.getRotationProcessor().getPitch();
                    final Location setbackLocation = new Location(this.world, this.setbackX, this.setbackY, this.setbackZ, yaw, pitch);
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().teleport(setbackLocation, PlayerTeleportEvent.TeleportCause.UNKNOWN));
                    this.ticksSinceGhostBlockSetback = 0;
                    this.lastGhostBlockSetback = Vulcan.INSTANCE.getTickManager().getTicks();
                }
                if (Config.GHOST_BLOCK_MESSAGE_ENABLED) {
                    this.data.getPlayer().sendMessage(ColorUtil.translate(Config.GHOST_BLOCK_MESSAGE));
                }
                if (Config.GHOST_BLOCK_STAFF_MESSAGE_ENABLED) {
                    Vulcan.INSTANCE.getAlertManager().sendMessage(Config.GHOST_BLOCK_STAFF_MESSAGE.replaceAll("%x%", Integer.toString(this.blockX)).replaceAll("%y%", Integer.toString(this.blockY)).replaceAll("%z%", Integer.toString(this.blockZ)).replaceAll("%ticks%", Integer.toString(this.serverAirTicks)).replaceAll("%world%", this.data.getPlayer().getWorld().getName()).replaceAll("%player%", this.data.getPlayer().getName()));
                }
                if (Config.GHOST_BLOCK_PRINT_TO_CONSOLE) {
                    Vulcan.INSTANCE.getPlugin().getLogger().log(Level.INFO, ColorUtil.translate(Config.GHOST_BLOCK_CONSOLE_MESSAGE.replaceAll("%ticks%", Integer.toString(this.serverAirTicks)).replaceAll("%player%", this.data.getPlayer().getName()).replaceAll("%world%", this.data.getPlayer().getWorld().getName()).replaceAll("%z%", Integer.toString(this.blockZ)).replaceAll("%y%", Integer.toString(this.blockY)).replaceAll("%x%", Integer.toString(this.blockX))));
                }
                this.serverAirTicks = 0;
                this.ghostBlockBuffer = 0.0;
            }
        }
        else if (this.ghostBlockBuffer > 0.0) {
            this.ghostBlockBuffer -= Config.GHOST_BLOCK_BUFFER_DECAY;
        }
    }
    
    public void handleTransaction(final WrappedPacketInTransaction wrapper) {
        if (this.queuedAbilities.containsKey(wrapper.getActionNumber())) {
            this.sinceFlyingTicks = 0;
            final WrappedPacketOutAbilities confirmation = this.queuedAbilities.get(wrapper.getActionNumber());
            if (confirmation == null) {
                return;
            }
            this.lastServerAbilities = Vulcan.INSTANCE.getTickManager().getTicks();
            this.allowFlight = confirmation.isFlightAllowed();
            this.walkSpeed = confirmation.getWalkSpeed() * 2.0f;
            this.flySpeed = confirmation.getFlySpeed() * 2.0f;
            this.queuedAbilities.remove(wrapper.getActionNumber());
        }
    }
    
    public void handlePong(final WrappedPacketInPong wrapper) {
        final short id = (short)wrapper.getId();
        if (this.queuedAbilities.containsKey(id)) {
            this.sinceFlyingTicks = 0;
            final WrappedPacketOutAbilities confirmation = this.queuedAbilities.get(id);
            if (confirmation == null) {
                return;
            }
            this.lastServerAbilities = Vulcan.INSTANCE.getTickManager().getTicks();
            this.allowFlight = confirmation.isFlightAllowed();
            this.walkSpeed = confirmation.getWalkSpeed() * 2.0f;
            this.flySpeed = confirmation.getFlySpeed() * 2.0f;
            this.queuedAbilities.remove(id);
        }
    }
    
    public void handleAbilities(final WrappedPacketOutAbilities wrapper) {
        ++this.abilitiesTransactionId;
        ++this.abilitiesPingId;
        if (this.abilitiesTransactionId > -29769) {
            this.abilitiesTransactionId = -30768;
        }
        if (this.abilitiesPingId > -29769) {
            this.abilitiesPingId = -30768;
        }
        if (ServerUtil.isHigherThan1_17()) {
            this.data.sendPing(this.abilitiesPingId);
        }
        else {
            this.data.sendTransaction(this.abilitiesTransactionId);
        }
        this.queuedAbilities.put(this.abilitiesTransactionId, wrapper);
    }
    
    public boolean isOnSoulSand() {
        if (ServerUtil.isHigherThan1_13()) {
            return this.nearbyBlocksModern != null && !this.nearbyBlocksModern.isEmpty() && BlockUtil.isSoulSandOnly(this.nearbyBlocksModern.get(16));
        }
        return this.nearbyBlocks != null && !this.nearbyBlocks.isEmpty() && BlockUtil.isSoulSandOnly(this.nearbyBlocks.get(16));
    }
    
    public boolean isOnHoney() {
        if (ServerUtil.isHigherThan1_13()) {
            return this.nearbyBlocksModern != null && !this.nearbyBlocksModern.isEmpty() && BlockUtil.isHoney(this.nearbyBlocksModern.get(16));
        }
        return this.nearbyBlocks != null && !this.nearbyBlocks.isEmpty() && BlockUtil.isHoney(this.nearbyBlocks.get(16));
    }
    
    public void setback() {
        if (this.y < this.setbackY && Config.SETBACK_LOWER_POSITION) {
            return;
        }
        this.sinceSetbackTicks = 0;
        Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().teleport(new Location(this.world, this.setbackX, this.setbackY, this.setbackZ, this.data.getRotationProcessor().getYaw(), this.data.getRotationProcessor().getPitch()), PlayerTeleportEvent.TeleportCause.UNKNOWN));
    }
    
    public void handleUnloadedChunk() {
        if (!Config.UNLOADED_CHUNK_SETBACK) {
            return;
        }
        final boolean exempt = this.data.getExemptProcessor().isExempt(ExemptType.JOINED, ExemptType.TELEPORT, ExemptType.WORLD_CHANGE, ExemptType.CHUNK, ExemptType.WORLD_CHANGE, ExemptType.FLIGHT, ExemptType.ELYTRA, ExemptType.GLIDING, ExemptType.SLOW_FALLING) || this.data.getActionProcessor().getPositionTicksExisted() < 20 || this.data.getActionProcessor().isTeleporting();
        final double diff = Math.abs(Math.abs(this.deltaY) - 0.09800000190734881);
        if (this.deltaY < -0.09000000357627869 && !this.clientOnGround && !exempt && diff < 0.001) {
            final double unloadedChunkBuffer = this.unloadedChunkBuffer + 1.0;
            this.unloadedChunkBuffer = unloadedChunkBuffer;
            if (unloadedChunkBuffer > Config.MAX_UNLOADED_CHUNK_TICKS) {
                final float yaw = this.data.getRotationProcessor().getYaw();
                final float pitch = this.data.getRotationProcessor().getPitch();
                final Location setbackLocation = new Location(this.world, this.setbackX, this.setbackY, this.setbackZ, yaw, pitch);
                Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().teleport(setbackLocation, PlayerTeleportEvent.TeleportCause.UNKNOWN));
                if (!Config.UNLOADED_CHUNK_SETBACK_MESSAGE.equals("")) {
                    Vulcan.INSTANCE.getAlertManager().sendMessage(Config.UNLOADED_CHUNK_SETBACK_MESSAGE.replaceAll("%x%", Integer.toString(this.blockX)).replaceAll("%y%", Integer.toString(this.blockY)).replaceAll("%z%", Integer.toString(this.blockZ)).replaceAll("%ticks%", Integer.toString(this.serverAirTicks)).replaceAll("%world%", this.data.getPlayer().getWorld().getName()).replaceAll("%player%", this.data.getPlayer().getName()));
                }
                this.unloadedChunkBuffer = 0.0;
            }
            else if (this.unloadedChunkBuffer > 0.0) {
                this.unloadedChunkBuffer -= 0.025;
            }
        }
    }
    
    public PlayerData getData() {
        return this.data;
    }
    
    public boolean isNearFence() {
        return this.nearFence;
    }
    
    public boolean isNearLiquid() {
        return this.nearLiquid;
    }
    
    public boolean isNearSlime() {
        return this.nearSlime;
    }
    
    public boolean isNearSoulSand() {
        return this.nearSoulSand;
    }
    
    public boolean isNearClimbable() {
        return this.nearClimbable;
    }
    
    public boolean isNearWeb() {
        return this.nearWeb;
    }
    
    public boolean isNearLilyPad() {
        return this.nearLilyPad;
    }
    
    public boolean isNearDaylightSensor() {
        return this.nearDaylightSensor;
    }
    
    public boolean isNearBrewingStand() {
        return this.nearBrewingStand;
    }
    
    public boolean isNearWall() {
        return this.nearWall;
    }
    
    public boolean isNearStair() {
        return this.nearStair;
    }
    
    public boolean isNearSlab() {
        return this.nearSlab;
    }
    
    public boolean isNearHoney() {
        return this.nearHoney;
    }
    
    public boolean isNearScaffolding() {
        return this.nearScaffolding;
    }
    
    public boolean isNearTrapdoor() {
        return this.nearTrapdoor;
    }
    
    public boolean isNearSkull() {
        return this.nearSkull;
    }
    
    public boolean isNearPortalFrame() {
        return this.nearPortalFrame;
    }
    
    public boolean isNearCampfire() {
        return this.nearCampfire;
    }
    
    public boolean isNearSweetBerries() {
        return this.nearSweetBerries;
    }
    
    public boolean isNearShulkerBox() {
        return this.nearShulkerBox;
    }
    
    public boolean isVehicleNearBubbleColumn() {
        return this.vehicleNearBubbleColumn;
    }
    
    public boolean isNearSnow() {
        return this.nearSnow;
    }
    
    public boolean isNearAnvil() {
        return this.nearAnvil;
    }
    
    public boolean isNearEndRod() {
        return this.nearEndRod;
    }
    
    public boolean isNearChain() {
        return this.nearChain;
    }
    
    public boolean isNearPiston() {
        return this.nearPiston;
    }
    
    public boolean isNearDoor() {
        return this.nearDoor;
    }
    
    public boolean isTouchingAir() {
        return this.touchingAir;
    }
    
    public boolean isNearCauldron() {
        return this.nearCauldron;
    }
    
    public boolean isNearLava() {
        return this.nearLava;
    }
    
    public boolean isNearHopper() {
        return this.nearHopper;
    }
    
    public boolean isNearFenceGate() {
        return this.nearFenceGate;
    }
    
    public boolean isNearFlowerPot() {
        return this.nearFlowerPot;
    }
    
    public boolean isOnClimbable() {
        return this.onClimbable;
    }
    
    public boolean isInWeb() {
        return this.inWeb;
    }
    
    public boolean isInBubbleColumn() {
        return this.inBubbleColumn;
    }
    
    public boolean isFullySubmerged() {
        return this.fullySubmerged;
    }
    
    public boolean isInLiquid() {
        return this.inLiquid;
    }
    
    public boolean isCollidingVertically() {
        return this.collidingVertically;
    }
    
    public boolean isNearKelp() {
        return this.nearKelp;
    }
    
    public boolean isFullyStuck() {
        return this.fullyStuck;
    }
    
    public boolean isPartiallyStuck() {
        return this.partiallyStuck;
    }
    
    public boolean isOnIce() {
        return this.onIce;
    }
    
    public boolean isClientOnGround() {
        return this.clientOnGround;
    }
    
    public boolean isMathematicallyOnGround() {
        return this.mathematicallyOnGround;
    }
    
    public boolean isNearShulker() {
        return this.nearShulker;
    }
    
    public boolean isNearBoat() {
        return this.nearBoat;
    }
    
    public boolean isNearBell() {
        return this.nearBell;
    }
    
    public boolean isNearBed() {
        return this.nearBed;
    }
    
    public boolean isNearCarpet() {
        return this.nearCarpet;
    }
    
    public boolean isNearLectern() {
        return this.nearLectern;
    }
    
    public boolean isNearTurtleEgg() {
        return this.nearTurtleEgg;
    }
    
    public boolean isNearSeaPickle() {
        return this.nearSeaPickle;
    }
    
    public boolean isNearIce() {
        return this.nearIce;
    }
    
    public boolean isNearConduit() {
        return this.nearConduit;
    }
    
    public boolean isCollidingHorizontally() {
        return this.collidingHorizontally;
    }
    
    public boolean isNearRepeater() {
        return this.nearRepeater;
    }
    
    public boolean isNearSolid() {
        return this.nearSolid;
    }
    
    public boolean isNearPane() {
        return this.nearPane;
    }
    
    public boolean isVehicleNearIce() {
        return this.vehicleNearIce;
    }
    
    public boolean isVehicleNearLiquid() {
        return this.vehicleNearLiquid;
    }
    
    public boolean isVehicleNearSlime() {
        return this.vehicleNearSlime;
    }
    
    public boolean isVehicleInAir() {
        return this.vehicleInAir;
    }
    
    public boolean isMoving() {
        return this.moving;
    }
    
    public boolean isNearFrostedIce() {
        return this.nearFrostedIce;
    }
    
    public boolean isNearBubbleColumn() {
        return this.nearBubbleColumn;
    }
    
    public boolean isNearFarmland() {
        return this.nearFarmland;
    }
    
    public boolean isLastClientOnGround() {
        return this.lastClientOnGround;
    }
    
    public boolean isAllowFlight() {
        return this.allowFlight;
    }
    
    public boolean isNearSeaGrass() {
        return this.nearSeaGrass;
    }
    
    public boolean isNearChest() {
        return this.nearChest;
    }
    
    public boolean isVehicleNearPiston() {
        return this.vehicleNearPiston;
    }
    
    public boolean isNearCake() {
        return this.nearCake;
    }
    
    public boolean isVehicleNearBed() {
        return this.vehicleNearBed;
    }
    
    public boolean isNearAmethyst() {
        return this.nearAmethyst;
    }
    
    public boolean isNearDripstone() {
        return this.nearDripstone;
    }
    
    public boolean isNearPowderSnow() {
        return this.nearPowderSnow;
    }
    
    public boolean isSetbackGround() {
        return this.setbackGround;
    }
    
    public boolean isCollidingEntity() {
        return this.collidingEntity;
    }
    
    public boolean isLastLastClientOnGround() {
        return this.lastLastClientOnGround;
    }
    
    public boolean isVehicleNearEntity() {
        return this.vehicleNearEntity;
    }
    
    public boolean isNearPath() {
        return this.nearPath;
    }
    
    public boolean isNearLantern() {
        return this.nearLantern;
    }
    
    public boolean isNearBorder() {
        return this.nearBorder;
    }
    
    public boolean isNearRail() {
        return this.nearRail;
    }
    
    public boolean isNearSign() {
        return this.nearSign;
    }
    
    public boolean isNearBamboo() {
        return this.nearBamboo;
    }
    
    public boolean isNearPressurePlate() {
        return this.nearPressurePlate;
    }
    
    public boolean isFuckedPosition() {
        return this.fuckedPosition;
    }
    
    public boolean isNearEnchantmentTable() {
        return this.nearEnchantmentTable;
    }
    
    public Location getFrom() {
        return this.from;
    }
    
    public Location getTo() {
        return this.to;
    }
    
    public short getAbilitiesTransactionId() {
        return this.abilitiesTransactionId;
    }
    
    public short getAbilitiesPingId() {
        return this.abilitiesPingId;
    }
    
    public Map<Short, WrappedPacketOutAbilities> getQueuedAbilities() {
        return this.queuedAbilities;
    }
    
    public boolean isFrozen() {
        return this.frozen;
    }
    
    public List<Entity> getNearbyEntities() {
        return this.nearbyEntities;
    }
    
    public Material getBlockBelow() {
        return this.blockBelow;
    }
    
    public Material getBlockBelow2() {
        return this.blockBelow2;
    }
    
    public Material getBlockBelow3() {
        return this.blockBelow3;
    }
    
    public List<Material> getNearbyBlocks() {
        return this.nearbyBlocks;
    }
    
    public List<Material> getBlocksBelow() {
        return this.blocksBelow;
    }
    
    public List<Material> getBlocksAbove() {
        return this.blocksAbove;
    }
    
    public List<Material> getBlocksAround() {
        return this.blocksAround;
    }
    
    public List<Material> getGlitchedBlocksAbove() {
        return this.glitchedBlocksAbove;
    }
    
    public List<Material> getNearbyBlocksModern() {
        return this.nearbyBlocksModern;
    }
    
    public List<Material> getVehicleBlocks() {
        return this.vehicleBlocks;
    }
    
    public List<Material> getBlocksUnderModern() {
        return this.blocksUnderModern;
    }
    
    public Material getBlockBelowModern() {
        return this.blockBelowModern;
    }
    
    public Material getBlockBelow2Modern() {
        return this.blockBelow2Modern;
    }
    
    public Material getBlockBelow3Modern() {
        return this.blockBelow3Modern;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public double getLastX() {
        return this.lastX;
    }
    
    public double getLastY() {
        return this.lastY;
    }
    
    public double getLastZ() {
        return this.lastZ;
    }
    
    public double getDeltaX() {
        return this.deltaX;
    }
    
    public double getDeltaY() {
        return this.deltaY;
    }
    
    public double getDeltaZ() {
        return this.deltaZ;
    }
    
    public double getDeltaXZ() {
        return this.deltaXZ;
    }
    
    public double getDeltaXYZ() {
        return this.deltaXYZ;
    }
    
    public double getLastDeltaX() {
        return this.lastDeltaX;
    }
    
    public double getLastDeltaZ() {
        return this.lastDeltaZ;
    }
    
    public double getLastDeltaY() {
        return this.lastDeltaY;
    }
    
    public double getLastDeltaXZ() {
        return this.lastDeltaXZ;
    }
    
    public double getVehicleX() {
        return this.vehicleX;
    }
    
    public double getVehicleY() {
        return this.vehicleY;
    }
    
    public double getVehicleZ() {
        return this.vehicleZ;
    }
    
    public double getLastVehicleX() {
        return this.lastVehicleX;
    }
    
    public double getLastVehicleY() {
        return this.lastVehicleY;
    }
    
    public double getLastVehicleZ() {
        return this.lastVehicleZ;
    }
    
    public double getLastVehicleDeltaX() {
        return this.lastVehicleDeltaX;
    }
    
    public double getLastVehicleDeltaY() {
        return this.lastVehicleDeltaY;
    }
    
    public double getLastVehicleDeltaZ() {
        return this.lastVehicleDeltaZ;
    }
    
    public double getVehicleDeltaX() {
        return this.vehicleDeltaX;
    }
    
    public double getVehicleDeltaY() {
        return this.vehicleDeltaY;
    }
    
    public double getVehicleDeltaZ() {
        return this.vehicleDeltaZ;
    }
    
    public double getVehicleDeltaXZ() {
        return this.vehicleDeltaXZ;
    }
    
    public double getLastOnGroundX() {
        return this.lastOnGroundX;
    }
    
    public double getLastOnGroundY() {
        return this.lastOnGroundY;
    }
    
    public double getLastOnGroundZ() {
        return this.lastOnGroundZ;
    }
    
    public double getSetbackX() {
        return this.setbackX;
    }
    
    public double getSetbackY() {
        return this.setbackY;
    }
    
    public double getSetbackZ() {
        return this.setbackZ;
    }
    
    public double getGhostBlockBuffer() {
        return this.ghostBlockBuffer;
    }
    
    public double getNewSetbackX() {
        return this.newSetbackX;
    }
    
    public double getNewSetbackY() {
        return this.newSetbackY;
    }
    
    public double getNewSetbackZ() {
        return this.newSetbackZ;
    }
    
    public double getGhostBlockSetbackX() {
        return this.ghostBlockSetbackX;
    }
    
    public double getGhostBlockSetbackY() {
        return this.ghostBlockSetbackY;
    }
    
    public double getGhostBlockSetbackZ() {
        return this.ghostBlockSetbackZ;
    }
    
    public double getLastLegitX() {
        return this.lastLegitX;
    }
    
    public double getLastLegitY() {
        return this.lastLegitY;
    }
    
    public double getLastLegitZ() {
        return this.lastLegitZ;
    }
    
    public double getLastLastX() {
        return this.lastLastX;
    }
    
    public double getLastLastY() {
        return this.lastLastY;
    }
    
    public double getLastLastZ() {
        return this.lastLastZ;
    }
    
    public double getFirstJoinX() {
        return this.firstJoinX;
    }
    
    public double getFirstJoinY() {
        return this.firstJoinY;
    }
    
    public double getFirstJoinZ() {
        return this.firstJoinZ;
    }
    
    public double getUnloadedChunkBuffer() {
        return this.unloadedChunkBuffer;
    }
    
    public int getSinceVehicleTicks() {
        return this.sinceVehicleTicks;
    }
    
    public int getSinceFlyingTicks() {
        return this.sinceFlyingTicks;
    }
    
    public int getSinceTrapdoorTicks() {
        return this.sinceTrapdoorTicks;
    }
    
    public int getClientAirTicks() {
        return this.clientAirTicks;
    }
    
    public int getClientGroundTicks() {
        return this.clientGroundTicks;
    }
    
    public int getSinceSwimmingTicks() {
        return this.sinceSwimmingTicks;
    }
    
    public int getSinceRiptideTicks() {
        return this.sinceRiptideTicks;
    }
    
    public int getSinceGlidingTicks() {
        return this.sinceGlidingTicks;
    }
    
    public int getSinceIceTicks() {
        return this.sinceIceTicks;
    }
    
    public int getSinceDolphinsGraceTicks() {
        return this.sinceDolphinsGraceTicks;
    }
    
    public int getSinceLevitationTicks() {
        return this.sinceLevitationTicks;
    }
    
    public int getSinceBubbleColumnTicks() {
        return this.sinceBubbleColumnTicks;
    }
    
    public int getGlidingTicks() {
        return this.glidingTicks;
    }
    
    public int getSinceSlowFallingTicks() {
        return this.sinceSlowFallingTicks;
    }
    
    public int getServerAirTicks() {
        return this.serverAirTicks;
    }
    
    public int getServerGroundTicks() {
        return this.serverGroundTicks;
    }
    
    public int getSinceWebTicks() {
        return this.sinceWebTicks;
    }
    
    public int getSinceLiquidTicks() {
        return this.sinceLiquidTicks;
    }
    
    public int getClimbableTicks() {
        return this.climbableTicks;
    }
    
    public int getSinceJumpBoostTicks() {
        return this.sinceJumpBoostTicks;
    }
    
    public int getSinceNearSlimeTicks() {
        return this.sinceNearSlimeTicks;
    }
    
    public int getSinceHoneyTicks() {
        return this.sinceHoneyTicks;
    }
    
    public int getSinceCollidingVerticallyTicks() {
        return this.sinceCollidingVerticallyTicks;
    }
    
    public int getSinceNearFenceTicks() {
        return this.sinceNearFenceTicks;
    }
    
    public int getSinceSoulSpeedTicks() {
        return this.sinceSoulSpeedTicks;
    }
    
    public int getSinceNearBedTicks() {
        return this.sinceNearBedTicks;
    }
    
    public int getLastPulledByFishingRod() {
        return this.lastPulledByFishingRod;
    }
    
    public int getVehicleTicks() {
        return this.vehicleTicks;
    }
    
    public int getSinceFireworkTicks() {
        return this.sinceFireworkTicks;
    }
    
    public int getSinceNearIceTicks() {
        return this.sinceNearIceTicks;
    }
    
    public int getSinceNearPistonTicks() {
        return this.sinceNearPistonTicks;
    }
    
    public int getLastWaterLogged() {
        return this.lastWaterLogged;
    }
    
    public int getSinceSpeedTicks() {
        return this.sinceSpeedTicks;
    }
    
    public int getLastAttributeModifier() {
        return this.lastAttributeModifier;
    }
    
    public int getSinceAroundSlimeTicks() {
        return this.sinceAroundSlimeTicks;
    }
    
    public int getSinceAroundPistonTicks() {
        return this.sinceAroundPistonTicks;
    }
    
    public int getSinceVehicleNearIceTicks() {
        return this.sinceVehicleNearIceTicks;
    }
    
    public int getSinceVehicleNearLiquidTicks() {
        return this.sinceVehicleNearLiquidTicks;
    }
    
    public int getSinceVehicleNearSlimeTicks() {
        return this.sinceVehicleNearSlimeTicks;
    }
    
    public int getSinceVehicleNearBubbleColumnTicks() {
        return this.sinceVehicleNearBubbleColumnTicks;
    }
    
    public int getBlockX() {
        return this.blockX;
    }
    
    public int getBlockY() {
        return this.blockY;
    }
    
    public int getBlockZ() {
        return this.blockZ;
    }
    
    public int getSinceNearFrostedIceTicks() {
        return this.sinceNearFrostedIceTicks;
    }
    
    public int getSinceNearScaffoldingTicks() {
        return this.sinceNearScaffoldingTicks;
    }
    
    public int getTicksSinceGhostBlockSetback() {
        return this.ticksSinceGhostBlockSetback;
    }
    
    public int getLastServerAbilities() {
        return this.lastServerAbilities;
    }
    
    public int getVehicleAirTicks() {
        return this.vehicleAirTicks;
    }
    
    public int getSinceNearShulkerBoxTicks() {
        return this.sinceNearShulkerBoxTicks;
    }
    
    public int getSinceElytraTicks() {
        return this.sinceElytraTicks;
    }
    
    public int getLastGhostBlockSetback() {
        return this.lastGhostBlockSetback;
    }
    
    public int getSinceNearFarmlandTicks() {
        return this.sinceNearFarmlandTicks;
    }
    
    public int getSinceNearStairTicks() {
        return this.sinceNearStairTicks;
    }
    
    public int getSinceNearSlabTicks() {
        return this.sinceNearSlabTicks;
    }
    
    public int getSinceEntityCollisionTicks() {
        return this.sinceEntityCollisionTicks;
    }
    
    public int getSinceVehicleNearPistonTicks() {
        return this.sinceVehicleNearPistonTicks;
    }
    
    public int getSinceFishingRodTicks() {
        return this.sinceFishingRodTicks;
    }
    
    public int getSinceAttributeModifierTicks() {
        return this.sinceAttributeModifierTicks;
    }
    
    public int getSinceHighFlySpeedTicks() {
        return this.sinceHighFlySpeedTicks;
    }
    
    public int getSinceAroundSlabTicks() {
        return this.sinceAroundSlabTicks;
    }
    
    public int getSinceVehicleNearBedTicks() {
        return this.sinceVehicleNearBedTicks;
    }
    
    public int getSinceWaterLogTicks() {
        return this.sinceWaterLogTicks;
    }
    
    public int getSinceGroundSpeedFailTicks() {
        return this.sinceGroundSpeedFailTicks;
    }
    
    public int getSinceNearClimbableTicks() {
        return this.sinceNearClimbableTicks;
    }
    
    public int getBoatsAround() {
        return this.boatsAround;
    }
    
    public int getSinceSetbackTicks() {
        return this.sinceSetbackTicks;
    }
    
    public int getSinceCollidingHorizontallyTicks() {
        return this.sinceCollidingHorizontallyTicks;
    }
    
    public int getSinceFlagTicks() {
        return this.sinceFlagTicks;
    }
    
    public int getSinceFuckingEntityTicks() {
        return this.sinceFuckingEntityTicks;
    }
    
    public int getSinceSpectatorTicks() {
        return this.sinceSpectatorTicks;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public Entity getBukkitVehicle() {
        return this.bukkitVehicle;
    }
    
    public Entity getLastBukkitVehicle() {
        return this.lastBukkitVehicle;
    }
    
    public float getMoveForward() {
        return this.moveForward;
    }
    
    public float getMoveStrafing() {
        return this.moveStrafing;
    }
    
    public float getWalkSpeed() {
        return this.walkSpeed;
    }
    
    public float getFlySpeed() {
        return this.flySpeed;
    }
    
    public boolean isExemptFlight() {
        return this.exemptFlight;
    }
    
    public boolean isExemptCreative() {
        return this.exemptCreative;
    }
    
    public boolean isExemptJoined() {
        return this.exemptJoined;
    }
    
    public boolean isExemptLiquid() {
        return this.exemptLiquid;
    }
    
    public boolean isExemptLevitation() {
        return this.exemptLevitation;
    }
    
    public boolean isExemptSlowFalling() {
        return this.exemptSlowFalling;
    }
    
    public boolean isExemptRiptide() {
        return this.exemptRiptide;
    }
    
    public boolean isExemptVehicle() {
        return this.exemptVehicle;
    }
    
    public boolean isExemptLenientScaffolding() {
        return this.exemptLenientScaffolding;
    }
    
    public boolean isExemptBukkitVelocity() {
        return this.exemptBukkitVelocity;
    }
    
    public boolean isExemptGliding() {
        return this.exemptGliding;
    }
    
    public boolean isExemptElytra() {
        return this.exemptElytra;
    }
    
    public boolean isExemptTeleport() {
        return this.exemptTeleport;
    }
    
    public boolean isExemptEnderPearl() {
        return this.exemptEnderPearl;
    }
    
    public boolean isExemptChunk() {
        return this.exemptChunk;
    }
    
    public boolean isExemptComboMode() {
        return this.exemptComboMode;
    }
    
    public boolean isExemptMythicMob() {
        return this.exemptMythicMob;
    }
    
    public boolean isExemptClimbable() {
        return this.exemptClimbable;
    }
    
    public void setNearFence(final boolean nearFence) {
        this.nearFence = nearFence;
    }
    
    public void setNearLiquid(final boolean nearLiquid) {
        this.nearLiquid = nearLiquid;
    }
    
    public void setNearSlime(final boolean nearSlime) {
        this.nearSlime = nearSlime;
    }
    
    public void setNearSoulSand(final boolean nearSoulSand) {
        this.nearSoulSand = nearSoulSand;
    }
    
    public void setNearClimbable(final boolean nearClimbable) {
        this.nearClimbable = nearClimbable;
    }
    
    public void setNearWeb(final boolean nearWeb) {
        this.nearWeb = nearWeb;
    }
    
    public void setNearLilyPad(final boolean nearLilyPad) {
        this.nearLilyPad = nearLilyPad;
    }
    
    public void setNearDaylightSensor(final boolean nearDaylightSensor) {
        this.nearDaylightSensor = nearDaylightSensor;
    }
    
    public void setNearBrewingStand(final boolean nearBrewingStand) {
        this.nearBrewingStand = nearBrewingStand;
    }
    
    public void setNearWall(final boolean nearWall) {
        this.nearWall = nearWall;
    }
    
    public void setNearStair(final boolean nearStair) {
        this.nearStair = nearStair;
    }
    
    public void setNearSlab(final boolean nearSlab) {
        this.nearSlab = nearSlab;
    }
    
    public void setNearHoney(final boolean nearHoney) {
        this.nearHoney = nearHoney;
    }
    
    public void setNearScaffolding(final boolean nearScaffolding) {
        this.nearScaffolding = nearScaffolding;
    }
    
    public void setNearTrapdoor(final boolean nearTrapdoor) {
        this.nearTrapdoor = nearTrapdoor;
    }
    
    public void setNearSkull(final boolean nearSkull) {
        this.nearSkull = nearSkull;
    }
    
    public void setNearPortalFrame(final boolean nearPortalFrame) {
        this.nearPortalFrame = nearPortalFrame;
    }
    
    public void setNearCampfire(final boolean nearCampfire) {
        this.nearCampfire = nearCampfire;
    }
    
    public void setNearSweetBerries(final boolean nearSweetBerries) {
        this.nearSweetBerries = nearSweetBerries;
    }
    
    public void setNearShulkerBox(final boolean nearShulkerBox) {
        this.nearShulkerBox = nearShulkerBox;
    }
    
    public void setVehicleNearBubbleColumn(final boolean vehicleNearBubbleColumn) {
        this.vehicleNearBubbleColumn = vehicleNearBubbleColumn;
    }
    
    public void setNearSnow(final boolean nearSnow) {
        this.nearSnow = nearSnow;
    }
    
    public void setNearAnvil(final boolean nearAnvil) {
        this.nearAnvil = nearAnvil;
    }
    
    public void setNearEndRod(final boolean nearEndRod) {
        this.nearEndRod = nearEndRod;
    }
    
    public void setNearChain(final boolean nearChain) {
        this.nearChain = nearChain;
    }
    
    public void setNearPiston(final boolean nearPiston) {
        this.nearPiston = nearPiston;
    }
    
    public void setNearDoor(final boolean nearDoor) {
        this.nearDoor = nearDoor;
    }
    
    public void setTouchingAir(final boolean touchingAir) {
        this.touchingAir = touchingAir;
    }
    
    public void setNearCauldron(final boolean nearCauldron) {
        this.nearCauldron = nearCauldron;
    }
    
    public void setNearLava(final boolean nearLava) {
        this.nearLava = nearLava;
    }
    
    public void setNearHopper(final boolean nearHopper) {
        this.nearHopper = nearHopper;
    }
    
    public void setNearFenceGate(final boolean nearFenceGate) {
        this.nearFenceGate = nearFenceGate;
    }
    
    public void setNearFlowerPot(final boolean nearFlowerPot) {
        this.nearFlowerPot = nearFlowerPot;
    }
    
    public void setOnClimbable(final boolean onClimbable) {
        this.onClimbable = onClimbable;
    }
    
    public void setInWeb(final boolean inWeb) {
        this.inWeb = inWeb;
    }
    
    public void setInBubbleColumn(final boolean inBubbleColumn) {
        this.inBubbleColumn = inBubbleColumn;
    }
    
    public void setFullySubmerged(final boolean fullySubmerged) {
        this.fullySubmerged = fullySubmerged;
    }
    
    public void setInLiquid(final boolean inLiquid) {
        this.inLiquid = inLiquid;
    }
    
    public void setCollidingVertically(final boolean collidingVertically) {
        this.collidingVertically = collidingVertically;
    }
    
    public void setNearKelp(final boolean nearKelp) {
        this.nearKelp = nearKelp;
    }
    
    public void setFullyStuck(final boolean fullyStuck) {
        this.fullyStuck = fullyStuck;
    }
    
    public void setPartiallyStuck(final boolean partiallyStuck) {
        this.partiallyStuck = partiallyStuck;
    }
    
    public void setOnIce(final boolean onIce) {
        this.onIce = onIce;
    }
    
    public void setClientOnGround(final boolean clientOnGround) {
        this.clientOnGround = clientOnGround;
    }
    
    public void setMathematicallyOnGround(final boolean mathematicallyOnGround) {
        this.mathematicallyOnGround = mathematicallyOnGround;
    }
    
    public void setNearShulker(final boolean nearShulker) {
        this.nearShulker = nearShulker;
    }
    
    public void setNearBoat(final boolean nearBoat) {
        this.nearBoat = nearBoat;
    }
    
    public void setNearBell(final boolean nearBell) {
        this.nearBell = nearBell;
    }
    
    public void setNearBed(final boolean nearBed) {
        this.nearBed = nearBed;
    }
    
    public void setNearCarpet(final boolean nearCarpet) {
        this.nearCarpet = nearCarpet;
    }
    
    public void setNearLectern(final boolean nearLectern) {
        this.nearLectern = nearLectern;
    }
    
    public void setNearTurtleEgg(final boolean nearTurtleEgg) {
        this.nearTurtleEgg = nearTurtleEgg;
    }
    
    public void setNearSeaPickle(final boolean nearSeaPickle) {
        this.nearSeaPickle = nearSeaPickle;
    }
    
    public void setNearIce(final boolean nearIce) {
        this.nearIce = nearIce;
    }
    
    public void setNearConduit(final boolean nearConduit) {
        this.nearConduit = nearConduit;
    }
    
    public void setCollidingHorizontally(final boolean collidingHorizontally) {
        this.collidingHorizontally = collidingHorizontally;
    }
    
    public void setNearRepeater(final boolean nearRepeater) {
        this.nearRepeater = nearRepeater;
    }
    
    public void setNearSolid(final boolean nearSolid) {
        this.nearSolid = nearSolid;
    }
    
    public void setNearPane(final boolean nearPane) {
        this.nearPane = nearPane;
    }
    
    public void setVehicleNearIce(final boolean vehicleNearIce) {
        this.vehicleNearIce = vehicleNearIce;
    }
    
    public void setVehicleNearLiquid(final boolean vehicleNearLiquid) {
        this.vehicleNearLiquid = vehicleNearLiquid;
    }
    
    public void setVehicleNearSlime(final boolean vehicleNearSlime) {
        this.vehicleNearSlime = vehicleNearSlime;
    }
    
    public void setVehicleInAir(final boolean vehicleInAir) {
        this.vehicleInAir = vehicleInAir;
    }
    
    public void setMoving(final boolean moving) {
        this.moving = moving;
    }
    
    public void setNearFrostedIce(final boolean nearFrostedIce) {
        this.nearFrostedIce = nearFrostedIce;
    }
    
    public void setNearBubbleColumn(final boolean nearBubbleColumn) {
        this.nearBubbleColumn = nearBubbleColumn;
    }
    
    public void setNearFarmland(final boolean nearFarmland) {
        this.nearFarmland = nearFarmland;
    }
    
    public void setLastClientOnGround(final boolean lastClientOnGround) {
        this.lastClientOnGround = lastClientOnGround;
    }
    
    public void setAllowFlight(final boolean allowFlight) {
        this.allowFlight = allowFlight;
    }
    
    public void setNearSeaGrass(final boolean nearSeaGrass) {
        this.nearSeaGrass = nearSeaGrass;
    }
    
    public void setNearChest(final boolean nearChest) {
        this.nearChest = nearChest;
    }
    
    public void setVehicleNearPiston(final boolean vehicleNearPiston) {
        this.vehicleNearPiston = vehicleNearPiston;
    }
    
    public void setNearCake(final boolean nearCake) {
        this.nearCake = nearCake;
    }
    
    public void setVehicleNearBed(final boolean vehicleNearBed) {
        this.vehicleNearBed = vehicleNearBed;
    }
    
    public void setNearAmethyst(final boolean nearAmethyst) {
        this.nearAmethyst = nearAmethyst;
    }
    
    public void setNearDripstone(final boolean nearDripstone) {
        this.nearDripstone = nearDripstone;
    }
    
    public void setNearPowderSnow(final boolean nearPowderSnow) {
        this.nearPowderSnow = nearPowderSnow;
    }
    
    public void setSetbackGround(final boolean setbackGround) {
        this.setbackGround = setbackGround;
    }
    
    public void setCollidingEntity(final boolean collidingEntity) {
        this.collidingEntity = collidingEntity;
    }
    
    public void setLastLastClientOnGround(final boolean lastLastClientOnGround) {
        this.lastLastClientOnGround = lastLastClientOnGround;
    }
    
    public void setVehicleNearEntity(final boolean vehicleNearEntity) {
        this.vehicleNearEntity = vehicleNearEntity;
    }
    
    public void setNearPath(final boolean nearPath) {
        this.nearPath = nearPath;
    }
    
    public void setNearLantern(final boolean nearLantern) {
        this.nearLantern = nearLantern;
    }
    
    public void setNearBorder(final boolean nearBorder) {
        this.nearBorder = nearBorder;
    }
    
    public void setNearRail(final boolean nearRail) {
        this.nearRail = nearRail;
    }
    
    public void setNearSign(final boolean nearSign) {
        this.nearSign = nearSign;
    }
    
    public void setNearBamboo(final boolean nearBamboo) {
        this.nearBamboo = nearBamboo;
    }
    
    public void setNearPressurePlate(final boolean nearPressurePlate) {
        this.nearPressurePlate = nearPressurePlate;
    }
    
    public void setFuckedPosition(final boolean fuckedPosition) {
        this.fuckedPosition = fuckedPosition;
    }
    
    public void setNearEnchantmentTable(final boolean nearEnchantmentTable) {
        this.nearEnchantmentTable = nearEnchantmentTable;
    }
    
    public void setFrom(final Location from) {
        this.from = from;
    }
    
    public void setTo(final Location to) {
        this.to = to;
    }
    
    public void setAbilitiesTransactionId(final short abilitiesTransactionId) {
        this.abilitiesTransactionId = abilitiesTransactionId;
    }
    
    public void setAbilitiesPingId(final short abilitiesPingId) {
        this.abilitiesPingId = abilitiesPingId;
    }
    
    public void setBlockBelow(final Material blockBelow) {
        this.blockBelow = blockBelow;
    }
    
    public void setBlockBelow2(final Material blockBelow2) {
        this.blockBelow2 = blockBelow2;
    }
    
    public void setBlockBelow3(final Material blockBelow3) {
        this.blockBelow3 = blockBelow3;
    }
    
    public void setNearbyBlocks(final List<Material> nearbyBlocks) {
        this.nearbyBlocks = nearbyBlocks;
    }
    
    public void setBlocksBelow(final List<Material> blocksBelow) {
        this.blocksBelow = blocksBelow;
    }
    
    public void setBlocksAbove(final List<Material> blocksAbove) {
        this.blocksAbove = blocksAbove;
    }
    
    public void setBlocksAround(final List<Material> blocksAround) {
        this.blocksAround = blocksAround;
    }
    
    public void setGlitchedBlocksAbove(final List<Material> glitchedBlocksAbove) {
        this.glitchedBlocksAbove = glitchedBlocksAbove;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setLastX(final double lastX) {
        this.lastX = lastX;
    }
    
    public void setLastY(final double lastY) {
        this.lastY = lastY;
    }
    
    public void setLastZ(final double lastZ) {
        this.lastZ = lastZ;
    }
    
    public void setDeltaX(final double deltaX) {
        this.deltaX = deltaX;
    }
    
    public void setDeltaY(final double deltaY) {
        this.deltaY = deltaY;
    }
    
    public void setDeltaZ(final double deltaZ) {
        this.deltaZ = deltaZ;
    }
    
    public void setDeltaXZ(final double deltaXZ) {
        this.deltaXZ = deltaXZ;
    }
    
    public void setDeltaXYZ(final double deltaXYZ) {
        this.deltaXYZ = deltaXYZ;
    }
    
    public void setLastDeltaX(final double lastDeltaX) {
        this.lastDeltaX = lastDeltaX;
    }
    
    public void setLastDeltaZ(final double lastDeltaZ) {
        this.lastDeltaZ = lastDeltaZ;
    }
    
    public void setLastDeltaY(final double lastDeltaY) {
        this.lastDeltaY = lastDeltaY;
    }
    
    public void setLastDeltaXZ(final double lastDeltaXZ) {
        this.lastDeltaXZ = lastDeltaXZ;
    }
    
    public void setVehicleX(final double vehicleX) {
        this.vehicleX = vehicleX;
    }
    
    public void setVehicleY(final double vehicleY) {
        this.vehicleY = vehicleY;
    }
    
    public void setVehicleZ(final double vehicleZ) {
        this.vehicleZ = vehicleZ;
    }
    
    public void setLastVehicleX(final double lastVehicleX) {
        this.lastVehicleX = lastVehicleX;
    }
    
    public void setLastVehicleY(final double lastVehicleY) {
        this.lastVehicleY = lastVehicleY;
    }
    
    public void setLastVehicleZ(final double lastVehicleZ) {
        this.lastVehicleZ = lastVehicleZ;
    }
    
    public void setLastVehicleDeltaX(final double lastVehicleDeltaX) {
        this.lastVehicleDeltaX = lastVehicleDeltaX;
    }
    
    public void setLastVehicleDeltaY(final double lastVehicleDeltaY) {
        this.lastVehicleDeltaY = lastVehicleDeltaY;
    }
    
    public void setLastVehicleDeltaZ(final double lastVehicleDeltaZ) {
        this.lastVehicleDeltaZ = lastVehicleDeltaZ;
    }
    
    public void setVehicleDeltaX(final double vehicleDeltaX) {
        this.vehicleDeltaX = vehicleDeltaX;
    }
    
    public void setVehicleDeltaY(final double vehicleDeltaY) {
        this.vehicleDeltaY = vehicleDeltaY;
    }
    
    public void setVehicleDeltaZ(final double vehicleDeltaZ) {
        this.vehicleDeltaZ = vehicleDeltaZ;
    }
    
    public void setVehicleDeltaXZ(final double vehicleDeltaXZ) {
        this.vehicleDeltaXZ = vehicleDeltaXZ;
    }
    
    public void setLastOnGroundX(final double lastOnGroundX) {
        this.lastOnGroundX = lastOnGroundX;
    }
    
    public void setLastOnGroundY(final double lastOnGroundY) {
        this.lastOnGroundY = lastOnGroundY;
    }
    
    public void setLastOnGroundZ(final double lastOnGroundZ) {
        this.lastOnGroundZ = lastOnGroundZ;
    }
    
    public void setSetbackX(final double setbackX) {
        this.setbackX = setbackX;
    }
    
    public void setSetbackY(final double setbackY) {
        this.setbackY = setbackY;
    }
    
    public void setSetbackZ(final double setbackZ) {
        this.setbackZ = setbackZ;
    }
    
    public void setGhostBlockBuffer(final double ghostBlockBuffer) {
        this.ghostBlockBuffer = ghostBlockBuffer;
    }
    
    public void setNewSetbackX(final double newSetbackX) {
        this.newSetbackX = newSetbackX;
    }
    
    public void setNewSetbackY(final double newSetbackY) {
        this.newSetbackY = newSetbackY;
    }
    
    public void setNewSetbackZ(final double newSetbackZ) {
        this.newSetbackZ = newSetbackZ;
    }
    
    public void setGhostBlockSetbackX(final double ghostBlockSetbackX) {
        this.ghostBlockSetbackX = ghostBlockSetbackX;
    }
    
    public void setGhostBlockSetbackY(final double ghostBlockSetbackY) {
        this.ghostBlockSetbackY = ghostBlockSetbackY;
    }
    
    public void setGhostBlockSetbackZ(final double ghostBlockSetbackZ) {
        this.ghostBlockSetbackZ = ghostBlockSetbackZ;
    }
    
    public void setLastLegitX(final double lastLegitX) {
        this.lastLegitX = lastLegitX;
    }
    
    public void setLastLegitY(final double lastLegitY) {
        this.lastLegitY = lastLegitY;
    }
    
    public void setLastLegitZ(final double lastLegitZ) {
        this.lastLegitZ = lastLegitZ;
    }
    
    public void setLastLastX(final double lastLastX) {
        this.lastLastX = lastLastX;
    }
    
    public void setLastLastY(final double lastLastY) {
        this.lastLastY = lastLastY;
    }
    
    public void setLastLastZ(final double lastLastZ) {
        this.lastLastZ = lastLastZ;
    }
    
    public void setFirstJoinX(final double firstJoinX) {
        this.firstJoinX = firstJoinX;
    }
    
    public void setFirstJoinY(final double firstJoinY) {
        this.firstJoinY = firstJoinY;
    }
    
    public void setFirstJoinZ(final double firstJoinZ) {
        this.firstJoinZ = firstJoinZ;
    }
    
    public void setUnloadedChunkBuffer(final double unloadedChunkBuffer) {
        this.unloadedChunkBuffer = unloadedChunkBuffer;
    }
    
    public void setSinceVehicleTicks(final int sinceVehicleTicks) {
        this.sinceVehicleTicks = sinceVehicleTicks;
    }
    
    public void setSinceFlyingTicks(final int sinceFlyingTicks) {
        this.sinceFlyingTicks = sinceFlyingTicks;
    }
    
    public void setSinceTrapdoorTicks(final int sinceTrapdoorTicks) {
        this.sinceTrapdoorTicks = sinceTrapdoorTicks;
    }
    
    public void setClientAirTicks(final int clientAirTicks) {
        this.clientAirTicks = clientAirTicks;
    }
    
    public void setClientGroundTicks(final int clientGroundTicks) {
        this.clientGroundTicks = clientGroundTicks;
    }
    
    public void setSinceSwimmingTicks(final int sinceSwimmingTicks) {
        this.sinceSwimmingTicks = sinceSwimmingTicks;
    }
    
    public void setSinceRiptideTicks(final int sinceRiptideTicks) {
        this.sinceRiptideTicks = sinceRiptideTicks;
    }
    
    public void setSinceGlidingTicks(final int sinceGlidingTicks) {
        this.sinceGlidingTicks = sinceGlidingTicks;
    }
    
    public void setSinceIceTicks(final int sinceIceTicks) {
        this.sinceIceTicks = sinceIceTicks;
    }
    
    public void setSinceDolphinsGraceTicks(final int sinceDolphinsGraceTicks) {
        this.sinceDolphinsGraceTicks = sinceDolphinsGraceTicks;
    }
    
    public void setSinceLevitationTicks(final int sinceLevitationTicks) {
        this.sinceLevitationTicks = sinceLevitationTicks;
    }
    
    public void setSinceBubbleColumnTicks(final int sinceBubbleColumnTicks) {
        this.sinceBubbleColumnTicks = sinceBubbleColumnTicks;
    }
    
    public void setGlidingTicks(final int glidingTicks) {
        this.glidingTicks = glidingTicks;
    }
    
    public void setSinceSlowFallingTicks(final int sinceSlowFallingTicks) {
        this.sinceSlowFallingTicks = sinceSlowFallingTicks;
    }
    
    public void setServerAirTicks(final int serverAirTicks) {
        this.serverAirTicks = serverAirTicks;
    }
    
    public void setServerGroundTicks(final int serverGroundTicks) {
        this.serverGroundTicks = serverGroundTicks;
    }
    
    public void setSinceWebTicks(final int sinceWebTicks) {
        this.sinceWebTicks = sinceWebTicks;
    }
    
    public void setSinceLiquidTicks(final int sinceLiquidTicks) {
        this.sinceLiquidTicks = sinceLiquidTicks;
    }
    
    public void setClimbableTicks(final int climbableTicks) {
        this.climbableTicks = climbableTicks;
    }
    
    public void setSinceJumpBoostTicks(final int sinceJumpBoostTicks) {
        this.sinceJumpBoostTicks = sinceJumpBoostTicks;
    }
    
    public void setSinceNearSlimeTicks(final int sinceNearSlimeTicks) {
        this.sinceNearSlimeTicks = sinceNearSlimeTicks;
    }
    
    public void setSinceHoneyTicks(final int sinceHoneyTicks) {
        this.sinceHoneyTicks = sinceHoneyTicks;
    }
    
    public void setSinceCollidingVerticallyTicks(final int sinceCollidingVerticallyTicks) {
        this.sinceCollidingVerticallyTicks = sinceCollidingVerticallyTicks;
    }
    
    public void setSinceNearFenceTicks(final int sinceNearFenceTicks) {
        this.sinceNearFenceTicks = sinceNearFenceTicks;
    }
    
    public void setSinceSoulSpeedTicks(final int sinceSoulSpeedTicks) {
        this.sinceSoulSpeedTicks = sinceSoulSpeedTicks;
    }
    
    public void setSinceNearBedTicks(final int sinceNearBedTicks) {
        this.sinceNearBedTicks = sinceNearBedTicks;
    }
    
    public void setLastPulledByFishingRod(final int lastPulledByFishingRod) {
        this.lastPulledByFishingRod = lastPulledByFishingRod;
    }
    
    public void setVehicleTicks(final int vehicleTicks) {
        this.vehicleTicks = vehicleTicks;
    }
    
    public void setSinceFireworkTicks(final int sinceFireworkTicks) {
        this.sinceFireworkTicks = sinceFireworkTicks;
    }
    
    public void setSinceNearIceTicks(final int sinceNearIceTicks) {
        this.sinceNearIceTicks = sinceNearIceTicks;
    }
    
    public void setSinceNearPistonTicks(final int sinceNearPistonTicks) {
        this.sinceNearPistonTicks = sinceNearPistonTicks;
    }
    
    public void setLastWaterLogged(final int lastWaterLogged) {
        this.lastWaterLogged = lastWaterLogged;
    }
    
    public void setSinceSpeedTicks(final int sinceSpeedTicks) {
        this.sinceSpeedTicks = sinceSpeedTicks;
    }
    
    public void setLastAttributeModifier(final int lastAttributeModifier) {
        this.lastAttributeModifier = lastAttributeModifier;
    }
    
    public void setSinceAroundSlimeTicks(final int sinceAroundSlimeTicks) {
        this.sinceAroundSlimeTicks = sinceAroundSlimeTicks;
    }
    
    public void setSinceAroundPistonTicks(final int sinceAroundPistonTicks) {
        this.sinceAroundPistonTicks = sinceAroundPistonTicks;
    }
    
    public void setSinceVehicleNearIceTicks(final int sinceVehicleNearIceTicks) {
        this.sinceVehicleNearIceTicks = sinceVehicleNearIceTicks;
    }
    
    public void setSinceVehicleNearLiquidTicks(final int sinceVehicleNearLiquidTicks) {
        this.sinceVehicleNearLiquidTicks = sinceVehicleNearLiquidTicks;
    }
    
    public void setSinceVehicleNearSlimeTicks(final int sinceVehicleNearSlimeTicks) {
        this.sinceVehicleNearSlimeTicks = sinceVehicleNearSlimeTicks;
    }
    
    public void setSinceVehicleNearBubbleColumnTicks(final int sinceVehicleNearBubbleColumnTicks) {
        this.sinceVehicleNearBubbleColumnTicks = sinceVehicleNearBubbleColumnTicks;
    }
    
    public void setBlockX(final int blockX) {
        this.blockX = blockX;
    }
    
    public void setBlockY(final int blockY) {
        this.blockY = blockY;
    }
    
    public void setBlockZ(final int blockZ) {
        this.blockZ = blockZ;
    }
    
    public void setSinceNearFrostedIceTicks(final int sinceNearFrostedIceTicks) {
        this.sinceNearFrostedIceTicks = sinceNearFrostedIceTicks;
    }
    
    public void setSinceNearScaffoldingTicks(final int sinceNearScaffoldingTicks) {
        this.sinceNearScaffoldingTicks = sinceNearScaffoldingTicks;
    }
    
    public void setTicksSinceGhostBlockSetback(final int ticksSinceGhostBlockSetback) {
        this.ticksSinceGhostBlockSetback = ticksSinceGhostBlockSetback;
    }
    
    public void setLastServerAbilities(final int lastServerAbilities) {
        this.lastServerAbilities = lastServerAbilities;
    }
    
    public void setVehicleAirTicks(final int vehicleAirTicks) {
        this.vehicleAirTicks = vehicleAirTicks;
    }
    
    public void setSinceNearShulkerBoxTicks(final int sinceNearShulkerBoxTicks) {
        this.sinceNearShulkerBoxTicks = sinceNearShulkerBoxTicks;
    }
    
    public void setSinceElytraTicks(final int sinceElytraTicks) {
        this.sinceElytraTicks = sinceElytraTicks;
    }
    
    public void setLastGhostBlockSetback(final int lastGhostBlockSetback) {
        this.lastGhostBlockSetback = lastGhostBlockSetback;
    }
    
    public void setSinceNearFarmlandTicks(final int sinceNearFarmlandTicks) {
        this.sinceNearFarmlandTicks = sinceNearFarmlandTicks;
    }
    
    public void setSinceNearStairTicks(final int sinceNearStairTicks) {
        this.sinceNearStairTicks = sinceNearStairTicks;
    }
    
    public void setSinceNearSlabTicks(final int sinceNearSlabTicks) {
        this.sinceNearSlabTicks = sinceNearSlabTicks;
    }
    
    public void setSinceEntityCollisionTicks(final int sinceEntityCollisionTicks) {
        this.sinceEntityCollisionTicks = sinceEntityCollisionTicks;
    }
    
    public void setSinceVehicleNearPistonTicks(final int sinceVehicleNearPistonTicks) {
        this.sinceVehicleNearPistonTicks = sinceVehicleNearPistonTicks;
    }
    
    public void setSinceFishingRodTicks(final int sinceFishingRodTicks) {
        this.sinceFishingRodTicks = sinceFishingRodTicks;
    }
    
    public void setSinceAttributeModifierTicks(final int sinceAttributeModifierTicks) {
        this.sinceAttributeModifierTicks = sinceAttributeModifierTicks;
    }
    
    public void setSinceHighFlySpeedTicks(final int sinceHighFlySpeedTicks) {
        this.sinceHighFlySpeedTicks = sinceHighFlySpeedTicks;
    }
    
    public void setSinceAroundSlabTicks(final int sinceAroundSlabTicks) {
        this.sinceAroundSlabTicks = sinceAroundSlabTicks;
    }
    
    public void setSinceVehicleNearBedTicks(final int sinceVehicleNearBedTicks) {
        this.sinceVehicleNearBedTicks = sinceVehicleNearBedTicks;
    }
    
    public void setSinceWaterLogTicks(final int sinceWaterLogTicks) {
        this.sinceWaterLogTicks = sinceWaterLogTicks;
    }
    
    public void setSinceGroundSpeedFailTicks(final int sinceGroundSpeedFailTicks) {
        this.sinceGroundSpeedFailTicks = sinceGroundSpeedFailTicks;
    }
    
    public void setSinceNearClimbableTicks(final int sinceNearClimbableTicks) {
        this.sinceNearClimbableTicks = sinceNearClimbableTicks;
    }
    
    public void setBoatsAround(final int boatsAround) {
        this.boatsAround = boatsAround;
    }
    
    public void setSinceSetbackTicks(final int sinceSetbackTicks) {
        this.sinceSetbackTicks = sinceSetbackTicks;
    }
    
    public void setSinceCollidingHorizontallyTicks(final int sinceCollidingHorizontallyTicks) {
        this.sinceCollidingHorizontallyTicks = sinceCollidingHorizontallyTicks;
    }
    
    public void setSinceFlagTicks(final int sinceFlagTicks) {
        this.sinceFlagTicks = sinceFlagTicks;
    }
    
    public void setSinceFuckingEntityTicks(final int sinceFuckingEntityTicks) {
        this.sinceFuckingEntityTicks = sinceFuckingEntityTicks;
    }
    
    public void setSinceSpectatorTicks(final int sinceSpectatorTicks) {
        this.sinceSpectatorTicks = sinceSpectatorTicks;
    }
    
    public void setWorld(final World world) {
        this.world = world;
    }
    
    public void setBukkitVehicle(final Entity bukkitVehicle) {
        this.bukkitVehicle = bukkitVehicle;
    }
    
    public void setLastBukkitVehicle(final Entity lastBukkitVehicle) {
        this.lastBukkitVehicle = lastBukkitVehicle;
    }
    
    public void setMoveForward(final float moveForward) {
        this.moveForward = moveForward;
    }
    
    public void setMoveStrafing(final float moveStrafing) {
        this.moveStrafing = moveStrafing;
    }
    
    public void setWalkSpeed(final float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }
    
    public void setFlySpeed(final float flySpeed) {
        this.flySpeed = flySpeed;
    }
    
    public void setExemptFlight(final boolean exemptFlight) {
        this.exemptFlight = exemptFlight;
    }
    
    public void setExemptCreative(final boolean exemptCreative) {
        this.exemptCreative = exemptCreative;
    }
    
    public void setExemptJoined(final boolean exemptJoined) {
        this.exemptJoined = exemptJoined;
    }
    
    public void setExemptLiquid(final boolean exemptLiquid) {
        this.exemptLiquid = exemptLiquid;
    }
    
    public void setExemptLevitation(final boolean exemptLevitation) {
        this.exemptLevitation = exemptLevitation;
    }
    
    public void setExemptSlowFalling(final boolean exemptSlowFalling) {
        this.exemptSlowFalling = exemptSlowFalling;
    }
    
    public void setExemptRiptide(final boolean exemptRiptide) {
        this.exemptRiptide = exemptRiptide;
    }
    
    public void setExemptVehicle(final boolean exemptVehicle) {
        this.exemptVehicle = exemptVehicle;
    }
    
    public void setExemptLenientScaffolding(final boolean exemptLenientScaffolding) {
        this.exemptLenientScaffolding = exemptLenientScaffolding;
    }
    
    public void setExemptBukkitVelocity(final boolean exemptBukkitVelocity) {
        this.exemptBukkitVelocity = exemptBukkitVelocity;
    }
    
    public void setExemptGliding(final boolean exemptGliding) {
        this.exemptGliding = exemptGliding;
    }
    
    public void setExemptElytra(final boolean exemptElytra) {
        this.exemptElytra = exemptElytra;
    }
    
    public void setExemptTeleport(final boolean exemptTeleport) {
        this.exemptTeleport = exemptTeleport;
    }
    
    public void setExemptEnderPearl(final boolean exemptEnderPearl) {
        this.exemptEnderPearl = exemptEnderPearl;
    }
    
    public void setExemptChunk(final boolean exemptChunk) {
        this.exemptChunk = exemptChunk;
    }
    
    public void setExemptComboMode(final boolean exemptComboMode) {
        this.exemptComboMode = exemptComboMode;
    }
    
    public void setExemptMythicMob(final boolean exemptMythicMob) {
        this.exemptMythicMob = exemptMythicMob;
    }
    
    public void setExemptClimbable(final boolean exemptClimbable) {
        this.exemptClimbable = exemptClimbable;
    }
    
    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }
    
    public void setNearbyEntities(final List<Entity> nearbyEntities) {
        this.nearbyEntities = nearbyEntities;
    }
    
    public void setNearbyBlocksModern(final List<Material> nearbyBlocksModern) {
        this.nearbyBlocksModern = nearbyBlocksModern;
    }
    
    public void setVehicleBlocks(final List<Material> vehicleBlocks) {
        this.vehicleBlocks = vehicleBlocks;
    }
    
    public void setBlocksUnderModern(final List<Material> blocksUnderModern) {
        this.blocksUnderModern = blocksUnderModern;
    }
    
    public void setBlockBelowModern(final Material blockBelowModern) {
        this.blockBelowModern = blockBelowModern;
    }
    
    public void setBlockBelow2Modern(final Material blockBelow2Modern) {
        this.blockBelow2Modern = blockBelow2Modern;
    }
    
    public void setBlockBelow3Modern(final Material blockBelow3Modern) {
        this.blockBelow3Modern = blockBelow3Modern;
    }
}
