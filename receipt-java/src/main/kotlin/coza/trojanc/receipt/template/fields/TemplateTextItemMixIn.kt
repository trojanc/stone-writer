package coza.trojanc.receipt.template.fields

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

/**
 * An interface representing an item that can be placed in a printing template
 * @author Charl Thiem
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(JsonSubTypes.Type(value = DynamicText::class, name = "dynamicText"), JsonSubTypes.Type(value = Text::class, name = "staticText"))
abstract class TemplateTextItemMixIn