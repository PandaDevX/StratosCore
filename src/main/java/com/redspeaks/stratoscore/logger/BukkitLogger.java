package com.redspeaks.stratoscore.logger;

import com.redspeaks.stratoscore.utils.chat.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitLogger {

    private final String name;
    public BukkitLogger(JavaPlugin plugin) {
        this.name = plugin.getName();
    }

    public void info(String text) {
        getConsole().sendMessage(ChatUtil.colorize("&8[&bINFO&8] &6" + name + ">> " + text));
    }

    public void alert(String text) {
        getConsole().sendMessage(ChatUtil.colorize("&8[&4ALERT&8] &6" + name + ">> " + text));
    }

    public void warn(String text) {
        getConsole().sendMessage(ChatUtil.colorize("&8[&cWARN&8] &6" + name + ">> " + text));
    }

    private ConsoleCommandSender getConsole() {
        return Bukkit.getServer().getConsoleSender();
    }

}
