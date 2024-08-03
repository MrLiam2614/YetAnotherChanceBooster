package com.metacontent.yetanotherchancebooster.forge;

import net.minecraftforge.fml.common.Mod;

import com.metacontent.yetanotherchancebooster.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModForge {
    public ExampleModForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
