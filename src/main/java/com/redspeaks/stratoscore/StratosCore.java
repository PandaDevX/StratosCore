package com.redspeaks.stratoscore;

import com.redspeaks.stratoscore.logger.BukkitLogger;
import com.redspeaks.stratoscore.utils.player.Cooldown;
import com.redspeaks.stratoscore.utils.player.UserRegistry;
import com.redspeaks.stratoscore.utils.player.Verification;
import org.bukkit.plugin.java.JavaPlugin;

public final class StratosCore extends JavaPlugin {

    private static StratosCore instance = null;
    private BukkitLogger bukkitLogger = null;
    private Verification verification = null;
    private Cooldown coolDown = null;
    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        bukkitLogger = new BukkitLogger(this);
        verification = new Verification();
        coolDown = new Cooldown();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        UserRegistry.clear();
    }

    public Cooldown getCoolDown() {
        return coolDown;
    }

    public Verification getVerification() {
        return verification;
    }

    public BukkitLogger getBukkitLogger() {
        return bukkitLogger;
    }

    public static StratosCore getInstance() {
        return instance;
    }
}
