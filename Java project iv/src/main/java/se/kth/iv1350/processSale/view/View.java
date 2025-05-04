package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.dto.ItemDTO;

/**
 * The View class is responsible for displaying information to the user and interacting with the controller.
 * It serves as the user interface for the system.
 */
public class View{
	private final Controller contr;

	/**
	 * Creates a new instance of the View class with the specified controller.
	 * 
	 * @param contr The controller that handles the interaction between the view and the model.
	 */
	public View(Controller contr) {
		this.contr = contr;
	}

	private void printItemDetails(ItemDTO item) {
		System.out.println(
			"Item ID: " + item.getItemID() + "\n" + 
			"Item name: " + item.getName() + "\n" +
			"Item price: " + item.getItemPrice() + " SEK" + "\n" +
			"VAT: " + item.getVAT() * 100 + "%" + "\n" +
			"Item description: " + item.getDescription() + "\n"
		);

		System.out.println("Total price (incl VAT): " + contr.getRunningTotal() + " SEK");
		System.out.println("Total VAT: " + contr.getTotalVAT() + " SEK" + "\n");
	}
	
	/**
	 * Simulates a sale process to demonstrate the system's functionality.
	 * The method includes registering items, calculating totals, receiving a payment,
	 * and printing out the result of each step.
	 */
	public void runFakeExecution() {
		contr.startSale();

		ItemDTO firstRegisteredItem = contr.registerNewItem(1234, 2);
		System.out.println("Add 2 items with item ID 1234:");
		printItemDetails(firstRegisteredItem);

		ItemDTO secondRegisteredItem = contr.registerNewItem(5678, 1);
		System.out.println("Add 1 item with item ID 5678:");
		printItemDetails(secondRegisteredItem);

		ItemDTO thirdRegisteredItem = contr.registerNewItem(7891, 2);
		System.out.println("Add 2 items with item ID 7891:");
		printItemDetails(thirdRegisteredItem);

		System.out.println("End sale:");
		System.out.println("Total price (incl VAT): " + contr.getRunningTotal() + " SEK" + "\n");

		contr.receivePayment(500);
		contr.endSale();

	}
}