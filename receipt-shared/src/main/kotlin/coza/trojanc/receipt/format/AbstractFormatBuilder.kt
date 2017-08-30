package coza.trojanc.receipt.format

import coza.trojanc.receipt.shared.Align

/**
 * @author Charl Thiem
 */
abstract class AbstractFormatBuilder: PrintFormatBuilder{
    /** flag indicating that the char buffer has been used  */
    protected var lineBufferInUse = false

    /** Width of a line  */
    private val lineWidth: Int

    /**
     * Creates a new instance of a `AbstractFormatBuilder` setting
     * the line width.
     * @param line_width The number of characters that can be displayed on a line
     */
    constructor(lineWidth: Int){
        this.lineWidth = lineWidth;
    }

    override fun getLineWidth(): Int {
        return this.lineWidth;
    }


    override fun insertText(text: String, offset: Int?, align: Align): PrintFormatBuilder {
        if (align == Align.LEFT) {
            if (offset == null) {
                left(text)
            } else {
                insertLeft(text, offset)
            }
        } else if (align == Align.CENTER) {
            if (offset == null) {
                center(text)
            } else {
                insertCenter(text, offset)
            }
        } else if (align == Align.RIGHT) {
            if (offset == null) {
                right(text)
            } else {
                insertRight(text, offset)
            }
        }
        return this
    }
}
