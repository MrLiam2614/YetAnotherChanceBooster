package com.metacontent.yetanotherchancebooster.command.boost;

import com.cobblemon.mod.common.command.argument.PokemonArgumentType;
import com.cobblemon.mod.common.pokemon.Species;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class SpeciesWeightBoostCommand extends BoostCommand {
    public static final String SPECIES = "species";

    @Override
    protected RequiredArgumentBuilder<ServerCommandSource, Species> boost() {
        return CommandManager.argument(SPECIES, PokemonArgumentType.Companion.pokemon()).executes(this::run);
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        Species species = PokemonArgumentType.Companion.getPokemon(context, SPECIES);
        float amplifier = FloatArgumentType.getFloat(context, AMPLIFIER);
        long duration = LongArgumentType.getLong(context, DURATION);

        BoostManagerData.getOrCreate(player).getManager().addSpeciesWeightBoost(amplifier, duration, species.showdownId());

        return 1;
    }
}
