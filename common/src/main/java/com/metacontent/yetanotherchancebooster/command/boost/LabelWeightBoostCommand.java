package com.metacontent.yetanotherchancebooster.command.boost;

import com.metacontent.yetanotherchancebooster.command.argument.LabelsArgumentType;
import com.metacontent.yetanotherchancebooster.util.BoosterUser;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Set;

public class LabelWeightBoostCommand extends BoostCommand {
    public static final String LABELS = "labels";

    @Override
    protected ArgumentBuilder<ServerCommandSource, RequiredArgumentBuilder<ServerCommandSource, Set<String>>> boost() {
        return CommandManager.argument(LABELS, LabelsArgumentType.labels()).executes(this::run);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        Set<String> labels = LabelsArgumentType.getLabels(context, LABELS);
        float amplifier = FloatArgumentType.getFloat(context, AMPLIFIER);
        long duration = LongArgumentType.getLong(context, DURATION);

        ((BoosterUser) player).yacb$getBoostManager().addLabelWeightBoost(amplifier, duration, labels);

        return 1;
    }
}
