package com.metacontent.yetanotherchancebooster.command;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import com.metacontent.yetanotherchancebooster.command.argument.BoostListArgumentType;
import com.metacontent.yetanotherchancebooster.command.argument.LabelsArgumentType;
import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CommandRegistryProvider {
    private static final List<Command> queue = new ArrayList<>();

    public static void add(Command command) {
        queue.add(command);
    }

    public static void registerCommands(Consumer<Command> consumer) {
        queue.forEach(consumer);
    }

    public static void registerArguments(BiConsumer<Identifier, ArgumentType<?>> consumer) {
        consumer.accept(new Identifier(YetAnotherChanceBooster.MOD_ID, "labels_argument_type"), LabelsArgumentType.labels());
        consumer.accept(new Identifier(YetAnotherChanceBooster.MOD_ID, "boost_list_argument_type"), BoostListArgumentType.boostList());
    }
}
