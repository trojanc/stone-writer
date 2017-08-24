package coza.trojanc.receipt.format

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.shared.LineWrap

/**
 * @author Charl Thiem
 */
interface PrintFormatBuilder {

    /**
     * Insert text print format builder.
     *
     * @param text   the text
     * @param offset the offset
     * @param align  the align
     * @return the print format builder
     */
    fun insertText(text: String, offset: Int?, align: Align): PrintFormatBuilder

    /**
     * Insert left print format builder.
     *
     * @param text          the text
     * @param position_left the position left
     * @return the print format builder
     */
    fun insertLeft(text: String, position_left: Int): PrintFormatBuilder

    /**
     * Insert left print format builder.
     *
     * @param text          the text
     * @param position_left the position left
     * @param lineWrap      the line wrap
     * @return print format builder
     */
    fun insertLeft(text: String, position_left: Int, lineWrap: LineWrap): PrintFormatBuilder

    /**
     * Left print format builder.
     *
     * @param text the text
     * @return the print format builder
     */
    fun left(text: String): PrintFormatBuilder

    fun left(text: String, lineWrap: LineWrap): PrintFormatBuilder

    /**
     * Insert center print format builder.
     *
     * @param text     the text
     * @param position the position
     * @return the print format builder
     */
    fun insertCenter(text: String, position: Int): PrintFormatBuilder


    /**
     * Insert center print format builder.
     *
     * @param text     the text
     * @param position the position
     * @param lineWrap the line wrap
     * @return the print format builder
     */
    fun insertCenter(text: String, position: Int, lineWrap: LineWrap): PrintFormatBuilder

    /**
     * Center print format builder.
     *
     * @param text the text
     * @return the print format builder
     */
    fun center(text: String, lineWrap: LineWrap): PrintFormatBuilder

    fun center(text: String): PrintFormatBuilder

    /**
     * Insert right print format builder.
     *
     * @param text           the text
     * @param index the position right
     * @return the print format builder
     */
    fun insertRight(text: String, index: Int): PrintFormatBuilder

    /**
     *
     * @param text
     * @param index
     * @param lineWrap
     * @return
     */
    fun insertRight(text: String, index: Int, lineWrap: LineWrap): PrintFormatBuilder

    /**
     * Right print format builder.
     *
     * @param text the text
     * @return the print format builder
     */
    fun right(text: String): PrintFormatBuilder

    fun right(text: String, lineWrap: LineWrap): PrintFormatBuilder

    /**
     * Nl print format builder.
     *
     * @return the print format builder
     */
    fun nl(): PrintFormatBuilder

    /**
     * Add a feed
     *
     * @return print format builder
     */
    fun feed(): PrintFormatBuilder

    /**
     * Gets format.
     *
     * @return the format
     */
    val format: Any

    /**
     * Gets line width.
     *
     * @return the line width
     */
    val lineWidth: Int

    companion object {

        /**
         * The constant DEFAULT_LINE_WRAP.
         */
        val DEFAULT_LINE_WRAP = LineWrap.WRAP
    }
}
