package com.metacontent.yetanotherchancebooster;

import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory;
import com.metacontent.yetanotherchancebooster.effect.ShinyBoostEffect;
import com.metacontent.yetanotherchancebooster.influence.ShinyBooster;

public final class YetAnotherChanceBooster {
    public static final String MOD_ID = "yetanotherchancebooster";

    public static final ShinyBoostEffect SHINY_BOOST_EFFECT = new ShinyBoostEffect();

    public static void init() {
        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(ShinyBooster::new);
    }
}
