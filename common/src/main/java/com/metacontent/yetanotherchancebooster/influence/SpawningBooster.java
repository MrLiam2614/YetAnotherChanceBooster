package com.metacontent.yetanotherchancebooster.influence;

import com.cobblemon.mod.common.api.spawning.SpawnBucket;
import com.cobblemon.mod.common.api.spawning.context.SpawningContext;
import com.cobblemon.mod.common.api.spawning.context.calculators.SpawningContextCalculator;
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction;
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail;
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

public abstract class SpawningBooster implements SpawningInfluence {
    protected final ServerPlayerEntity player;

    public SpawningBooster(ServerPlayerEntity player) {
        this.player = player;
    }

    @Override
    public void affectAction(@NotNull SpawnAction<?> spawnAction) {

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
