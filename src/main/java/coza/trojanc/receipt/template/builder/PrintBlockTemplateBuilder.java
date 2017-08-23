package coza.trojanc.receipt.template.builder;

import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.template.fields.RepeatBlock;
import coza.trojanc.receipt.template.fields.TemplateLine;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintBlockTemplateBuilder extends AbstractTemplateBuilder{

	/**
	 * Template we are busy building
	 */
	private final PrintTemplateBuilder parentBuilder;

	/**
	 * Block we are busy with
	 */
	private RepeatBlock block;


	/**
	 * Instantiates a new Print template builder.
	 */
	public PrintBlockTemplateBuilder(PrintTemplateBuilder parentBuilder){
		this.block = new RepeatBlock();
		this.parentBuilder = parentBuilder;
	}

	/**
	 * Set the name of the template
	 *
	 * @param name the name
	 * @return print template builder
	 */
	public PrintBlockTemplateBuilder repeatOn(String name){
		block.setRepeatOn(name);
		return this;
	}

	public RepeatBlock getBlock() {
		return block;
	}

	public PrintTemplateBuilder end(){
		super.finishBusyLine();
		return parentBuilder;
	}

	@Override
	protected void addTemplateLine(TemplateLine line) {
		block.getLines().add(line);
	}


	/**
	 * Line print template builder.
	 *
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder line(){
		finishBusyLine();
		super.addLine();
		return this;
	}

	/**
	 * Feed print template builder.
	 *
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder feed(){
		super.addFeed();
		return this;
	}

	/**
	 * Feed print template builder.
	 *
	 * @param lines the lines
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder feed(int lines){
		super.addFeed(lines);
		return this;
	}

	/**
	 * Fill line print template builder.
	 *
	 * @param character the character
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder fillLine(char character){
		super.addFillLine(character);
		return this;
	}


	/**
	 * Text print template builder.
	 *
	 * @param text the text
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder text(String text){
		return staticText(text);
	}

	/**
	 * Text print template builder.
	 *
	 * @param text    the text
	 * @param dynamic the dynamic
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder text(String text, boolean dynamic){
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
	public PrintBlockTemplateBuilder staticText(String text){
		super.addStaticText(text);
		return this;
	}

	/**
	 * Dynamic text print template builder.
	 *
	 * @param key the key
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder dynamicText(String key){
		super.addDynamicText(key);
		return this;
	}


	/**
	 * Align print template builder.
	 *
	 * @param align the align
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder align(Align align){
		super.addAlign(align);
		return this;
	}

	/**
	 * Offset print template builder.
	 *
	 * @param offset the offset
	 * @return the print template builder
	 */
	public PrintBlockTemplateBuilder offset(Integer offset){
		super.addOffset(offset);
		return this;
	}
}
