package com.metacontent.yetanotherchancebooster.command;

import com.metacontent.yetanotherchancebooster.command.boost.*;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class Commands {
    public static final String BASE = "yacb";
    public static final int PERMISSION_LEVEL = 2;
    public static final LiteralArgumentBuilder<ServerCommandSource> BASE_COMMAND = CommandManager.literal(BASE)
            .requires(source -> source.hasPermissionLevel(PERMISSION_LEVEL));

    public static void init() {
        CommandRegistryProvider.add(new ShinyBoostCommand());
        CommandRegistryProvider.add(new SpeciesWeightBoostCommand());
        CommandRegistryProvider.add(new LabelWeightBoostCommand());
        CommandRegistryProvider.add(new RemoveAllBoostsCommand());
        CommandRegistryProvider.add(new RemoveShinyBoostCommand());
        CommandRegistryProvider.add(new RemoveSpeciesWeightBoostCommand());
        CommandRegistryProvider.add(new RemoveLabelWeightBoostCommand());
        CommandRegistryProvider.add(new ListBoostsCommand());
    }
}
