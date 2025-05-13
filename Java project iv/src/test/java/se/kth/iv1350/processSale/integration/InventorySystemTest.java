package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.processSale.dto.ItemDTO;

public class InventorySystemTest {
    private InventorySystem inventorySystem = new InventorySystem();

    private static final int VALID_ITEM_ID = 1234;
    private static final int INVALID_ITEM_ID = 1111;

    @Test 
    public void testIfValidateidentifierReturnsCorrectItemDTOIfItemIDIsValid() {
        ItemDTO testingItemDTO = inventorySystem.validateIdentifier(VALID_ITEM_ID);
        assertNotNull(testingItemDTO, "Item ID should be found for the valid itemID and therefore not null");
        assertEquals(VALID_ITEM_ID, testingItemDTO.getItemID(), "Returned ItemDTO should have the same item ID as specified");
    }

    @Test 
    public void testIfValidateIdentifierReturnsNullIfItemIDIsInvalid() {
        ItemDTO nonExistingItemDTO = inventorySystem.validateIdentifier(INVALID_ITEM_ID);
        assertNull(nonExistingItemDTO, "Item should not be found for an invalid item ID");
    }
    
}
