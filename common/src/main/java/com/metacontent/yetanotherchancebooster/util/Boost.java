package com.metacontent.yetanotherchancebooster.util;

public abstract class Boost {
    private float amplifier;
    private long ticksRemain;

    public Boost(float amplifier, long duration) {
        this.amplifier = amplifier;
        ticksRemain = duration;
    }

    public void tick() {
        if (ticksRemain > 0) {
            ticksRemain--;
        }
    }

    public boolean isEnded() {
        return ticksRemain <= 0;
    }

    public void setTicksRemain(long ticksRemain) {
        this.ticksRemain = ticksRemain;
    }

    public void addDuration(long value) {
        ticksRemain += value;
    }

    public float getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(float amplifier) {
        this.amplifier = amplifier;
    }

    public void update(float amplifier, long duration) {
        setAmplifier(amplifier);
        addDuration(duration);
    }
}
