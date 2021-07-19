package com.redspeaks.stratoscore.utils.player;

import com.redspeaks.stratoscore.file.AbstractFile;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface User {

    void sendMessage(String message);
    void sendMessage(String... message);
    void sendMessage(Collection<String> message);
    void sendMessage(AbstractFile abstractFile, String path);
    String getUniqueId();
    Player asPlayer();
    OfflinePlayer asOfflinePlayer();
    boolean hasPermission(String permission);
    void askForVerification();
    void verify();
    boolean isVerified();
    void addCooldown(int seconds);
    void removeCooldown(int seconds);
    void removeCooldown();
    void setCooldown(long cooldown);
    boolean hasCooldown();
    long getRemainingTime();
    void finalize();
}
