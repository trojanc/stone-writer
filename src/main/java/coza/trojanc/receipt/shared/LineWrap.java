package coza.trojanc.receipt.shared;

public enum LineWrap {
	/**
	 * Do not wrap the line. Cut line
	 */
	NO_WRAP("none"),

	/**
	 * Wrap lines applying the offset and alignment for each wrapped line
	 */
	WRAP("wrap"),

	/**
	 * Wrap lines, but don't apply alignment or offsets, instead keep wrapped lines fully left
	 * with zero offset applied
	 */
	WRAP_LEFT("left");


	String value;
	LineWrap(String value){
		this.value = value;
	}
}
