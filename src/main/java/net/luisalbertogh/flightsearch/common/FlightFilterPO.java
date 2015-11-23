/**
 * The flight filter PO
 */
package net.luisalbertogh.flightsearch.common;

import net.luisalbertogh.flightsearch.domain.AirportPO;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public final class FlightFilterPO {
	/** The airport of origin. */
	private AirportPO origin;

	/** The airport of destination. */
	private AirportPO destination;

	/** The operating airline. */
	private String airline;

	/**
	 * Default constructor.
	 * */
	public FlightFilterPO() {
		/* Empty */
	}

	/**
	 * Constructor with fields.
	 *
	 * @param originArg
	 * @param destinationArg
	 * @param airlineArg
	 */
	public FlightFilterPO(AirportPO originArg, AirportPO destinationArg,
			String airlineArg) {
		origin = originArg;
		destination = destinationArg;
		airline = airlineArg;
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
	 * Equals method.
	 *
	 * @param otherFilter
	 *            - Other filter
	 * @return True or false
	 */
	@Override
	public boolean equals(Object otherFilter) {
		return true;
	}
}
