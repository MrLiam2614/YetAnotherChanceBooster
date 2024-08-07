package com.metacontent.yetanotherchancebooster.command.boost;

import com.metacontent.yetanotherchancebooster.boost.LabelWeightBoost;
import com.metacontent.yetanotherchancebooster.command.argument.LabelsArgumentType;
import com.metacontent.yetanotherchancebooster.event.BoostStartedEvent;
import com.metacontent.yetanotherchancebooster.event.Events;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class LabelWeightBoostCommand extends BoostCommand {
    public static final String LABELS = "labels";

    @Override
    protected ArgumentBuilder<ServerCommandSource, RequiredArgumentBuilder<ServerCommandSource, String>> boost() {
        return CommandManager.argument(LABELS, StringArgumentType.greedyString()).executes(this::run);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity source = context.getSource().getPlayerOrThrow();
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        String argument = StringArgumentType.getString(context, LABELS);
        Set<String> labels = Set.of(argument.strip().split(",")).stream().map(String::strip).filter(s -> !s.isEmpty())
                 .collect(Collectors.toSet());
        float amplifier = FloatArgumentType.getFloat(context, AMPLIFIER);
        long duration = LongArgumentType.getLong(context, DURATION);

        LabelWeightBoost boost = new LabelWeightBoost(amplifier, duration, labels);
        BoostManagerData.getOrCreate(player).getManager().addBoost(boost);
        Events.BOOST_STARTED.emit(new BoostStartedEvent(player, boost, source.getEntityName()));

        return 1;
    }
}
