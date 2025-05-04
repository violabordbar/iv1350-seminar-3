package se.kth.iv1350.processSale.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.dto.SaleDTO;

/**
 * The Sale class represents a sale transaction in the system.
 * It's responsible for setting the time and date of the sale, handling the items being registered during the sale,
 * calculating the total price and generating the receipt for the transaction.
 */
public class Sale{
	private Payment payment;
	private final LocalTime saleTime;
	private final LocalDate saleDate;
	private final List<RegisteredItem> listOfItems;

	/**
	 * Creates a new instance of the Sale class with the current time and date and also creates an empty list of items.
	 */
	public Sale() {
		this.saleTime = LocalTime.now();
		this.saleDate = LocalDate.now();
		this.listOfItems = new ArrayList<>();
	}

	/**
	 * Returns the time of the sale.
	 * 
	 * @return The time of the sale.
	 */
	public LocalTime getSaleTime() {
		return saleTime;
	}

	/**
	 * Returns the date of the sale.
	 * 
	 * @return The date of the sale.
	 */
	public LocalDate getSaleDate() {
		return saleDate;
	}

	/**
	 * Sets the payment for the sale.
	 * 
	 * @param payment The payment object representing the payment made by the customer.
	 */
	public void registerPayment(Payment payment) {
		this.payment = payment;
	}

	/**
	 * Creates and returns a SaleDTO object containing the sale information
	 * The sale information includes the total price, total VAT, paid amount, and change. 
	 * 
	 * @return A SaleDTO object containing the sale information.
	 */
	public SaleDTO registerSaleInfo() {
    	int totalCost = calculateCurrentPrice();
		int paidAmount = payment.getPaidAmount();
   	 	int change = calculateChange();
		double totalVAT = calculateTotalVAT();
    	SaleDTO saleDTO = new SaleDTO(
        	saleTime,
        	saleDate,
        	totalCost,
        	totalVAT,
       	 	paidAmount,
        	change,
        	listOfItems
    		);
      return saleDTO;
	}

	/**
	 * Adds an item to the sale with the specified quantity.
	 * 
	 * @param itemDTO The data transfer object representing the item to be added.
	 * @param quantity The quantity of the item to be added.
	 */
	public void addItem(ItemDTO itemDTO, int quantity) { 
		RegisteredItem newItem = new RegisteredItem(itemDTO, quantity);
		if (listOfItems.contains(newItem)) {
			RegisteredItem existingItem = listOfItems.get(listOfItems.indexOf(newItem));
			existingItem.updateQuantity(quantity);
		} 
		else {
			listOfItems.add(newItem);
		}
	}

	/**
	 * Calculates the total VAT for the sale based on the registered items.
	 * 
	 * @return The total VAT for the sale as a rounded integer.
	 */
	public double calculateTotalVAT() {
		double totalVAT = 0;
		for(RegisteredItem item : listOfItems) {
			totalVAT += item.getItem().getItemPrice() * item.getQuantity() * item.getItem().getVAT();
		}
		return Math.round(totalVAT);
	}

	/**
	 * Retrieves the list of the registered items.
	 * 
	 * @return The list of the registered items.
	 */
	public List<RegisteredItem> getListOfItems() {
        return listOfItems;
    }

	/**
	 * Calculates the current price of the sale and rounds it to an integer.
	 * 
	 * @return The current price of the sale as an integer.
	 */
	public int calculateCurrentPrice() {
		double currentTotal = 0;
		for(RegisteredItem itemDTO : listOfItems) {
			currentTotal += itemDTO.getItem().getItemPrice() * itemDTO.getQuantity();
		}
		return (int) Math.round(currentTotal);
	}

	/**
	 * Calculates the change to be given to the customer.
	 * 
	 * @return The change to be given to the customer.
	 */
	public int calculateChange() {
		return payment.getPaidAmount() - calculateCurrentPrice();
	}

}

