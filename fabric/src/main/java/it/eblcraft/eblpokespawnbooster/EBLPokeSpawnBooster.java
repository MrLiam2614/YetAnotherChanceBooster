package it.eblcraft.eblpokespawnbooster;

import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;
import com.metacontent.yetanotherchancebooster.command.CommandRegistryProvider;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class EBLPokeSpawnBooster implements ModInitializer {
    @Override
    public void onInitialize() {
        YetAnotherChanceBooster.init();
        CommandRegistryProvider.registerCommands(command -> CommandRegistrationCallback.EVENT.register(command::register));
    }
}
