package com.wonkglorg.utilitylib.manager;

import com.wonkglorg.utilitylib.inventory.profile.MenuProfile;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A utility class to manage player profiles of any type.
 *
 * @param <T>
 */
@SuppressWarnings("unused")
public final class ProfileManager<T extends MenuProfile>{
	/**
	 * Sets the default MenuProfile to asign to a player when non could be found for the player.
	 * Inherit all values passed with the class besides the owner being reasigned to the new player.
	 */
	@Getter
	private final T defaultMenu;
	private final Map<UUID, T> utilityMap = new HashMap<>();
	
	/**
	 * Creates a new ProfileManagerm add the default menu that should be asigned when a player has no menu and gets the menu called for them
	 *
	 * @param defaultMenu
	 */
	public ProfileManager(T defaultMenu) {
		this.defaultMenu = defaultMenu;
	}
	
	/**
	 * Gets the MenuProfile for the player, if none is found a new one is created and returned.
	 *
	 * @param player the player to get the profile for
	 * @return the MenuProfile for the player
	 */
	@SuppressWarnings("unchecked")
	public T get(Player player) {
		UUID uniqueId = player.getUniqueId();
		if(utilityMap.containsKey(uniqueId)){
			return utilityMap.get(uniqueId);
		}
		T profile = (T) defaultMenu.clone();
		profile.setOwner(player);
		utilityMap.put(uniqueId, profile);
		return profile;
	}
	
	/**
	 * Removes an entry from the manager
	 *
	 * @param uuid the player to remove it from
	 */
	public void remove(UUID uuid) {
		utilityMap.remove(uuid);
	}
	
	/**
	 * Adds a profile into the manager or overrides the existing one
	 *
	 * @param profile the profile to add
	 */
	public void add(T profile) {
		utilityMap.put(profile.getOwner().getUniqueId(), profile);
	}
	
}