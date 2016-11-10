package coza.trojanc.stonewriter.format;

import coza.trojanc.stonewriter.shared.PrintStringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * An abstract implementation of a print text layout builder
 *
 * @author Charl Thiem
 */
public abstract class AbstractPlainTextFormatBuilder extends AbstractFormatBuilder {

	/**
	 * Builder used to create the text to display/print
	 */
	protected StringBuilder builder;

	/** The default text in a char buffer when starting a line */
	private final char[] defaultCharBuffer;

	/**
	 * An array of chars to the width of the line
	 */
	protected char[] charBuffer = null;

	/**
	 * Chars that should be removed from strings as it could possible break the print command
	 */
	protected final Pattern invalidCharsPattern;

	/**
	 * Char that should be placed in the place of an invalid char
	 */
	protected final char invalidCharReplacement;


	/**
	 * Creates a new instance of a <code>AbstractThinClientPrintBuilder</code> setting
	 * the line width.
	 *
	 * @param line_width The number of characters that can be displayed on a line
	 */
	protected AbstractPlainTextFormatBuilder(int line_width){
		this(line_width, null, ' ');
	}

	/**
	 * Creates a new instance of a <code>AbstractThinClientPrintBuilder</code> setting
	 * the line width.
	 *
	 * @param line_width The number of characters that can be displayed on a line
	 * @param invalidCharsRegex Regular expression of invalid characters
	 * @param invalidCharReplacement The character that should be used to replace the invalid character
	 */
	protected AbstractPlainTextFormatBuilder(int line_width, String invalidCharsRegex, char invalidCharReplacement){
		super(line_width);
		this.defaultCharBuffer = PrintStringUtil.getLineBuffer(line_width);
		this.builder = new StringBuilder();
		this.charBuffer = new char[this.lineWidth];
		if (invalidCharsRegex != null){
			this.invalidCharsPattern = Pattern.compile(invalidCharsRegex);
		} 
		else {
			this.invalidCharsPattern = null;
		}
		this.invalidCharReplacement = invalidCharReplacement;
		initialize();
	}

	/**
	 * Checks a String and removes illegal characters
	 *
	 * @param str the str
	 * @return string string
	 */
	protected String fixCharacters(String str){
		if (str == null) {
			return "";		//TODO confirm behaviour
		}
		
		if (this.invalidCharsPattern != null){
			Matcher m = this.invalidCharsPattern.matcher(str);
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
		this.lineBufferInUse = false;
	}

	/**
	 * Initialize print format builder.
	 *
	 * @return the print format builder
	 */
	public PrintFormatBuilder initialize(){
		this.resetCharBuffer();
		return this;
	}

	/**
	 * Returns the builder as a raw String
	 */
	public String toString(){
		if (this.lineBufferInUse){
			this.nl(); // Complete the line
		}
		return this.builder.toString();
	}

	public Object getFormat(){
		return this.toString();
	}

	public PrintFormatBuilder insertLeft(String text, int position_left){
		return this.insertLeft(text, position_left, this.lineWidth);
	}

	public PrintFormatBuilder insertRight(String text, int position_right){
		return this.insertRight(text, position_right, this.lineWidth);
	}

	public PrintFormatBuilder insertCenter(String text, final int position){
		return this.insertCenter(text, position, this.lineWidth);
	}



	/**
	 * Insert right print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	public PrintFormatBuilder insertRight(String text) {
		return this.insertRight(text, this.getLineWidth()-1);
	}



	public PrintFormatBuilder left(String text) {
		//complete char buffer line?
		this.completeCharBuffer();

		//add all lines left aligned
		int w = this.lineWidth;
		int line = 0;
		String[] strs = PrintStringUtil.getLines(text, w, "\n");
		for (String value : strs) {
			if(line++ != 0) {
				this.nl();
			}
			this.builder.append(value);
		}

		return this;
	}

	public PrintFormatBuilder center(String text) {
		//complete char buffer line?
		this.completeCharBuffer();

		int w = this.lineWidth;

		//add all lines center aligned
		String[] strs = PrintStringUtil.getLines(text, w, "\n");
		int line = 0;
		int pos = this.lineWidth / 2;
		for (String value : strs) {
			if(line++ != 0) {
				this.nl();
			}
			PrintStringUtil.insertCenterAligned(this.charBuffer, pos, value, lineWidth);
			this.builder.append(new String(this.charBuffer));
			this.resetCharBuffer();
		}

		return this;
	}


	public PrintFormatBuilder right(String text) {
		//complete char buffer line?
		this.completeCharBuffer();

		//add all lines right aligned
		String[] strs = PrintStringUtil.getLines(text, lineWidth, "\n");
		int pos = lineWidth-1;
		int line = 0;
		for (String value : strs) {
			PrintStringUtil.insertRightAligned(this.charBuffer, pos, value, lineWidth);
			if(line++ != 0) {
				this.nl();
			}
			this.builder.append(new String(this.charBuffer));
			this.resetCharBuffer();
		}

		return this;
	}


	/**
	 * Inserts left given a specific line width
	 *
	 * @param text the text
	 * @param position_left the position left
	 * @param width the width
	 * @return print format builder
	 */
	protected PrintFormatBuilder insertLeft(String text, int position_left, int width){
		if (text == null) {
			return this;
		}
		
		this.lineBufferInUse = true;
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
	 * @param text the text
	 * @param position_right the position right
	 * @param width the width
	 * @return print format builder
	 */
	protected PrintFormatBuilder insertRight(String text, int position_right, int width){
		if (text == null) {
			return this;
		}
		
		this.lineBufferInUse = true;
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
	 * @param text the text
	 * @param position the position
	 * @param width the width
	 * @return print format builder
	 */
	protected PrintFormatBuilder insertCenter(String text, final int position, int width){
		if (text == null) {
			return this;
		}
		
		this.lineBufferInUse = true;
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
		if (this.lineBufferInUse) {
			int last_space_position = charBuffer.length-1;
			for (; last_space_position > 0; last_space_position--) {	//> 0 since we will at least print one space char
				if (charBuffer[last_space_position] != ' ')
					break;
			}
			this.builder.append(this.charBuffer, 0, last_space_position+1);
			this.resetCharBuffer();
		}
	}


	public PrintFormatBuilder nl() {
		if (this.lineBufferInUse){
			this.builder.append(String.valueOf(charBuffer));
			/* Clear each character */
			for (int i = 0; i < lineWidth; i++){
				this.charBuffer[i] = ' ';
			}
			this.lineBufferInUse = false;
		}
		return this;
	}
}
