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
public abstract class GuiSmithing<T extends MenuProfile> extends GuiInventory<T> {

    public GuiSmithing(Component name, JavaPlugin plugin, Player player) {
        super(Bukkit.createInventory(player, InventoryType.SMITHING, name), plugin, player);
    }

    public GuiSmithing(JavaPlugin plugin, Player player) {
        super(Bukkit.createInventory(null, InventoryType.SMITHING), plugin, player);
    }


    /**
     * @return the template slot of the inventory
     */
    public int getTemplateSlot() {
        return 0;
    }

    /**
     * @return the base slot of the inventory
     */
    public int getBaseSlot() {
        return 1;
    }

    /**
     * @return the material slot of the inventory
     */
    public int getMaterialSlot() {
        return 2;
    }

    /**
     * @return the result slot of the inventory
     */
    public int getResultSlot() {
        return 3;
    }

    /**
     * Adds a button to the template slot
     *
     * @param button the button to add
     */
    public void addTemplateButton(Button button) {
        add(button, getTemplateSlot());
    }

    /**
     * Adds an item to the template slot
     *
     * @param item the item to add
     */
    public void addTemplateItem(ItemStack item) {
        add(item, getTemplateSlot());
    }

    /**
     * Adds a button to the base slot
     *
     * @param button the button to add
     */
    public void addBaseButton(Button button) {
        add(button, getBaseSlot());
    }

    /**
     * Adds an item to the base slot
     *
     * @param item the item to add
     */
    public void addBaseItem(ItemStack item) {
        add(item, getBaseSlot());
    }

    /**
     * Adds a button to the material slot
     *
     * @param button the button to add
     */
    public void addMaterialButton(Button button) {
        add(button, getMaterialSlot());
    }

    /**
     * Adds an item to the material slot
     *
     * @param item the item to add
     */
    public void addMaterialItem(ItemStack item) {
        add(item, getMaterialSlot());
    }

    /**
     * Adds a button to the result slot
     *
     * @param button the button to add
     */
    public void addResultButton(Button button) {
        add(button, getResultSlot());
    }

    /**
     * Adds an item to the result slot
     *
     * @param item the item to add
     */
    public void addResultItem(ItemStack item) {
        add(item, getResultSlot());
    }

    /**
     * Adds an item to the result slot
     * @param item the item to add
     */
    public void addOutputItem(ItemStack item) {
        add(item, getResultSlot());
    }

    /**
     * Adds a button to the result slot
     * @param button the button to add
     */
    public void addOutputButton(Button button) {
        add(button, getResultSlot());
    }

}

