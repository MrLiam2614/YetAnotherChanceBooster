package com.metacontent.yetanotherchancebooster.fabric;

import net.fabricmc.api.ModInitializer;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;

public final class YetAnotherChangeBoosterFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        YetAnotherChanceBooster.init();
    }
}
