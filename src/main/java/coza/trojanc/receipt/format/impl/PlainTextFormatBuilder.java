package coza.trojanc.receipt.format.impl;

import coza.trojanc.receipt.format.AbstractPlainTextFormatBuilder;
import coza.trojanc.receipt.format.PrintFormatBuilder;

/**
 * @author Charl Thiem
 */
public class PlainTextFormatBuilder extends AbstractPlainTextFormatBuilder {

	public PlainTextFormatBuilder(int line_width) {
		super(line_width);
	}

	public PlainTextFormatBuilder(int line_width, String invalidCharsRegex, char invalidCharReplacement) {
		super(line_width, invalidCharsRegex, invalidCharReplacement);
	}

	@Override
	public PrintFormatBuilder nl() {
		super.nl();
		super.builder.append("\n");
		return this;
	}

}
