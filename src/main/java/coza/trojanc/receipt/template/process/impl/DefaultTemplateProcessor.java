package coza.trojanc.receipt.template.process.impl;

import coza.trojanc.receipt.context.ContextMap;
import coza.trojanc.receipt.context.impl.DefaultContextMap;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.fields.*;
import coza.trojanc.receipt.template.process.ProcessedTemplate;
import coza.trojanc.receipt.template.process.TemplateProcessor;
import coza.trojanc.receipt.template.process.fields.ProcessedFeed;
import coza.trojanc.receipt.template.process.fields.ProcessedLine;
import coza.trojanc.receipt.template.process.fields.ProcessedText;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class DefaultTemplateProcessor implements TemplateProcessor {

	private ProcessedTemplate processedTemplate;
	private ContextMap context;

	private void processTemplateItem(TemplateLine item){

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
				processedText.setOffset(text.getOffset());
				processedLine.getLineItems().add(processedText);
			}

			else if (DynamicText.class.isAssignableFrom(lineItem.getClass())) {
				DynamicText text = (DynamicText) lineItem;
				ProcessedText processedText = new ProcessedText();
				processedText.setAlignment(text.getAlignment());
				processedText.setMode(text.getMode());
				processedText.setText(context.get(text.getContextKey()));
				processedText.setOffset(text.getOffset());
				processedLine.getLineItems().add(processedText);
			}
		});
	}


	@Override
	public ProcessedTemplate process(PrintTemplate template) {
		return process(template, new DefaultContextMap(0));
	}

	@Override
	public ProcessedTemplate process(PrintTemplate template, ContextMap context) {
		this.processedTemplate = new ProcessedTemplate();
		this.context = context;

		template.getLines().forEach(this::processTemplateItem);
		return processedTemplate;
	}
}
