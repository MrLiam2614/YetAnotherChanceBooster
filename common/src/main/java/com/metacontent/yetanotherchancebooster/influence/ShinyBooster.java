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
            pokemonSpawnAction.getProps().setShiny(
                player.getRandom().nextFloat() < (
                    BoostManagerData.getOrCreate(player).getManager().getShinyAmplifier() /
                        Cobblemon.config.getShinyRate()
                )
            );
        }
    }
}
