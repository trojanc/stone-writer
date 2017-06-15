package coza.trojanc.receipt.template.builder;

import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.fields.*;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintTemplateBuilder {

	/**
	 * Template we are busy building
	 */
	private PrintTemplate template;

	/**
	 * Current busy line
	 */
	private Line line;

	/**
	 * Current busy text item
	 */
	private AbstractTextItem textItem;

	public PrintTemplateBuilder(){
		template = new PrintTemplate();
	}

	/**
	 * Set the name of the template
	 * @param name
	 * @return
	 */
	public PrintTemplateBuilder name(String name){
		template.setName(name);
		return this;
	}


	public PrintTemplateBuilder line(){
		finishBusyLine();
		line = new Line();
		return this;
	}

	public PrintTemplateBuilder feed(){
		finishBusyLine();
		this.template.addLine(new Feed());
		return this;
	}

	public PrintTemplateBuilder feed(int lines){
		finishBusyLine();
		this.template.addLine(new Feed(lines));
		return this;
	}

	public PrintTemplate build(){
		finishBusyLine();
		return template;
	}


	public PrintTemplateBuilder text(String text){
		return staticText(text);
	}

	public PrintTemplateBuilder text(String text, boolean dynamic){
		if(dynamic){
			return dynamicText(text);
		}
		else{
			return staticText(text);
		}
	}

	public PrintTemplateBuilder staticText(String text){
		finishLineItem();
		checkValidLine();
		textItem = new Text();

		if(DynamicText.class.isAssignableFrom(textItem.getClass())){
			throw new IllegalArgumentException("Cannot add text value to dynamic text");
		}
		((Text)textItem).setText(text);

		return this;
	}

	public PrintTemplateBuilder dynamicText(String key){
		finishLineItem();
		checkValidLine();
		textItem = new DynamicText();
		if(!DynamicText.class.isAssignableFrom(textItem.getClass())){
			throw new IllegalArgumentException("Cannot add key to static text");
		}
		((DynamicText)textItem).setContextKey(key);
		return this;
	}


	public PrintTemplateBuilder align(Align align){
		checkValidLine();
		ensureTextItem();
		textItem.setAlignment(align);
		return this;
	}

	public PrintTemplateBuilder offset(Integer offset){
		checkValidLine();
		ensureTextItem();
		textItem.setOffset(offset);
		return this;
	}


	/**
	 * Finish the line if we are busy with one
	 */
	private void finishBusyLine(){
		if(line != null){
			finishLineItem();
			template.addLine(line);
			line = null;
		}
	}

	/**
	 * Finish a line item if we are busy with one
	 */
	private void finishLineItem(){
		if(textItem != null){
			this.line.addLineItem(textItem);
			textItem = null;
		}
	}

	/**
	 * Check that we are busy with a line
	 */
	private void checkValidLine(){
		if(line == null){
			throw new IllegalArgumentException("Not busy with a line!");
		}
	}

	/**
	 * Ensure a text item is ready to be used
	 */
	private void ensureTextItem(){
		if(textItem == null){
			throw new IllegalArgumentException("Not busy with a text item!");
		}
	}
}
