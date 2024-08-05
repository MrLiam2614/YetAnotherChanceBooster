package com.metacontent.yetanotherchancebooster.util;

public class ShinyBoost extends Boost {
    public ShinyBoost() {
        super(0, 0);
    }

    @Override
    public String toString() {
        return "amplifier: " + getAmplifier() + " | duration: " + getTicksRemain();
    }
}
