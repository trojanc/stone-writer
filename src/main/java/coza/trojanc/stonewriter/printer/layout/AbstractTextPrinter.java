package coza.trojanc.stonewriter.printer.layout;

import coza.trojanc.stonewriter.template.process.ProcessedTemplate;
import coza.trojanc.stonewriter.template.process.fields.ProcessedFeed;
import coza.trojanc.stonewriter.template.process.fields.ProcessedLine;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public abstract class AbstractTextPrinter {

	/**
	 * Number of characters that can fit into one line (on normal width mode)
	 */
	private int width;

	/**
	 * Add a line feed
	 */
	public abstract void feed();

	public abstract void init();

	public void print(ProcessedTemplate template){

		template.getItems().forEach(processedLineItem -> {
			if (ProcessedFeed.class.isAssignableFrom(processedLineItem.getClass())) {
				feed();
			}
			else if (ProcessedLine.class.isAssignableFrom(processedLineItem.getClass())) {
				printLine((ProcessedLine)processedLineItem);
			}
		});
	}

	protected abstract void printLine(ProcessedLine processedLine);

}
