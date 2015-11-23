/**
 * Main integration test suite.
 */
package net.luisalbertogh.flightsearch.test;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestSuite;
import net.luisalbertogh.flightsearch.FlightSearch;
import net.luisalbertogh.flightsearch.common.FlightFilterPO;
import net.luisalbertogh.flightsearch.dao.AirlineDAO;
import net.luisalbertogh.flightsearch.dao.FlightDAO;
import net.luisalbertogh.flightsearch.domain.AirportPO;
import net.luisalbertogh.flightsearch.domain.FlightPO;
import net.luisalbertogh.flightsearch.service.dto.FlightResultDTO;
import net.luisalbertogh.flightsearch.service.dto.FlightSearchFilterDTO;
import net.luisalbertogh.flightsearch.service.impl.FlightSearchServiceImpl;
import net.luisalbertogh.flightsearch.test.utils.TestUtils;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(FlightSearch.class)
@IntegrationTest
public class FlightSearchITest {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(TestSuite.class);

	/** The airline DAO */
	@Mock
	private AirlineDAO airlineDAO;

	/** The flight DAO */
	@Mock
	private FlightDAO flightDAO;

	/** The flight service */
	private FlightSearchServiceImpl flightService;

	/**
	 * Set up the test suite.
	 */
	@BeforeClass
	public static void setUpSuite() {
		logger.info("[INTEGRATION TEST] Setting up test suite...");

		/* Load data from files */
		TestUtils.init();
	}

	/**
	 * Set up any test.
	 */
	@Before
	public void setUp() {
		logger.info("[INTEGRATION TEST] Setting up a single test...");

		/* Init service */
		flightService = new FlightSearchServiceImpl();

		/* Set mock DAOs */
		flightService.setAirlineDAO(airlineDAO);
		flightService.setFlightDAO(flightDAO);
	}

	/**
	 * Destroy the test suite.
	 */
	@AfterClass
	public static void tearDownSuite() {
		logger.info("[INTEGRATION TEST] Cleaning up test suite...");

		/* Destroy mock data */
		TestUtils.destroy();
	}

	/**
	 * Destroy any test.
	 */
	@After
	public void tearDown() {
		logger.info("[INTEGRATION TEST] Cleaning up a single test...");
	}

	/**
	 * Test flight search service. Example 01.
	 */
	@Test
	public void testFlightSearchService01() {
		logger.info("[INTEGRATION TEST] Test flight search service - Example 1");
		logger.info("1 adult, more than 30 days to departure, from AMS to FRA");

		/* Airports (origin & destination) */
		AirportPO origin = new AirportPO("AMS", "Amsterdam");
		AirportPO destination = new AirportPO("FRA", "Frankfurt");

		/* Mock flight DAO */
		FlightFilterPO filter = new FlightFilterPO();
		filter.setOrigin(origin);
		filter.setDestination(destination);
		when(flightDAO.findByFilter(filter)).thenReturn(
				TestUtils.getFlightByFilter(filter));

		/* Mock airline DAO */
		when(airlineDAO.getByCode("TK")).thenReturn(
				TestUtils.getAirlineByCode("TK"));
		when(airlineDAO.getByCode("LH")).thenReturn(
				TestUtils.getAirlineByCode("LH"));

		/* Invoke service */
		FlightSearchFilterDTO filterDTO = new FlightSearchFilterDTO();
		filterDTO.setOrigin(origin);
		filterDTO.setDestination(destination);
		filterDTO.setDate(LocalDate.now().plusDays(31));
		filterDTO.setNumAdults(1);
		List<FlightResultDTO> results = flightService
				.findFlightsByFilter(filterDTO);

		/* Verify DAO invocations */
		verify(flightDAO).findByFilter(filter);
		verify(airlineDAO, atLeastOnce()).getByCode("TK");
		verify(airlineDAO, atLeastOnce()).getByCode("LH");

		/* Check result */
		List<FlightResultDTO> checkResults = new ArrayList<>();
		checkResults.add(new FlightResultDTO(new FlightPO(origin, destination,
				"TK2372", 197f), 157.6f));
		checkResults.add(new FlightResultDTO(new FlightPO(origin, destination,
				"TK2659", 248f), 198.4f));
		checkResults.add(new FlightResultDTO(new FlightPO(origin, destination,
				"LH5909", 113f), 90.4f));
		assertArrayEquals(checkResults.toArray(), flightService
				.findFlightsByFilter(filterDTO).toArray());

		/* Print results */
		printResults(results);
	}

	/**
	 * Test flight search service. Example 02
	 */
	@Test
	public void testFlightSearchService02() {
		logger.info("[INTEGRATION TEST] Test flight search service - Example 2");
		logger.info("2 adults, 1 child, 1 infant, 15 days to departure, from LHR to IST");

		/* Airports (origin & destination) */
		AirportPO origin = new AirportPO("LHR", "London");
		AirportPO destination = new AirportPO("IST", "Istanbul");

		/* Mock flight DAO */
		FlightFilterPO filter = new FlightFilterPO();
		filter.setOrigin(origin);
		filter.setDestination(destination);
		when(flightDAO.findByFilter(filter)).thenReturn(
				TestUtils.getFlightByFilter(filter));

		/* Mock airline DAO */
		when(airlineDAO.getByCode("TK")).thenReturn(
				TestUtils.getAirlineByCode("TK"));
		when(airlineDAO.getByCode("LH")).thenReturn(
				TestUtils.getAirlineByCode("LH"));

		/* Invoke service */
		FlightSearchFilterDTO filterDTO = new FlightSearchFilterDTO();
		filterDTO.setOrigin(origin);
		filterDTO.setDestination(destination);
		filterDTO.setDate(LocalDate.now().plusDays(16));
		filterDTO.setNumAdults(2);
		filterDTO.setNumChildren(1);
		filterDTO.setNumInfants(1);
		List<FlightResultDTO> results = flightService
				.findFlightsByFilter(filterDTO);

		/* Verify DAO invocations */
		verify(flightDAO).findByFilter(filter);
		verify(airlineDAO, atLeastOnce()).getByCode("TK");
		verify(airlineDAO, atLeastOnce()).getByCode("LH");

		/* Check result */
		List<FlightResultDTO> checkResults = new ArrayList<>();
		checkResults.add(new FlightResultDTO(new FlightPO(origin, destination,
				"TK8891", 250f), 705.0f));
		checkResults.add(new FlightResultDTO(new FlightPO(origin, destination,
				"LH1085", 148f), 421.4f));
		assertArrayEquals(checkResults.toArray(), flightService
				.findFlightsByFilter(filterDTO).toArray());

		/* Print results */
		printResults(results);
	}

	/**
	 * Test flight search service. Example 03
	 */
	@Test
	public void testFlightSearchService03() {
		logger.info("[INTEGRATION TEST] Test flight search service - Example 3");
		logger.info("1 adult, 2 children, 2 days to departure, from BCN to MAD");

		/* Airports (origin & destination) */
		AirportPO origin = new AirportPO("BCN", "Barcelona");
		AirportPO destination = new AirportPO("MAD", "Madrid");

		/* Mock flight DAO */
		FlightFilterPO filter = new FlightFilterPO();
		filter.setOrigin(origin);
		filter.setDestination(destination);
		when(flightDAO.findByFilter(filter)).thenReturn(
				TestUtils.getFlightByFilter(filter));

		/* Mock airline DAO */
		when(airlineDAO.getByCode("IB")).thenReturn(
				TestUtils.getAirlineByCode("IB"));
		when(airlineDAO.getByCode("LH")).thenReturn(
				TestUtils.getAirlineByCode("LH"));

		/* Invoke service */
		FlightSearchFilterDTO filterDTO = new FlightSearchFilterDTO();
		filterDTO.setOrigin(origin);
		filterDTO.setDestination(destination);
		filterDTO.setDate(LocalDate.now().plusDays(2));
		filterDTO.setNumAdults(1);
		filterDTO.setNumChildren(2);
		List<FlightResultDTO> results = flightService
				.findFlightsByFilter(filterDTO);

		/* Verify DAO invocations */
		verify(flightDAO).findByFilter(filter);
		verify(airlineDAO, atLeastOnce()).getByCode("IB");
		verify(airlineDAO, atLeastOnce()).getByCode("LH");

		/* Check result */
		List<FlightResultDTO> checkResults = new ArrayList<>();
		checkResults.add(new FlightResultDTO(new FlightPO(origin, destination,
				"IB2171", 259f), 1010.1f));
		checkResults.add(new FlightResultDTO(new FlightPO(origin, destination,
				"LH5496", 293f), 1142.7f));
		assertArrayEquals(checkResults.toArray(), flightService
				.findFlightsByFilter(filterDTO).toArray());

		/* Print results */
		printResults(results);
	}

	/**
	 * Print results.
	 *
	 * @param results
	 *            - The result list
	 */
	private void printResults(List<FlightResultDTO> results) {
		for (FlightResultDTO result : results) {
			logger.info(result.getFlight().getAirline() + ", "
					+ result.getTotalPrice());
		}
	}
}
