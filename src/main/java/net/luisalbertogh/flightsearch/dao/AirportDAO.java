/**
 * Airport DAO interface.
 */
package net.luisalbertogh.flightsearch.dao;

import java.util.List;

import net.luisalbertogh.flightsearch.domain.AirportPO;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public interface AirportDAO {
	/**
	 * Find all airports.
	 *
	 * @return The lis of airports
	 */
	public List<AirportPO> findAll();

	/**
	 * Get airport by code.
	 *
	 * @param code
	 *            - The airport code
	 * @return The airport PO
	 */
	public AirportPO getByCode(String code);
}
