package coza.trojanc.receipt.printer.impl;

import coza.trojanc.receipt.printer.Printer;
import coza.trojanc.receipt.format.impl.PlainTextFormatBuilder;
import coza.trojanc.receipt.format.PrintFormatBuilder;

/**
 * @author Charl Thiem
 */
public class PlainTextPrinter implements Printer{

	private PrintFormatBuilder layoutBuilder;

	private int width = 40;

	@Override
	public PrintFormatBuilder getLayoutBuilder() {
		if(layoutBuilder == null){
			layoutBuilder = new PlainTextFormatBuilder(width);
		}
		return layoutBuilder;
	}

	@Override
	public void print() {
		layoutBuilder = null; // Clear the layout builder for next print
	}
}
