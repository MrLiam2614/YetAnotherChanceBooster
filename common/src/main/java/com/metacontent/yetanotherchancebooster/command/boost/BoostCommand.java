package com.metacontent.yetanotherchancebooster.command.boost;

import com.metacontent.yetanotherchancebooster.command.Command;
import com.metacontent.yetanotherchancebooster.command.Commands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public abstract class BoostCommand implements Command {
    public static final String BOOST_COMMAND = "boost";
    public static final String AMPLIFIER = "amplifier";
    public static final String DURATION = "duration";

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(Commands.BASE_COMMAND
                .then(CommandManager.literal(BOOST_COMMAND)
                        .then(CommandManager.argument(PLAYER, EntityArgumentType.player())
                                .then(CommandManager.argument(AMPLIFIER, FloatArgumentType.floatArg(0f))
                                        .then(CommandManager.argument(DURATION, LongArgumentType.longArg(0L))
                                                .then(boost()))))));
    }

    protected abstract ArgumentBuilder<ServerCommandSource, ?> boost();
}
