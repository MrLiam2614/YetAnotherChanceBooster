package com.metacontent.yetanotherchancebooster.boost;

public abstract class Boost {
    private float amplifier;
    private long ticksRemain;
    private boolean finite;

    public Boost(float amplifier, long duration) {
        this.amplifier = amplifier;
        ticksRemain = duration;
    }

    public void tick() {
        if (ticksRemain > 0) {
            ticksRemain--;
        }
    }

    public String tickToTimeString() {
        long seconds = getTicksRemain() / 20;
        long hours = seconds / 3600;
        long minutes = seconds / 60 % 60;
        seconds %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public boolean isEnded() {
        return ticksRemain <= 0 && finite;
    }

    public void setTicksRemain(long ticksRemain) {
        this.ticksRemain = ticksRemain;
    }

    public void addDuration(long value) {
        ticksRemain += value;
    }

    public long getTicksRemain() {
        return ticksRemain;
    }

    public float getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(float amplifier) {
        this.amplifier = amplifier;
    }

    public boolean isFinite() {
        return finite;
    }

    public void setFinite(boolean finite) {
        this.finite = finite;
    }

    public void update(float amplifier, long duration) {
        setAmplifier(amplifier);
        addDuration(duration);
    }

    public void update(Boost boost) {
        update(boost.amplifier, boost.ticksRemain);
    }

    public void end() {
        setAmplifier(0);
        setTicksRemain(0);
    }

    public abstract String info();
}
