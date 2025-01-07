package com.metacontent.yetanotherchancebooster.boost;

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
        return " &ePokemon: &b" + species + "\n  &eMoltiplicatore: &b" + getAmplifier() + "\n   &eDurata: &b" + tickToTimeString();
    }

    @Override
    public String info() {
        return "&eBoost moltiplicatore pokemon &b" + species + "&e";
    }
}
