package com.metacontent.yetanotherchancebooster.fabric;

import com.metacontent.yetanotherchancebooster.effect.ShinyBoostEffect;
import net.fabricmc.api.ModInitializer;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class YetAnotherChangeBoosterFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        YetAnotherChanceBooster.init();

        Registry.register(Registries.STATUS_EFFECT, new Identifier(YetAnotherChanceBooster.MOD_ID, "shiny_boost_effect"),
                new ShinyBoostEffect());
    }
}
