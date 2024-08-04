package com.metacontent.yetanotherchancebooster.util;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BoostManager {
    private final ShinyBoost shinyBoost = new ShinyBoost();
    private final List<SpeciesWeightBoost> speciesWeightBoosts = new ArrayList<>();
    private final List<LabelWeightBoost> labelWeightBoosts = new ArrayList<>();

    public void tick() {
        shinyBoost.tick();
        speciesWeightBoosts.removeIf(boost -> {
            boost.tick();
            return boost.isEnded();
        });
        labelWeightBoosts.removeIf(boost -> {
            boost.tick();
            return boost.isEnded();
        });
    }

    public void addShinyBoost(float amplifier, long duration) {
        shinyBoost.update(amplifier, duration);
    }

    public void addSpeciesWeightBoost(float amplifier, long duration, String species) {
        SpeciesWeightBoost weightBoost = getBoost(species);
        if (weightBoost == null) {
            speciesWeightBoosts.add(new SpeciesWeightBoost(amplifier, duration, species));
        }
        else {
            weightBoost.update(amplifier, duration);
        }
    }

    public void addLabelWeightBoost(float amplifier, long duration, Set<String> labels) {
        LabelWeightBoost weightBoost = getBoost(labels);
        if (weightBoost == null) {
            labelWeightBoosts.add(new LabelWeightBoost(amplifier, duration, labels));
        }
        else {
            weightBoost.update(amplifier, duration);
        }
    }

    public float getShinyAmplifier() {
        return shinyBoost.getAmplifier();
    }

    public float getWeightAmplifier(String species) {
        float amplifier = 0;
        Boost weightBoost = getBoost(species);
        if (weightBoost != null) {
            amplifier = weightBoost.getAmplifier();
        }
        return amplifier;
    }

    public float getWeightAmplifier(Set<String> labels) {
        float amplifier = 0;
        Boost weightBoost = getBoost(labels);
        if (weightBoost != null) {
            amplifier = weightBoost.getAmplifier();
        }
        return amplifier;
    }

    @Nullable
    public SpeciesWeightBoost getBoost(String species) {
        return speciesWeightBoosts.stream().filter(boost -> boost.getSpecies().equals(species)).findFirst().orElse(null);
    }

    @Nullable
    public LabelWeightBoost getBoost(Set<String> labels) {
        return labelWeightBoosts.stream().filter(boost -> boost.getLabels().equals(labels)).findFirst().orElse(null);
    }
}
