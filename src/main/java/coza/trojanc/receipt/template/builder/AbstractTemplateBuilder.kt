package coza.trojanc.receipt.template.builder

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.template.fields.*

/**
 * @author Charl Thiem
 */
abstract class AbstractTemplateBuilder
/**
 * Instantiates a new Print template builder.
 */
internal constructor() {

    /**
     * Current busy line
     */
    private var line: Line? = null

    /**
     * Current busy text item
     */
    private var textItem: AbstractTextItem? = null

    protected abstract fun addTemplateLine(line: TemplateLine)

    /**
     * Line print template builder.
     *
     * @return the print template builder
     */
    fun addLine() {
        finishBusyLine()
        line = Line()
    }

    /**
     * Feed print template builder.
     *
     * @return the print template builder
     */
    fun addFeed() {
        finishBusyLine()
        addTemplateLine(Feed())
    }

    /**
     * Feed print template builder.
     *
     * @param lines the lines
     * @return the print template builder
     */
    fun addFeed(lines: Int) {
        finishBusyLine()
        addTemplateLine(Feed(lines))
    }

    /**
     * Fill line print template builder.
     *
     * @param character the character
     * @return the print template builder
     */
    fun addFillLine(character: Char) {
        finishBusyLine()
        addTemplateLine(FillLine(character))
    }


    /**
     * Static text print template builder.
     *
     * @param text the text
     * @return the print template builder
     */
    fun addStaticText(text: String) {
        finishLineItem()
        checkValidLine()
        textItem = Text()

        if (DynamicText::class.java.isAssignableFrom(textItem!!.javaClass)) {
            throw IllegalArgumentException("Cannot add text value to dynamic text")
        }
        (textItem as Text).setText(text);
    }

    /**
     * Dynamic text print template builder.
     *
     * @param key the key
     * @return the print template builder
     */
    fun addDynamicText(key: String) {
        finishLineItem()
        checkValidLine()
        textItem = DynamicText()
        if (!DynamicText::class.java.isAssignableFrom(textItem!!.javaClass)) {
            throw IllegalArgumentException("Cannot add key to static text")
        }
        (textItem as DynamicText).setContextKey(key);
    }


    /**
     * Align print template builder.
     *
     * @param align the align
     * @return the print template builder
     */
    fun addAlign(align: Align) {
        checkValidLine()
        ensureTextItem()
        textItem!!.setAlignment(align);
    }

    /**
     * Offset print template builder.
     *
     * @param offset the offset
     * @return the print template builder
     */
    fun addOffset(offset: Int?) {
        checkValidLine()
        ensureTextItem()
        textItem!!.setOffset(offset);
    }


    /**
     * Finish the line if we are busy with one
     */
    protected fun finishBusyLine() {
        if (line != null) {
            finishLineItem()
            addTemplateLine(line!!)
            line = null
        }
    }

    /**
     * Finish a line item if we are busy with one
     */
    private fun finishLineItem() {
        if (textItem != null) {
            this.line!!.addLineItem(textItem!!)
            textItem = null
        }
    }

    /**
     * Check that we are busy with a line
     */
    private fun checkValidLine() {
        if (line == null) {
            throw IllegalArgumentException("Not busy with a line!")
        }
    }

    /**
     * Ensure a text item is ready to be used
     */
    private fun ensureTextItem() {
        if (textItem == null) {
            throw IllegalArgumentException("Not busy with a text item!")
        }
    }

}
