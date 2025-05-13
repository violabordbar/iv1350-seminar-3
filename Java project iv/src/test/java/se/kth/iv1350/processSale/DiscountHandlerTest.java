package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.model.Sale;

public class DiscountHandlerTest {
    private DiscountHandler discountHandler;
    private Sale sale;
    private ItemDTO testingItem;

    private static final int CUSTOMER_ID_WITH_DISCOUNT = 1212;
    private static final int CUSTOMER_ID_WITHOUT_DISCOUNT = 1100;

    private static final int SMALL_ITEM_QUANTITY = 2;
    private static final int MEDIUM_ITEM_QUANTITY = 10;
    private static final int BIG_ITEM_QUANTITY = 20;
   

    private static final int ITEM_ID = 1234;
    private static final double ITEM_PRICE = 29.99;
    private static final double ITEM_VAT = 0.06;
    private static final String ITEM_DESCRIPTION = "BigWheel Oatmeal 500 g , whole grain oats ,high fiber , gluten free";
    private static final String ITEM_NAME = "BigWheel Oatmeal";

    @BeforeEach 
    public void setUp() {
        discountHandler = new DiscountHandler();
        sale = new Sale();
        testingItem = new ItemDTO (ITEM_NAME, ITEM_PRICE, ITEM_VAT, ITEM_ID, ITEM_DESCRIPTION);
    }

    @Test
    public void testIfFindDiscountReturnsCorrectReducedPriceForSpecifiedCustomerID() {
        sale.addItem(testingItem, SMALL_ITEM_QUANTITY);
        double expectedCustomerDiscountPrice = sale.calculateCurrentPrice() *0.8;
        double actualCustomerDiscountPrice = discountHandler.findDiscount(CUSTOMER_ID_WITH_DISCOUNT, sale);
        assertEquals(expectedCustomerDiscountPrice, actualCustomerDiscountPrice, "Customer with ID 1212 should get 20% discount" );
    }

    @Test
    public void testIfFindDiscountReturnsCorrectReducedPriceForTotalSaleCost() {
        sale.addItem(testingItem, BIG_ITEM_QUANTITY);
        double expectedTotalCostDiscountedPrice = sale.calculateCurrentPrice() * 0.85;
        double actualTotalCostDiscountedPrice = discountHandler.findDiscount(CUSTOMER_ID_WITHOUT_DISCOUNT, sale);
        assertEquals(expectedTotalCostDiscountedPrice, actualTotalCostDiscountedPrice, "A sale with a total cost greater than 500 should get a 15% discount");
    }

    @Test
    public void testIfFindDiscountReturnsCorrectReducedPriceForNumberOfBoughtItems() {
        sale.addItem(testingItem, MEDIUM_ITEM_QUANTITY);
        double expectedBoughtItemsDiscountedPrice = sale.calculateCurrentPrice() - 100;
        double actualBoughtItemsDiscountedPrice = discountHandler.findDiscount(CUSTOMER_ID_WITHOUT_DISCOUNT, sale);
        assertEquals(expectedBoughtItemsDiscountedPrice, actualBoughtItemsDiscountedPrice, "A sale with 10 or more items should get a discount of 100 SEK");
    }
}


