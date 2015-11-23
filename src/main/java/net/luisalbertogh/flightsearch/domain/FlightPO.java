/**
 * Flight PO
 */
package net.luisalbertogh.flightsearch.domain;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public final class FlightPO {
	/** The airport of origin. */
	private AirportPO origin;

	/** The airport of destination. */
	private AirportPO destination;

	/** The operating airline. */
	private String airline;

	/** The base price. */
	private float basePrice;

	/**
	 * Default constructor.
	 * */
	public FlightPO() {
		/* Empty */
	}

	/**
	 * Constructor with fields.
	 *
	 * @param originArg
	 * @param destinationArg
	 * @param airlineArg
	 * @param basePriceArg
	 */
	public FlightPO(AirportPO originArg, AirportPO destinationArg,
			String airlineArg, float basePriceArg) {
		origin = originArg;
		destination = destinationArg;
		airline = airlineArg;
		basePrice = basePriceArg;
	}

	/**
	 * @return the origin
	 */
	public AirportPO getOrigin() {
		return origin;
	}

	/**
	 * @param originArg
	 *            the origin to set
	 */
	public void setOrigin(AirportPO originArg) {
		origin = originArg;
	}

	/**
	 * @return the destination
	 */
	public AirportPO getDestination() {
		return destination;
	}

	/**
	 * @param destinationArg
	 *            the destination to set
	 */
	public void setDestination(AirportPO destinationArg) {
		destination = destinationArg;
	}

	/**
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * @param airlineArg
	 *            the airline to set
	 */
	public void setAirline(String airlineArg) {
		airline = airlineArg;
	}

	/**
	 * @return the basePrice
	 */
	public float getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePriceArg
	 *            the basePrice to set
	 */
	public void setBasePrice(float basePriceArg) {
		basePrice = basePriceArg;
	}
}
