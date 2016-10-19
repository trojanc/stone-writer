package coza.trojanc.stonewriter.printer;

import coza.trojanc.stonewriter.printer.layout.PrintTextLayoutBuilder;

/**
 * Created by Charl-PC on 2016-10-17.
 */
public interface Printer {

	/**
	 * Gets the layout builder instance for the printer
	 * @return
	 */
	PrintTextLayoutBuilder getLayoutBuilder();

	/**
	 * Prints the layout that has been setup
	 */
	void print();
}
