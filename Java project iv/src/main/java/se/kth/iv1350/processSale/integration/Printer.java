package se.kth.iv1350.processSale.integration;

import java.util.Locale;
import java.time.format.DateTimeFormatter;
import se.kth.iv1350.processSale.dto.SaleDTO;
import se.kth.iv1350.processSale.model.RegisteredItem;

/**
 * The Printer class is responsible for printing the receipt of a sale. 
 * It takes a SaleDTO object as input and formats the receipt information for display.
 */
public class Printer{

	/**
	 * Creates a new instance of the Printer class.
	 * 
	 */
	public Printer() {
	}

	/**
	 * Prints the receipt to the user interface. 
	 * It displays the sale's time and date, the details for each bought item, 
	 * the total price, total VAT, amount paid in cash, and the change to be given to the customer.
	 * 
	 * @param saleDTO The data transfer object containing the sale information to be printed.
	 */
	public void printReceipt(SaleDTO saleDTO) {
		System.out.println("-------------------- Begin receipt --------------------" + "\n");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
		String  formattedTime = saleDTO.getSaleTime().format(timeFormat);
		System.out.println("Time and date of sale: " + saleDTO.getSaleDate() + " " + formattedTime + "\n");

		for(RegisteredItem registeredItem : saleDTO.getListOfItems()) {
			String itemName = registeredItem.getItem().getName();
			int itemQuantity = registeredItem.getQuantity();
			double itemPrice = registeredItem.getItem().getItemPrice();
			double totalItemPrice = itemQuantity * itemPrice;

			System.out.printf(Locale.US, "%-25s %-12s %-7.2f SEK%n", 
            itemName, 
            itemQuantity + " x " + itemPrice, 
            totalItemPrice);  
		}

		System.out.println();
		System.out.println("Total: " + saleDTO.getTotalPrice() + " SEK");
		System.out.println("VAT: " + saleDTO.getTotalVAT() + " SEK\n");

		System.out.println("Cash: " + saleDTO.getPaidAmount() + " SEK");
		System.out.println("Change: " + saleDTO.getChange() + " SEK\n");

		System.out.println("-------------------- End receipt --------------------");
	}
}