package com.metacontent.yetanotherchancebooster.util;

import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BoostManager {
    private final ServerPlayerEntity player;
    private final ShinyBoost shinyBoost = new ShinyBoost();
    private final Map<String, SpeciesWeightBoost> speciesWeightBoosts = new HashMap<>();
    private final Map<Set<String>, LabelWeightBoost> labelWeightBoosts = new HashMap<>();

    public BoostManager(ServerPlayerEntity player) {
        this.player = player;
    }

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
    }

    public void addBoost(SpeciesWeightBoost boost) {
        SpeciesWeightBoost existingBoost = getBoost(boost.getSpecies());
        if (existingBoost == null) {
            speciesWeightBoosts.put(boost.getSpecies(), boost);
        }
        else {
            existingBoost.update(boost);
        }
    }

    public void addBoost(LabelWeightBoost boost) {
        LabelWeightBoost existingBoost = getBoost(boost.getLabels());
        if (existingBoost == null) {
            labelWeightBoosts.put(boost.getLabels(), boost);
        }
        else {
            existingBoost.update(boost);
        }
    }

    public void addShinyBoost(float amplifier, long duration) {
        shinyBoost.update(amplifier, duration);
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
