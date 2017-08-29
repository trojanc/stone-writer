package coza.trojanc.receipt.template.fields

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.shared.Mode

/**
 * An interface representing an item that can be placed in a printing template
 * @author Charl Thiem
 */
interface TemplateTextItem {

    fun getAlignment(): Align

    fun getOffset(): Int?

    fun getMode(): Mode
}
