package coza.trojanc.receipt.template.builder

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.template.fields.RepeatBlock
import coza.trojanc.receipt.template.fields.TemplateLine

/**
 * @author Charl Thiem
 */
class PrintBlockTemplateBuilder
/**
 * Instantiates a new Print template builder.
 */
(
        /**
         * Template we are busy building
         */
        private val parentBuilder: PrintTemplateBuilder) : AbstractTemplateBuilder() {

    /**
     * Block we are busy with
     */
    val block: RepeatBlock


    init {
        this.block = RepeatBlock()
    }

    /**
     * Set the name of the template
     *
     * @param name the name
     * @return print template builder
     */
    fun repeatOn(name: String): PrintBlockTemplateBuilder {
        block.repeatOn = name
        return this
    }

    fun end(): PrintTemplateBuilder {
        super.finishBusyLine()
        return parentBuilder
    }

    override fun addTemplateLine(line: TemplateLine) {
        block.lines.add(line)
    }


    /**
     * Line print template builder.
     *
     * @return the print template builder
     */
    fun line(): PrintBlockTemplateBuilder {
        finishBusyLine()
        super.addLine()
        return this
    }

    /**
     * Feed print template builder.
     *
     * @return the print template builder
     */
    fun feed(): PrintBlockTemplateBuilder {
        super.addFeed()
        return this
    }

    /**
     * Feed print template builder.
     *
     * @param lines the lines
     * @return the print template builder
     */
    fun feed(lines: Int): PrintBlockTemplateBuilder {
        super.addFeed(lines)
        return this
    }

    /**
     * Fill line print template builder.
     *
     * @param character the character
     * @return the print template builder
     */
    fun fillLine(character: Char): PrintBlockTemplateBuilder {
        super.addFillLine(character)
        return this
    }


    /**
     * Text print template builder.
     *
     * @param text the text
     * @return the print template builder
     */
    fun text(text: String): PrintBlockTemplateBuilder {
        return staticText(text)
    }

    /**
     * Text print template builder.
     *
     * @param text    the text
     * @param dynamic the dynamic
     * @return the print template builder
     */
    fun text(text: String, dynamic: Boolean): PrintBlockTemplateBuilder {
        return if (dynamic) {
            dynamicText(text)
        } else {
            staticText(text)
        }
    }

    /**
     * Static text print template builder.
     *
     * @param text the text
     * @return the print template builder
     */
    fun staticText(text: String): PrintBlockTemplateBuilder {
        super.addStaticText(text)
        return this
    }

    /**
     * Dynamic text print template builder.
     *
     * @param key the key
     * @return the print template builder
     */
    fun dynamicText(key: String): PrintBlockTemplateBuilder {
        super.addDynamicText(key)
        return this
    }


    /**
     * Align print template builder.
     *
     * @param align the align
     * @return the print template builder
     */
    fun align(align: Align): PrintBlockTemplateBuilder {
        super.addAlign(align)
        return this
    }

    /**
     * Offset print template builder.
     *
     * @param offset the offset
     * @return the print template builder
     */
    fun offset(offset: Int?): PrintBlockTemplateBuilder {
        super.addOffset(offset)
        return this
    }
}
