package com.metacontent.yetanotherchancebooster.fabric;

import com.metacontent.yetanotherchancebooster.command.CommandRegistryProvider;
import com.metacontent.yetanotherchancebooster.command.argument.BoostListArgumentType;
import com.metacontent.yetanotherchancebooster.command.argument.LabelsArgumentType;
import net.fabricmc.api.ModInitializer;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;

public final class YetAnotherChangeBoosterFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        YetAnotherChanceBooster.init();
        CommandRegistryProvider.registerCommands(command -> CommandRegistrationCallback.EVENT.register(command::register));
        ArgumentTypeRegistry.registerArgumentType(new Identifier(YetAnotherChanceBooster.MOD_ID, "boost_list"), BoostListArgumentType.class, ConstantArgumentSerializer.of(BoostListArgumentType::boostList));
        ArgumentTypeRegistry.registerArgumentType(new Identifier(YetAnotherChanceBooster.MOD_ID, "labels"), LabelsArgumentType.class, ConstantArgumentSerializer.of(LabelsArgumentType::labels));
    }
}
