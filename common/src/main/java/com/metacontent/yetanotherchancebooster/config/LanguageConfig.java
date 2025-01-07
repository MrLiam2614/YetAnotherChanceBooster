package com.metacontent.yetanotherchancebooster.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metacontent.yetanotherchancebooster.YetAnotherChanceBooster;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public record LanguageConfig(
    String prefix,
    String yourBooster,
    String boostEndedMessage,
    String boostStartedMessage,
    String shiny,
    String species,
    String labels
) {
    public static final String PATH = "config/yetanotherchancebooster/language.json";

    private LanguageConfig() {
        this("&2&lEBL&cpoke spawn &eBooster §b» ","&eI tuoi boosters" , "&e%s &cè finito", "&e%s &bx %.2f &aè iniziato e durerà&c %s",
                "&b&lSHINY", "&c&lPOKEMON", "&6&lLABEL");
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
