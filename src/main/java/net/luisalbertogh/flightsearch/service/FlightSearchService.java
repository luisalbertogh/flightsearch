/**
 * The flight search service interface.
 */
package net.luisalbertogh.flightsearch.service;

import java.util.List;

import net.luisalbertogh.flightsearch.service.dto.FlightResultDTO;
import net.luisalbertogh.flightsearch.service.dto.FlightSearchFilterDTO;

/**
 * @author lagarcia
 *
 */
public interface FlightSearchService {
	/**
	 * Find flights by filter.
	 * 
	 * @param filter
	 *            - The flight search filter
	 * @return The list of flights
	 */
	public List<FlightResultDTO> findFlightsByFilter(
			FlightSearchFilterDTO filter);
}
