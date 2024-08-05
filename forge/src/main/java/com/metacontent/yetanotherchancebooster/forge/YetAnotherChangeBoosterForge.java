package com.metacontent.yetanotherchancebooster.forge;

import com.metacontent.yetanotherchancebooster.command.CommandRegistryProvider;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.registry.RegistryKeys;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

@Mod(YetAnotherChanceBooster.MOD_ID)
public final class YetAnotherChangeBoosterForge {
    public YetAnotherChangeBoosterForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        YetAnotherChanceBooster.init();
        eventBus.addListener(this::registerArguments);
        eventBus.addListener(this::registerCommands);
    }

    public void registerCommands(RegisterCommandsEvent event) {
        CommandRegistryProvider.registerCommands(command -> command.register(event.getDispatcher()));
    }

    public void registerArguments(RegisterEvent event) {
        event.register(RegistryKeys.COMMAND_ARGUMENT_TYPE, helper ->
                CommandRegistryProvider.registerArguments((identifier, argumentType) -> helper.register(identifier, ConstantArgumentSerializer.of(access -> argumentType))));
    }
}
