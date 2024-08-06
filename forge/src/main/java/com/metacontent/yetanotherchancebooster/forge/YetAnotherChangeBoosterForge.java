package com.metacontent.yetanotherchancebooster.forge;

import com.metacontent.yetanotherchancebooster.command.CommandRegistryProvider;
import com.metacontent.yetanotherchancebooster.command.argument.BoostListArgumentType;
import com.metacontent.yetanotherchancebooster.command.argument.LabelsArgumentType;
import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.serialize.ArgumentSerializer;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(YetAnotherChanceBooster.MOD_ID)
public final class YetAnotherChangeBoosterForge {
    private final DeferredRegister<ArgumentSerializer<?, ?>> argumentTypes = DeferredRegister.create(RegistryKeys.COMMAND_ARGUMENT_TYPE,
            YetAnotherChanceBooster.MOD_ID);

    public YetAnotherChangeBoosterForge() {
        registerArgument(new Identifier(YetAnotherChanceBooster.MOD_ID, "boost_list"), BoostListArgumentType.class, ConstantArgumentSerializer.of(BoostListArgumentType::boostList));
        registerArgument(new Identifier(YetAnotherChanceBooster.MOD_ID, "labels"), LabelsArgumentType.class, ConstantArgumentSerializer.of(LabelsArgumentType::labels));
        argumentTypes.register(FMLJavaModLoadingContext.get().getModEventBus());
        YetAnotherChanceBooster.init();
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
    }

    public void registerCommands(RegisterCommandsEvent event) {
        CommandRegistryProvider.registerCommands(command -> command.register(event.getDispatcher()));
    }

    public <A extends ArgumentType<?>, T extends ArgumentSerializer.ArgumentTypeProperties<A>> void registerArgument(Identifier id, Class<A> aClass, ArgumentSerializer<A, T> serializer) {
        argumentTypes.register(id.getPath(), () -> ArgumentTypes.registerByClass(aClass, serializer));
    }
}
