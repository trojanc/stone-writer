package coza.trojanc.receipt.printer;

import coza.trojanc.receipt.format.PrintFormatBuilder;
import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.template.process.ProcessedTemplate;
import coza.trojanc.receipt.template.process.fields.ProcessedFeed;
import coza.trojanc.receipt.template.process.fields.ProcessedLine;
import coza.trojanc.receipt.template.process.fields.ProcessedText;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class PrinterService {

	private PrintFormatBuilder builder;

	public void print(ProcessedTemplate template, PrintFormatBuilder builder){
		this.builder = builder;

		template.getItems().forEach(processedLineItem -> {

			if(ProcessedFeed.class.isAssignableFrom(processedLineItem.getClass())){
				builder.feed();
			}
			else if(ProcessedLine.class.isAssignableFrom(processedLineItem.getClass())){
				ProcessedLine line = (ProcessedLine)processedLineItem;
				printLine(line);
			}
		});

	}

	private void printLine(ProcessedLine line){
		line.getLineItems().forEach(processedText -> printText(processedText, line.getLineItems().size() == 1));
		builder.nl();
	}

	private void printText(ProcessedText processedText, boolean onlyItemInLine){
		Integer offset = processedText.getOffset();
		if(!onlyItemInLine){
			if(Align.RIGHT == processedText.getAlignment()){
				offset = offset == null ? builder.getLineWidth()-1 : offset;
			}
			else if(Align.CENTER == processedText.getAlignment()){
				offset = offset == null ? builder.getLineWidth() / 2 : offset;
			}
			else{
				offset = offset == null ? 0 : offset;
			}
		}

		// Calculate offset based on alignment
		builder.insertText(processedText.getText(), offset, processedText.getAlignment());
	}
}
