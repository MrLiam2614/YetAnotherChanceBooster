package com.metacontent.yetanotherchancebooster.event.handler;

import com.metacontent.yetanotherchancebooster.boost.Boost;
import com.metacontent.yetanotherchancebooster.event.BoostStartedEvent;
import kotlin.Unit;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class BoostStartedEventHandler implements EventHandler<BoostStartedEvent> {
    @Override
    public Unit handle(BoostStartedEvent event) {
        ServerPlayerEntity player = event.player();
        Boost boost = event.boost();
        long seconds = boost.getTicksRemain() / 20;
        player.sendMessage(Text.literal(boost.info() + " with amplifier " + boost.getAmplifier()
                + " has started and will last for " + secondsToTimeString(seconds)
                + " (source: " + event.source() + ")"));
        return Unit.INSTANCE;
    }

    public static String secondsToTimeString(long seconds) {
        long hours = seconds / 3600;
        long minutes = seconds / 60 % 60;
        seconds %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
