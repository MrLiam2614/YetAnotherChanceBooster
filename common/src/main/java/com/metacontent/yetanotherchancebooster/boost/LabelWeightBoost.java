package com.metacontent.yetanotherchancebooster.boost;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class LabelWeightBoost extends Boost {
    private final Set<String> labels;

    public LabelWeightBoost(float amplifier, long duration, @NotNull Set<String> labels) {
        super(amplifier, duration);
        this.labels = labels;
    }

    public Set<String> getLabels() {
        return labels;
    }

    @Override
    public String toString() {
        return "labels: " + labels + " | amplifier: " + getAmplifier() + " | duration: " + getTicksRemain();
    }

    @Override
    public String info() {
        return "Label weight boost " + labels.toString().replace("[", "(").replace("]", ")");
    }
}
