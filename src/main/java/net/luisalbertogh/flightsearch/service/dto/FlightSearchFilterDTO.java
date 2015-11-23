/**
 * The flight search filter DTO
 */
package net.luisalbertogh.flightsearch.service.dto;

import java.time.LocalDate;

import net.luisalbertogh.flightsearch.domain.AirportPO;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public final class FlightSearchFilterDTO {
	/** The airport of origin */
	private AirportPO origin;

	/** The airport of destination */
	private AirportPO destination;

	/** The departure date */
	private LocalDate date;

	/** The number of adults */
	private int numAdults;

	/** The number of children */
	private int numChildren;

	/** The number of infants */
	private int numInfants;

	/**
	 * Default constructor.
	 */
	public FlightSearchFilterDTO() {
		/* Empty */
	}

	/**
	 * Constructor with fields.
	 *
	 * @param originArg
	 * @param destinationArg
	 * @param dateArg
	 * @param numAdultsArg
	 * @param numChildrenArg
	 * @param numInfantsArg
	 */
	public FlightSearchFilterDTO(AirportPO originArg, AirportPO destinationArg,
			LocalDate dateArg, int numAdultsArg, int numChildrenArg,
			int numInfantsArg) {
		origin = originArg;
		destination = destinationArg;
		date = dateArg;
		numAdults = numAdultsArg;
		numChildren = numChildrenArg;
		numInfants = numInfantsArg;
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
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param dateArg
	 *            the date to set
	 */
	public void setDate(LocalDate dateArg) {
		date = dateArg;
	}

	/**
	 * @return the numAdults
	 */
	public int getNumAdults() {
		return numAdults;
	}

	/**
	 * @param numAdultsArg
	 *            the numAdults to set
	 */
	public void setNumAdults(int numAdultsArg) {
		numAdults = numAdultsArg;
	}

	/**
	 * @return the numChildren
	 */
	public int getNumChildren() {
		return numChildren;
	}

	/**
	 * @param numChildrenArg
	 *            the numChildren to set
	 */
	public void setNumChildren(int numChildrenArg) {
		numChildren = numChildrenArg;
	}

	/**
	 * @return the numInfants
	 */
	public int getNumInfants() {
		return numInfants;
	}

	/**
	 * @param numInfantsArg
	 *            the numInfants to set
	 */
	public void setNumInfants(int numInfantsArg) {
		numInfants = numInfantsArg;
	}

}
