package com.metacontent.yetanotherchancebooster.boost;

public class ShinyBoost extends Boost {
    private boolean active = false;

    public ShinyBoost() {
        super(0, 0);
    }

    @Override
    public String toString() {
        return "amplifier: " + getAmplifier() + " | duration: " + getTicksRemain();
    }

    @Override
    public String info() {
        return "Shiny boost";
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
