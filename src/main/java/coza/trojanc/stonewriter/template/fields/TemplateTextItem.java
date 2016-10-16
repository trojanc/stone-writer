package coza.trojanc.stonewriter.template.fields;

import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.shared.Mode;

/**
 * An interface representing an item that can be placed in a printing template
 * Created by Charl-PC on 2016-10-10.
 */
public interface TemplateTextItem {

	Align getAlignment();

	int getOffset();

	Mode getMode();
}
