package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.dto.ItemDTO;
import java.util.Objects;

/**
 * The RegisteredItem class represents an item registered during a sale. 
 * It contains information about the item and the quantity registered in the sale.
 */
public class RegisteredItem {
    private final ItemDTO item; 
    private int quantity;

    /**
     * Creates a new instance of the RegisteredItem class with the specified item and quantity.
     * 
     * @param item The ItemDTO (data transfer object) representing the registered item.
     * @param quantity The quantity of the item being registered in the sale.
     */
    public RegisteredItem (ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Returns the registered item.
     * 
     * @return The registered item as an ItemDTO object.
     */
    public ItemDTO getItem() {
        return this.item;
    }
    
    /**
     * Returns the quantity of the registered item.
     * 
     * @return The quantity of the registered item.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Updates the quantity of the registered item.
     * 
     * @param addedQuantity The quantity to be added to the current quantity of the registered item.
     * @return the updated quantity of the registered item.
     */
    public void updateQuantity(int addedQuantity) {
        this.quantity += addedQuantity;
    }

    /**
     * Compares this RegisteredItem object with another object for equality based on the item ID.
     * 
     * @param obj The object to compare with this RegisteredItem object.
     * @return true if the two RegisteredItem objects have the same identifier, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if they are the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Ensure the other object is not null and is of the same class
        RegisteredItem other = (RegisteredItem) obj;
        return this.item.getItemID() == other.item.getItemID(); // Compare only item ID
    }

    /**
     * Returns the hash code of this RegisteredItem object based on the item's ID.
     * 
     * @return The hash code of this RegisteredItem object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(item.getItemID());  
    }
}
