package coza.trojanc.stonewriter.printer.layout;

import coza.trojanc.stonewriter.shared.Align;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface TextLayoutBuilder {

	void insertText(String text, Integer offset, Align align);

	void nl();
}
