package com.wonkglorg.minecraft.gui.inventory.specialised;

import com.wonkglorg.minecraft.gui.inventory.GuiInventory;
import com.wonkglorg.minecraft.gui.inventory.profile.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiHopper<T extends MenuProfile> extends GuiInventory<T> {

    public GuiHopper(Component name, JavaPlugin plugin, Player player) {
        super(Bukkit.createInventory(player, InventoryType.HOPPER, name), plugin, player);
    }

    public GuiHopper(JavaPlugin plugin, Player player) {
        super(Bukkit.createInventory(null, InventoryType.HOPPER), plugin, player);
    }

    public GuiHopper(Inventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

}