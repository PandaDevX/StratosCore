package com.redspeaks.stratoscore;

import com.redspeaks.stratoscore.utils.chat.ChatUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    public ItemBuilder(Material type) {
        itemStack = new ItemStack(type);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material type, String displayName) {
        itemStack = new ItemStack(type);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.colorize(displayName));
        itemStack.setItemMeta(itemMeta);
    }

    public ItemBuilder(Material type, String displayName, String... lore) {
        itemStack = new ItemStack(type);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.colorize(displayName));
        List<String> list = new ArrayList<>();
        Arrays.stream(lore).forEach(l -> list.add(ChatUtil.colorize(l)));
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
    }

    public ItemBuilder(Material type, String displayName, List<String> lore) {
        itemStack = new ItemStack(type);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatUtil.colorize(displayName));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    public void addItemFlag(ItemFlag... flag) {
        ItemMeta newMeta = itemStack.getItemMeta();
        newMeta.addItemFlags(flag);
        itemStack.setItemMeta(newMeta);
    }

    public void addEnchantment(Enchantment... enchantments) {
        ItemMeta newMeta = itemStack.getItemMeta();
        Arrays.stream(enchantments).forEach(e -> newMeta.addEnchant(e, 1, true));
        itemStack.setItemMeta(newMeta);
    }

    public ItemStack build() {
        return itemStack;
    }

    /**
     *
     * @param what - The ItemStack to be converted into a string
     * @return The String that contains the ItemStack (will return null if anything goes wrong)
     */
    public static String convertItemStackToString(ItemStack what){
        // serialize the object
        String serializedObject = "";
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(what);
            so.flush();
            serializedObject = bo.toString();
            return serializedObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param data - The String to be converted into an ItemStack
     * @return The ItemStack Array obtained from the string (will return void should anything go wrong)
     */
    public static ItemStack convertStringToItemStack(String data){
        // deserialize the object
        try {
            byte b[] = data.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            ItemStack obj = (ItemStack) si.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param what - The ItemStack to be converted into a string
     * @return The String that contains the ItemStack (will return null if anything goes wrong)
     */
    public static String convertItemStackArrayToString(ItemStack what[]) {
        // serialize the object
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(what);
            so.flush();
            return bo.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param data - The String to be converted into an ItemStack Array
     * @return The ItemStack Array obtained from the string (will return void should anything go wrong)
     */
    public static ItemStack[] convertStringToItemStackArray(String data){
        // deserialize the object
        try {
            byte b[] = data.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            return (ItemStack[]) si.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
