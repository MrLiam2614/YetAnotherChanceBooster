package com.metacontent.yetanotherchancebooster.boost;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class BoostManager {
    public static final int SAVE_PERIOD = YetAnotherChanceBooster.CONFIG.savePeriod();

    private final ShinyBoost shinyBoost = new ShinyBoost();
    private final Map<String, SpeciesWeightBoost> speciesWeightBoosts = new HashMap<>();
    @JsonAdapter(LabelMapAdapter.class)
    private final Map<Set<String>, LabelWeightBoost> labelWeightBoosts = new HashMap<>();

    private boolean shouldSave = false;
    private int saveTimer = SAVE_PERIOD;

    public void tick() {
        tickAndCheckBoost(shinyBoost);

        speciesWeightBoosts.entrySet().removeIf(boostEntry -> {
            SpeciesWeightBoost boost = boostEntry.getValue();
            return tickAndCheckBoost(boost);
        });

        labelWeightBoosts.entrySet().removeIf(boostEntry -> {
            LabelWeightBoost boost = boostEntry.getValue();
            return tickAndCheckBoost(boost);
        });

        if (!shouldSave) {
            saveTimer--;
        }
        if (saveTimer <= 0) {
            shouldSave = true;
            saveTimer = SAVE_PERIOD;
        }
    }

    private boolean tickAndCheckBoost(Boost boost) {
        boost.tick();
        if (boost.isEnded()) {
            shouldSave = true;
        }
        return boost.isEnded();
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

    public float getWeightAmplifier(String species) {
        float amplifier = 1;
        Boost weightBoost = getBoost(species);
        if (weightBoost != null) {
            amplifier = weightBoost.getAmplifier();
        }
        return amplifier;
    }

    public float getWeightAmplifier(Set<String> labels) {
        Set<LabelWeightBoost> weightBoosts = getLabelWeightBoosts(labels);
        if (weightBoosts.isEmpty()) {
            return 1;
        }
        float amplifier = 0;
        for (LabelWeightBoost boost : weightBoosts) {
            amplifier += boost.getAmplifier();
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

    public Set<LabelWeightBoost> getLabelWeightBoosts(Set<String> labels) {
        return labelWeightBoosts.entrySet().stream().filter(entry -> labels.containsAll(entry.getKey())).map(Map.Entry::getValue).collect(Collectors.toSet());
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

    public void endShinyBoost() {
        shinyBoost.end();
    }

    public void endWeightBoost(String species) {
         Boost boost = getBoost(species);
         if (boost != null) {
             boost.end();
         }
    }

    public void endWeightBoost(Set<String> labels) {
        Boost boost = getBoost(labels);
        if (boost != null) {
            boost.end();
        }
    }

    public void endAll() {
        endShinyBoost();
        speciesWeightBoosts.keySet().forEach(this::endWeightBoost);
        labelWeightBoosts.keySet().forEach(this::endWeightBoost);
    }

    public static class LabelMapAdapter extends TypeAdapter<Map<Set<String>, LabelWeightBoost>> {
        private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        @Override
        public void write(JsonWriter out, Map<Set<String>, LabelWeightBoost> value) throws IOException {
            GSON.toJson(value, value.getClass(), out);
        }

        @Override
        public Map<Set<String>, LabelWeightBoost> read(JsonReader in) throws IOException {
            Type type = new TypeToken<Map<String, LabelWeightBoost>>(){}.getType();
            Map<String, LabelWeightBoost> rawMap = GSON.fromJson(in, type);
            return rawMap.entrySet().stream()
                    .map(entry -> {
                        String string = entry.getKey();
                        Set<String> labels = Set.of(string.substring(1, string.length() - 1).strip().split(","));
                        return Map.entry(labels, entry.getValue());
                    })
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
    }
}
