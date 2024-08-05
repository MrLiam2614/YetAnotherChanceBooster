package com.metacontent.yetanotherchancebooster.command.argument;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.serialization.Codec;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.StringIdentifiable;

import java.util.function.Supplier;

public class BoostListArgumentType extends EnumArgumentType<BoostListType> {
    private BoostListArgumentType(Codec<BoostListType> codec, Supplier<BoostListType[]> valuesSupplier) {
        super(codec, valuesSupplier);
    }

    public static BoostListArgumentType boostList() {
        return new BoostListArgumentType(CODEC, BoostListType::values);
    }

    public static BoostListType getType(CommandContext<ServerCommandSource> context, String name) {
        return context.getArgument(name, BoostListType.class);
    }

    private static final Codec<BoostListType> CODEC = StringIdentifiable.createCodec(BoostListType::values);
}
