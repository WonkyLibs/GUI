package com.wonkglorg.minecraft.gui.inventory.specialised;

import com.wonkglorg.minecraft.gui.inventory.GuiInventory;
import com.wonkglorg.minecraft.gui.inventory.profile.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiBrewer<T extends MenuProfile> extends GuiInventory<T>{
	
	protected GuiBrewer(Component name, JavaPlugin plugin, Player player) {
		super(Bukkit.createInventory(player, InventoryType.BREWING, name), plugin, player);
	}
	
	protected GuiBrewer(JavaPlugin plugin, Player player) {
		super(Bukkit.createInventory(null, InventoryType.BREWING), plugin, player);
	}
	
	protected GuiBrewer(BrewerInventory inventory, JavaPlugin plugin, Player player) {
		super(inventory, plugin, player);
	}
	
	@Override
	public BrewerInventory getInventory() {
		return (BrewerInventory) super.getInventory();
	}
}
