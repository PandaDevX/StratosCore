package com.redspeaks.stratoscore.file;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class AbstractFile {


    private File file = null;
    private FileConfiguration config = null;
    public AbstractFile(JavaPlugin plugin, String path) {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(path + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public AbstractFile(JavaPlugin plugin, String folder, String path) {
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        File flder = new File(folder);
        if(!flder.exists()) {
            flder.mkdir();
        }
        file = new File(path + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    public void set(String path, Object object) {
        config.set(path, object);
        try {
            config.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> Optional<T> getRegisteredData(String path, Class<T> type) {
        return Optional.ofNullable(type.cast(getConfig().get(path)));
    }

    public <T> T getDirectData(String path, Class<T> type) {
        return type.cast(getConfig().get(path));
    }
}
