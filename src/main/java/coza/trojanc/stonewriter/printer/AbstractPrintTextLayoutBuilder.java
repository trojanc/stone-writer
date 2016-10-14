package coza.trojanc.stonewriter.printer;

import coza.trojanc.stonewriter.processor.layout.LayoutBuilderTester;
import coza.trojanc.stonewriter.processor.layout.PrintTextLayoutBuilder;
import coza.trojanc.stonewriter.processor.layout.TextLayoutBuilder;
import coza.trojanc.stonewriter.shared.PrintStringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * An abstract implementation of a print text layout builder
 * @author Charl Thiem
 */
public abstract class AbstractPrintTextLayoutBuilder implements PrintTextLayoutBuilder
{
	/** Static string of 'single line' chars */
	protected static char[] emptyBuf		= "                                                                                                 ".toCharArray(); 
	/** Static string of 'single line' chars */
	protected static String lineSingleChars = "-------------------------------------------------------------------------------------------------"; 
	/** Static string of 'double line' chars */
	protected static String lineDoubleChars = "================================================================================================="; 
	/** Builder used to create the text to display/print */
	protected StringBuilder	builder;
	/** Width of a line */
	protected final int		lineWidth;
	/** Current index in the line */
	protected int			appendIndex		= 0;
	/** An array of chars to the width of the line */
	protected char[]		charBuffer		= null;
	/** flag indicating that the char buffer has been used */
	protected boolean		charBufferInUse	= false;
	/** Chars that should be removed from strings as it could possible break the print command */
	protected final Pattern	invalidCharsPatern;
	/** Char that should be placed in the place of an invalid char */
	protected final char	invalidCharReplacement;


	/**
	 * Creates a new instance of a <code>AbstractThinClientPrintBuilder</code>
	 */
	protected AbstractPrintTextLayoutBuilder(){
		this(40, null, ' ');
	}

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
	protected AbstractPrintTextLayoutBuilder(int line_width, String invalidCharsRegex, char invalidCharReplacement)
	{
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
		System.arraycopy(emptyBuf, 0, this.charBuffer, 0, this.lineWidth);
		this.charBufferInUse = false;
		this.appendIndex = 0;
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

	public char[] toChars(){
		return toString().toCharArray();
	}

	public byte[] toBytes() {
		return toString().getBytes();
	}

	public TextLayoutBuilder append(long number){
		this.builder.append(number);
		return this;
	}

	public TextLayoutBuilder insertCenter(long number, int position){
		return this.insertCenter(Long.toString(number), position);
	}

	public TextLayoutBuilder insertLeft(long number, int position_left){
		return this.insertLeft(Long.toString(number), position_left);
	}

	public TextLayoutBuilder insertRight(long number, int position_right){
		return this.insertRight(Long.toString(number), position_right);
	}

	public TextLayoutBuilder nl(int num_line){
		for (int i = 0; i < num_line; i++){
			this.nl();
		}
		return this;
	}

	public TextLayoutBuilder insertLeft(String text, int position_left){
		return this.insertLeft(text, position_left, this.lineWidth);
	}

	public TextLayoutBuilder insertRight(String text, int position_right){
		return this.insertRight(text, position_right, this.lineWidth);
	}

	public TextLayoutBuilder insertCenter(String text, final int position){
		return this.insertCenter(text, position, this.lineWidth);
	}

	
	/**
	 * Inserts left given a specific line width
	 * 
	 * @param text
	 * @param position_left
	 * @param width
	 * @return
	 */
	protected TextLayoutBuilder insertLeft(String text, int position_left, int width){
		if (text == null) {
			return this;
		}
		
		this.charBufferInUse = true;
		if (position_left < 0){
			PrintStringUtil.insertLeftAligned(this.charBuffer, width+position_left-1, text, width);
		} 
		else {
			PrintStringUtil.insertLeftAligned(this.charBuffer, position_left, text, width);
			this.appendIndex += text.length(); // What if this runs over the line?!
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
	protected TextLayoutBuilder insertRight(String text, int position_right, int width){
		if (text == null) {
			return this;
		}
		
		this.charBufferInUse = true;
		if (position_right < 0){
			PrintStringUtil.insertRightAligned(this.charBuffer, width + position_right - 1, text, width);
			this.appendIndex = width - Math.abs(position_right);
		} 
		else {
			PrintStringUtil.insertRightAligned(this.charBuffer, position_right, text, width);
			this.appendIndex = width - position_right - 1;
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
	protected TextLayoutBuilder insertCenter(String text, final int position, int width){
		if (text == null) {
			return this;
		}
		
		this.charBufferInUse = true;
		if (position < 0){
			PrintStringUtil.insertCenterAligned(this.charBuffer, width+position-1, text, width);
			this.appendIndex = width + position + (text.length() / 2); // Might exceed line width
		} 
		else {
			PrintStringUtil.insertCenterAligned(this.charBuffer, position, text, width);
			this.appendIndex = position + (text.length() / 2);
		}
		return this;
	}


	public TextLayoutBuilder insertChar(char chr, int position){
		this.charBufferInUse = true;
		if (position < 0) {
			position = this.lineWidth + position - 1;
		}
		if ((position < 0) || (position >= this.lineWidth)) {
			return this;
		}
		
		this.appendIndex = position + 1; // What if this runs over the line?! - whoever uses append index should not assume it will be correct.
		this.charBuffer[position] = chr;
		
		return this;
	}
	
	/**
	 * Creates a test printing
	 * @return
	 */
	public PrintTextLayoutBuilder createTestPrint(){
		return LayoutBuilderTester.testPrintBuilder(this);
	}

	/**
	 * Flushes the char buffer
	 */
	public void flushCharBuffer() {
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


	public PrintTextLayoutBuilder nameValue(String name, int name_pos, String value, int value_pos) {
		if (name == null) {
			return this;
		}
		
		if (value == null) {
			value = "";
		}
		
		//name
		this.insertLeft(name, name_pos);
		int name_length = name.length();
		
		//verify that the value position is valid 
		if ((name_pos + name_length >= value_pos - 2) || (value_pos < 0) || (value_pos >= this.lineWidth))  {
			value_pos = name_pos + name_length + 2;
		}

		//colon just in front of value position
		this.insertChar(':', value_pos - 2);

		//verify that the value will fit after the colon on the same line
		if (value_pos + value.length() <= this.lineWidth) {
			this.insertLeft(value, value_pos).nl();
		} 
//		//verify that the value will fit directly after the colon on the same line
//		else if (value_pos + value.length() - 1 < this.lineWidth) {
//			this.insertLeft(value, value_pos - 1).nl();
//		} 
		//value will have to go onto the next line
		else {
			this.nl();
			this.insertRight(value, -1).nl();
		}
		
		return this;
	}

	public TextLayoutBuilder centerB(String text) {
		return this.center(text);
	}

	public TextLayoutBuilder insertRight(String text) {
		return this.insertRight(text, this.getLineWidth()-1);
	}

	public TextLayoutBuilder insertRight(long number) {
		return this.insertRight(number, this.getLineWidth()-1);
	}
}
