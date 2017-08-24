package coza.trojanc.receipt.printer.impl

import coza.trojanc.receipt.printer.Printer
import coza.trojanc.receipt.format.impl.PlainTextFormatBuilder
import coza.trojanc.receipt.format.PrintFormatBuilder

/**
 * @author Charl Thiem
 */
class PlainTextPrinter : Printer {

    private var layoutBuilder: PrintFormatBuilder? = null

    private val width = 40

    override fun getLayoutBuilder(): PrintFormatBuilder {
        if (layoutBuilder == null) {
            layoutBuilder = PlainTextFormatBuilder(width)
        }
        return layoutBuilder
    }

    override fun print() {
        layoutBuilder = null // Clear the layout builder for next print
    }
}
