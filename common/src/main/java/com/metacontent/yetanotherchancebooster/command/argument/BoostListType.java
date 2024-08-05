package com.metacontent.yetanotherchancebooster.command.argument;

import net.minecraft.util.StringIdentifiable;

public enum BoostListType implements StringIdentifiable {
    ALL,
    SHINY,
    SPECIES,
    LABELS;

    @Override
    public String asString() {
        return this.name().toLowerCase();
    }
}
