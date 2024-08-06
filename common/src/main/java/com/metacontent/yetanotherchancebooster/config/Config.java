package com.metacontent.yetanotherchancebooster.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public record Config(int savePeriod, boolean saveWhenDisconnected) {
    public static final String PATH = "config/yetanotherchancebooster/main.json";

    private Config() {
        this(1200, true);
    }

    public static Config init() {
        Config config;
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        File file = new File(PATH);
        file.getParentFile().mkdirs();
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file)) {
                config = gson.fromJson(fileReader, Config.class);
            }
            catch (Throwable throwable) {
                YetAnotherChanceBooster.LOGGER.error(throwable.getMessage(), throwable);
                config = new Config();
            }
        }
        else {
            config = new Config();
        }

        try (PrintWriter printWriter = new PrintWriter(file)) {
            gson.toJson(config, printWriter);
        }
        catch (Throwable throwable) {
            YetAnotherChanceBooster.LOGGER.error(throwable.getMessage(), throwable);
        }

        return config;
    }
}
