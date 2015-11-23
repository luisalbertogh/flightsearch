/**
 * Flight search result DTO.
 */
package net.luisalbertogh.flightsearch.service.dto;

import net.luisalbertogh.flightsearch.domain.FlightPO;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public final class FlightResultDTO {
	/** The resulting flight */
	private FlightPO flight;

	/** The total price */
	private float totalPrice;

	/**
	 * Default constructor.
	 */
	public FlightResultDTO() {
		/* EMPTY */
	}

	/**
	 * Constructor with fields.
	 *
	 * @param flightArg
	 * @param totalPriceArg
	 */
	public FlightResultDTO(FlightPO flightArg, float totalPriceArg) {
		flight = flightArg;
		totalPrice = totalPriceArg;
	}

	/**
	 * @return the flight
	 */
	public FlightPO getFlight() {
		return flight;
	}

	/**
	 * @param flightArg
	 *            the flight to set
	 */
	public void setFlight(FlightPO flightArg) {
		flight = flightArg;
	}

	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPriceArg
	 *            the totalPrice to set
	 */
	public void setTotalPrice(float totalPriceArg) {
		totalPrice = totalPriceArg;
	}

	/**
	 * Equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FlightResultDTO) {
			FlightResultDTO otherResult = (FlightResultDTO) obj;
			if (otherResult.getFlight().getAirline()
					.equalsIgnoreCase(this.flight.getAirline())
					&& otherResult.getTotalPrice() == this.totalPrice) {
				return true;
			}
		}

		return false;
	}
}
