package com.elevatemc.anticheat.manager;


import com.elevatemc.anticheat.base.gui.IGUI;
import com.elevatemc.anticheat.base.gui.impl.MainGUI;
import com.elevatemc.anticheat.base.gui.impl.check.CheckGUI;
import com.elevatemc.anticheat.base.gui.impl.check.combat.CombatChecksGUI;
import com.elevatemc.anticheat.base.gui.impl.check.combat.impl.*;
import com.elevatemc.anticheat.base.gui.impl.check.misc.MiscChecksGUI;
import com.elevatemc.anticheat.base.gui.impl.check.misc.impl.*;
import com.elevatemc.anticheat.base.gui.impl.check.movement.MovementChecksGUI;
import com.elevatemc.anticheat.base.gui.impl.check.movement.impl.*;
import com.elevatemc.anticheat.base.gui.impl.misc.HoodSuspectsGUI;

import java.util.Arrays;
import java.util.List;


public class GuiManager {
    private final List<IGUI> guis;

    public GuiManager() {
        this.guis = Arrays.asList(
                new MainGUI(),
                new CheckGUI(),
                new CombatChecksGUI(),
                new HoodSuspectsGUI(),
                new AimAssistGUI(),
                new KillAuraGUI(),
                new VelocityGUI(),
                new RangeGUI(),
                new HitSelectGUI(),
                new MovementChecksGUI(),
                new FlyGUI(),
                new GroundGUI(),
                new MotionGUI(),
                new SpeedGUI(),
                new DisablerGUI(),
                new MiscChecksGUI(),
                new BadPacketsGUI(),
                new PingSpoofGUI(),
                new TimerGUI(),
                new BackTrackGUI(),
                new RefillGUI(),
                new Prediction()
        );
    }

    public <T extends IGUI> T getByClass(Class<T> clazz){
        return (T) guis.stream().filter(p -> p.getClass().equals(clazz)).findAny().orElse(null);
    }
}