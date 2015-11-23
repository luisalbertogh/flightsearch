/**
 * The flight DAO interface.
 */
package net.luisalbertogh.flightsearch.dao;

import java.util.List;

import net.luisalbertogh.flightsearch.common.FlightFilterPO;
import net.luisalbertogh.flightsearch.domain.FlightPO;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public interface FlightDAO {
	/**
	 * Find all flights.
	 *
	 * @return The list of flights.
	 */
	public List<FlightPO> findAll();

	/**
	 * Find flight by filter.
	 *
	 * @param filter
	 *            - The flight filter
	 * @return The list of selected flights
	 */
	public List<FlightPO> findByFilter(FlightFilterPO filter);
}
