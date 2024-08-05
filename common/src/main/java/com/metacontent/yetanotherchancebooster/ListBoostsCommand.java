package com.metacontent.yetanotherchancebooster;

import com.metacontent.yetanotherchancebooster.command.Command;
import com.metacontent.yetanotherchancebooster.command.Commands;
import com.metacontent.yetanotherchancebooster.command.argument.BoostListArgumentType;
import com.metacontent.yetanotherchancebooster.command.argument.BoostListType;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.metacontent.yetanotherchancebooster.util.BoostManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ListBoostsCommand implements Command {
    public static final String LIST = "list";
    public static final String PLAYER = "player";
    public static final String LIST_TYPE = "boosts";

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(Commands.BASE_COMMAND
                .then(CommandManager.literal(LIST)
                        .then(CommandManager.argument(PLAYER, EntityArgumentType.player())
                                .then(CommandManager.argument(LIST_TYPE, BoostListArgumentType.boostList())
                                        .executes(this::run)))));
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity source = context.getSource().getPlayerOrThrow();
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        BoostListType type = BoostListArgumentType.getType(context, LIST_TYPE);

        List<String> list = new ArrayList<>();
        BoostManager manager = BoostManagerData.getOrCreate(player).getManager();

        switch (type) {
            case ALL -> {
                list.add("Shiny ->");
                list.add(manager.getShinyBoostString());
                list.add("Species ->");
                list.addAll(manager.listSpeciesWeightBoosts());
                list.add("Labels ->");
                list.addAll(manager.listLabelWeightBoosts());
            }
            case SHINY -> {
                list.add("Shiny ->");
                list.add(manager.getShinyBoostString());
            }
            case SPECIES -> {
                list.add("Species ->");
                list.addAll(manager.listSpeciesWeightBoosts());
            }
            case LABELS -> {
                list.add("Labels: ->");
                list.addAll(manager.listLabelWeightBoosts());
            }
        }

        list.forEach(string -> source.sendMessage(Text.literal(string)));

        return 1;
    }
}
