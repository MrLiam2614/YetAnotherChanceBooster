package com.metacontent.yetanotherchancebooster.influence;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.api.spawning.context.SpawningContext;
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnDetail;
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail;
import com.cobblemon.mod.common.pokemon.Species;
import com.metacontent.yetanotherchancebooster.util.BoosterUser;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class LabelWeightBooster extends SpawningBooster {
    public LabelWeightBooster(ServerPlayerEntity player) {
        super(player);
    }

    @Override
    public float affectWeight(@NotNull SpawnDetail spawnDetail, @NotNull SpawningContext spawningContext, float weight) {
        if (spawnDetail instanceof PokemonSpawnDetail pokemonSpawnDetail) {
            String speciesName = pokemonSpawnDetail.getPokemon().getSpecies();
            if (speciesName == null) return weight;
            Species species = PokemonSpecies.INSTANCE.getByName(speciesName);
            if (species == null) return weight;
            Set<String> labels = species.getLabels();
            if (labels.isEmpty()) return weight;

            float amplifier = ((BoosterUser) player).yacb$getBoostManager().getLabelWeightAmplifier(labels);
            return weight * amplifier;
        }
        return super.affectWeight(spawnDetail, spawningContext, weight);
    }
}
