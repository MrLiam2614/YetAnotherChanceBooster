package com.metacontent.yetanotherchancebooster.command;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import com.metacontent.yetanotherchancebooster.config.LanguageConfig;
import com.metacontent.yetanotherchancebooster.store.BoostManagerData;
import com.metacontent.yetanotherchancebooster.boost.BoostManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import it.eblcraft.eblpokespawnbooster.Messages;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ListBoostsCommand implements Command {
    public static final List<String> LIST_TYPES = List.of("all", "shiny", "species", "labels");
    public static final String LIST = "list";
    public static final String PLAYER = "player";
    public static final String LIST_TYPE = "boosts";

    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(Commands.BASE_COMMAND
                .then(CommandManager.literal(LIST)
                        .then(CommandManager.argument(PLAYER, EntityArgumentType.player())
                                .then(CommandManager.argument(LIST_TYPE, StringArgumentType.word())
                                        .suggests((context, builder) -> {
                                            for (String type : LIST_TYPES) {
                                                if (CommandSource.shouldSuggest(builder.getRemaining(), type)) {
                                                    builder.suggest(type);
                                                }
                                            }
                                            return builder.buildFuture();
                                        })
                                        .executes(this::run)))));
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity source = context.getSource().getPlayerOrThrow();
        ServerPlayerEntity player = EntityArgumentType.getPlayer(context, PLAYER);
        String type = StringArgumentType.getString(context, LIST_TYPE);

        List<String> list = new ArrayList<>();
        BoostManager manager = BoostManagerData.getOrCreate(player).getManager();

        // i mean... it could be worse
        LanguageConfig language = YetAnotherChanceBooster.LANGUAGE;
        list.add(language.prefix() + " " + language.yourBooster());
        switch (type) {
            case "all" -> {
                list.add(language.shiny());
                list.add(manager.getShinyBoostString());
                list.add(language.species());
                list.addAll(manager.listSpeciesWeightBoosts());
                list.add(language.labels());
                list.addAll(manager.listLabelWeightBoosts());
            }
            case "shiny" -> {
                list.add(language.shiny());
                list.add(manager.getShinyBoostString());
            }
            case "species" -> {
                list.add(language.species());
                list.addAll(manager.listSpeciesWeightBoosts());
            }
            case "labels" -> {
                list.add(language.labels());
                list.addAll(manager.listLabelWeightBoosts());
            }
            default -> {
                list.add("all");
                list.add("shiny");
                list.add("species");
                list.add("labels");
            }
        }

        list.forEach(string -> Messages.sendBeautyMessage(string, source));

        return 1;
    }
}
