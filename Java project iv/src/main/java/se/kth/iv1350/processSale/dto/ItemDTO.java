package se.kth.iv1350.processSale.dto;

/**
 * The ItemDTO class represents a data transfer object for an item in the system. 
 * It contains the name, price, VAT, ID and description of the item.
 */
public final class ItemDTO{
	private final String name;
	private final double itemPrice;
	private final double VAT;
	private final int itemID;
	private final String description;

	/**
	 * Creates an instance of the ItemDTO class with the specified name, price, VAT, ID and description.
	 * 
	 * @param name The name of the item.
	 * @param itemPrice The price of the item.
	 * @param VAT The tax rate/VAT of the item.
	 * @param itemID The identifier of the item.
	 * @param description The description of the item.
	 */
	public ItemDTO(String name, double itemPrice, double VAT, int itemID, String description) {
		this.name = name;
		this.itemPrice = itemPrice;
		this.VAT = VAT;
		this.itemID = itemID;
		this.description = description;
	}

		/**
		 * Returns the name of the item.
		 * 
		 * @return The name of the item.
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * Returns the price of the item.
		 * 
		 * @return The price of the item.
		 */
		public double getItemPrice() {
			return this.itemPrice;
		}

		/**
		 * Returns the VAT percentage rate of the item.
		 * 
		 * @return The VAT of the item represented as a percentage.
		 */
		public double getVAT() {
			return this.VAT;
		}

		/**
		 * Returns the ID of the item.
		 * 
		 * @return The ID of the item.
		 */
		public int getItemID() {
			return this.itemID;
		}

		/**
		 * Returns the description of the item.
		 * 
		 * @return The description of the item.
		 */
		public String getDescription() {
			return this.description;
		}
}