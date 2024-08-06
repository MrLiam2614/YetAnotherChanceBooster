package com.metacontent.yetanotherchancebooster.command;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CommandRegistryProvider {
    private static final List<Command> QUEUE = new ArrayList<>();

    public static void add(Command command) {
        QUEUE.add(command);
    }

    public static void registerCommands(Consumer<Command> consumer) {
        QUEUE.forEach(consumer);
    }
}
