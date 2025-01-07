package com.metacontent.yetanotherchancebooster.store;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.player.GeneralPlayerData;
import com.cobblemon.mod.common.api.storage.player.PlayerInstancedDataStoreTypes;
import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDataUtil {
    public static void save(ServerPlayerEntity player) {
        GeneralPlayerData data = Cobblemon.playerDataManager.getGenericData(player);
        Cobblemon.playerDataManager.saveSingle(data, PlayerInstancedDataStoreTypes.INSTANCE.getGENERAL());
    }

    public static void onDisconnect(ServerPlayerEntity player) {
        if (YetAnotherChanceBooster.CONFIG.saveWhenDisconnected()) {
            save(player);
        }
    }
}
