package coza.trojanc.stonewriter.printer.impl;

import coza.trojanc.stonewriter.printer.Printer;
import coza.trojanc.stonewriter.format.impl.PlainTextFormatBuilder;
import coza.trojanc.stonewriter.format.PrintFormatBuilder;

/**
 * Created by Charl-PC on 2016-10-16.
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
