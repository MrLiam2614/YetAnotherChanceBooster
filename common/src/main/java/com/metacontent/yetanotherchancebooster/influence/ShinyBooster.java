package com.metacontent.yetanotherchancebooster.influence;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.spawning.SpawnBucket;
import com.cobblemon.mod.common.api.spawning.context.SpawningContext;
import com.cobblemon.mod.common.api.spawning.context.calculators.SpawningContextCalculator;
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnAction;
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction;
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail;
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence;
import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public class ShinyBooster implements SpawningInfluence {
    private final PlayerEntity player;

    public ShinyBooster(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void affectAction(@NotNull SpawnAction<?> spawnAction) {
        if (spawnAction instanceof PokemonSpawnAction pokemonSpawnAction) {
            if (Boolean.TRUE.equals(pokemonSpawnAction.getProps().getShiny())) return;

            StatusEffectInstance effect = player.getStatusEffect(YetAnotherChanceBooster.SHINY_BOOST_EFFECT);
            if (effect == null) return;

            int amplifier = effect.getAmplifier();
            int shinyRate = (int) Cobblemon.config.getShinyRate();
            int roll = player.getRandom().nextInt(shinyRate);
            boolean shiny = roll < amplifier * 100;

//            player.sendMessage(Text.literal("roll: " + roll + " | amplifier: " + amplifier * 100 + " | shiny: " + shiny));

            pokemonSpawnAction.getProps().setShiny(shiny);
        }
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean affectSpawnable(@NotNull SpawnDetail spawnDetail, @NotNull SpawningContext spawningContext) {
        return true;
    }

    @Override
    public float affectWeight(@NotNull SpawnDetail spawnDetail, @NotNull SpawningContext spawningContext, float v) {
        return v;
    }

    @Override
    public void affectSpawn(@NotNull Entity entity) {

    }

    @Override
    public float affectBucketWeight(@NotNull SpawnBucket spawnBucket, float v) {
        return v;
    }

    @Override
    public boolean isAllowedPosition(@NotNull ServerWorld serverWorld, @NotNull BlockPos blockPos, @NotNull SpawningContextCalculator<?, ?> spawningContextCalculator) {
        return true;
    }
}
