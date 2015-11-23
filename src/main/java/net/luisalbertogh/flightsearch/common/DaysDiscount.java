/**
 * Discount percentage based on days.
 */
package net.luisalbertogh.flightsearch.common;

/**
 * @author luisalbertogh@hotmail.com
 *
 */
public enum DaysDiscount {
	/** More than 30 days */
	MORE_30(80, 31),
	/** Btw 30 & 16 days */
	_30_16(100, 16),
	/** Btw 15 & 3 days */
	_15_3(120, 3),
	/** Less than 3 days */
	_3(150, 0);

	/** The discount percentage */
	private int discount;

	/** The minimum number of days */
	private int minDays;

	/**
	 * Constructor with fields.
	 *
	 * @param discountArg
	 */
	private DaysDiscount(int discountArg, int minDaysArg) {
		discount = discountArg;
		minDays = minDaysArg;
	}

	/**
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * @return the maxDays
	 */
	public int getMinDays() {
		return minDays;
	}
}
