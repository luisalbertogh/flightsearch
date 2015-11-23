/**
 * Flight search service implementation.
 */
package net.luisalbertogh.flightsearch.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import net.luisalbertogh.flightsearch.common.DaysDiscount;
import net.luisalbertogh.flightsearch.common.FlightFilterPO;
import net.luisalbertogh.flightsearch.dao.AirlineDAO;
import net.luisalbertogh.flightsearch.dao.FlightDAO;
import net.luisalbertogh.flightsearch.domain.AirlinePO;
import net.luisalbertogh.flightsearch.domain.FlightPO;
import net.luisalbertogh.flightsearch.service.FlightSearchService;
import net.luisalbertogh.flightsearch.service.dto.FlightResultDTO;
import net.luisalbertogh.flightsearch.service.dto.FlightSearchFilterDTO;

import org.apache.log4j.Logger;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public final class FlightSearchServiceImpl implements FlightSearchService {

	/** A logger reference */
	private static Logger logger = Logger
			.getLogger(FlightSearchServiceImpl.class);

	/** The flight DAO */
	private FlightDAO flightDAO;

	/** The airline DAO */
	private AirlineDAO airlineDAO;

	/**
	 * Find flights by filter.
	 *
	 * @param filter
	 *            - The flight search filter
	 * @return The list of flights
	 */
	@Override
	public List<FlightResultDTO> findFlightsByFilter(
			FlightSearchFilterDTO filterArg) {
		List<FlightResultDTO> result = new ArrayList<>();

		/* Find flights matching criteria */
		FlightFilterPO filterPO = new FlightFilterPO();
		filterPO.setOrigin(filterArg.getOrigin());
		filterPO.setDestination(filterArg.getDestination());
		List<FlightPO> flights = this.getFlightDAO().findByFilter(filterPO);

		/* Generate result list */
		if (flights != null && !flights.isEmpty()) {
			for (FlightPO flight : flights) {
				/* Calculate totals */
				float total = calculateTotal(flight, filterArg);
				/* Create flight result */
				FlightResultDTO fResult = new FlightResultDTO();
				fResult.setFlight(flight);
				fResult.setTotalPrice(total);
				result.add(fResult);
			}
		}

		return result;
	}

	/**
	 * Calculate total price for flight and passengers.
	 *
	 * @param flight
	 *            - The selected flight
	 * @param filter
	 *            - The selected criteria
	 * @return The total price in euros
	 */
	private float calculateTotal(FlightPO flight, FlightSearchFilterDTO filter) {
		float total = 0.0f;

		/* Base price for adults */
		float basePrice = flight.getBasePrice();

		/* Passengers */
		int numAdults = filter.getNumAdults();
		int numChildren = filter.getNumChildren();
		int numInfants = filter.getNumInfants();

		/* Selected date */
		LocalDate date = filter.getDate();

		/* Perform calculation */
		/* Today */
		LocalDate today = LocalDate.now();

		/* Check that today is not after the selected date */
		if (!today.isAfter(date)) {
			/* 1. Discount based on days to departure */
			int days = (int) today.until(date, ChronoUnit.DAYS);
			float newBasePrice = calculateBasePriceBasedOnDays(basePrice, days);

			/* 2. Price for adults (adults * newBasePrice) */
			float adultPrice = numAdults * newBasePrice;

			/*
			 * 3. Price for children (children * (newBasePrice - (20% of
			 * newBasePrice)))
			 */
			float childrenDiscount = (newBasePrice * 20) / 100;
			float childrenPrice = numChildren
					* (newBasePrice - childrenDiscount);

			/* 4. Price for infants (infants * price per infant ) */
			/* Get airline based on code */
			AirlinePO airline = this.getAirlineDAO().getByCode(
					flight.getAirline().substring(0, 2));
			float infantPrice = numInfants * airline.getInfantPrice();

			/* 5. Total price */
			total = adultPrice + childrenPrice + infantPrice;

		} else {
			logger.error("Selected date is older than today!");
			throw new RuntimeException("Selected date is older than today!");
		}

		return total;
	}

	/**
	 * Calculate new base price based on number of previous days before
	 * departure.
	 *
	 * @param basePrice
	 *            - The base price for the flight
	 * @param days
	 *            - The number of previous days
	 * @return the new based price
	 */
	private float calculateBasePriceBasedOnDays(float basePrice, int days) {
		float discount = 0f;
		/* Return percent */
		if (days >= DaysDiscount.MORE_30.getMinDays()) {
			discount = DaysDiscount.MORE_30.getDiscount();
		} else if (days >= DaysDiscount._30_16.getMinDays()) {
			discount = DaysDiscount._30_16.getDiscount();
		} else if (days >= DaysDiscount._15_3.getMinDays()) {
			discount = DaysDiscount._15_3.getDiscount();
		} else {
			discount = DaysDiscount._3.getDiscount();
		}

		return (basePrice * discount) / 100.0f;
	}

	/**
	 * @return the flightDAO
	 */
	public FlightDAO getFlightDAO() {
		return flightDAO;
	}

	/**
	 * @param flightDAOArg
	 *            the flightDAO to set
	 */
	public void setFlightDAO(FlightDAO flightDAOArg) {
		flightDAO = flightDAOArg;
	}

	/**
	 * @return the airlineDAO
	 */
	public AirlineDAO getAirlineDAO() {
		return airlineDAO;
	}

	/**
	 * @param airlineDAOArg
	 *            the airlineDAO to set
	 */
	public void setAirlineDAO(AirlineDAO airlineDAOArg) {
		airlineDAO = airlineDAOArg;
	}

}
