package coza.trojanc.stonewriter.processor;

import coza.trojanc.stonewriter.processor.fields.ProcessedFeed;
import coza.trojanc.stonewriter.processor.fields.ProcessedLine;
import coza.trojanc.stonewriter.processor.fields.ProcessedText;
import coza.trojanc.stonewriter.shared.DynamicType;
import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.fields.*;
import org.apache.commons.jexl3.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class TemplateProcessor {

	private final PrintTemplate template;
	private final JexlContext jc;

	private ProcessedTemplate processedTemplate;
	private JexlEngine jexl;


	public TemplateProcessor(PrintTemplate template){
		this(template, Collections.emptyMap());
	}

	public TemplateProcessor(PrintTemplate template, Map<String, Object> context){
		this.template = template;
		this.jc = new MapContext(context);
		init();
	}

	private void init(){
		this.jexl = new JexlBuilder().create();
		this.processedTemplate = new ProcessedTemplate();
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
				ProcessedText processedText = processDynamicText((DynamicText) lineItem);
				processedLine.getLineItems().add(processedText);
			}
		});
	}

	private ProcessedText processDynamicText(DynamicText text){
		ProcessedText processedText = new ProcessedText();
		processedText.setAlignment(text.getAlignment());
		processedText.setMode(text.getMode());

		final Object evaluatedObject = evaluateExpression(text.getExpression());

		// Plain String
		if(text.getType() == DynamicType.String){
			processedText.setText(evaluatedObject.toString());
		}

		// Date
		else if(text.getType() == DynamicType.Date){
			if(!Date.class.isAssignableFrom(evaluatedObject.getClass())){
				throw new IllegalArgumentException("Expected evaluated object to be of type java.util.Date instead found: " + evaluatedObject.getClass().toString());
			}
			SimpleDateFormat sdf = new SimpleDateFormat(text.getFormatting());
			String formattedDate = sdf.format((Date)evaluatedObject);
			processedText.setText(formattedDate);
		}

		// Decimal
		else if(text.getType() == DynamicType.Decimal){
			if(!Number.class.isAssignableFrom(evaluatedObject.getClass())){
				throw new IllegalArgumentException("Expected evaluated object to be of type java.lang.Number instead found: " + evaluatedObject.getClass().toString());
			}
			if(text.getFormatting() == null){
				processedText.setText(evaluatedObject.toString());
			}
			else{
				DecimalFormat df = new DecimalFormat(text.getFormatting());
				String formattedNumber = df.format(evaluatedObject);
				processedText.setText(formattedNumber);
			}
		}

		// Number
		else if(text.getType() == DynamicType.Number){
			if(!Number.class.isAssignableFrom(evaluatedObject.getClass())){
				throw new IllegalArgumentException("Expected evaluated object to be of type java.lang.Number instead found: " + evaluatedObject.getClass().toString());
			}
			if(text.getFormatting() == null){
				processedText.setText(evaluatedObject.toString());
			}
			else{
				String formattedNumber = String.format(text.getFormatting(), evaluatedObject).trim();
				processedText.setText(formattedNumber);
			}

		}

		return processedText;
	}

	private Object evaluateExpression(String expression){
		JexlExpression e = jexl.createExpression(expression);
		return e.evaluate(this.jc);
	}
}
