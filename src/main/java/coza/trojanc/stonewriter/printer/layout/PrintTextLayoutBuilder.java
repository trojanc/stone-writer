package coza.trojanc.stonewriter.printer.layout;

import coza.trojanc.stonewriter.shared.Align;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface PrintTextLayoutBuilder {

	PrintTextLayoutBuilder insertText(String text, Integer offset, Align align);

	PrintTextLayoutBuilder insertLeft(String text, int position_left);
	PrintTextLayoutBuilder left(String text);

	PrintTextLayoutBuilder insertCenter(String text, int position);
	PrintTextLayoutBuilder center(String text);

	PrintTextLayoutBuilder insertRight(String text, int position_right);
	PrintTextLayoutBuilder right(String text);

	PrintTextLayoutBuilder nl();
}
