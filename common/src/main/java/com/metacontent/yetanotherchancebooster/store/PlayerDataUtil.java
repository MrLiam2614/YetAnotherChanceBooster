package com.metacontent.yetanotherchancebooster.store;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.player.PlayerData;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDataUtil {
    public static void save(ServerPlayerEntity player) {
        PlayerData data = Cobblemon.playerData.get(player);
        Cobblemon.playerData.saveSingle(data);
    }
}
