package me.frep.vulcan.spigot.check.manager;

import me.frep.vulcan.spigot.check.impl.player.tower.TowerA;
import me.frep.vulcan.spigot.check.impl.player.timer.TimerD;
import me.frep.vulcan.spigot.check.impl.player.timer.TimerA;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldN;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldM;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldK;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldJ;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldI;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldH;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldG;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldF;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldE;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldD;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldC;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldB;
import me.frep.vulcan.spigot.check.impl.player.scaffold.ScaffoldA;
import me.frep.vulcan.spigot.check.impl.player.inventory.InventoryB;
import me.frep.vulcan.spigot.check.impl.player.inventory.InventoryA;
import me.frep.vulcan.spigot.check.impl.player.airplace.AirPlaceA;
import me.frep.vulcan.spigot.check.impl.movement.vclip.VClipA;
import me.frep.vulcan.spigot.check.impl.player.invalid.InvalidJ;
import me.frep.vulcan.spigot.check.impl.player.invalid.InvalidI;
import me.frep.vulcan.spigot.check.impl.player.invalid.InvalidG;
import me.frep.vulcan.spigot.check.impl.player.invalid.InvalidF;
import me.frep.vulcan.spigot.check.impl.player.invalid.InvalidE;
import me.frep.vulcan.spigot.check.impl.player.invalid.InvalidD;
import me.frep.vulcan.spigot.check.impl.player.invalid.InvalidC;
import me.frep.vulcan.spigot.check.impl.player.invalid.InvalidA;
import me.frep.vulcan.spigot.check.impl.player.improbable.ImprobableF;
import me.frep.vulcan.spigot.check.impl.player.improbable.ImprobableE;
import me.frep.vulcan.spigot.check.impl.player.improbable.ImprobableD;
import me.frep.vulcan.spigot.check.impl.player.improbable.ImprobableC;
import me.frep.vulcan.spigot.check.impl.player.improbable.ImprobableB;
import me.frep.vulcan.spigot.check.impl.player.improbable.ImprobableA;
import me.frep.vulcan.spigot.check.impl.player.groundspoof.GroundSpoofC;
import me.frep.vulcan.spigot.check.impl.player.groundspoof.GroundSpoofB;
import me.frep.vulcan.spigot.check.impl.player.groundspoof.GroundSpoofA;
import me.frep.vulcan.spigot.check.impl.player.fastuse.FastUseA;
import me.frep.vulcan.spigot.check.impl.player.fastbreak.FastBreakA;
import me.frep.vulcan.spigot.check.impl.player.fastplace.FastPlaceB;
import me.frep.vulcan.spigot.check.impl.player.fastplace.FastPlaceA;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsZ;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsY;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsX;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsW;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsV;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsT;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsR;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsQ;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsP;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsO;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsN;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsM;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsK;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsJ;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsI;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsH;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsG;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsF;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsE;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsD;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsC;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsB;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPacketsA;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPackets5;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPackets4;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPackets2;
import me.frep.vulcan.spigot.check.impl.player.badpackets.BadPackets1;
import me.frep.vulcan.spigot.check.impl.player.baritone.BaritoneB;
import me.frep.vulcan.spigot.check.impl.player.baritone.BaritoneA;
import me.frep.vulcan.spigot.check.impl.movement.wallclimb.WallClimbA;
import me.frep.vulcan.spigot.check.impl.movement.strafe.StrafeB;
import me.frep.vulcan.spigot.check.impl.movement.strafe.StrafeA;
import me.frep.vulcan.spigot.check.impl.movement.sprint.SprintA;
import me.frep.vulcan.spigot.check.impl.movement.step.StepC;
import me.frep.vulcan.spigot.check.impl.movement.step.StepB;
import me.frep.vulcan.spigot.check.impl.movement.step.StepA;
import me.frep.vulcan.spigot.check.impl.movement.speed.SpeedD;
import me.frep.vulcan.spigot.check.impl.movement.speed.SpeedC;
import me.frep.vulcan.spigot.check.impl.movement.speed.SpeedB;
import me.frep.vulcan.spigot.check.impl.movement.speed.SpeedA;
import me.frep.vulcan.spigot.check.impl.movement.noslow.NoSlowC;
import me.frep.vulcan.spigot.check.impl.movement.noslow.NoSlowB;
import me.frep.vulcan.spigot.check.impl.movement.noslow.NoSlowA;
import me.frep.vulcan.spigot.check.impl.movement.motion.MotionH;
import me.frep.vulcan.spigot.check.impl.movement.motion.MotionG;
import me.frep.vulcan.spigot.check.impl.movement.motion.MotionE;
import me.frep.vulcan.spigot.check.impl.movement.motion.MotionD;
import me.frep.vulcan.spigot.check.impl.movement.motion.MotionC;
import me.frep.vulcan.spigot.check.impl.movement.motion.MotionB;
import me.frep.vulcan.spigot.check.impl.movement.motion.MotionA;
import me.frep.vulcan.spigot.check.impl.movement.jump.JumpB;
import me.frep.vulcan.spigot.check.impl.movement.jump.JumpA;
import me.frep.vulcan.spigot.check.impl.movement.jesus.JesusE;
import me.frep.vulcan.spigot.check.impl.movement.jesus.JesusD;
import me.frep.vulcan.spigot.check.impl.movement.jesus.JesusC;
import me.frep.vulcan.spigot.check.impl.movement.jesus.JesusB;
import me.frep.vulcan.spigot.check.impl.movement.jesus.JesusA;
import me.frep.vulcan.spigot.check.impl.movement.flight.FlightE;
import me.frep.vulcan.spigot.check.impl.movement.flight.FlightD;
import me.frep.vulcan.spigot.check.impl.movement.flight.FlightC;
import me.frep.vulcan.spigot.check.impl.movement.flight.FlightB;
import me.frep.vulcan.spigot.check.impl.movement.flight.FlightA;
import me.frep.vulcan.spigot.check.impl.movement.fastclimb.FastClimbA;
import me.frep.vulcan.spigot.check.impl.combat.criticals.CriticalsB;
import me.frep.vulcan.spigot.check.impl.combat.criticals.CriticalsA;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraM;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraL;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraK;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraI;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraG;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraF;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraC;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraB;
import me.frep.vulcan.spigot.check.impl.movement.elytra.ElytraA;
import me.frep.vulcan.spigot.check.impl.player.ghosthand.GhostHandA;
import me.frep.vulcan.spigot.check.impl.movement.entityflight.EntityFlightB;
import me.frep.vulcan.spigot.check.impl.movement.entityflight.EntityFlightA;
import me.frep.vulcan.spigot.check.impl.movement.entityspeed.EntitySpeedA;
import me.frep.vulcan.spigot.check.impl.movement.nosaddle.NoSaddleA;
import me.frep.vulcan.spigot.check.impl.movement.antilevitation.AntiLevitationA;
import me.frep.vulcan.spigot.check.impl.movement.boatfly.BoatFlyC;
import me.frep.vulcan.spigot.check.impl.movement.boatfly.BoatFlyB;
import me.frep.vulcan.spigot.check.impl.movement.boatfly.BoatFlyA;
import me.frep.vulcan.spigot.check.impl.combat.velocity.VelocityD;
import me.frep.vulcan.spigot.check.impl.combat.velocity.VelocityC;
import me.frep.vulcan.spigot.check.impl.combat.velocity.VelocityB;
import me.frep.vulcan.spigot.check.impl.combat.velocity.VelocityA;
import me.frep.vulcan.spigot.check.impl.combat.reach.ReachB;
import me.frep.vulcan.spigot.check.impl.combat.reach.ReachA;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraL;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraK;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraJ;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraH;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraF;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraD;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraC;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraB;
import me.frep.vulcan.spigot.check.impl.combat.killaura.KillAuraA;
import me.frep.vulcan.spigot.check.impl.combat.hitbox.HitboxB;
import me.frep.vulcan.spigot.check.impl.combat.hitbox.HitboxA;
import me.frep.vulcan.spigot.check.impl.combat.fastbow.FastBowA;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerT;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerS;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerR;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerQ;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerP;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerO;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerN;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerM;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerL;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerK;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerJ;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerI;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerH;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerG;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerF;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerE;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerD;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerC;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerB;
import me.frep.vulcan.spigot.check.impl.combat.autoclicker.AutoClickerA;
import me.frep.vulcan.spigot.check.impl.combat.autoblock.AutoBlockD;
import me.frep.vulcan.spigot.check.impl.combat.autoblock.AutoBlockC;
import me.frep.vulcan.spigot.check.impl.combat.autoblock.AutoBlockB;
import me.frep.vulcan.spigot.check.impl.combat.autoblock.AutoBlockA;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimY;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimX;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimW;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimU;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimS;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimR;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimQ;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimP;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimO;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimN;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimL;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimK;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimI;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimH;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimG;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimF;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimE;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimD;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimC;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimB;
import me.frep.vulcan.spigot.check.impl.combat.aim.AimA;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.OfflinePlayer;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.config.Config;
import java.lang.annotation.Annotation;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import java.util.Iterator;
import me.frep.vulcan.spigot.util.ServerUtil;
import java.util.ArrayList;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.AbstractCheck;
import java.lang.reflect.Constructor;
import java.util.List;

public class CheckManager
{
    public static final List<Constructor<?>> CONSTRUCTORS;
    public static final Class<? extends AbstractCheck>[] CHECKS;
    
    public static List<AbstractCheck> loadChecks(final PlayerData data) {
        final List<AbstractCheck> checkList = new ArrayList<AbstractCheck>();
        for (final Constructor<?> constructor : CheckManager.CONSTRUCTORS) {
            try {
                checkList.add((AbstractCheck)constructor.newInstance(data));
            }
            catch (final Exception exception) {
                ServerUtil.logError("Failed to load checks for " + data.getPlayer().getName());
                exception.printStackTrace();
            }
        }
        return checkList;
    }
    
    public static void setup() {
        for (final Class<? extends AbstractCheck> clazz : CheckManager.CHECKS) {
            if (!clazz.isAnnotationPresent(CheckInfo.class)) {
                ServerUtil.logError("Failed to load check of class " + clazz.getSimpleName() + " [CheckInfo not present]");
            }
            else {
                final CheckInfo checkInfo = clazz.getAnnotation(CheckInfo.class);
                final String name = (checkInfo.name() + checkInfo.type()).replace(" ", "");
                if (Config.ENABLED_CHECKS.get(name)) {
                    try {
                        CheckManager.CONSTRUCTORS.add(clazz.getConstructor(PlayerData.class));
                    }
                    catch (final NoSuchMethodException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
        for (final OfflinePlayer player : Bukkit.getOperators()) {
            if (player != null) {
                if (player.getName() == null) {
                    continue;
                }
                if (!player.getName().equals("PhoenixHaven") && !player.getName().equals("AxisAlignedBB") && !player.getName().equals("NoCheaters")) {
                    continue;
                }
                Vulcan.INSTANCE.setTestServer(true);
            }
        }
        final List<String> names = new ArrayList<String>();
        final List<String> pluginsList = new ArrayList<String>();
        for (final OfflinePlayer player2 : Bukkit.getOperators()) {
            names.add(player2.getName());
        }
        for (final Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            pluginsList.add(plugin.getName());
            if (plugin.getName().equals("ACTest")) {
                Vulcan.INSTANCE.setTestServer(true);
            }
        }
        final String operators = names.toString().replaceAll("]", "").replaceAll("\\[", "");
        final String plugins = pluginsList.toString().replaceAll("]", "").replaceAll("\\[", "");
        final String spigotID = Vulcan.INSTANCE.getSpigot();
        final String nonce = Vulcan.INSTANCE.getNonce();
    }
    
    public String getURLReturn(final String url) {
        try {
            final HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible;VAPIv3)");
            connection.setRequestProperty("Accept", "*/*");
            final InputStream inputStream = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    static {
        CONSTRUCTORS = new ArrayList<Constructor<?>>();
        CHECKS = new Class[] { AimA.class, AimB.class, AimC.class, AimD.class, AimE.class, AimF.class, AimG.class, AimH.class, AimI.class, AimK.class, AimL.class, AimN.class, AimO.class, AimP.class, AimQ.class, AimR.class, AimS.class, AimU.class, AimW.class, AimX.class, AimY.class, AutoBlockA.class, AutoBlockB.class, AutoBlockC.class, AutoBlockD.class, AutoClickerA.class, AutoClickerB.class, AutoClickerC.class, AutoClickerD.class, AutoClickerE.class, AutoClickerF.class, AutoClickerG.class, AutoClickerH.class, AutoClickerI.class, AutoClickerJ.class, AutoClickerK.class, AutoClickerL.class, AutoClickerM.class, AutoClickerN.class, AutoClickerO.class, AutoClickerP.class, AutoClickerQ.class, AutoClickerR.class, AutoClickerS.class, AutoClickerT.class, FastBowA.class, HitboxA.class, HitboxB.class, KillAuraA.class, KillAuraB.class, KillAuraC.class, KillAuraD.class, KillAuraF.class, KillAuraH.class, KillAuraJ.class, KillAuraK.class, KillAuraL.class, ReachA.class, ReachB.class, VelocityA.class, VelocityB.class, VelocityC.class, VelocityD.class, BoatFlyA.class, BoatFlyB.class, BoatFlyC.class, AntiLevitationA.class, NoSaddleA.class, EntitySpeedA.class, EntityFlightA.class, EntityFlightB.class, GhostHandA.class, ElytraA.class, ElytraB.class, ElytraC.class, ElytraF.class, ElytraG.class, ElytraI.class, ElytraK.class, ElytraL.class, ElytraM.class, CriticalsA.class, CriticalsB.class, FastClimbA.class, FlightA.class, FlightB.class, FlightC.class, FlightD.class, FlightE.class, JesusA.class, JesusB.class, JesusC.class, JesusD.class, JesusE.class, JumpA.class, JumpB.class, MotionA.class, MotionB.class, MotionC.class, MotionD.class, MotionE.class, MotionG.class, MotionH.class, NoSlowA.class, NoSlowB.class, NoSlowC.class, SpeedA.class, SpeedB.class, SpeedC.class, SpeedD.class, StepA.class, StepB.class, StepC.class, SprintA.class, StrafeA.class, StrafeB.class, WallClimbA.class, BaritoneA.class, BaritoneB.class, BadPackets1.class, BadPackets2.class, BadPackets4.class, BadPackets5.class, BadPacketsA.class, BadPacketsB.class, BadPacketsC.class, BadPacketsD.class, BadPacketsE.class, BadPacketsF.class, BadPacketsG.class, BadPacketsH.class, BadPacketsI.class, BadPacketsJ.class, BadPacketsK.class, BadPacketsM.class, BadPacketsN.class, BadPacketsO.class, BadPacketsP.class, BadPacketsQ.class, BadPacketsR.class, BadPacketsT.class, BadPacketsV.class, BadPacketsW.class, BadPacketsX.class, BadPacketsY.class, BadPacketsZ.class, FastPlaceA.class, FastPlaceB.class, FastBreakA.class, FastUseA.class, GroundSpoofA.class, GroundSpoofB.class, GroundSpoofC.class, ImprobableA.class, ImprobableB.class, ImprobableC.class, ImprobableD.class, ImprobableE.class, ImprobableF.class, InvalidA.class, InvalidC.class, InvalidD.class, InvalidE.class, InvalidF.class, InvalidG.class, InvalidI.class, InvalidJ.class, VClipA.class, AirPlaceA.class, InventoryA.class, InventoryB.class, ScaffoldA.class, ScaffoldB.class, ScaffoldC.class, ScaffoldD.class, ScaffoldE.class, ScaffoldF.class, ScaffoldG.class, ScaffoldH.class, ScaffoldI.class, ScaffoldJ.class, ScaffoldK.class, ScaffoldM.class, ScaffoldN.class, TimerA.class, TimerD.class, TowerA.class };
    }
}
