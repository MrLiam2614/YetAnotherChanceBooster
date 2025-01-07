package com.metacontent.yetanotherchancebooster.command.boost;

import com.metacontent.yetanotherchancebooster.boost.BoostManager;
import com.metacontent.yetanotherchancebooster.event.BoostStartedEvent;
import com.metacontent.yetanotherchancebooster.event.Events;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class ShinyBoostCommand extends BoostCommand {
    public static final String SHINY = "shiny";

    @Override
    protected ArgumentBuilder<ServerCommandSource, LiteralArgumentBuilder<ServerCommandSource>> boost() {
        return CommandManager.literal(SHINY).executes(this::run);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity source = context.getSource().getPlayerOrThrow();
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        float amplifier = FloatArgumentType.getFloat(context, AMPLIFIER);
        long duration = LongArgumentType.getLong(context, DURATION);

        BoostManager manager = BoostManagerData.getOrCreate(player).getManager();
        manager.addShinyBoost(amplifier, duration);
        Events.BOOST_STARTED.emit(new BoostStartedEvent(player, manager.getShinyBoost(), source.getName().getString()));

        return 1;
    }
}
