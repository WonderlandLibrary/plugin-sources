package me.vagdedes.spartan.f;

import me.vagdedes.spartan.k.g.PluginTicks;
import org.bukkit.Location;
import java.util.Iterator;
import me.vagdedes.spartan.k.d.MathUtils;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.PlayerInventory;
import me.vagdedes.spartan.j.ProtocolSupport;
import me.vagdedes.spartan.c.ProtocolLib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.a.a.ItemClickUsage;
import java.util.Collection;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.j.NPC;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import org.bukkit.World;
import java.net.InetSocketAddress;
import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.entity.Entity;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class SpartanPlayer
{
    private static final UUID b;
    public static final ConcurrentHashMap<UUID, SpartanPlayer> d;
    private String name;
    private UUID uuid;
    private SpartanLocation a;
    private Entity a;
    private boolean allowFlight;
    private boolean t;
    private boolean dead;
    private boolean u;
    private PotionEffect[] a;
    private float walkSpeed;
    private float flySpeed;
    private SpartanInventory a;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private double at;
    private ItemStack itemInHand;
    private GameMode a;
    private double health;
    private boolean z;
    private boolean A;
    private boolean B;
    private boolean C;
    private InetSocketAddress a;
    private long h;
    private long i;
    private boolean D;
    private int fireTicks;
    private double au;
    private boolean E;
    private SpartanOpenInventory a;
    private int ping;
    private int O;
    private float fallDistance;
    private int foodLevel;
    private World world;
    private boolean p;
    CopyOnWriteArrayList<Entity> a;
    
    @Override
    public int hashCode() {
        return Objects.hash(this.uuid, this.name, Boolean.valueOf(this.p));
    }
    
    public static void clear() {
        SpartanPlayer.d.clear();
    }
    
    public static void a(final int n) {
        if (n == 1) {
            for (final SpartanPlayer spartanPlayer : NPC.a()) {
                final Player player = spartanPlayer.getPlayer();
                if (player != null) {
                    spartanPlayer.setFireTicks(player.getFireTicks());
                    spartanPlayer.setInvulnerable(Register.v1_9 && player.isInvulnerable());
                    spartanPlayer.setSleeping(player.isSleeping());
                    spartanPlayer.a(player.getActivePotionEffects());
                    spartanPlayer.g(ItemClickUsage.a(player) == 1.0f);
                    spartanPlayer.a(player.getOpenInventory());
                    spartanPlayer.setPing(LatencyUtils.c(player));
                }
            }
        }
        else if (n == 10) {
            for (final SpartanPlayer spartanPlayer2 : NPC.a()) {
                PlayerData.b(spartanPlayer2, true);
                PlayerData.az(spartanPlayer2);
                PlayerData.aw(spartanPlayer2);
                PlayerData.aD(spartanPlayer2);
                PlayerData.aC(spartanPlayer2);
            }
        }
        else if (n == 20) {
            for (final SpartanPlayer spartanPlayer3 : NPC.a()) {
                final Player player2 = spartanPlayer3.getPlayer();
                if (player2 != null) {
                    spartanPlayer3.setFlying(player2.isFlying());
                    spartanPlayer3.setHealth(player2.getHealth());
                    spartanPlayer3.a(player2.getInventory());
                    spartanPlayer3.setItemInHand(player2.getItemInHand());
                    spartanPlayer3.setFoodLevel(player2.getFoodLevel());
                    spartanPlayer3.a(player2.getEyeHeight());
                    spartanPlayer3.setAllowFlight(player2.getAllowFlight());
                    spartanPlayer3.setMaximumNoDamageTicks(player2.getMaximumNoDamageTicks());
                }
            }
        }
    }
    
    public void b(final int n) {
        if (n != 0) {
            final Player player = this.getPlayer();
            if (player == null) {
                final World world = (World)Bukkit.getWorlds().get(0);
                this.a = new SpartanLocation(0, null, world, world.getLoadedChunks()[0], 0.0, 0.0, 0.0, 0.0f, 0.0f, new SpartanBlock(world.getBlockAt(0, 0, 0)), null, false);
            }
            else {
                this.a = new SpartanLocation(this.uuid, player.getLocation(), n);
            }
        }
    }
    
    public SpartanPlayer(final Player player) {
        super();
        this.uuid = SpartanPlayer.b;
        if (player != null) {
            final UUID uniqueId = player.getUniqueId();
            final PlayerInventory inventory = player.getInventory();
            final InventoryView openInventory = player.getOpenInventory();
            final SpartanOpenInventory a = new SpartanOpenInventory(openInventory.getCursor(), openInventory.countSlots(), openInventory.getTitle(), openInventory.getTopInventory().getContents());
            this.name = player.getName();
            this.uuid = uniqueId;
            this.a = player.getVehicle();
            this.allowFlight = player.getAllowFlight();
            this.t = player.isFlying();
            this.dead = player.isDead();
            this.u = player.isSleeping();
            this.walkSpeed = player.getWalkSpeed();
            this.x = player.isSprinting();
            this.y = player.isSneaking();
            this.at = player.getEyeHeight();
            this.itemInHand = player.getItemInHand();
            this.flySpeed = player.getFlySpeed();
            this.a = player.getGameMode();
            this.health = player.getHealth();
            this.A = player.isOnline();
            this.a = player.getAddress();
            this.D = (ItemClickUsage.a(player) == 1.0f);
            this.fireTicks = player.getFireTicks();
            this.au = player.getLastDamage();
            this.a = a;
            this.O = player.getMaximumNoDamageTicks();
            this.fallDistance = player.getFallDistance();
            this.foodLevel = player.getFoodLevel();
            this.a = player.getActivePotionEffects().<PotionEffect>toArray(new PotionEffect[0]);
            this.B = ProtocolLib.b(player);
            this.C = ProtocolSupport.f(player);
            this.ping = LatencyUtils.c(player);
            this.world = player.getWorld();
            this.a = this.a(6.0);
            this.p = false;
            if (Register.v1_9) {
                this.v = player.isGliding();
                this.z = player.isInvulnerable();
                this.a = new SpartanInventory(inventory.getContents(), new ItemStack[] { inventory.getHelmet(), inventory.getChestplate(), inventory.getLeggings(), inventory.getBoots() }, this.itemInHand, inventory.getItemInOffHand());
            }
            else {
                this.v = false;
                this.z = false;
                this.a = new SpartanInventory(inventory.getContents(), new ItemStack[] { inventory.getHelmet(), inventory.getChestplate(), inventory.getLeggings(), inventory.getBoots() }, this.itemInHand, null);
            }
            if (Register.v1_13) {
                this.w = player.isSwimming();
                this.E = player.hasCooldown(this.itemInHand.getType());
            }
            else {
                this.w = false;
                this.E = false;
            }
            if (this.B) {
                this.h = System.currentTimeMillis();
                this.i = System.currentTimeMillis();
            }
            else {
                this.h = player.getLastPlayed();
                this.i = player.getFirstPlayed();
            }
            SpartanPlayer.d.put(uniqueId, this);
        }
    }
    
    public SpartanPlayer(final SpartanPlayer spartanPlayer) {
        super();
        this.uuid = SpartanPlayer.b;
        this.name = spartanPlayer.getName();
        this.uuid = spartanPlayer.getUniqueId();
        this.a = spartanPlayer.getVehicle();
        this.allowFlight = spartanPlayer.getAllowFlight();
        this.t = spartanPlayer.isFlying();
        this.dead = spartanPlayer.isDead();
        this.u = spartanPlayer.isSleeping();
        this.walkSpeed = spartanPlayer.getWalkSpeed();
        this.x = spartanPlayer.isSprinting();
        this.y = spartanPlayer.isSneaking();
        this.at = spartanPlayer.getEyeHeight();
        this.itemInHand = spartanPlayer.getItemInHand();
        this.flySpeed = spartanPlayer.getFlySpeed();
        this.a = spartanPlayer.getGameMode();
        this.health = spartanPlayer.getHealth();
        this.A = spartanPlayer.isOnline();
        this.a = spartanPlayer.getAddress();
        this.h = spartanPlayer.getLastPlayed();
        this.i = spartanPlayer.getFirstPlayed();
        this.D = spartanPlayer.m();
        this.fireTicks = spartanPlayer.getFireTicks();
        this.au = spartanPlayer.getLastDamage();
        this.a = spartanPlayer.a();
        this.O = spartanPlayer.getMaximumNoDamageTicks();
        this.fallDistance = spartanPlayer.getFallDistance();
        this.foodLevel = spartanPlayer.getFoodLevel();
        this.a = spartanPlayer.a();
        this.B = ProtocolLib.F(spartanPlayer);
        this.C = ProtocolSupport.ad(spartanPlayer);
        this.ping = spartanPlayer.getPing();
        this.world = spartanPlayer.getWorld();
        this.v = spartanPlayer.isGliding();
        this.z = spartanPlayer.isInvulnerable();
        this.a = spartanPlayer.a();
        this.w = spartanPlayer.isSwimming();
        this.E = spartanPlayer.n();
        this.p = true;
        this.a = this.a(6.0);
    }
    
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
    
    public SpartanPlayer a() {
        return new SpartanPlayer(this);
    }
    
    public SpartanPlayer b() {
        this.p = false;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public UUID getUniqueId() {
        return this.uuid;
    }
    
    public SpartanLocation a() {
        return (this.a == null) ? null : ((this.a != null && this.a instanceof LivingEntity) ? new SpartanLocation(this.uuid, this.a.getLocation(), this.a.j()) : this.a.b());
    }
    
    public int getFireTicks() {
        if (this.fireTicks <= 0 && this.a != null && (this.a.a().getType() == Material.FIRE || this.a.b().b(0.0, 1.0, 0.0).a().getType() == Material.FIRE)) {
            return 1;
        }
        return this.fireTicks;
    }
    
    public void setFireTicks(final int fireTicks) {
        this.fireTicks = fireTicks;
    }
    
    public boolean isOp() {
        final Player player = this.getPlayer();
        return player != null && player.isOp();
    }
    
    public boolean getAllowFlight() {
        return this.allowFlight;
    }
    
    public void setAllowFlight(final boolean allowFlight) {
        this.allowFlight = allowFlight;
    }
    
    public boolean isFlying() {
        return this.t;
    }
    
    public void setFlying(final boolean t) {
        this.t = t;
    }
    
    public boolean isDead() {
        return this.dead;
    }
    
    public void e(final boolean dead) {
        this.dead = dead;
    }
    
    public boolean isSleeping() {
        return this.u;
    }
    
    public void setSleeping(final boolean u) {
        this.u = u;
    }
    
    public boolean isSprinting() {
        return this.x;
    }
    
    public void setSprinting(final boolean x) {
        this.x = x;
    }
    
    public boolean isSneaking() {
        return this.y;
    }
    
    public void setSneaking(final boolean y) {
        this.y = y;
    }
    
    public boolean isOnGround() {
        final Player player = this.getPlayer();
        return player != null && player.isOnGround();
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public void setWorld(final World world) {
        this.world = world;
    }
    
    public boolean isBlocking() {
        return this.m();
    }
    
    public Entity getVehicle() {
        final Player player = this.getPlayer();
        return (player == null) ? null : player.getVehicle();
    }
    
    public void a(final Entity a) {
        this.a = a;
    }
    
    public float getWalkSpeed() {
        return this.walkSpeed;
    }
    
    public void setWalkSpeed(final float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }
    
    public float getFlySpeed() {
        return this.flySpeed;
    }
    
    public void setFlySpeed(final float flySpeed) {
        this.flySpeed = flySpeed;
    }
    
    public boolean isInvulnerable() {
        return this.z;
    }
    
    public void setInvulnerable(final boolean z) {
        this.z = z;
    }
    
    public int getFoodLevel() {
        return this.foodLevel;
    }
    
    public void setFoodLevel(final int foodLevel) {
        this.foodLevel = foodLevel;
    }
    
    public double getHealth() {
        return this.health;
    }
    
    public void setHealth(final double health) {
        this.health = health;
    }
    
    public float getFallDistance() {
        return this.fallDistance;
    }
    
    public void setFallDistance(final float fallDistance) {
        this.fallDistance = fallDistance;
    }
    
    public GameMode getGameMode() {
        return this.a;
    }
    
    public void setGameMode(final GameMode a) {
        this.a = a;
    }
    
    public double getEyeHeight() {
        return this.at;
    }
    
    public void a(final double at) {
        this.at = at;
    }
    
    public boolean isOnline() {
        return this.A;
    }
    
    public void f(final boolean a) {
        this.A = a;
    }
    
    public boolean k() {
        return this.B;
    }
    
    public boolean l() {
        return this.C;
    }
    
    public boolean isGliding() {
        return this.v;
    }
    
    public void setGliding(final boolean v) {
        this.v = v;
    }
    
    public boolean isSwimming() {
        return this.w;
    }
    
    public void setSwimming(final boolean w) {
        this.w = w;
    }
    
    public ItemStack getItemInHand() {
        return this.itemInHand;
    }
    
    public void setItemInHand(final ItemStack itemInHand) {
        this.itemInHand = itemInHand;
    }
    
    public SpartanOpenInventory a() {
        return this.a;
    }
    
    public void a(final InventoryView inventoryView) {
        this.a = new SpartanOpenInventory(inventoryView.getCursor(), inventoryView.countSlots(), inventoryView.getTitle(), inventoryView.getTopInventory().getContents());
    }
    
    public InetSocketAddress getAddress() {
        return this.a;
    }
    
    public long getLastPlayed() {
        return this.h;
    }
    
    public long getFirstPlayed() {
        return this.i;
    }
    
    public boolean m() {
        return this.D;
    }
    
    public void g(final boolean d) {
        this.D = d;
    }
    
    public int getPing() {
        return this.ping;
    }
    
    public void setPing(final int ping) {
        this.ping = ping;
    }
    
    public double getLastDamage() {
        return this.au;
    }
    
    public void setLastDamage(final double au) {
        this.au = au;
    }
    
    public int getMaximumNoDamageTicks() {
        return this.O;
    }
    
    public void setMaximumNoDamageTicks(final int o) {
        this.O = o;
    }
    
    public boolean n() {
        return this.E;
    }
    
    public void h(final boolean e) {
        this.E = e;
    }
    
    public PotionEffect[] a() {
        return this.a;
    }
    
    public void a(final Collection<PotionEffect> collection) {
        this.a = collection.<PotionEffect>toArray(new PotionEffect[0]);
    }
    
    public PotionEffect getPotionEffect(final PotionEffectType potionEffectType) {
        for (final PotionEffect potionEffect : this.a()) {
            if (potionEffect.getType().equals((Object)potionEffectType)) {
                return potionEffect;
            }
        }
        return new PotionEffect(potionEffectType, 0, 0, false, false);
    }
    
    public boolean hasPotionEffect(final PotionEffectType potionEffectType) {
        final PotionEffect[] a = this.a;
        for (int length = a.length, i = 0; i < length; ++i) {
            if (a[i].getType().equals((Object)potionEffectType)) {
                return true;
            }
        }
        return false;
    }
    
    public SpartanInventory a() {
        return this.a;
    }
    
    public void a(final PlayerInventory playerInventory) {
        if (Register.v1_9) {
            this.a = new SpartanInventory(playerInventory.getContents(), new ItemStack[] { playerInventory.getHelmet(), playerInventory.getChestplate(), playerInventory.getLeggings(), playerInventory.getBoots() }, this.itemInHand, playerInventory.getItemInOffHand());
        }
        else {
            this.a = new SpartanInventory(playerInventory.getContents(), new ItemStack[] { playerInventory.getHelmet(), playerInventory.getChestplate(), playerInventory.getLeggings(), playerInventory.getBoots() }, this.itemInHand, null);
        }
    }
    
    public List<Entity> getNearbyEntities(double max, final double b, final double b2) {
        if (this.a == null) {
            return new ArrayList<Entity>();
        }
        final ArrayList<Entity> list = new ArrayList<Entity>(this.a.size());
        final SpartanLocation a = this.a;
        if (a != null) {
            max = Math.max(Math.max(max, b), b2);
            for (final Entity e : this.a) {
                final Location location = e.getLocation();
                if (a.getWorld() == location.getWorld() && MathUtils.a(a.getX(), location.getX(), a.getY(), location.getY(), a.getZ(), location.getZ()) <= max) {
                    list.add(e);
                }
            }
        }
        return list;
    }
    
    private CopyOnWriteArrayList<Entity> a(final double n) {
        final Player player = this.getPlayer();
        final CopyOnWriteArrayList<Entity> list = new CopyOnWriteArrayList<Entity>();
        if (player == null || this.a == null || System.currentTimeMillis() - this.getLastPlayed() <= 3000L || PluginTicks.get() <= 60L || Thread.currentThread().getId() > 29L) {
            return list;
        }
        list.addAll(player.getNearbyEntities(n, n, n));
        return list;
    }
    
    public List<Entity> a(final double n) {
        if (this.a == null || n != 6.0) {
            final Player player = this.getPlayer();
            final CopyOnWriteArrayList<Entity> a = new CopyOnWriteArrayList<Entity>();
            if (player == null) {
                return a;
            }
            a.addAll(player.getNearbyEntities(n, n, n));
            this.a = a;
        }
        return this.a;
    }
    
    static {
        b = UUID.randomUUID();
        d = new ConcurrentHashMap<UUID, SpartanPlayer>();
    }
}
