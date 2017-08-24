package coza.trojanc.receipt.template.fields

import java.util.ArrayList

/**
 * Class that represents a line within a template.
 * @author Charl Thiem
 */
class Line : TemplateLine {

    private val lineItems: MutableList<TemplateTextItem>

    init {
        lineItems = ArrayList()
    }

    fun getLineItems(): List<TemplateTextItem> {
        return lineItems
    }

    fun addLineItem(item: TemplateTextItem) {
        lineItems.add(item)
    }

    override fun toString(): String {
        val sb = StringBuilder()
                .append("Line[")
        lineItems.forEach { templateTextItem -> sb.append(templateTextItem.toString()) }
        sb.append("]")
        return sb.toString()
    }
}
