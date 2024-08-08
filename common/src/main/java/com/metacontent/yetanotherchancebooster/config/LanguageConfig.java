package com.metacontent.yetanotherchancebooster.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public record LanguageConfig(
    String boostEndedMessage,
    String boostStartedMessage,
    String shiny,
    String species,
    String labels
) {
    public static final String PATH = "config/yetanotherchancebooster/language.json";

    private LanguageConfig() {
        this("%s has ended", "%s with amplifier %02f has started and will last for %s (source: %s)",
                "Shiny ->", "Species ->", "Labels ->");
    }

    public static LanguageConfig init() {
        LanguageConfig config;
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        File file = new File(PATH);
        file.getParentFile().mkdirs();
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file)) {
                config = gson.fromJson(fileReader, LanguageConfig.class);
            }
            catch (Throwable throwable) {
                YetAnotherChanceBooster.LOGGER.error(throwable.getMessage(), throwable);
                config = new LanguageConfig();
            }
        }
        else {
            config = new LanguageConfig();
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
