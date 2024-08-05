package com.metacontent.yetanotherchancebooster.influence;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnAction;
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class ShinyBooster extends SpawningBooster {
    public ShinyBooster(ServerPlayerEntity player) {
        super(player);
    }

    @Override
    public void affectAction(@NotNull SpawnAction<?> spawnAction) {
        if (spawnAction instanceof PokemonSpawnAction pokemonSpawnAction) {
            if (Boolean.TRUE.equals(pokemonSpawnAction.getProps().getShiny())) return;
            float amplifier = BoostManagerData.getOrCreate(player).getManager().getShinyAmplifier();
            int shinyRate = (int) Cobblemon.config.getShinyRate();
            int roll = player.getRandom().nextInt(shinyRate);
            boolean shiny = roll < amplifier;

            pokemonSpawnAction.getProps().setShiny(shiny);
        }
    }
}
