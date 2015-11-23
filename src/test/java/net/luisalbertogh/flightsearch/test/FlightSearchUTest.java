/**
 * Main unit test suite.
 */
package net.luisalbertogh.flightsearch.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import junit.framework.TestSuite;
import net.luisalbertogh.flightsearch.FlightSearch;
import net.luisalbertogh.flightsearch.common.FlightFilterPO;
import net.luisalbertogh.flightsearch.dao.AirlineDAO;
import net.luisalbertogh.flightsearch.dao.AirportDAO;
import net.luisalbertogh.flightsearch.dao.FlightDAO;
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
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(FlightSearch.class)
public class FlightSearchUTest {

	/** A logger reference */
	private static Logger logger = Logger.getLogger(TestSuite.class);

	/** The airline DAO */
	@Mock
	private AirlineDAO airlineDAO;

	/** The airport DAO */
	@Mock
	private AirportDAO airportDAO;

	/** The flight DAO */
	@Mock
	private FlightDAO flightDAO;

	/**
	 * Set up the test suite.
	 */
	@BeforeClass
	public static void setUpSuite() {
		logger.info("Setting up test suite...");

		/* Load data from files */
		TestUtils.init();
	}

	/**
	 * Set up any test.
	 */
	@Before
	public void setUp() {
		logger.info("Setting up a single test...");
	}

	/**
	 * Destroy the test suite.
	 */
	@AfterClass
	public static void tearDownSuite() {
		logger.info("Cleaning up test suite...");

		/* Destroy mock data */
		TestUtils.destroy();
	}

	/**
	 * Destroy any test.
	 */
	@After
	public void tearDown() {
		logger.info("Cleaning up a single test...");
	}

	/**
	 * Test airline DAO mock.
	 */
	@Test
	public void testAirlineDAO() {
		logger.info("Test airline DAO:");

		/* Check DAO is not null */
		assertNotNull(airlineDAO);

		/* Check DAO interface */
		when(airlineDAO.findAll()).thenReturn(TestUtils.getAirlinesAsList());
		assertArrayEquals(TestUtils.getAirlinesAsList().toArray(), airlineDAO
				.findAll().toArray());

		when(airlineDAO.getByCode("IB")).thenReturn(
				TestUtils.getAirlineByCode("IB"));
		assertEquals(TestUtils.getAirlineByCode("IB"),
				airlineDAO.getByCode("IB"));
	}

	/**
	 * Test airport DAO mock.
	 */
	@Test
	public void testAirportDAO() {
		logger.info("Test airport DAO:");

		/* Check DAO is not null */
		assertNotNull(airportDAO);

		/* Check DAO interface */
		when(airportDAO.findAll()).thenReturn(TestUtils.getAirportAsList());
		assertArrayEquals(TestUtils.getAirportAsList().toArray(), airportDAO
				.findAll().toArray());

		when(airportDAO.getByCode("MAD")).thenReturn(
				TestUtils.getAirportByCode("MAD"));
		assertEquals(TestUtils.getAirportByCode("MAD"),
				airportDAO.getByCode("MAD"));
	}

	/**
	 * Test flight DAO mock.
	 */
	@Test
	public void testFlightDAO() {
		logger.info("Test flight DAO:");

		/* Check DAO is not null */
		assertNotNull(flightDAO);

		/* Check DAO interface */
		when(flightDAO.findAll()).thenReturn(TestUtils.getFlightsList());
		assertArrayEquals(TestUtils.getFlightsList().toArray(), flightDAO
				.findAll().toArray());

		FlightFilterPO filter = new net.luisalbertogh.flightsearch.common.FlightFilterPO(
				TestUtils.getAirportByCode("CPH"),
				TestUtils.getAirportByCode("FRA"), "IB2818");
		when(flightDAO.findByFilter(filter)).thenReturn(
				TestUtils.getFlightByFilter(filter));
		assertArrayEquals(TestUtils.getFlightByFilter(filter).toArray(),
				flightDAO.findByFilter(filter).toArray());
	}
}
