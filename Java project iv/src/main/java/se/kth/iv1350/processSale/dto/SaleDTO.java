package se.kth.iv1350.processSale.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import se.kth.iv1350.processSale.model.RegisteredItem;

/**
 * The SaleDTO class represents a data transfer object for a sale.
 * It contains information about the sale's time, date, total price, change, and the registered items.
 */
public class SaleDTO{
	private final LocalTime saleTime;
	private final LocalDate saleDate;
	private final double totalPrice;
	private final double totalVAT;
	private final int paidAmount;
	private final int change;
	private final List<RegisteredItem> listOfItems;

	/**
	 * Creates a new instance of the SaleDTO class with the specified sale time, sale date, total price, total VAT, change, paid amount and registered items.
	 * 
	 * @param saleTime The time of the sale.
	 * @param saleDate The date of the sale.
	 * @param totalPrice The total price of the sale.
	 * @param totalVAT The total VAT of the sale.
	 * @param paidAmount The amount paid by the customer.
	 * @param change The change to be given to the customer.
	 * @param listOfItems The list of registered items in the sale represented as RegisteredItem objects.
	 */
	public SaleDTO(LocalTime saleTime, LocalDate saleDate, double totalPrice, double totalVAT, int paidAmount, int change, List<RegisteredItem> originalList) {
		this.saleTime = saleTime;
		this.saleDate = saleDate;
		this.totalPrice = totalPrice;
		this.totalVAT = totalVAT;
		this.paidAmount = paidAmount;
		this.change = change;
		this.listOfItems = List.copyOf(originalList);
	}

		/**
		 * Returns the time of the sale.
		 * 
		 * @return The time of the sale.
		 */
		public LocalTime getSaleTime() {
			return this.saleTime;
		}

		/**
		 * Returns the date of the sale.
		 * 
		 * @return The date of the sale.
		 */
		public LocalDate getSaleDate() {
			return this.saleDate;
		}

		/**
		 * Returns the total price of the sale.
		 * 
		 * @return The total price of the sale.
		 */
		public double getTotalPrice() {
			return this.totalPrice;
		}

		/**
		 * Returns the total VAT of the sale.
		 * 
		 * @return The total VAT of the sale.
		 */
		public double getTotalVAT() {
			return this.totalVAT;
		}

		/**
		 * Returns the amount paid by the customer.
		 * 
		 * @return The amount paid by the customer.
		 */
		public int getPaidAmount() {
			return this.paidAmount;
		}

		/**
		 * Returns the change to be given to the customer.
		 * 
		 * @return The change to be given to the customer.
		 */
		public int getChange() {
			return this.change;
		}

		/**
		 * Returns the list of registered items.
		 * 
		 * @return The list of registered items as RegisteredItem objects.
		 */
		public List<RegisteredItem> getListOfItems() {
			return this.listOfItems;
		}

}

