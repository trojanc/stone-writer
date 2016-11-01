package coza.trojanc.stonewriter.printer;

import coza.trojanc.stonewriter.format.PrintFormatBuilder;

/**
 * Created by Charl-PC on 2016-10-17.
 */
public interface Printer {

	/**
	 * Gets the layout builder instance for the printer
	 * @return
	 */
	PrintFormatBuilder getLayoutBuilder();

	/**
	 * Prints the layout that has been setup
	 */
	void print();
}
