package se.kth.iv1350.processSale.integration;

/**
 * The SystemCreator class is responsible for creating instances of various system components used in the program.
 * It initializes the InventorySystem, AccountingSystem, DiscountHandler, and Printer classes.
 */
public class SystemCreator{
	private final InventorySystem inventorySystem;
	private final AccountingSystem accountingSystem;
	private final DiscountHandler discountHandler;
	private final Printer printer;


	/**
	 * Creates a new instance of the SystemCreator class.
	 * It initializes the necessary system components InventorySystem, AccountingSystem, DiscountHandler, and Printer.
	 */
	public SystemCreator() {
		this.inventorySystem = new InventorySystem();
		this.accountingSystem = new AccountingSystem();
		this.discountHandler = new DiscountHandler();
		this.printer = new Printer();
	}

	/**
	 * Returns the AccountingSystem instance.
	 * 
	 * @return The AccountingSystem instance.
	 */
	public AccountingSystem getAccountingSystem() {
		return this.accountingSystem;
	}

	/**
	 * Returns the InventorySystem instance.
	 * 
	 * @return The InventorySystem instance.
	 */
	public InventorySystem getInventorySystem() {
		return this.inventorySystem;
	}

	/**
	 * Returns the DiscountHandler instance.
	 * 
	 * @return The DiscountHandler instance.
	 */
	public DiscountHandler getDiscountHandler() {
		return this.discountHandler;
	}

	/**
	 * Returns the Printer instance.
	 * 
	 * @return The Printer instance.
	 */
	public Printer getPrinter() {
		return this.printer;
	}
}
