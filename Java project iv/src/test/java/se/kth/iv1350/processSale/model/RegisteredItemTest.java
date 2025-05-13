package se.kth.iv1350.processSale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.dto.ItemDTO;

public class RegisteredItemTest {
    private RegisteredItem  registeredTestingItem;
    private ItemDTO testingItem;
    private static final int ITEM_ID = 5678;
    private static final double ITEM_PRICE = 21.99;
    private static final double ITEM_VAT = 0.06;
    private static final int ITEM_QUANTITY = 3;
    private static final String ITEM_DESCRIPTION = "Apple Juice 1 L , 100% pure apple juice , no added sugar";
    private static final String ITEM_NAME = "Apple juice";

    @BeforeEach
    public void setUp() {  
        testingItem = new ItemDTO (ITEM_NAME, ITEM_PRICE, ITEM_VAT, ITEM_ID, ITEM_DESCRIPTION);
        registeredTestingItem = new RegisteredItem(testingItem, ITEM_QUANTITY);
    }

    @Test
    public void testIfUpdateQuantityAddsToExistingQuantit() {
        registeredTestingItem.updateQuantity(2);
        assertEquals(5, registeredTestingItem.getQuantity(), "Quantity should be updated to 5");
    }

    @Test
    public void testIfEqualsReturnTrueForSameItemID() {
        ItemDTO equalTestingItem = new ItemDTO (ITEM_NAME, ITEM_PRICE, ITEM_VAT, ITEM_ID, ITEM_DESCRIPTION);
        RegisteredItem equalRegisteredTestingItem = new RegisteredItem(equalTestingItem, ITEM_QUANTITY);
        assertTrue(registeredTestingItem.equals(equalRegisteredTestingItem), "Registered Items with the same itemID should be equal");
    }

    @Test
     public void testIfEqualsReturnFalseForDifferentItemID() {
        ItemDTO differentTestingItem = new ItemDTO ("Banana", 9.99, 0.06, 7891, "Banana 1 kg , fresh bananas , high in potassium");
        RegisteredItem differentRegisteredTestingItem = new RegisteredItem(differentTestingItem, 2);
        assertFalse(registeredTestingItem.equals(differentRegisteredTestingItem), "Registered items with different itemID should not be equal");
    }
    
}
