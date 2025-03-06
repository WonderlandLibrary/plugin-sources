package me.frep.vulcan.spigot.util;

import java.util.List;
import me.frep.vulcan.spigot.check.AbstractCheck;
import me.frep.vulcan.spigot.check.data.CheckData;
import org.apache.commons.lang.StringUtils;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.manager.CheckManager;
import me.frep.vulcan.spigot.config.Config;

public final class CacheUtil
{
    public static void resetConfigValues() {
        Config.CHECK_DATA.clear();
        Config.ENABLED_CHECKS.clear();
        Config.MAX_VIOLATIONS.clear();
        Config.ALERT_INTERVAL.clear();
        Config.MINIMUM_VL_TO_NOTIFY.clear();
        Config.PUNISHMENT_COMMANDS.clear();
        Config.PUNISHABLE.clear();
        Config.BROADCAST_PUNISHMENT.clear();
        Config.SETBACKS.clear();
        Config.MAX_BUFFERS.clear();
        Config.BUFFER_DECAYS.clear();
        Config.BUFFER_MULTIPLES.clear();
        Config.HOTBAR_SHUFFLE.clear();
        Config.HOTBAR_SHUFFLE_MINIMUM.clear();
        Config.HOTBAR_SHUFFLE_EVERY.clear();
        Config.RANDOM_ROTATION.clear();
        Config.RANDOM_ROTATION_MINIMUM.clear();
        Config.RANDOM_ROTATION_EVERY.clear();
        Config.DISCORD_ALERT_INTERVAL.clear();
        Config.MINIMUM_VIOLATIONS_TO_SETBACK.clear();
    }
    
    public static void updateCheckValues() {
        for (final Class clazz : CheckManager.CHECKS) {
            final CheckInfo checkInfo = (CheckInfo) clazz.getAnnotation(CheckInfo.class);
            String checkCategory = "";
            if (clazz.getName().contains("combat")) {
                checkCategory = "combat";
            }
            else if (clazz.getName().contains("movement")) {
                checkCategory = "movement";
            }
            else if (clazz.getName().contains("player")) {
                checkCategory = "player";
            }
            final String checkName = StringUtils.lowerCase(checkInfo.name()).replaceAll(" ", "");
            final char checkType = Character.toLowerCase(checkInfo.type());
            final String path = "checks." + checkCategory + "." + checkName + "." + checkType + ".";
            final boolean enabled = Config.getBoolean(path + "enabled");
            final int maxViolations = Config.getInt(path + "max-violations");
            final int minimumToNotify = Config.getInt(path + "dont-alert-until");
            final int violationsToNotify = Config.getInt(path + "alert-interval");
            final int maximumPing = Config.getInt(path + "maximum-ping");
            final double minimumTps = Config.getDouble(path + "minimum-tps");
            final List<String> punishmentCommands = Config.getStringList(path + "punishment-commands");
            final boolean punishable = Config.getBoolean(path + "punishable");
            final boolean broadcastPunishment = Config.getBoolean(path + "broadcast-punishment");
            final boolean setback = Config.getBoolean(path + "setback");
            final int discordAlertInterval = Config.getInt(path + "discord-alert-interval");
            final int minVlToSetback = Config.getInt(path + "min-vl-to-setback");
            Config.ENABLED_CHECKS.put(clazz.getSimpleName(), enabled);
            Config.MAX_VIOLATIONS.put(clazz.getSimpleName(), maxViolations);
            Config.MINIMUM_VL_TO_NOTIFY.put(clazz.getSimpleName(), minimumToNotify);
            Config.ALERT_INTERVAL.put(clazz.getSimpleName(), violationsToNotify);
            Config.MAXIMUM_PING.put(clazz.getSimpleName(), maximumPing);
            Config.MINIMUM_TPS.put(clazz.getSimpleName(), minimumTps);
            Config.PUNISHMENT_COMMANDS.put(clazz.getSimpleName(), punishmentCommands);
            Config.PUNISHABLE.put(clazz.getSimpleName(), punishable);
            Config.BROADCAST_PUNISHMENT.put(clazz.getSimpleName(), broadcastPunishment);
            Config.SETBACKS.put(clazz.getSimpleName(), setback);
            Config.DISCORD_ALERT_INTERVAL.put(clazz.getSimpleName(), discordAlertInterval);
            Config.MINIMUM_VIOLATIONS_TO_SETBACK.put(clazz.getSimpleName(), minVlToSetback);
            final int maxBuffer = Config.getInt(path + "buffer.max");
            final double bufferMultipleOnFlag = Config.getDouble(path + "buffer.multiple");
            final double bufferDecay = Config.getDouble(path + "buffer.decay");
            Config.MAX_BUFFERS.put(clazz.getSimpleName(), maxBuffer);
            Config.BUFFER_MULTIPLES.put(clazz.getSimpleName(), bufferMultipleOnFlag);
            Config.BUFFER_DECAYS.put(clazz.getSimpleName(), bufferDecay);
            final boolean hotbarShuffleEnabled = Config.getBoolean(path + "hotbar-shuffle.enabled");
            final int hotbarShuffleMinimum = Config.getInt(path + "hotbar-shuffle.minimum-vl-to-shuffle");
            final int hotbarShuffleEvery = Config.getInt(path + "hotbar-shuffle.shuffle-every");
            Config.HOTBAR_SHUFFLE.put(clazz.getSimpleName(), hotbarShuffleEnabled);
            Config.HOTBAR_SHUFFLE_MINIMUM.put(clazz.getSimpleName(), hotbarShuffleMinimum);
            Config.HOTBAR_SHUFFLE_EVERY.put(clazz.getSimpleName(), hotbarShuffleEvery);
            final boolean randomRotationEnabled = Config.getBoolean(path + "random-rotation.enabled");
            final int randomRotationMinimum = Config.getInt(path + "random-rotation.minimum-vl-to-randomly-rotate");
            final int randomRotationEvery = Config.getInt(path + "random-rotation.rotate-every");
            Config.RANDOM_ROTATION.put(clazz.getSimpleName(), randomRotationEnabled);
            Config.RANDOM_ROTATION_MINIMUM.put(clazz.getSimpleName(), randomRotationMinimum);
            Config.RANDOM_ROTATION_EVERY.put(clazz.getSimpleName(), randomRotationEvery);
            if (Config.ENABLE_API) {
                final CheckData checkData = new CheckData(clazz.getSimpleName());
                checkData.setEnabled(enabled);
                checkData.setPunishable(punishable);
                checkData.setBroadcastPunishment(broadcastPunishment);
                checkData.setHotbarShuffle(hotbarShuffleEnabled);
                checkData.setRandomRotation(randomRotationEnabled);
                checkData.setMaxViolations(maxViolations);
                checkData.setAlertInterval(violationsToNotify);
                checkData.setMinimumVlToNotify(minimumToNotify);
                checkData.setMaximumPing(maximumPing);
                checkData.setRandomRotationInterval(randomRotationEvery);
                checkData.setRandomRotationMinimumVl(randomRotationMinimum);
                checkData.setHotbarShuffleMinimumVl(hotbarShuffleMinimum);
                checkData.setHotbarShuffleInterval(hotbarShuffleEvery);
                checkData.setMinimumTps(minimumTps);
                checkData.setMaxBuffer(maxBuffer);
                checkData.setBufferDecay(bufferDecay);
                checkData.setBufferMultiple(bufferMultipleOnFlag);
                checkData.setPunishmentCommands(punishmentCommands);
                Config.CHECK_DATA.put(clazz.getSimpleName(), checkData);
            }
        }
    }
    
    private CacheUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
