package com.metacontent.yetanotherchancebooster.mixin;

import com.metacontent.yetanotherchancebooster.util.BoostManager;
import com.metacontent.yetanotherchancebooster.util.BoosterUser;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements BoosterUser {
    @Unique private final BoostManager yacb$boostManager = new BoostManager();

    @Override
    public BoostManager yacb$getBoostManager() {
        return yacb$boostManager;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    protected void injectTick(CallbackInfo ci) {
        yacb$boostManager.tick();
    }
}
