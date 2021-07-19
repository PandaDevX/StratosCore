package com.redspeaks.stratoscore.commands;


import com.redspeaks.stratoscore.StratosCore;
import com.redspeaks.stratoscore.utils.chat.ChatUtil;
import com.redspeaks.stratoscore.utils.player.User;
import com.redspeaks.stratoscore.utils.player.UserRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    private final CommandInfo info;
    Object

    public AbstractCommand() {
        info = getClass().getDeclaredAnnotation(CommandInfo.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.hasPermission(info.permission())) {
            sender.sendMessage(ChatUtil.colorize("&cYou have insufficient permission"));
            return true;
        }
        if(info.requiresPlayer()) {
            if(!(sender instanceof Player)) {
                StratosCore.getInstance().getBukkitLogger().alert("You must be a player to do that");
                return true;
            }
            execute(UserRegistry.getUser((Player)sender), args);
            return true;
        }
        execute(sender, args);
        return false;
    }
    
    public void execute(CommandSender sender, String[] args) {}
    public void execute(User user, String[] args) {}
}
