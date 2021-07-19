package com.redspeaks.stratoscore.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class GUI implements Listener, InventoryHolder {

    private final Inventory inventory;
    private final String title;
    private final int hash;
    public GUI(String title, int rows, JavaPlugin plugin) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        if(rows > 6) {
            rows = 6;
        }
        if(rows < 1) {
            rows = 1;
        }
        this.inventory = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', title));
        this.hash = title.hashCode();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public Inventory getInventory() {
        init(inventory);
        return inventory;
    }

    public void open(Player player) {
        player.openInventory(getInventory());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) return;
        if(e.getClickedInventory() instanceof PlayerInventory) return;
        if(!e.getView().getTitle().equals(getTitle())) return;
        onClick(e, (Player)e.getWhoClicked());
    }

    protected void init(Inventory inventory) { }
    protected void onClick(InventoryClickEvent e, Player player) { }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof GUI)) {
            return false;
        }
        return getTitle().equals(((GUI)obj).getTitle());
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
