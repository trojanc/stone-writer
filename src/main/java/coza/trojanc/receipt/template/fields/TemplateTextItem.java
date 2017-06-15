package coza.trojanc.receipt.template.fields;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.shared.Mode;

/**
 * An interface representing an item that can be placed in a printing template
 * Created by Charl-PC on 2016-10-10.
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = DynamicText.class, name = "dynamicText"),
		@JsonSubTypes.Type(value = Text.class, name = "staticText"),
})
public interface TemplateTextItem {

	Align getAlignment();

	Integer getOffset();

	Mode getMode();
}
