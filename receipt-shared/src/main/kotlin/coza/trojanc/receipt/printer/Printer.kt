package coza.trojanc.receipt.printer

import coza.trojanc.receipt.format.PrintFormatBuilder

/**
 * @author Charl Thiem
 */
interface Printer {

    /**
     * Gets the layout builder instance for the printer
     *
     * @return layout builder
     */
    fun getLayoutBuilder(): PrintFormatBuilder

    /**
     * Prints the layout that has been setup
     */
    fun print()
}
