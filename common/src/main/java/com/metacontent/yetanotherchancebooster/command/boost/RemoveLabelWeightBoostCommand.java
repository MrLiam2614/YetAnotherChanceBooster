package com.metacontent.yetanotherchancebooster.command.boost;

import com.metacontent.yetanotherchancebooster.command.argument.LabelsArgumentType;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Set;

public class RemoveLabelWeightBoostCommand extends RemoveBoostCommand {
    @Override
    protected ArgumentBuilder<ServerCommandSource, ?> remove() {
        return CommandManager.argument(LabelWeightBoostCommand.LABELS, LabelsArgumentType.labels())
                .executes(this::run);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        Set<String> labels = LabelsArgumentType.getLabels(context, LabelWeightBoostCommand.LABELS);
        BoostManagerData.getOrCreate(player).getManager().endWeightBoost(labels);
        return 1;
    }
}
