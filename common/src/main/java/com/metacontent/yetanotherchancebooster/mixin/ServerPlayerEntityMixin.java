package com.metacontent.yetanotherchancebooster.mixin;

import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.metacontent.yetanotherchancebooster.store.PlayerDataUtil;
import com.metacontent.yetanotherchancebooster.util.BoostManager;
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
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    @Unique private BoostManager yacb$boostManager;

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    protected void injectTick(CallbackInfo ci) {
        if (yacb$boostManager == null) {
            yacb$boostManager = BoostManagerData.getOrCreate((ServerPlayerEntity) (Object) this).getManager();
        }
        yacb$boostManager.tick();

        if (yacb$boostManager.isSaveNeeded()) {
            PlayerDataUtil.save((ServerPlayerEntity) (Object) this);
            yacb$boostManager.setShouldSave(false);
        }
    }

    @Inject(method = "onDisconnect", at = @At("TAIL"))
    protected void injectOnDisconnect(CallbackInfo ci) {
        PlayerDataUtil.onDisconnect((ServerPlayerEntity) (Object) this);
    }
}
