package com.metacontent.yetanotherchancebooster;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawnerFactory;
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtensionRegistry;
import com.metacontent.yetanotherchancebooster.boost.Boost;
import com.metacontent.yetanotherchancebooster.command.Commands;
import com.metacontent.yetanotherchancebooster.config.Config;
import com.metacontent.yetanotherchancebooster.config.LanguageConfig;
import com.metacontent.yetanotherchancebooster.event.Events;
import com.metacontent.yetanotherchancebooster.influence.LabelWeightBooster;
import com.metacontent.yetanotherchancebooster.influence.ShinyBooster;
import com.metacontent.yetanotherchancebooster.influence.SpeciesWeightBooster;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import it.eblcraft.eblpokespawnbooster.Messages;
import kotlin.Unit;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YetAnotherChanceBooster {
    public static final String MOD_ID = "yetanotherchancebooster";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Config CONFIG = Config.init();
    public static final LanguageConfig LANGUAGE = LanguageConfig.init();

    public static void init() {
        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(ShinyBooster::new);
        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(SpeciesWeightBooster::new);
        PlayerSpawnerFactory.INSTANCE.getInfluenceBuilders().add(LabelWeightBooster::new);

        PlayerDataExtensionRegistry.INSTANCE.register(BoostManagerData.NAME, BoostManagerData.class, false);

        Commands.init();

        Events.BOOST_ENDED.subscribe(Priority.NORMAL, event -> {
            ServerPlayerEntity player = event.player();
            Boost boost = event.boost();
            Messages.sendBeautyMessage(String.format(LANGUAGE.prefix() + LANGUAGE.boostEndedMessage(), boost.info()), player);
            return Unit.INSTANCE;
        });
        Events.BOOST_STARTED.subscribe(Priority.NORMAL, Events.BOOST_STARTED_EVENT_HANDLER::handle);
    }
}
