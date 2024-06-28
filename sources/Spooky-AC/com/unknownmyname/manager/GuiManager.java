/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.manager;

import com.unknownmyname.gui.impl.CheckGui;
import com.unknownmyname.gui.impl.MainGui;
import com.unknownmyname.gui.impl.MouvementGui;
import com.unknownmyname.gui.impl.OtherGui;

public class GuiManager {
    private static /* synthetic */ GuiManager instance;
    private /* synthetic */ MouvementGui mouvementGui;
    private /* synthetic */ CheckGui checkGui;
    private /* synthetic */ MainGui mainGui;
    private /* synthetic */ OtherGui otherGui;

    public CheckGui getCheckGui() {
        return this.checkGui;
    }

    public MouvementGui getMovGui() {
        return this.mouvementGui;
    }

    public static GuiManager getInstance() {
        GuiManager guiManager;
        if (instance == null) {
            guiManager = instance = new GuiManager();
            "".length();
            if (false < false) {
                throw null;
            }
        } else {
            guiManager = instance;
        }
        GuiManager guiManager2 = guiManager;
        return guiManager2;
    }

    public OtherGui getOtherGui() {
        return this.otherGui;
    }

    public MainGui getMainGui() {
        return this.mainGui;
    }

    public void enable() {
        this.mainGui = new MainGui();
        this.checkGui = new CheckGui();
        this.mouvementGui = new MouvementGui();
        this.otherGui = new OtherGui();
    }
}

