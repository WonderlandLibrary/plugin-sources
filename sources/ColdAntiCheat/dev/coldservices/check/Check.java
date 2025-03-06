package dev.coldservices.check;

import lombok.Getter;
import lombok.Setter;
import dev.coldservices.CAC;
import dev.coldservices.alert.AlertManager;
import dev.coldservices.check.api.Buffer;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.api.enums.CheckState;
import dev.coldservices.config.ConfigManager;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.tracker.impl.PositionTracker;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.util.string.ChatUtil;
import org.atteo.classindex.IndexSubclasses;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
@IndexSubclasses
public abstract class Check {

    protected final PlayerData data;
    protected final Player player;

    private final CheckManifest info;

    private final String name, type, description;
    private final CheckState state;
    private final int decay;

    protected Buffer buffer;

    private final boolean enabled, punishing;
    private final List<String> punishments;
    private final int maxVl;

    @Setter
    private int violations;

    public Check(final PlayerData data) {
        this.data = data;
        this.player = data.getPlayer();

        if (this.getClass().isAnnotationPresent(CheckManifest.class)) {
            this.info = this.getClass().getDeclaredAnnotation(CheckManifest.class);

            this.name = this.info.name();
            this.type = this.info.type();
            this.description = this.info.description();

            this.state = this.info.state();

            this.decay = this.info.decay();

            this.buffer = new Buffer(this.info.maxBuffer());
        } else {
            this.info = null;
            throw new IllegalStateException("The CheckManifest annotation has not been added on " + this.getClass().getName());
        }

        final String name = this.name + "." + this.type;
        final ConfigManager config = CAC.get(ConfigManager.class);

        this.enabled = config.getEnabledMap().get(name);
        this.punishing = config.getPunishMap().get(name);
        this.punishments = config.getCommandsMap().get(name);
        this.maxVl = config.getMaxViolationsMap().get(name);
    }

    protected final void fail() {
        this.fail("No information.");
    }

    protected final void fail(final String debug) {
        ++this.violations;

        CAC.get(AlertManager.class).handleAlert(this, debug);

        if (this.violations >= this.maxVl) {
            CAC.get(AlertManager.class).handlePunishment(this);
        }
    }

    protected final void failNoBan(final String debug) {
        ++this.violations;

        CAC.get(AlertManager.class).handleAlert(this, debug);

    }

    protected final void executeSetbackToPosition(double x, double y, double z) {
        PositionTracker tracker = data.getPositionTracker();

        Location loc = new Location(data.getPlayer().getWorld(), x, y, z);

        data.getPlayer().teleport(loc);
    }

    protected final void executeSetback(boolean brokenYaw, boolean brokenPitch) {
        PositionTracker tracker = data.getPositionTracker();

        Location loc = tracker.getLastLocation();
        if(brokenYaw) {
            loc.setYaw((float) (loc.getYaw() - Math.random() * 180));
        }

        if(brokenPitch) {
            loc.setPitch((float) (0 + Math.random() * 90));
        }

        data.getPlayer().teleport(loc);
    }

    protected final void fail(final String debug, final Object... params) {
        this.fail(String.format(debug, params));
    }

    protected final boolean isExempt(final ExemptType... exemptTypes) {
        return data.getExemptTracker().isExempt(exemptTypes);
    }

    protected final void debug(final Object object, final Object... objects) {
        data.getPlayer().sendMessage(ChatUtil.translate(String.format("&6Cold &8> " + ChatColor.WHITE + object.toString(), objects)));
    }

    protected boolean canClick() {
        return !data.getActionTracker().isPlacing();
    }
}
