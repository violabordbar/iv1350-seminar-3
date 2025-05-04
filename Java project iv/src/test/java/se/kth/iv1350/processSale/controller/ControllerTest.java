package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.processSale.integration.AccountingSystem;
import se.kth.iv1350.processSale.integration.DiscountHandler;
import se.kth.iv1350.processSale.integration.InventorySystem;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.RegisteredItem;
import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.dto.SaleDTO;

public class ControllerTest{
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private DiscountHandler discountHandler;
    private Printer printer;
    private Controller controller;
    private ItemDTO testingItem;

    @BeforeEach
    public void setUp() {
        accountingSystem = new AccountingSystem();
        inventorySystem = new InventorySystem();
        discountHandler = new DiscountHandler();
        printer = new Printer();
        controller = new Controller(accountingSystem, inventorySystem, discountHandler, printer);
        controller.startSale();
        testingItem = controller.registerNewItem(1234, 2);
    }

    @Test
    public void testIfItemIsNotNullWhenSaleIsStarted() {
        assertNotNull(testingItem, "Item should not be null after sale is started");
    }

    @Test
    public void testIfItemQuantityDefaultsToZeroWhenUnspecified() {
       controller.registerNewItem(1234, 0);
       controller.receivePayment(100);
       RegisteredItem itemWithTestingQuantity = controller.getSaleInfo().getListOfItems().get(1);
       assertEquals(1, itemWithTestingQuantity.getQuantity(), "Item quantity should default to 1 when unspecified");
    }

    @Test
    public void testIfNullIsReturnedWhenUnexistingItemGetsRegistered() {
        assertEquals(null, controller.registerNewItem(0000, 0), "Registering the item should return null when the item does not exist");
    }

    @Test
    public void testIfTheCorrectItemIsAddedToTheSaleWhenIdentifierIsEntered() {
        assertEquals(1234, testingItem.getItemID(), "Item ID should match the entered identifier");
    }

    @Test
    public void testIfSaleInfoIsNotNullWhenPaymentIsReceived() {
        controller.receivePayment(100);
        assertNotNull(controller.getSaleInfo(), "Sale information should not be null");
    }

    @Test
    public void testIfRunningTotalIsMoreThanZeroWhenItemIsRegistered() {
        assertTrue(controller.getRunningTotal() > 0, "Running total should be greater than 0");
    }

    @Test
    public void testIfTotalVATIsMoreThanZeroWhenItemIsRegistered() {
        assertTrue(controller.getTotalVAT() > 0, "Total VAT should be greater than 0");
    }

    @Test
    public void testIfDiscountIsAppliedWhenDiscountRequestIsMade() {
        assertTrue(controller.discountRequest(1212) < controller.getRunningTotal(), "Discounted total should be less than running total");
    }

    @Test
    public void testIfPaidAmountMatchesTheReceivedPayment() {
        controller.receivePayment(300);
        SaleDTO saleInfo = controller.getSaleInfo();
        assertEquals(300, saleInfo.getPaidAmount(), "Paid amount should match the received payment");
    }
}


