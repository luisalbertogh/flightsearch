/**
 * Test utilities class.
 */
package net.luisalbertogh.flightsearch.test.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.luisalbertogh.flightsearch.common.FlightFilterPO;
import net.luisalbertogh.flightsearch.domain.AirlinePO;
import net.luisalbertogh.flightsearch.domain.AirportPO;
import net.luisalbertogh.flightsearch.domain.FlightPO;

import org.apache.log4j.Logger;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public final class TestUtils {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(TestUtils.class);

	/** The airports map. */
	private static Map<String, AirportPO> airportsMap;

	/** The airlines map. */
	private static Map<String, AirlinePO> airlinesMap;

	/** The flights map. */
	private static List<FlightPO> flightsList;

	/**
	 * Load data.
	 */
	public static void init() {
		logger.info("Loading data...");
		try {
			airlinesMap = loadAirlinesFromFile("src/test/resources/airlines.csv");
			airportsMap = loadAirportsFromFile("src/test/resources/airports.csv");
			flightsList = loadFlightsFromFile("src/test/resources/flights.csv");
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/**
	 * Clean up data.
	 */
	public static void destroy() {
		logger.info("Clean up data...");
		airlinesMap = null;
		airportsMap = null;
		flightsList = null;
	}

	/**
	 * Return airline by code.
	 *
	 * @param key
	 *            - The key map
	 * @return The airline PO
	 */
	public static AirlinePO getAirlineByCode(String key) {
		return airlinesMap.get(key);
	}

	/**
	 * Return airport by code.
	 *
	 * @param key
	 *            - The key map
	 * @return The airport PO
	 */
	public static AirportPO getAirportByCode(String key) {
		return airportsMap.get(key);
	}

	/**
	 * Return flights by filter.
	 *
	 * @param filter
	 *            - The flight filter
	 * @return The matching flight list
	 */
	public static List<FlightPO> getFlightByFilter(FlightFilterPO filter) {
		List<FlightPO> aList = new ArrayList<>();
		for (FlightPO flight : flightsList) {
			boolean valid = false;

			/* Filtered by origin & destination */
			if (filter.getOrigin() != null && filter.getDestination() != null) {
				if (flight.getOrigin().getCode()
						.equals(filter.getOrigin().getCode())
						&& flight.getDestination().getCode()
								.equals(filter.getDestination().getCode())) {
					valid = true;
				} else {
					valid = false;
				}
			}

			/* If valid, add to the list */
			if (valid) {
				aList.add(flight);
			}
		}

		return aList;
	}

	/**
	 * Return the airlines as a list.
	 *
	 * @return The airlines list.
	 */
	public static List<AirlinePO> getAirlinesAsList() {
		List<AirlinePO> aList = new ArrayList<>();
		Set<Entry<String, AirlinePO>> entries = airlinesMap.entrySet();
		for (Entry<String, AirlinePO> entry : entries) {
			aList.add(entry.getValue());
		}

		return aList;
	}

	/**
	 * Return the airports as a list.
	 *
	 * @return The airlines list.
	 */
	public static List<AirportPO> getAirportAsList() {
		List<AirportPO> aList = new ArrayList<>();
		Set<Entry<String, AirportPO>> entries = airportsMap.entrySet();
		for (Entry<String, AirportPO> entry : entries) {
			aList.add(entry.getValue());
		}

		return aList;
	}

	/**
	 * Load flights from file.
	 *
	 * @param filepath
	 *            - The file path
	 * @return The list of flight POs
	 * @throws Exception
	 */
	public static List<FlightPO> loadFlightsFromFile(String filepath)
			throws Exception {

		/* The flights list */
		List<FlightPO> flights = new ArrayList<>();

		/* Load from file */
		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(new FileReader(filepath));
		try {
			String line = null;
			while ((line = bf.readLine()) != null) {
				/* Skip comment lines */
				if (line.indexOf("#") != -1) {
					continue;
				}

				FlightPO flight = createFlightPO(line);
				flights.add(flight);
			}
		} catch (IOException ioe) {
			logger.error(ioe);
		} finally {
			bf.close();
		}

		return flights;
	}

	/**
	 * Load airports from file.
	 *
	 * @param filepath
	 *            - The file path
	 * @return The map of airport POs
	 * @throws Exception
	 */
	public static Map<String, AirportPO> loadAirportsFromFile(String filepath)
			throws Exception {

		/* The airports map */
		Map<String, AirportPO> airports = new LinkedHashMap<>();

		/* Load from file */
		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(new FileReader(filepath));
		try {
			String line = null;
			while ((line = bf.readLine()) != null) {
				/* Skip comment lines */
				if (line.indexOf("#") != -1) {
					continue;
				}

				AirportPO airport = createAirportPO(line);
				airports.put(line.split(",")[0], airport);
			}
		} catch (IOException ioe) {
			logger.error(ioe);
		} finally {
			bf.close();
		}

		return airports;
	}

	/**
	 * Load airlines from file.
	 *
	 * @param filepath
	 *            - The file path
	 * @return The map of airline POs
	 * @throws Exception
	 */
	public static Map<String, AirlinePO> loadAirlinesFromFile(String filepath)
			throws Exception {

		/* The airlines map */
		Map<String, AirlinePO> airlines = new LinkedHashMap<>();

		/* Load from file */
		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(new FileReader(filepath));
		try {
			String line = null;
			while ((line = bf.readLine()) != null) {
				/* Skip comment lines */
				if (line.indexOf("#") != -1) {
					continue;
				}

				AirlinePO airline = createAirlinePO(line);
				airlines.put(line.split(",")[0], airline);
			}
		} catch (IOException ioe) {
			logger.error(ioe);
		} finally {
			bf.close();
		}

		return airlines;
	}

	/**
	 * Create a flight PO from a string line.
	 *
	 * @param line
	 *            - The string line
	 * @return A flight PO
	 */
	private static FlightPO createFlightPO(String line) {
		String[] parts = line.split(",");

		/* Flight details */
		AirportPO origin = airportsMap.get(parts[0]);
		AirportPO destination = airportsMap.get(parts[1]);

		return new FlightPO(origin, destination, parts[2],
				Float.parseFloat(parts[3]));
	}

	/**
	 * Create an airport PO from a string line.
	 *
	 * @param line
	 *            - The string line
	 * @return An airport PO
	 */
	private static AirportPO createAirportPO(String line) {
		String[] parts = line.split(",");
		return new AirportPO(parts[0], parts[1]);
	}

	/**
	 * Create an airline PO from a string line.
	 *
	 * @param line
	 *            - The string line
	 * @return An airport PO
	 */
	private static AirlinePO createAirlinePO(String line) {
		String[] parts = line.split(",");
		return new AirlinePO(parts[0], parts[1], Float.parseFloat(parts[2]));
	}

	/**
	 * @return the airportsMap
	 */
	public static Map<String, AirportPO> getAirportsMap() {
		return airportsMap;
	}

	/**
	 * @return the airlinesMap
	 */
	public static Map<String, AirlinePO> getAirlinesMap() {
		return airlinesMap;
	}

	/**
	 * @return the flightsList
	 */
	public static List<FlightPO> getFlightsList() {
		return flightsList;
	}
}
