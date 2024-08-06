package com.metacontent.yetanotherchancebooster.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class LabelsArgumentType implements ArgumentType<Set<String>> {
    public static final List<String> EXAMPLES = List.of("legendary,gen3", "gen4", "legendary,mythical");

    @Override
    public Set<String> parse(StringReader reader) throws CommandSyntaxException {
        String argument = reader.getRemaining();
        reader.setCursor(reader.getTotalLength());
        return Set.of(argument.strip().split(",")).stream().map(String::strip).filter(s -> !s.isEmpty()).collect(Collectors.toSet());
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    private LabelsArgumentType() {}

    public static LabelsArgumentType labels() {
        return new LabelsArgumentType();
    }

    public static Set<String> getLabels(CommandContext<ServerCommandSource> context, String name) {
        return context.getArgument(name, Set.class);
    }
}
