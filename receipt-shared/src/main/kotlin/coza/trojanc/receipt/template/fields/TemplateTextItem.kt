package coza.trojanc.receipt.template.fields

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.shared.Mode

/**
 * An interface representing an item that can be placed in a printing template
 * @author Charl Thiem
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(JsonSubTypes.Type(value = DynamicText::class, name = "dynamicText"), JsonSubTypes.Type(value = Text::class, name = "staticText"))
interface TemplateTextItem {

    fun getAlignment(): Align

    fun getOffset(): Int?

    fun getMode(): Mode
}
