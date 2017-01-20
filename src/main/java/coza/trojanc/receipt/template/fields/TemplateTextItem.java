package coza.trojanc.receipt.template.fields;

import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.shared.Mode;

/**
 * An interface representing an item that can be placed in a printing template
 * Created by Charl-PC on 2016-10-10.
 */
public interface TemplateTextItem {

	Align getAlignment();

	Integer getOffset();

	Mode getMode();
}
