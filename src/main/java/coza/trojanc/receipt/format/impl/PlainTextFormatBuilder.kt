package coza.trojanc.receipt.format.impl

import coza.trojanc.receipt.format.AbstractPlainTextFormatBuilder
import coza.trojanc.receipt.format.PrintFormatBuilder

/**
 * @author Charl Thiem
 */
class PlainTextFormatBuilder : AbstractPlainTextFormatBuilder {

    constructor(line_width: Int) : super(line_width) {}

    constructor(line_width: Int, invalidCharsRegex: String, invalidCharReplacement: Char) : super(line_width, invalidCharsRegex, invalidCharReplacement) {}

    override fun nl(): PrintFormatBuilder {
        super.nl()
        super.builder.append("\n")
        return this
    }

}
