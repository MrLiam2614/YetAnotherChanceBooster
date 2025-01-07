package com.metacontent.yetanotherchancebooster.boost;

public class ShinyBoost extends Boost {
    private boolean active = false;

    public ShinyBoost() {
        super(0, 0);
    }

    @Override
    public String toString() {
        return " &eMoltiplicatore: &b" + getAmplifier() + "\n   &eDurata: &b" + tickToTimeString();
    }

    @Override
    public String info() {
        return "&eBoost Shiny";
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
