package coza.trojanc.receipt.template.builder;

import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.template.fields.*;

import java.awt.*;

/**
 * @author Charl Thiem
 */
public abstract class AbstractTemplateBuilder {

	/**
	 * Current busy line
	 */
	private Line line;

	/**
	 * Current busy text item
	 */
	private AbstractTextItem textItem;

	/**
	 * Instantiates a new Print template builder.
	 */
	AbstractTemplateBuilder(){
	}

	protected abstract void addTemplateLine(TemplateLine line);

	/**
	 * Line print template builder.
	 *
	 * @return the print template builder
	 */
	public void addLine(){
		finishBusyLine();
		line = new Line();
	}
	/**
	 * Feed print template builder.
	 *
	 * @return the print template builder
	 */
	public void addFeed(){
		finishBusyLine();
		addTemplateLine(new Feed());
	}

	/**
	 * Feed print template builder.
	 *
	 * @param lines the lines
	 * @return the print template builder
	 */
	public void addFeed(int lines){
		finishBusyLine();
		addTemplateLine(new Feed(lines));
	}

	/**
	 * Barcode image print template builder.
	 *
	 * @param image the barcode
	 * @return the print template builder
	 */
	public void addBarcodeImage(Image image){
		finishLineItem();
		checkValidLine();
		BarcodeImage barcodeItem = new BarcodeImage();

		if(BarcodeImage.class.isAssignableFrom(BarcodeImage.class)){
			throw new IllegalArgumentException("Cannot add image value to barcode");
		}
		barcodeItem.setBarcodeEAN(image);
	}

	/**
	 * Fill line print template builder.
	 *
	 * @param character the character
	 * @return the print template builder
	 */
	public void addFillLine(char character){
		finishBusyLine();
		addTemplateLine(new FillLine(character));
	}



	/**
	 * Static text print template builder.
	 *
	 * @param text the text
	 * @return the print template builder
	 */
	public void addStaticText(String text){
		finishLineItem();
		checkValidLine();
		textItem = new Text();

		if(DynamicText.class.isAssignableFrom(textItem.getClass())){
			throw new IllegalArgumentException("Cannot add text value to dynamic text");
		}
		((Text)textItem).setText(text);
	}

	/**
	 * Dynamic text print template builder.
	 *
	 * @param key the key
	 * @return the print template builder
	 */
	public void addDynamicText(String key){
		finishLineItem();
		checkValidLine();
		textItem = new DynamicText();
		if(!DynamicText.class.isAssignableFrom(textItem.getClass())){
			throw new IllegalArgumentException("Cannot add key to static text");
		}
		((DynamicText)textItem).setContextKey(key);
	}


	/**
	 * Align print template builder.
	 *
	 * @param align the align
	 * @return the print template builder
	 */
	public void addAlign(Align align){
		checkValidLine();
		ensureTextItem();
		textItem.setAlignment(align);
	}

	/**
	 * Offset print template builder.
	 *
	 * @param offset the offset
	 * @return the print template builder
	 */
	public void addOffset(Integer offset){
		checkValidLine();
		ensureTextItem();
		textItem.setOffset(offset);
	}


	/**
	 * Finish the line if we are busy with one
	 */
	protected void finishBusyLine(){
		if(line != null){
			finishLineItem();
			addTemplateLine(line);
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
