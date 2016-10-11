package coza.trojanc.stonewriter.processor;

import coza.trojanc.stonewriter.processor.fields.ProcessedFeed;
import coza.trojanc.stonewriter.processor.fields.ProcessedLine;
import coza.trojanc.stonewriter.processor.fields.ProcessedText;
import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.fields.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class TemplateProcessor {

	private final PrintTemplate template;
	private final ProcessedTemplate processedTemplate;
	private final Map<String, ?extends Object> context;


	public TemplateProcessor(PrintTemplate template){
		this(template, Collections.emptyMap());
	}

	public TemplateProcessor(PrintTemplate template, Map<String, ?extends Object> context){
		this.template = template;
		this.processedTemplate = new ProcessedTemplate();
		this.context = context;
	}


	public ProcessedTemplate process(){
		List<PrintableTemplateItem> lines = template.getLines();
		lines.forEach(printableTemplateItem -> {
			processTemplateItem(printableTemplateItem);
		});
		return processedTemplate;
	}

	private void processTemplateItem(PrintableTemplateItem item){

		// If it is a feed
		if(Feed.class.isAssignableFrom(item.getClass())){
			Feed feed = (Feed)item;
			for(int i = 0 ; i < feed.getFeedAmount(); i++){
				processedTemplate.getItems().add(new ProcessedFeed());
			}
		}

		else if(Line.class.isAssignableFrom(item.getClass())){
			processLine((Line)item);
		}


	}

	private void processLine(Line line){
		ProcessedLine processedLine = new ProcessedLine();
		processedTemplate.getItems().add(processedLine);

		line.getLineItems().forEach(lineItem -> {

			if (Text.class.isAssignableFrom(lineItem.getClass())) {
				Text text = (Text) lineItem;
				ProcessedText processedText = new ProcessedText();
				processedText.setAlignment(text.getAlignment());
				processedText.setMode(text.getMode());
				processedText.setText(text.getText());
				processedLine.getLineItems().add(processedText);
			}

			else if (DynamicText.class.isAssignableFrom(lineItem.getClass())) {
				DynamicText text = (DynamicText) lineItem;
				ProcessedText processedText = new ProcessedText();
				processedText.setAlignment(text.getAlignment());
				processedText.setMode(text.getMode());
				processedText.setText(text.getExpression());// TODO execute expression
				processedLine.getLineItems().add(processedText);
			}
		});
	}
}
