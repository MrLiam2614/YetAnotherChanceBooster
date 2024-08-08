package com.metacontent.yetanotherchancebooster.command.boost;

import com.metacontent.yetanotherchancebooster.command.Command;
import com.metacontent.yetanotherchancebooster.command.Commands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public abstract class RemoveBoostCommand implements Command {
    public static final String REMOVE_COMMAND = "remove";

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(Commands.BASE_COMMAND
                .then(CommandManager.literal(REMOVE_COMMAND)
                        .then(CommandManager.argument(PLAYER, EntityArgumentType.player())
                                .then(remove()))));
    }

    protected abstract ArgumentBuilder<ServerCommandSource, ?> remove();
}
