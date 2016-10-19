package coza.trojanc.stonewriter.printer.impl;

import coza.trojanc.stonewriter.printer.Printer;
import coza.trojanc.stonewriter.printer.layout.PlainTextLayoutBuilder;
import coza.trojanc.stonewriter.printer.layout.PrintTextLayoutBuilder;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class PlainTextPrinter implements Printer{

	private PrintTextLayoutBuilder layoutBuilder;

	private int width = 40;

	@Override
	public PrintTextLayoutBuilder getLayoutBuilder() {
		if(layoutBuilder == null){
			layoutBuilder = new PlainTextLayoutBuilder(width);
		}
		return layoutBuilder;
	}

	@Override
	public void print() {
		layoutBuilder = null; // Clear the layout builder for next print
	}
}
