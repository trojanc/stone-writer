package coza.trojanc.stonewriter.printer.layout;

import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.shared.PrintStringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * An abstract implementation of a print text layout builder
 * @author Charl Thiem
 */
public abstract class AbstractPrintTextLayoutBuilder implements PrintTextLayoutBuilder
{	/** Builder used to create the text to display/print */
	protected StringBuilder	builder;

	/** The default text in a char buffer when starting a line */
	private final char[] defaultCharBuffer;

	/** Width of a line */
	protected final int		lineWidth;

	/** An array of chars to the width of the line */
	protected char[]		charBuffer		= null;

	/** flag indicating that the char buffer has been used */
	protected boolean		charBufferInUse	= false;

	/** Chars that should be removed from strings as it could possible break the print command */
	protected final Pattern	invalidCharsPatern;

	/** Char that should be placed in the place of an invalid char */
	protected final char	invalidCharReplacement;


	/**
	 * Creates a new instance of a <code>AbstractThinClientPrintBuilder</code> setting
	 * the line width.
	 * @param line_width The number of characters that can be displayed on a line
	 */
	protected AbstractPrintTextLayoutBuilder(int line_width){
		this(line_width, null, ' ');
	}

	/**
	 * Creates a new instance of a <code>AbstractThinClientPrintBuilder</code> setting
	 * the line width.
	 * @param line_width The number of characters that can be displayed on a line
	 * @param invalidCharsRegex Regular expression of invalid characters
	 * @param invalidCharReplacement The character that should be used to replace the invalid character
	 */
	protected AbstractPrintTextLayoutBuilder(int line_width, String invalidCharsRegex, char invalidCharReplacement){
		this.defaultCharBuffer = PrintStringUtil.getLineBuffer(line_width);
		this.builder = new StringBuilder();
		this.lineWidth = line_width;
		this.charBuffer = new char[this.lineWidth];
		if (invalidCharsRegex != null){
			this.invalidCharsPatern = Pattern.compile(invalidCharsRegex);
		} 
		else {
			this.invalidCharsPatern = null;
		}
		this.invalidCharReplacement = invalidCharReplacement;
		initialize();
	}

	/**
	 * Checks a String and removes illegal characters
	 * @param str
	 * @return
	 */
	protected String fixCharacters(String str){
		if (str == null) {
			return "";		//TODO confirm behaviour
		}
		
		if (this.invalidCharsPatern != null){
			Matcher m = this.invalidCharsPatern.matcher(str);
			return m.replaceAll(Character.toString(this.invalidCharReplacement));
		} 
		else {
			return str;
		}
	}

	/**
	 * Clears the char buffer;
	 */
	protected void resetCharBuffer() {
		System.arraycopy(defaultCharBuffer, 0, this.charBuffer, 0, this.lineWidth);
		this.charBufferInUse = false;
	}
	
	public PrintTextLayoutBuilder initialize(){
		this.resetCharBuffer();
		return this;
	}

	/**
	 * Returns the builder as a raw String
	 */
	public String toString(){
		if (this.charBufferInUse){
			this.nl(); // Complete the line
		}
		return this.builder.toString();
	}

	public PrintTextLayoutBuilder insertLeft(String text, int position_left){
		return this.insertLeft(text, position_left, this.lineWidth);
	}

	public PrintTextLayoutBuilder insertRight(String text, int position_right){
		return this.insertRight(text, position_right, this.lineWidth);
	}

	public PrintTextLayoutBuilder insertCenter(String text, final int position){
		return this.insertCenter(text, position, this.lineWidth);
	}



	public PrintTextLayoutBuilder left(String text) {
		//complete char buffer line?
		this.completeCharBuffer();

		//add all lines left aligned
		int w = this.lineWidth;

		String[] strs = PrintStringUtil.getLines(text, w, "\n");
		for (String value : strs) {
			nl();
			this.builder.append(value);
		}

		return this;
	}

	public PrintTextLayoutBuilder center(String text) {
		//complete char buffer line?
		this.completeCharBuffer();

		int w = this.lineWidth;

		//add all lines center aligned
		String[] strs = PrintStringUtil.getLines(text, w, "\n");
		int pos = this.lineWidth / 2;
		for (String value : strs) {
			this.nl();
			PrintStringUtil.insertCenterAligned(this.charBuffer, pos, value, lineWidth);
			this.builder.append(new String(this.charBuffer));
			this.resetCharBuffer();
		}

		return this;
	}


	public PrintTextLayoutBuilder right(String text) {
		//complete char buffer line?
		this.completeCharBuffer();

		//add all lines right aligned
		String[] strs = PrintStringUtil.getLines(text, lineWidth, "\n");
		int pos = lineWidth;
		for (String value : strs) {
			PrintStringUtil.insertRightAligned(this.charBuffer, pos, value, lineWidth);
			this.nl();
			this.builder.append(new String(this.charBuffer));
			this.resetCharBuffer();
		}

		return this;
	}

	
	/**
	 * Inserts left given a specific line width
	 * 
	 * @param text
	 * @param position_left
	 * @param width
	 * @return
	 */
	protected PrintTextLayoutBuilder insertLeft(String text, int position_left, int width){
		if (text == null) {
			return this;
		}
		
		this.charBufferInUse = true;
		if (position_left < 0){
			PrintStringUtil.insertLeftAligned(this.charBuffer, width+position_left-1, text, width);
		} 
		else {
			PrintStringUtil.insertLeftAligned(this.charBuffer, position_left, text, width);
		}
		return this;
	}

	/**
	 * Inserts Right given a specific line width
	 * 
	 * @param text
	 * @param position_right
	 * @param width
	 * @return
	 */
	protected PrintTextLayoutBuilder insertRight(String text, int position_right, int width){
		if (text == null) {
			return this;
		}
		
		this.charBufferInUse = true;
		if (position_right < 0){
			PrintStringUtil.insertRightAligned(this.charBuffer, width + position_right - 1, text, width);
		}
		else {
			PrintStringUtil.insertRightAligned(this.charBuffer, position_right, text, width);
		}
		return this;
	}

	/**
	 * Inserts centered given a specific line width
	 * 
	 * @param text
	 * @param position
	 * @param width
	 * @return
	 */
	protected PrintTextLayoutBuilder insertCenter(String text, final int position, int width){
		if (text == null) {
			return this;
		}
		
		this.charBufferInUse = true;
		if (position < 0){
			PrintStringUtil.insertCenterAligned(this.charBuffer, width+position-1, text, width);
		}
		else {
			PrintStringUtil.insertCenterAligned(this.charBuffer, position, text, width);
		}
		return this;
	}


	/**
	 * Flushes the char buffer
	 */
	public void completeCharBuffer() {
		if (this.charBufferInUse) {
			int last_space_position = charBuffer.length-1;
			for (; last_space_position > 0; last_space_position--) {	//> 0 since we will at least print one space char
				if (charBuffer[last_space_position] != ' ')
					break;
			}
			this.builder.append(this.charBuffer, 0, last_space_position+1);
			this.resetCharBuffer();
		}
	}
	
	/**
	 * @return the lineWidht
	 */
	public int getLineWidth() {
		return this.lineWidth;
	}


	public PrintTextLayoutBuilder insertRight(String text) {
		return this.insertRight(text, this.getLineWidth()-1);
	}

	@Override
	public PrintTextLayoutBuilder insertText(String text, Integer offset, Align align) {
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

	public PrintTextLayoutBuilder nl() {
		if (this.charBufferInUse){
			this.builder.append(String.valueOf(charBuffer));
			/* Clear each character */
			for (int i = 0; i < lineWidth; i++){
				this.charBuffer[i] = ' ';
			}
			this.charBufferInUse = false;
		}
		return this;
	}
}
