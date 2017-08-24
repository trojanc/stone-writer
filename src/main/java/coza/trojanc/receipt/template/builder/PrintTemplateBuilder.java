package coza.trojanc.receipt.template.builder;

import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.fields.*;

/**
 * @author Charl Thiem
 */
public class PrintTemplateBuilder extends AbstractTemplateBuilder{

	/**
	 * Template we are busy building
	 */
	private PrintTemplate template;


	/**
	 * Instantiates a new Print template builder.
	 */
	public PrintTemplateBuilder(){
		template = new PrintTemplate();
	}

	@Override
	protected void addTemplateLine(TemplateLine line) {
		template.addLine(line);
	}

	/**
	 * Set the name of the template
	 *
	 * @param name the name
	 * @return print template builder
	 */
	public PrintTemplateBuilder name(String name){
		template.setName(name);
		return this;
	}


	/**
	 * Line print template builder.
	 *
	 * @return the print template builder
	 */
	public PrintTemplateBuilder line(){
		finishBusyLine();
		super.addLine();
		return this;
	}

	/**
	 * Feed print template builder.
	 *
	 * @return the print template builder
	 */
	public PrintTemplateBuilder feed(){
		super.addFeed();
		return this;
	}

	/**
	 * Feed print template builder.
	 *
	 * @param lines the lines
	 * @return the print template builder
	 */
	public PrintTemplateBuilder feed(int lines){
		super.addFeed(lines);
		return this;
	}

	/**
	 * Fill line print template builder.
	 *
	 * @param character the character
	 * @return the print template builder
	 */
	public PrintTemplateBuilder fillLine(char character){
		super.addFillLine(character);
		return this;
	}


	/**
	 * Text print template builder.
	 *
	 * @param text the text
	 * @return the print template builder
	 */
	public PrintTemplateBuilder text(String text){
		return staticText(text);
	}

	/**
	 * Text print template builder.
	 *
	 * @param text    the text
	 * @param dynamic the dynamic
	 * @return the print template builder
	 */
	public PrintTemplateBuilder text(String text, boolean dynamic){
		if(dynamic){
			return dynamicText(text);
		}
		else{
			return staticText(text);
		}
	}


	/**
	 * Static text print template builder.
	 *
	 * @param text the text
	 * @return the print template builder
	 */
	public PrintTemplateBuilder staticText(String text){
		super.addStaticText(text);
		return this;
	}

	/**
	 * Dynamic text print template builder.
	 *
	 * @param key the key
	 * @return the print template builder
	 */
	public PrintTemplateBuilder dynamicText(String key){
		super.addDynamicText(key);
		return this;
	}


	/**
	 * Align print template builder.
	 *
	 * @param align the align
	 * @return the print template builder
	 */
	public PrintTemplateBuilder align(Align align){
		super.addAlign(align);
		return this;
	}

	/**
	 * Offset print template builder.
	 *
	 * @param offset the offset
	 * @return the print template builder
	 */
	public PrintTemplateBuilder offset(Integer offset){
		super.addOffset(offset);
		return this;
	}


	public PrintBlockTemplateBuilder repeat(String repeatOn){
		finishBusyLine();
		PrintBlockTemplateBuilder blockTemplateBuilder = new PrintBlockTemplateBuilder(this);
		blockTemplateBuilder.repeatOn(repeatOn);
		addTemplateLine(blockTemplateBuilder.getBlock());
		return blockTemplateBuilder;
	}

	public PrintTemplate build(){
		super.finishBusyLine();
		return this.template;
	}

}
