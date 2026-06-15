package com.wonkglorg.minecraft.gui.inventory.specialised;

import com.wonkglorg.minecraft.gui.inventory.GuiInventory;
import com.wonkglorg.minecraft.gui.inventory.profile.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.plugin.java.JavaPlugin;


@SuppressWarnings("unused")
public abstract class GuiSmoker<T extends MenuProfile> extends GuiInventory<T> {

    public GuiSmoker(Component name, JavaPlugin plugin, Player player) {
        super(Bukkit.createInventory(player, InventoryType.SMOKER, name), plugin, player);
    }

    public GuiSmoker(JavaPlugin plugin, Player player) {
        super(Bukkit.createInventory(null, InventoryType.SMOKER), plugin, player);
    }

    public GuiSmoker(FurnaceInventory inventory, JavaPlugin plugin, Player player) {
        super(inventory, plugin, player);
    }

    @Override
    public FurnaceInventory getInventory() {
        return (FurnaceInventory) super.getInventory();
    }
}
