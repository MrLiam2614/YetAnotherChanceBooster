package com.metacontent.yetanotherchancebooster.store;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.player.PlayerData;
import com.cobblemon.mod.common.api.storage.player.PlayerDataExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.metacontent.yetanotherchancebooster.util.BoostManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class BoostManagerData implements PlayerDataExtension {
    public static final String NAME = "yacb$boostManager";
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    private BoostManager manager = new BoostManager();

    public static BoostManagerData getOrCreate(ServerPlayerEntity player) {
        PlayerData data = Cobblemon.playerData.get(player);
        BoostManagerData boostManagerData = (BoostManagerData) data.getExtraData().get(NAME);
        if (boostManagerData == null) {
            boostManagerData = new BoostManagerData();
            data.getExtraData().put(NAME, boostManagerData);
            Cobblemon.playerData.saveSingle(data);
        }
        return boostManagerData;
    }

    public BoostManager getManager() {
        return manager;
    }

    @NotNull
    @Override
    public PlayerDataExtension deserialize(@NotNull JsonObject jsonObject) {
        JsonObject jsonManager = jsonObject.getAsJsonObject("manager");
        manager = GSON.fromJson(jsonManager, BoostManager.class);
        if (manager == null) {
            manager = new BoostManager();
        }
        return this;
    }

    @NotNull
    @Override
    public String name() {
        return NAME;
    }

    @NotNull
    @Override
    public JsonObject serialize() {
        return GSON.toJsonTree(this).getAsJsonObject();
    }
}
