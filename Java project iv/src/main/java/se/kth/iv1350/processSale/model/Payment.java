package se.kth.iv1350.processSale.model;

/**
 * The Payment class represents a payment made by the customer during a sale.
 * It contains information about the amount paid.
 */
public class Payment{
	private final int paidAmount;

	/**
	 * Creates a new instance of the Payment class with the specified paid amount.
	 * 
	 * @param paidAmount The amount paid by the customer in SEK.
	 */
	public Payment(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * Returns the amount paid by the customer.
	 * 
	 * @return The amount paid by the customer in SEK.
	 */
	public int getPaidAmount() {
		return this.paidAmount;
	}

}
