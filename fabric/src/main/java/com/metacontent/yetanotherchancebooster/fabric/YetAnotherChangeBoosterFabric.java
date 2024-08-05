package com.metacontent.yetanotherchancebooster.fabric;

import com.metacontent.yetanotherchancebooster.command.CommandRegistryProvider;
import net.fabricmc.api.ModInitializer;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;

public final class YetAnotherChangeBoosterFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        YetAnotherChanceBooster.init();
        CommandRegistryProvider.registerCommands(command -> CommandRegistrationCallback.EVENT.register(command::register));
        CommandRegistryProvider.registerArguments((identifier, argumentType) ->
                ArgumentTypeRegistry.registerArgumentType(identifier, argumentType.getClass(), ConstantArgumentSerializer.of(access -> argumentType)));
    }
}
