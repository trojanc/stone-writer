package coza.trojanc.receipt.template.builder

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.template.PrintTemplate
import coza.trojanc.receipt.template.fields.*

/**
 * @author Charl Thiem
 */
class PrintTemplateBuilder : AbstractTemplateBuilder() {

    /**
     * Template we are busy building
     */
    private val template: PrintTemplate

    /**
     * Instantiates a new Print template builder.
     */
    init {
        template = PrintTemplate()
    }

    override fun addTemplateLine(line: TemplateLine) {
        template.addLine(line)
    }

    /**
     * Set the name of the template
     *
     * @param name the name
     * @return print template builder
     */
    fun name(name: String): PrintTemplateBuilder {
        template.name = name
        return this
    }


    /**
     * Line print template builder.
     *
     * @return the print template builder
     */
    fun line(): PrintTemplateBuilder {
        finishBusyLine()
        super.addLine()
        return this
    }

    /**
     * Feed print template builder.
     *
     * @return the print template builder
     */
    fun feed(): PrintTemplateBuilder {
        super.addFeed()
        return this
    }

    /**
     * Feed print template builder.
     *
     * @param lines the lines
     * @return the print template builder
     */
    fun feed(lines: Int): PrintTemplateBuilder {
        super.addFeed(lines)
        return this
    }

    /**
     * Fill line print template builder.
     *
     * @param character the character
     * @return the print template builder
     */
    fun fillLine(character: Char): PrintTemplateBuilder {
        super.addFillLine(character)
        return this
    }


    /**
     * Text print template builder.
     *
     * @param text the text
     * @return the print template builder
     */
    fun text(text: String): PrintTemplateBuilder {
        return staticText(text)
    }

    /**
     * Text print template builder.
     *
     * @param text    the text
     * @param dynamic the dynamic
     * @return the print template builder
     */
    fun text(text: String, dynamic: Boolean): PrintTemplateBuilder {
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
    fun staticText(text: String): PrintTemplateBuilder {
        super.addStaticText(text)
        return this
    }

    /**
     * Dynamic text print template builder.
     *
     * @param key the key
     * @return the print template builder
     */
    fun dynamicText(key: String): PrintTemplateBuilder {
        super.addDynamicText(key)
        return this
    }


    /**
     * Align print template builder.
     *
     * @param align the align
     * @return the print template builder
     */
    fun align(align: Align): PrintTemplateBuilder {
        super.addAlign(align)
        return this
    }

    /**
     * Offset print template builder.
     *
     * @param offset the offset
     * @return the print template builder
     */
    fun offset(offset: Int?): PrintTemplateBuilder {
        super.addOffset(offset)
        return this
    }


    fun repeat(repeatOn: String): PrintBlockTemplateBuilder {
        finishBusyLine()
        val blockTemplateBuilder = PrintBlockTemplateBuilder(this)
        blockTemplateBuilder.repeatOn(repeatOn)
        addTemplateLine(blockTemplateBuilder.block)
        return blockTemplateBuilder
    }

    fun build(): PrintTemplate {
        super.finishBusyLine()
        return this.template
    }

}
