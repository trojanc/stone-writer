package coza.trojanc.receipt.template.process.impl;

import com.sun.deploy.util.StringUtils;
import coza.trojanc.receipt.context.ContextMap;
import coza.trojanc.receipt.context.impl.DefaultContextMap;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.fields.*;
import coza.trojanc.receipt.template.process.ProcessedTemplate;
import coza.trojanc.receipt.template.process.TemplateProcessor;
import coza.trojanc.receipt.template.process.fields.ProcessedFeed;
import coza.trojanc.receipt.template.process.fields.ProcessedFillLine;
import coza.trojanc.receipt.template.process.fields.ProcessedLine;
import coza.trojanc.receipt.template.process.fields.ProcessedText;

import static coza.trojanc.receipt.context.ContextResolver.ARRAY_LENGTH_SUFFIX;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class DefaultTemplateProcessor implements TemplateProcessor {

	/**
	 * The resulting processed template.
	 */
	private ProcessedTemplate processedTemplate;

	/**
	 * Context being used to retrieve dynamic values for the template.
	 */
	private ContextMap context;

	/**
	 * Process a template item.
	 * @param item The template item to process.
	 */
	private void processTemplateItem(TemplateLine item){
		this.processTemplateItem(item, null);
	}

	private void processTemplateItem(TemplateLine item, String repeatPrefix){

		// If it is a feed
		if(Feed.class.isAssignableFrom(item.getClass())){
			Feed feed = (Feed)item;
			for(int i = 0 ; i < feed.getFeedAmount(); i++){
				processedTemplate.getItems().add(new ProcessedFeed());
			}
		}

		else if(Line.class.isAssignableFrom(item.getClass())){
			processLine((Line)item, repeatPrefix);
		}

		else if(FillLine.class.isAssignableFrom(item.getClass())){
			processFillLine((FillLine)item);
		}

		else if(RepeatBlock.class.isAssignableFrom(item.getClass())){
			processRepeatBlock((RepeatBlock)item);
		}

	}


	/**
	 * Process a repeat block
	 * @param repeatBlock The repeat block to process
	 */
	private void processRepeatBlock(RepeatBlock repeatBlock){
		final String keyPrefix = repeatBlock.getRepeatOn();
		final int repeatSize = Integer.parseInt(context.get(keyPrefix+ARRAY_LENGTH_SUFFIX));
		for(int idx = 0 ; idx < repeatSize; idx++) {
			int iteration = idx;
			repeatBlock.getLines().forEach(line -> processTemplateItem(line, keyPrefix + "[" + iteration + "]"));
		}
	}

	/**
	 * Process a fill line.
	 * @param fillLine
	 */
	private void processFillLine(FillLine fillLine){
		processedTemplate.getItems().add(new ProcessedFillLine(fillLine.getCharacter()));
	}

	/**
	 * Process a line that can contain text
	 * @param line The line to process
	 */
	private void processLine(Line line, String repeatPrefix){
		ProcessedLine processedLine = new ProcessedLine();
		processedTemplate.getItems().add(processedLine);

		line.getLineItems().forEach(lineItem -> {
			if (Text.class.isAssignableFrom(lineItem.getClass())) {
				addStaticText((Text) lineItem, processedLine);
			}
			else if (DynamicText.class.isAssignableFrom(lineItem.getClass())) {
				addDynamicText((DynamicText) lineItem, processedLine, repeatPrefix);
			}
		});
	}

	private void addStaticText(Text text, ProcessedLine processedLine){
		ProcessedText processedText = new ProcessedText();
		processedText.setAlignment(text.getAlignment());
		processedText.setMode(text.getMode());
		processedText.setText(text.getText());
		processedText.setOffset(text.getOffset());
		processedLine.getLineItems().add(processedText);
	}

	private void addDynamicText(DynamicText text, ProcessedLine processedLine, String repeatPrefix){
		ProcessedText processedText = new ProcessedText();
		processedText.setAlignment(text.getAlignment());
		processedText.setMode(text.getMode());
		processedText.setOffset(text.getOffset());

		String contextKey;
		if(repeatPrefix == null){
			contextKey = text.getContextKey();
		}
		else{
			contextKey = repeatPrefix;
			if(!(text.getContextKey() == null || text.getContextKey().length() == 0)){
				contextKey += text.getContextKey();
			}
		}
		processedText.setText(context.get(contextKey));
		processedLine.getLineItems().add(processedText);
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
