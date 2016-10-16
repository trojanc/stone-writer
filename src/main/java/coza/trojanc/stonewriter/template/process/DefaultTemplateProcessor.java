package coza.trojanc.stonewriter.template.process;

import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.fields.*;
import coza.trojanc.stonewriter.template.process.fields.ProcessedFeed;
import coza.trojanc.stonewriter.template.process.fields.ProcessedLine;
import coza.trojanc.stonewriter.template.process.fields.ProcessedText;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class DefaultTemplateProcessor implements TemplateProcessor {

	private ProcessedTemplate processedTemplate;
	private Map<String, String> context;

	private void processTemplateItem(TemplateTextItem item){

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
				processedText.setText(context.get(text.getContextKey()));
				processedLine.getLineItems().add(processedText);
			}
		});
	}


	@Override
	public ProcessedTemplate process(PrintTemplate template) {
		return process(template, Collections.emptyMap());
	}

	@Override
	public ProcessedTemplate process(PrintTemplate template, Map<String, String> context) {
		this.processedTemplate = new ProcessedTemplate();
		this.context = context;


		List<TemplateTextItem> lines = template.getLines();
		lines.forEach(printableTemplateItem -> {
			processTemplateItem(printableTemplateItem);
		});
		return processedTemplate;
	}
}
