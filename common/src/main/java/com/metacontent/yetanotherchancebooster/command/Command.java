package com.metacontent.yetanotherchancebooster.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public interface Command {
    void register(CommandDispatcher<ServerCommandSource> dispatcher);

    default void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        register(dispatcher);
    }

    int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException;
}
