/**
 * Airline DAO interface.
 */
package net.luisalbertogh.flightsearch.dao;

import java.util.List;

import net.luisalbertogh.flightsearch.domain.AirlinePO;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public interface AirlineDAO {
	/**
	 * Find all airlines.
	 *
	 * @return The lis of airlines
	 */
	public List<AirlinePO> findAll();

	/**
	 * Get airline by code.
	 *
	 * @param code
	 *            - The airline code
	 * @return The airline PO
	 */
	public AirlinePO getByCode(String code);
}
