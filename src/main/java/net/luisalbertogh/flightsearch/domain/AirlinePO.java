/**
 * Airline PO.
 */
package net.luisalbertogh.flightsearch.domain;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public final class AirlinePO {
	/** The IATA code. */
	private String IATAcode;

	/** The name. */
	private String name;

	/** The infant price. */
	private float infantPrice;

	/**
	 * Default constructor.
	 */
	public AirlinePO() {
		/* Empty */
	}

	/**
	 * Constructor with fields.
	 *
	 * @param iATAcodeArg
	 * @param nameArg
	 * @param infantPriceArg
	 */
	public AirlinePO(String iATAcodeArg, String nameArg, float infantPriceArg) {
		IATAcode = iATAcodeArg;
		name = nameArg;
		infantPrice = infantPriceArg;
	}

	/**
	 * @return the iATAcode
	 */
	public String getIATAcode() {
		return IATAcode;
	}

	/**
	 * @param iATAcodeArg
	 *            the iATAcode to set
	 */
	public void setIATAcode(String iATAcodeArg) {
		IATAcode = iATAcodeArg;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param nameArg
	 *            the name to set
	 */
	public void setName(String nameArg) {
		name = nameArg;
	}

	/**
	 * @return the infantPrice
	 */
	public float getInfantPrice() {
		return infantPrice;
	}

	/**
	 * @param infantPriceArg
	 *            the infantPrice to set
	 */
	public void setInfantPrice(float infantPriceArg) {
		infantPrice = infantPriceArg;
	}
}
