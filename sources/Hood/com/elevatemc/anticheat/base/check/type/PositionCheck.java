package com.elevatemc.anticheat.base.check.type;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.Check;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;

public abstract class PositionCheck extends Check {
    public PositionCheck(PlayerData playerData, String name, String desc, ViolationHandler violationHandler, Category category, SubCategory subCategory, int falseProbability) {
        super(playerData, name, desc, violationHandler, category, subCategory, falseProbability);
    }

    public abstract void handle();
}