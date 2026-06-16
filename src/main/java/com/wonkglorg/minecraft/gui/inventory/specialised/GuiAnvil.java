package com.wonkglorg.minecraft.gui.inventory.specialised;

import com.wonkglorg.minecraft.gui.inventory.Button;
import com.wonkglorg.minecraft.gui.inventory.GuiInventory;
import com.wonkglorg.minecraft.gui.inventory.profile.MenuProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class GuiAnvil<T extends MenuProfile> extends GuiInventory<T>{
	
	protected GuiAnvil(Component name, JavaPlugin plugin, Player player) {
		super(Bukkit.createInventory(player, InventoryType.ANVIL, name), plugin, player);
	}
	
	protected GuiAnvil(JavaPlugin plugin, Player player) {
		super(Bukkit.createInventory(null, InventoryType.ANVIL), plugin, player);
	}
	
	/**
	 * Adds an item to the output slot of the anvil
	 *
	 * @param item the item to add
	 */
	public void addOutput(ItemStack item) {
		getInventory().setItem(getOutputSlot(), item);
	}
	
	/**
	 * Adds a button to the output slot of the anvil
	 *
	 * @param button the button to add
	 */
	public void addOutput(Button button) {
		add(button, getOutputSlot());
	}
	
	/**
	 * Adds an item to the primary slot of the anvil
	 *
	 * @param item the item to add
	 */
	public void addPrimary(ItemStack item) {
		add(item, getPrimarySlot());
	}
	
	/**
	 * Adds a button to the primary slot of the anvil
	 *
	 * @param button the button to add
	 */
	public void addPrimary(Button button) {
		add(button, getPrimarySlot());
	}
	
	/**
	 * Adds an item to the secondary slot of the anvil
	 *
	 * @param item the item to add
	 */
	public void addSecondary(ItemStack item) {
		add(item, getSecondarySlot());
	}
	
	/**
	 * Adds a button to the secondary slot of the anvil
	 *
	 * @param button the button to add
	 */
	public void addSecondary(Button button) {
		add(button, getSecondarySlot());
	}
	
	/**
	 * @return the primary slot of the anvil (first slot from the left)
	 */
	public int getPrimarySlot() {
		return 0;
	}
	
	/**
	 * @return the secondary slot of the anvil (second slot from the left)
	 */
	public int getSecondarySlot() {
		return 1;
	}
	
	/**
	 * @return the output slot of the anvil
	 */
	public int getOutputSlot() {
		return 2;
	}
}

