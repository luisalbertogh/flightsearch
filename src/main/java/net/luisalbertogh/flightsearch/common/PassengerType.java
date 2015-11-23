/**
 * The passenger types.
 */
package net.luisalbertogh.flightsearch.common;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public enum PassengerType {
	/** Adult */
	ADULT("Adult", 0),
	/** Child */
	CHILD("Child", 20),
	/** INFANT */
	INFANT("Infant", 0);

	/** The type name */
	private String name;

	/** The possible price discount. */
	private int discount;

	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param discount
	 */
	PassengerType(String name, int discount) {
		this.name = name;
		this.discount = discount;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}
}
