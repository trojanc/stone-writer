package coza.trojanc.receipt.printer;

import coza.trojanc.receipt.format.PrintFormatBuilder;

/**
 * Created by Charl-PC on 2016-10-17.
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
