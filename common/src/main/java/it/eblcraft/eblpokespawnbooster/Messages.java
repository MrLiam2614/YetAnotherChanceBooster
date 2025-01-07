package it.eblcraft.eblpokespawnbooster;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class Messages {
    public static void sendBeautyMessage(String message, PlayerEntity player){
        String[] lines = splitLines(message);

        for(String line : lines){
            player.sendMessage(Text.of(formatColor(line)));
        }
    }

    private static String[] splitLines(String message){
        return message.split("\n");
    }

    private static String formatColor(String message){
        return message.replaceAll("&", "§");
    }
}
