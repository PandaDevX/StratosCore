package com.redspeaks.stratoscore.utils.player;

import com.redspeaks.stratoscore.StratosCore;
import com.redspeaks.stratoscore.file.AbstractFile;
import com.redspeaks.stratoscore.utils.chat.ChatUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class UserRegistry {
    private final static HashMap<UUID, User> userRegistry = new HashMap<>();

    public static void clear() {
        userRegistry.values().forEach(User::finalize);
        userRegistry.clear();
    }


    public static User getUser(OfflinePlayer player) {
        if(userRegistry.containsKey(player.getUniqueId())) {
            return userRegistry.get(player.getUniqueId());
        }
        User user = new User() {

            @Override
            public void sendMessage(String message) {
                if(player.isOnline()) {
                    player.getPlayer().sendMessage(ChatUtil.colorize(message));
                }
            }

            @Override
            public void sendMessage(String... message) {
                if(player.isOnline()) {
                    Arrays.stream(message).forEach(this::sendMessage);
                }
            }

            @Override
            public void sendMessage(Collection<String> message) {
                if(player.isOnline()) {
                    message.forEach(this::sendMessage);
                }
            }

            @Override
            public void sendMessage(AbstractFile abstractFile, String path) {
                sendMessage(abstractFile.getRegisteredData(path, String.class).orElse("null"));
            }

            @Override
            public String getUniqueId() {
                return player.getUniqueId().toString();
            }

            @Override
            public Player asPlayer() {
                return player.getPlayer();
            }

            @Override
            public OfflinePlayer asOfflinePlayer() {
                return player;
            }

            @Override
            public boolean hasPermission(String permission) {
                return player.getPlayer().hasPermission(permission);
            }

            @Override
            public void askForVerification() {
                StratosCore.getInstance().getVerification().ask(this);
            }

            @Override
            public boolean isVerified() {
                return StratosCore.getInstance().getVerification().isVerified(this);
            }

            @Override
            public void verify() {
                StratosCore.getInstance().getVerification().verify(this);
            }

            @Override
            public void addCooldown(int seconds) {
                StratosCore.getInstance().getCoolDown().addCooldown(this, seconds);
            }

            @Override
            public void setCooldown(long cooldown) {
                StratosCore.getInstance().getCoolDown().setCooldown(this, cooldown);
            }

            @Override
            public void removeCooldown(int seconds) {
                StratosCore.getInstance().getCoolDown().removeCooldown(this, seconds);
            }

            @Override
            public void removeCooldown() {
                StratosCore.getInstance().getCoolDown().removeCooldown(this);
            }

            @Override
            public boolean hasCooldown() {
                return StratosCore.getInstance().getCoolDown().hasCooldown(this);
            }

            @Override
            public long getRemainingTime() {
                return StratosCore.getInstance().getCoolDown().getTimeRemaining(this);
            }

            @Override
            public void finalize() {
                try {
                    super.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

        };
        userRegistry.put(player.getUniqueId(), user);
        return user;
    }
}
