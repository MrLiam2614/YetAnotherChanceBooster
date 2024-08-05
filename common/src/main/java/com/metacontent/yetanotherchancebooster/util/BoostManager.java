package com.metacontent.yetanotherchancebooster.util;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BoostManager {
    public static final int SAVE_PERIOD = 1200;

    private final ShinyBoost shinyBoost = new ShinyBoost();
    private final Map<String, SpeciesWeightBoost> speciesWeightBoosts = new HashMap<>();
    private final Map<Set<String>, LabelWeightBoost> labelWeightBoosts = new HashMap<>();

    private boolean shouldSave = false;
    private int saveTimer = SAVE_PERIOD;

    public void tick() {
        shinyBoost.tick();

        speciesWeightBoosts.entrySet().removeIf(boostEntry -> {
            SpeciesWeightBoost boost = boostEntry.getValue();
            boost.tick();
            return boost.isEnded();
        });

        labelWeightBoosts.entrySet().removeIf(boostEntry -> {
            LabelWeightBoost boost = boostEntry.getValue();
            boost.tick();
            return boost.isEnded();
        });

        if (!shouldSave) {
            saveTimer--;
        }
        if (saveTimer <= 0) {
            shouldSave = true;
            saveTimer = SAVE_PERIOD;
        }
    }

    public boolean isSaveNeeded() {
        return shouldSave;
    }

    public void setShouldSave(boolean shouldSave) {
        this.shouldSave = shouldSave;
    }

    public void addBoost(SpeciesWeightBoost boost) {
        SpeciesWeightBoost existingBoost = getBoost(boost.getSpecies());
        if (existingBoost == null) {
            speciesWeightBoosts.put(boost.getSpecies(), boost);
        }
        else {
            existingBoost.update(boost);
        }
        shouldSave = true;
    }

    public void addBoost(LabelWeightBoost boost) {
        LabelWeightBoost existingBoost = getBoost(boost.getLabels());
        if (existingBoost == null) {
            labelWeightBoosts.put(boost.getLabels(), boost);
        }
        else {
            existingBoost.update(boost);
        }
        shouldSave = true;
    }

    public void addShinyBoost(float amplifier, long duration) {
        shinyBoost.update(amplifier, duration);
        shouldSave = true;
    }

    public void addSpeciesWeightBoost(float amplifier, long duration, String species) {
        SpeciesWeightBoost boost = new SpeciesWeightBoost(amplifier, duration, species);
        addBoost(boost);
    }

    public void addLabelWeightBoost(float amplifier, long duration, Set<String> labels) {
        LabelWeightBoost boost = new LabelWeightBoost(amplifier, duration, labels);
        addBoost(boost);
    }

    public float getShinyAmplifier() {
        float amplifier = 0;
        if (!shinyBoost.isEnded()) {
            amplifier = shinyBoost.getAmplifier();
        }
        return amplifier;
    }

    public float getSpeciesWeightAmplifier(String species) {
        float amplifier = 1;
        Boost weightBoost = getBoost(species);
        if (weightBoost != null) {
            amplifier = weightBoost.getAmplifier();
        }
        return amplifier;
    }

    public float getLabelWeightAmplifier(Set<String> labels) {
        float amplifier = 1;
        Boost weightBoost = getBoost(labels);
        if (weightBoost != null) {
            amplifier = weightBoost.getAmplifier();
        }
        return amplifier;
    }

    @Nullable
    public SpeciesWeightBoost getBoost(String species) {
        return speciesWeightBoosts.get(species);
    }

    @Nullable
    public LabelWeightBoost getBoost(Set<String> labels) {
        return labelWeightBoosts.get(labels);
    }

    public String getShinyBoostString() {
        return shinyBoost.toString();
    }

    public List<String> listSpeciesWeightBoosts() {
        return speciesWeightBoosts.values().stream().map(SpeciesWeightBoost::toString).toList();
    }

    public List<String> listLabelWeightBoosts() {
        return labelWeightBoosts.values().stream().map(LabelWeightBoost::toString).toList();
    }
}
