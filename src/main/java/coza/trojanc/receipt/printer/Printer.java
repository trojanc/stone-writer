package coza.trojanc.receipt.printer;

import coza.trojanc.receipt.format.PrintFormatBuilder;

/**
 * @author Charl Thiem
 */
public interface Printer {

	/**
	 * Gets the layout builder instance for the printer
	 *
	 * @return layout builder
	 */
	PrintFormatBuilder getLayoutBuilder();

	/**
	 * Prints the layout that has been setup
	 */
	void print();
}
