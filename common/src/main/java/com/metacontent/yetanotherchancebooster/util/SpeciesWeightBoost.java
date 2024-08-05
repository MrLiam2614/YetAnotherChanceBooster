package com.metacontent.yetanotherchancebooster.util;

import org.jetbrains.annotations.NotNull;

public class SpeciesWeightBoost extends Boost {
    private final String species;

    public SpeciesWeightBoost(float amplifier, long duration, @NotNull String species) {
        super(amplifier, duration);
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public String toString() {
        return "species: " + species + " | amplifier: " + getAmplifier() + " | duration: " + getTicksRemain();
    }
}
