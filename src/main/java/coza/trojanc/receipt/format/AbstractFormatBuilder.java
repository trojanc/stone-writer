package coza.trojanc.receipt.format;

import coza.trojanc.receipt.shared.Align;

/**
 * Created by Charl-PC on 2016-10-20.
 */
public abstract class AbstractFormatBuilder implements PrintFormatBuilder {


	/** flag indicating that the char buffer has been used */
	protected boolean lineBufferInUse = false;

	/** Width of a line */
	protected final int lineWidth;

	/**
	 * Creates a new instance of a <code>AbstractFormatBuilder</code> setting
	 * the line width.
	 * @param line_width The number of characters that can be displayed on a line
	 */
	protected AbstractFormatBuilder(int line_width){
		this.lineWidth = line_width;
	}

	/**
	 * @return the lineWidht
	 */
	public int getLineWidth() {
		return this.lineWidth;
	}


	@Override
	public PrintFormatBuilder insertText(String text, Integer offset, Align align) {
		if(align == Align.LEFT){
			if(offset == null){
				left(text);
			}
			else{
				insertLeft(text, offset);
			}
		}
		else if(align == Align.CENTER){
			if(offset == null){
				center(text);
			}
			else{
				insertCenter(text, offset);
			}
		}
		else if(align == Align.RIGHT){
			if(offset == null){
				right(text);
			}
			else{
				insertRight(text, offset);
			}
		}
		return this;
	}
}
