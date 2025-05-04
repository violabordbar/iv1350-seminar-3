package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.dto.SaleDTO;

public class SaleTest{
    private Sale sale;
    private Payment payment; 
    private ItemDTO item;
    private static final int ITEM_ID = 1234;
    private static final double ITEM_PRICE = 29.99;
    private static final double ITEM_VAT = 0.06;
    private static final int ITEM_QUANTITY = 2;
    private static final String ITEM_DESCRIPTION = "BigWheel Oatmeal 500 g , whole grain oats ,high fiber , gluten free";
    private static final String ITEM_NAME = "BigWheel Oatmeal";
    private static final int PAYMENT_AMOUNT = 100;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
        payment = new Payment(PAYMENT_AMOUNT);
        item = new ItemDTO(ITEM_NAME, ITEM_PRICE, ITEM_VAT, ITEM_ID, ITEM_DESCRIPTION);
    }

    @AfterEach
    public void tearDown() {
        payment = null;
        sale = null;
        item = null;
    }

    @Test
    public void testIfSaleIsCreatedWithTheCorrectAttributes() {
        assertNotNull(sale.getSaleTime(), "Sale time should not be null");
        assertNotNull(sale.getSaleDate(), "Sale date should not be null");
        assertNotNull(sale.getListOfItems(), "List of items should not be null");
        assertEquals(0, sale.getListOfItems().size(), "List of items should be empty");
    }

    @Test
    public void testIfSaleTimeIsCorrect() {
        LocalTime expectedSaleTime = LocalTime.now();
        LocalTime SaleTime = sale.getSaleTime();
        assertEquals(expectedSaleTime.getHour(), SaleTime.getHour(), "Sale time hour should be correct");
        assertEquals(expectedSaleTime.getMinute(), SaleTime.getMinute(), "Sale time minute should be correct");
    }

    @Test
    public void testIfSaleDateIsCorrect() {
        LocalDate expectedSaleDate = LocalDate.now();
        LocalDate SaleDate = sale.getSaleDate();
        assertEquals(expectedSaleDate,SaleDate, "Sale date should be correct");
    }

    @Test
    public void testIfPaidAmountIsCorrectlyRegistered() {
        sale.addItem(item, ITEM_QUANTITY);
        sale.registerPayment(payment);
        assertNotNull(sale.registerSaleInfo(), "Payment should not be null");
        assertEquals(payment.getPaidAmount(), sale.registerSaleInfo().getPaidAmount(), "Paid amount should be 100 SEK");
    }

    @Test
    public void testIfSaleInfoRegistersTheCorrectAttributes() {
        sale.addItem(item, ITEM_QUANTITY); 
        sale.registerPayment(payment);

        int expectedTotalPrice = sale.calculateCurrentPrice();
        int expectedChange = PAYMENT_AMOUNT - expectedTotalPrice;  
        double expectedTotalVAT = sale.calculateTotalVAT();
        SaleDTO saleDTO = sale.registerSaleInfo();
   
        assertNotNull(saleDTO, "SaleDTO should not be null");
        assertEquals(expectedTotalPrice, saleDTO.getTotalPrice(), "Total price should be 60 SEK");
        assertEquals(expectedChange, saleDTO.getChange(), "Change should be 40 SEK");
        assertEquals(PAYMENT_AMOUNT, saleDTO.getPaidAmount(), "Paid amount should be 100 SEK");
        assertEquals(expectedTotalVAT, saleDTO.getTotalVAT(), "Total VAT should be ~12 SEK");
    }

    @Test
    public void testIfQuantityIsUpdatedWhenAddingExistingItem() {
        sale.addItem(item, ITEM_QUANTITY); 
        sale.addItem(item, ITEM_QUANTITY);
        List<RegisteredItem> itemList = sale.getListOfItems();
        RegisteredItem itemInSale = itemList.get(0);
        assertEquals(ITEM_QUANTITY * 2, itemInSale.getQuantity(), "Quantity should be 4");
    }

    @Test
    public void testIfCorrectItemIsAddedToSaleWhenItemIsRegistered() {
        sale.addItem(item, ITEM_QUANTITY);
        List<RegisteredItem> itemsInSale = sale.getListOfItems();
        RegisteredItem addedItem = itemsInSale.get(0);
        assertEquals(ITEM_NAME, addedItem.getItem().getName(), "Item should be BigWheel Oatmeal");
        assertEquals(ITEM_QUANTITY, addedItem.getQuantity(), "Quantity should be 2");
    }

    @Test
    public void testIfTotalVATIsCorrectlyCalculatedForItemsInSale() {
        sale.addItem(item, ITEM_QUANTITY);
        double expectedTotalVAT = Math.round(ITEM_PRICE * ITEM_VAT * ITEM_QUANTITY);
        double TotalVAT = sale.calculateTotalVAT();
        assertEquals(expectedTotalVAT, TotalVAT, "Total VAT should be 12 SEK");
    } 

    @Test
    public void testIfSaleStartsWithEmptyItemList() {
        List<RegisteredItem> itemsInSale = sale.getListOfItems();
        assertTrue(itemsInSale.isEmpty(), "List of items should be empty");
    }

    @Test
    public void testIfCurrentPriceIsCorrectlyCalculatedWhenItemsAreAdded() {
        sale.addItem(item, ITEM_QUANTITY);
        double expectedCurrentPrice = Math.round(ITEM_PRICE * ITEM_QUANTITY);
        double CurrentPrice = sale.calculateCurrentPrice();
        assertEquals(expectedCurrentPrice, CurrentPrice, "Current price should be 60 SEK");
    }

    @Test
    public void testIfChangeIsCorrectlyCalculatedAfterPayment() {
        sale.addItem(item, ITEM_QUANTITY);
        sale.registerPayment(payment);
        int expectedChange = PAYMENT_AMOUNT - sale.calculateCurrentPrice();
        int change = sale.calculateChange();
        assertEquals(expectedChange, change, "Change should be 71 SEK");
    }
}


