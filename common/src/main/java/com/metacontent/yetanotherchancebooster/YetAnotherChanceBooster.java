package com.metacontent.yetanotherchancebooster;

import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory;
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtensionRegistry;
import com.metacontent.yetanotherchancebooster.command.Commands;
import com.metacontent.yetanotherchancebooster.config.Config;
import com.metacontent.yetanotherchancebooster.influence.LabelWeightBooster;
import com.metacontent.yetanotherchancebooster.influence.ShinyBooster;
import com.metacontent.yetanotherchancebooster.influence.SpeciesWeightBooster;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YetAnotherChanceBooster {
    public static final String MOD_ID = "yetanotherchancebooster";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Config CONFIG = Config.init();

    public static void init() {
        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(ShinyBooster::new);
        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(SpeciesWeightBooster::new);
        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(LabelWeightBooster::new);

        PlayerDataExtensionRegistry.INSTANCE.register(BoostManagerData.NAME, BoostManagerData.class, false);

        Commands.init();
    }
}
