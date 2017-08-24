package coza.trojanc.receipt.printer.impl

import coza.trojanc.receipt.printer.Printer
import coza.trojanc.receipt.format.impl.PlainTextFormatBuilder
import coza.trojanc.receipt.format.PrintFormatBuilder

/**
 * @author Charl Thiem
 */
class PlainTextPrinter : Printer {

    private var layoutBuilder: PrintFormatBuilder;

    private val width = 40

    constructor(){
        this.layoutBuilder = PlainTextFormatBuilder(width);
    }

    override fun getLayoutBuilder(): PrintFormatBuilder {
        return layoutBuilder
    }

    override fun print() {
        this.layoutBuilder = PlainTextFormatBuilder(width) // Clear the layout builder for next print
    }
}
