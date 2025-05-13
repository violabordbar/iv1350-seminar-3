package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.model.RegisteredItem;
import se.kth.iv1350.processSale.model.Sale;

/**
 * The DiscountHandler class handles the discount logic for the sale.
 * 
 */
public class DiscountHandler{

	/**
	 * Creates a new instance of the DiscountHandler class.
	 */
	public DiscountHandler() {
	}

	/**
	 * Calculates the discount based on the customer ID, the total cost of the sale and the number of bought items.
	 * The method applies either a fixed discount or a percentage discount based on certain conditions.
	 * If none of the discount requirements are met, the method returns the total cost without any discount.
	 * 
	 * @param customerID The identification number of the customer.
	 * @param sale The sale object containing the list of items and the total cost.
	 * @return The price after the discount has been applied. If no conditions are met, the total cost is returned without any discount.
	 */
	public double findDiscount(int customerID, Sale sale) {

		double totalCost = sale.calculateCurrentPrice();
		int numberOfItems = 0;
		for (RegisteredItem item : sale.getListOfItems()) {
			numberOfItems += item.getQuantity();
		}

		if(customerID == 1212) {
			totalCost *= 0.8;
		}

		if(totalCost > 500){
			totalCost *= 0.85;
		}

		if(numberOfItems >= 10){
			totalCost -= 100;
		}

		return totalCost;
	}
}
