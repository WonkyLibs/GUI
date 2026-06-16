package com.wonkglorg.minecraft.gui.inventory;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * A panel in an InventoryGUI which can be used to paginate items and buttons
 *
 * @author Redempt, Wonkglorg
 */
@SuppressWarnings("unused")
public final class PaginationGui{
	
	/**
	 * The InventoryGUI this panel is in
	 */
	private final GuiInventory<?> gui;
	/**
	 * The current page of the panel
	 */
	@Getter
	private int page = 1;
	
	/**
	 * A list that represents the entries in the panel the pagination is based on this lists ordering (if this ordering is modified so will the panel elements order) (to create an empty panel slot use null or leave the {@link PaginationEntry#object} null)
	 */
	private final ArrayList<PaginationEntry> entries = new ArrayList<>();
	
	/**
	 * Slots this pagination can use for display
	 */
	@Getter
	private final Set<Integer> slots = new TreeSet<>();
	
	@Setter
	private Runnable onUpdate = () -> {
	};
	/**
	 * The item to use to fill the rest with the empty slots
	 */
	@Setter
	private ItemStack fillerItem;
	
	/**
	 * The previous button assigned to this panel (is not required only for convenience in #updatePageButtons)
	 */
	private NavigationEntry previousPageControl;
	/**
	 * The Next button assigned to this panel (is not required only for convenience in #updatePageButtons)
	 */
	private NavigationEntry nextPageControl;
	
	/**
	 * Called whenever a click event happens for this pagination menu, this happens before any buttons fire their click events
	 */
	@Setter
	private Consumer<ClickData> onClick = event -> {
	};
	
	/**
	 * Constructs a PaginationPanel to work on a given InventoryGUI
	 *
	 * @param gui The InventoryGUI to paginate
	 */
	public PaginationGui(GuiInventory<?> gui) {
		this(gui, null);
	}
	
	/**
	 * Constructs a PaginationPanel to work on a given InventoryGUI
	 *
	 * @param gui The InventoryGUI to paginate
	 * @param fillerItem The item to use for the background
	 */
	public PaginationGui(GuiInventory<?> gui, ItemStack fillerItem) {
		this.gui = gui;
		this.fillerItem = fillerItem;
		gui.addPaginationGui(this);
	}
	
	/**
	 * Adds a paged button to the panel
	 *
	 * @param button The button to add
	 */
	public void add(Button button) {
		entries.add(new PaginationEntry(button));
	}
	
	/**
	 * Adds a paged item to the panel
	 *
	 * @param item The item to add
	 */
	public void add(ItemStack item) {
		entries.add(new PaginationEntry(item));
	}
	
	/**
	 * Removes an item from the paged panel.
	 *
	 * @param item The item to remove
	 */
	public void remove(ItemStack item) {
		entries.removeIf(e -> e.object.equals(item));
	}
	
	/**
	 * Removes a button from the paged panel.
	 *
	 * @param button The button to remove
	 */
	public void remove(Button button) {
		entries.removeIf(e -> e.object.equals(button));
	}
	
	/**
	 * Removes an item from the panel
	 *
	 * @param index The index of the item to remove
	 */
	public void remove(int index) {
		entries.remove(index);
	}
	
	/**
	 * Adds an item to the panel at a specific index
	 *
	 * @param index The index to add the item at
	 * @param item The item to add
	 */
	public void add(int index, ItemStack item) {
		ensureCapacity(index);
		entries.set(index, new PaginationEntry(item));
	}
	
	/**
	 * Adds a button to the panel at a specific index
	 *
	 * @param index The index to add the button at
	 * @param button The button to add
	 */
	public void add(int index, Button button) {
		ensureCapacity(index);
		entries.set(index, new PaginationEntry(button));
	}
	
	/**
	 * Ensures the entries list has a capacity of at least index
	 *
	 * @param index The index to ensure
	 */
	private void ensureCapacity(int index) {
		int start = entries.size();
		while(start <= index){
			entries.add(start++, null);
		}
	}
	
	/**
	 * @return The maximum page number of this panel with the current number of elements
	 */
	public int getMaxPage() {
		return (Math.max(0, entries.size() - 1) / Math.max(1, slots.size())) + 1;
	}
	
	/**
	 * Adds a slot which will be used to display elements
	 *
	 * @param slot The slot to add
	 */
	public void addSlot(int slot) {
		slots.add(slot);
	}
	
	/**
	 * Adds a range of slots which will be used to display elements
	 *
	 * @param start The start index of slots to add, inclusive (0-indexed)
	 * @param end The end index of slots to add, inclusive  (0-indexed)
	 */
	public void addSlots(int start, int end) {
		for(int i = start; i <= end; i++){
			slots.add(i);
		}
	}
	
	/**
	 * Adds a rectangular area of slots which will be used to display elements
	 *
	 * @param x1 The starting X of slots to add, inclusive (0-indexed)
	 * @param y1 The starting Y of slots to add, inclusive (0-indexed)
	 * @param x2 The ending X of slots to add, inclusive (0-indexed)
	 * @param y2 The ending Y of slots to add, inclusive (0-indexed)
	 */
	public void addSlots(int x1, int y1, int x2, int y2) {
		for(int x = x1; x <= x2; x++){
			for(int y = y1; y <= y2; y++){
				slots.add((y * gui.getMaxRows()) + x);
			}
		}
	}
	
	/**
	 * Removes a slot which will be used to display elements
	 *
	 * @param slot The slot to remove
	 */
	public void removeSlot(int slot) {
		slots.remove(slot);
	}
	
	/**
	 * Removes a range of slots which will be used to display elements
	 *
	 * @param start The start index of slots to remove, inclusive (0-indexed)
	 * @param end The end index of slots to remove, inclusive (0-indexed)
	 */
	public void removeSlots(int start, int end) {
		for(int i = start; i <= end; i++){
			slots.remove(i);
		}
	}
	
	/**
	 * Removes a rectangular area of slots which will be used to display elements
	 *
	 * @param x1 The starting X of slots to remove, inclusive (0-indexed)
	 * @param y1 The starting Y of slots to remove, inclusive (0-indexed)
	 * @param x2 The ending X of slots to remove, inclusive (0-indexed)
	 * @param y2 The ending Y of slots to remove, inclusive (0-indexed)
	 */
	public void removeSlots(int x1, int y1, int x2, int y2) {
		for(int x = x1; x <= x2; x++){
			for(int y = y1; y <= y2; y++){
				slots.remove((y * gui.getMaxRows()) + x);
			}
		}
	}
	
	/**
	 * Updates the elements displayed on the current page
	 */
	public void updatePage() {
		if(getPageSize() == 0 || entries.isEmpty()){
			slots.forEach(i -> gui.getInventory().setItem(i, fillerItem));
			onUpdate.run();
			return;
		}
		int start = (page - 1) * getPageSize();
		int end = Math.min(entries.size(), page * getPageSize());
		Iterator<Integer> slotIter = slots.iterator();
		
		for(int entryIndex = start; slotIter.hasNext(); entryIndex++){
			int slot = slotIter.next();
			
			if(entryIndex >= end){
				gui.add(fillerItem, slot);
				continue;
			}
			
			PaginationEntry entry = entries.get(entryIndex);
			
			if(entry == null || entry.object() == null){
				gui.add(fillerItem, slot);
				continue;
			}
			
			if(entry.isButton()){
				gui.add((Button) entry.object(), slot);
			} else {
				gui.add(entry.getItemStack(), slot);
			}
		}
		onUpdate.run();
		updatePageChangeButtons();
	}
	
	/**
	 * Sets the page of this panel
	 *
	 * @param page The page to set
	 */
	public void setPage(int page) {
		if(page < 1 || page > getMaxPage()){
			throw new IllegalArgumentException("Invalid page: " + page);
		}
		this.page = page;
		updatePage();
	}
	
	/**
	 * Removes all items and buttons from the panel
	 */
	public void clear() {
		entries.clear();
		updatePage();
	}
	
	/**
	 * @return All ItemStacks added to this panel
	 */
	public List<ItemStack> getItems() {
		//@formatter:off
		return entries.stream()
					  .map(PaginationEntry::object)
					  .filter(ItemStack.class::isInstance)
					  .map(ItemStack.class::cast)
					  .collect(Collectors.toList()); //NOSONAR
		//@formatter:on
	}
	
	/**
	 * @return All ItemButtons added to this panel
	 */
	public List<Button> getButtons() {
		//@formatter:off
		return entries.stream()
					  .map(PaginationEntry::object)
					  .filter(Button.class::isInstance)
					  .map(Button.class::cast)
					  .collect(Collectors.toList()); //NOSONAR
		//@formatter:on
	}
	
	/**
	 * @param item the item to check
	 * @return the position in the entries list of the item or -1 if not found
	 */
	public int getPosition(ItemStack item) {
		for(int i = 0; i < entries.size(); i++){
			PaginationEntry entry = entries.get(i);
			if(entry != null && Objects.equals(entry.object(), item)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param button the button to check
	 * @return the position in the entries list of the button or -1 if not found
	 */
	public int getPosition(Button button) {
		for(int i = 0; i < entries.size(); i++){
			PaginationEntry entry = entries.get(i);
			if(entry != null && Objects.equals(entry.object(), button)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Navigates to the next page, if there is one
	 */
	public void nextPage() {
		page = Math.min(page + 1, getMaxPage());
		updatePage();
	}
	
	/**
	 * Navigates to the previous page, if there is one
	 */
	public void prevPage() {
		page = Math.max(1, page - 1);
		updatePage();
	}
	
	/**
	 * FOR INTERNAL USE ONLY
	 *
	 * @param event the event
	 * @param object the object reference (Button or ItemStack)
	 * @param index the index of the object in the entries list
	 */
	public void onInventoryEvent(InventoryClickEvent event, Object object, int index) { //NOSONAR
		if(onClick != null){
			onClick.accept(new ClickData(event, this, object, index));
		}
		
		if(object instanceof Button button){
			button.onClick(event);
		}
		event.setCancelled(true);
	}
	
	/**
	 * Sets page controls (the button should call {@link #prevPage()} and {@link #nextPage()}
	 */
	public void setPageControlButtons(NavigationEntry previousPageControl, NavigationEntry nextPageControl) {
		this.previousPageControl = previousPageControl;
		this.nextPageControl = nextPageControl;
	}
	
	/**
	 * Updates the page buttons based on the current page count, set 2 buttons for previous and next to replace them if not needed
	 */
	public void updatePageChangeButtons() {
		if(previousPageControl == null || nextPageControl == null){
			return;
		}
		
		if(getMaxPage() == 1){
			previousPageControl.setReplacement(gui);
			nextPageControl.setReplacement(gui);
			return;
		}
		
		if(getPage() == getMaxPage()){
			gui.add(previousPageControl.slot, previousPageControl.button);
			nextPageControl.setReplacement(gui);
			return;
		}
		
		if(getPage() == 1){
			previousPageControl.setReplacement(gui);
			gui.add(nextPageControl.slot, nextPageControl.button);
			return;
		}
		
		gui.add(previousPageControl.slot, previousPageControl.button);
		gui.add(nextPageControl.slot, nextPageControl.button);
	}
	
	/**
	 * Represents an entry in the pagination panel
	 *
	 * @param object the object reference (Button or ItemStack)
	 */
	private record PaginationEntry(Object object){
		
		/**
		 * @return the objects itemstack (either directly or in the form of a buttons itemstack)
		 */
		public ItemStack getItemStack() {
			if(object instanceof ItemStack item){
				return item;
			}
			if(object instanceof Button button){
				return button.getItem();
			}
			return null;
		}
		
		public boolean isButton() {
			return object instanceof Button;
		}
	}
	
	public static final class NavigationEntry{
		private final int slot;
		private final Button button;
		private final Object object;
		
		public NavigationEntry(int slot, Button button, ItemStack replacement) {
			this.slot = slot;
			this.button = button;
			this.object = replacement;
		}
		
		public NavigationEntry(int slot, Button button, Button replacement) {
			this.slot = slot;
			this.button = button;
			this.object = replacement;
		}
		
		public void setReplacement(GuiInventory<?> inventory) {
			if(object == null){
				inventory.remove(slot);
				return;
			}
			
			if(object instanceof Button replaceButton){
				inventory.add(slot, replaceButton);
				return;
			}
			
			inventory.add(slot, (ItemStack) object);
		}
		
	}
	
	/**
	 * Represents the data of a click event
	 *
	 * @param event the click event
	 * @param gui the pagination gui this event is for
	 * @param object the clicked object reference (Button or ItemStack)
	 * @param index the index of the object in the entries list
	 */
	public record ClickData(InventoryClickEvent event, PaginationGui gui, Object object, int index){
		public boolean isButton() {
			return object instanceof Button;
		}
		
		public boolean isItem() {
			return object instanceof ItemStack;
		}
	}

	
	public int getSlotSize() {
		return slots.size();
	}
	
	public int getEntrySize() {
		return entries.size();
	}
	
	/**
	 * @return The max number of elements displayed on each page
	 */
	public int getPageSize() {return slots.size();}
	
}
