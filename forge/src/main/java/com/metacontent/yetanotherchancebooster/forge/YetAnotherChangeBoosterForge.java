package com.metacontent.yetanotherchancebooster.forge;

import com.metacontent.yetanotherchancebooster.effect.ShinyBoostEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.common.Mod;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

@Mod(YetAnotherChanceBooster.MOD_ID)
public final class YetAnotherChangeBoosterForge {
    public YetAnotherChangeBoosterForge() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerEffects);

        YetAnotherChanceBooster.init();
    }

    private void registerEffects(RegisterEvent event) {
        event.register(RegistryKeys.STATUS_EFFECT, helper -> {
            helper.register(new Identifier(YetAnotherChanceBooster.MOD_ID, "shiny_boost_effect"),
                    YetAnotherChanceBooster.SHINY_BOOST_EFFECT);
        });
    }
}
