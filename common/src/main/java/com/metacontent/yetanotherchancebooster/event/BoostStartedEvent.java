package com.metacontent.yetanotherchancebooster.event;

import com.metacontent.yetanotherchancebooster.boost.Boost;
import net.minecraft.server.network.ServerPlayerEntity;

public record BoostStartedEvent(ServerPlayerEntity player, Boost boost, String source) {
}
