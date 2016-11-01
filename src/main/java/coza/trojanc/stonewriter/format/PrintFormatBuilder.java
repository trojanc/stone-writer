package coza.trojanc.stonewriter.format;

import coza.trojanc.stonewriter.shared.Align;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface PrintFormatBuilder {

	PrintFormatBuilder insertText(String text, Integer offset, Align align);

	PrintFormatBuilder insertLeft(String text, int position_left);
	PrintFormatBuilder left(String text);

	PrintFormatBuilder insertCenter(String text, int position);
	PrintFormatBuilder center(String text);

	PrintFormatBuilder insertRight(String text, int position_right);
	PrintFormatBuilder right(String text);

	PrintFormatBuilder nl();

	Object getFormat();
}
