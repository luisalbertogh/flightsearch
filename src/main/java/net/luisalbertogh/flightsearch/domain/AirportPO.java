/**
 * Airport PO.
 */
package net.luisalbertogh.flightsearch.domain;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public final class AirportPO {
	/** The code. */
	private String code;

	/** The city. */
	private String city;

	/**
	 * Default constructor.
	 */
	public AirportPO() {
		/* Empty */
	}

	/**
	 * Constructor with fields.
	 *
	 * @param codeArg
	 * @param cityArg
	 */
	public AirportPO(String codeArg, String cityArg) {
		code = codeArg;
		city = cityArg;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param codeArg
	 *            the code to set
	 */
	public void setCode(String codeArg) {
		code = codeArg;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param cityArg
	 *            the city to set
	 */
	public void setCity(String cityArg) {
		city = cityArg;
	}
}
