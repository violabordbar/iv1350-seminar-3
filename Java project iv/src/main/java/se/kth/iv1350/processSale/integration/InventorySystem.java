package se.kth.iv1350.processSale.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.model.RegisteredItem;

/**
 * The InventorySystem class represents the available stock of items. 
 * It is responsible for validating item identifiers and updating the item stock.
 */
public class InventorySystem{
	private final List<ItemDTO> itemStock;

	/**
	 * Creates a new instance of the InventorySystem class and initializes the list containing the item stock.
	 *
	 */
	public InventorySystem() {
		this.itemStock = new ArrayList<>();
		addItemToInventory();
	}
	
	/**
	 * Adds items to the inventory stock list.
	 * Items are described with the corresponding ItemDTO attributes.
	 */
	public void addItemToInventory(){
		itemStock.add(new ItemDTO("BigWheel Oatmeal", 29.99, 0.06, 1234, "BigWheel Oatmeal 500 g , whole grain oats ,high fiber , gluten free"));
		itemStock.add(new ItemDTO("YouGoGo Blueberry", 14.99, 0.06, 3456, "YouGoGo Blueberry 240 g , low sugar youghurt , blueberry flavour"));
		itemStock.add(new ItemDTO("Apple Juice", 21.99, 0.06, 5678, "Apple Juice 1 L , 100% pure apple juice , no added sugar"));
		itemStock.add(new ItemDTO("Banana", 9.99, 0.06, 7891, "Banana 1 kg , fresh bananas , high in potassium"));
	}

	/**
	 * Validates the provided itemID by comparing it with the itemIDs in the itemStock list.
	 * 
	 * @param itemID The identifier of the item to be validated.
	 * @return The corresponding ItemDTO object if the identifier is valid, null if it isn't.
	 */
	public ItemDTO validateIdentifier(int itemID) {
		for(ItemDTO item : itemStock) {
			if(item.getItemID() == itemID) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Updates the inventory with the list of registered items.
	 * This method is not implemented because it was not required.
	 * 
	 * @param listOfItems A list of the items registered in the sale sold.
	 */
	public void updateInventory(List<RegisteredItem> listOfItems) {
	}

}