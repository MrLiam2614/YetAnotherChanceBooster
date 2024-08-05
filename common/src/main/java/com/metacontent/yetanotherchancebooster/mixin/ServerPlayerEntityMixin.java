package com.metacontent.yetanotherchancebooster.mixin;

import com.metacontent.yetanotherchancebooster.util.BoostManager;
import com.metacontent.yetanotherchancebooster.util.BoosterUser;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements BoosterUser {
    @Unique private final BoostManager yacb$boostManager = new BoostManager((ServerPlayerEntity) (Object) this);

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Override
    public BoostManager yacb$getBoostManager() {
        return yacb$boostManager;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    protected void injectTick(CallbackInfo ci) {
        yacb$boostManager.tick();
    }
}
