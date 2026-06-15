package com.wonkglorg.minecraft.gui.inventory.profile;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/**
 * Represents a player's profile for a menu. Can be extended to add additional data that can be used to store states of a menu for a player etc.
 */
@SuppressWarnings("unused")
public class MenuProfile implements Cloneable{
	/**
	 * The owner of the menu
	 */
	@Setter
	@Getter
	protected Player owner;
	
	public MenuProfile(Player player) {
		this.owner = player;
	}
	
	@Override
	public MenuProfile clone() {
		try{
			return (MenuProfile) super.clone();
		} catch(CloneNotSupportedException e){
			throw new AssertionError();
		}
	}
}