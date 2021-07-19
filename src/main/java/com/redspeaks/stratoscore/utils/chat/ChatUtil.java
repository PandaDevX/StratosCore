package com.redspeaks.stratoscore.utils.chat;

import org.bukkit.ChatColor;

import java.util.List;

public class ChatUtil {

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String stripColor(String text) {
        return ChatColor.stripColor(text);
    }

    public static List<String> colorizeList(List<String> list) {
        for(int i = 0; i < list.size(); i++) {
            list.set(i, colorize(list.get(i)));
        }
        return list;
    }

    public static boolean isInt(String text) {
        try {
            Integer.parseInt(text);
        }catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static int convertToInt(String text) {
        return Integer.parseInt(text);
    }

    public static String messageBuilder(int startingIndex, int endIndex, String[] array) {
        if(endIndex > array.length) {
            endIndex = array.length;
        }
        String text = "";
        for(int i = startingIndex; i < endIndex; i++) {
            text += array[i] + " ";
        }
        return text.trim();
    }

    public static String capitalizeText(String text) {
        text = text.substring(0, 1).toUpperCase() + text.substring(1);
        return text;
    }
}
