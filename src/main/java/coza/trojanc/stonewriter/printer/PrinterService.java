package coza.trojanc.stonewriter.printer;

import coza.trojanc.stonewriter.printer.layout.PrintTextLayoutBuilder;
import coza.trojanc.stonewriter.template.process.ProcessedTemplate;
import coza.trojanc.stonewriter.template.process.fields.ProcessedFeed;
import coza.trojanc.stonewriter.template.process.fields.ProcessedLine;
import coza.trojanc.stonewriter.template.process.fields.ProcessedText;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class PrinterService {

	private PrintTextLayoutBuilder builder;

	public void print(ProcessedTemplate template, PrintTextLayoutBuilder builder){
		this.builder = builder;

		template.getItems().forEach(processedLineItem -> {

			if(ProcessedFeed.class.isAssignableFrom(processedLineItem.getClass())){
//				builder.feed();
			}
			else if(ProcessedLine.class.isAssignableFrom(processedLineItem.getClass())){
				ProcessedLine line = (ProcessedLine)processedLineItem;
				printLine(line);
			}
		});
	}

	private void printLine(ProcessedLine line){
		line.getLineItems().forEach(processedText -> {
			printText(processedText, line.getLineItems().size() == 1);

		});
		builder.nl();
	}

	private void printText(ProcessedText processedText, boolean onlyItemInLine){
		Integer offset = processedText.getOffset();
		if(!onlyItemInLine){
			offset = offset == null ? 0 : offset;
		}

		builder.insertText(processedText.getText(), offset, processedText.getAlignment());
	}
}
