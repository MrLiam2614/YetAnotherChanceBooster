package com.metacontent.yetanotherchancebooster.command.boost;

import com.metacontent.yetanotherchancebooster.command.argument.LabelsArgumentType;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class RemoveLabelWeightBoostCommand extends RemoveBoostCommand {
    @Override
    protected ArgumentBuilder<ServerCommandSource, ?> remove() {
        return CommandManager.argument(LabelWeightBoostCommand.LABELS, StringArgumentType.greedyString())
                .executes(this::run);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        String argument = StringArgumentType.getString(context, LabelWeightBoostCommand.LABELS);
        Set<String> labels = Set.of(argument.strip().split(",")).stream().map(String::strip).filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
        BoostManagerData.getOrCreate(player).getManager().endWeightBoost(labels);
        return 1;
    }
}
