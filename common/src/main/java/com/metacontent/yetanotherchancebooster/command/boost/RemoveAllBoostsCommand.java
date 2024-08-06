package com.metacontent.yetanotherchancebooster.command.boost;

import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class RemoveAllBoostsCommand extends RemoveBoostCommand {
    public static final String ALL = "all";

    @Override
    protected ArgumentBuilder<ServerCommandSource, ?> remove() {
        return CommandManager.literal(ALL)
                .executes(this::run);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        BoostManagerData.getOrCreate(player).getManager().endAll();
        return 1;
    }
}
