package com.metacontent.yetanotherchancebooster.command.boost;

import com.cobblemon.mod.common.command.argument.SpeciesArgumentType;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class RemoveSpeciesWeightBoostCommand extends RemoveBoostCommand {
    @Override
    protected ArgumentBuilder<ServerCommandSource, ?> remove() {
        return CommandManager.argument(SpeciesWeightBoostCommand.SPECIES, SpeciesArgumentType.Companion.species())
                .executes(this::run);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        String species = SpeciesArgumentType.Companion.getPokemon(context, SpeciesWeightBoostCommand.SPECIES).showdownId();
        BoostManagerData.getOrCreate(player).getManager().endWeightBoost(species);
        return 1;
    }
}
