package com.wonkglorg.minecraft.gui.inventory.profile;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/**
 * Represents a player's profile for a menu. Can be extended to add additional data that can be passed between menus for a player.
 */
@SuppressWarnings("unused")
public class MenuProfile{
	/**
	 * The owner of the menu
	 */
	@Setter
	@Getter
	protected Player owner;
	
	public MenuProfile(Player player) {
		this.owner = player;
	}
}