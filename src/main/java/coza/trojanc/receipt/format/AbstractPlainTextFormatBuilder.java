package coza.trojanc.receipt.format;

import coza.trojanc.receipt.shared.LineWrap;
import coza.trojanc.receipt.shared.PrintStringUtil;

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
	protected AbstractPlainTextFormatBuilder(final int line_width){
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
	protected AbstractPlainTextFormatBuilder(final int line_width, final String invalidCharsRegex, final char invalidCharReplacement){
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
	protected String fixCharacters(final String str){
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

	public Object getFormat(){
		if (this.lineBufferInUse){
			this.nl(); // Complete the line
		}
		return this.builder.toString();
	}

	public PrintFormatBuilder insertLeft(final String text, final int position_left){
		return this.insertLeft(text, position_left, this.lineWidth);
	}

	public PrintFormatBuilder insertLeft(final String text, final int position_left, final LineWrap lineWrap){
		return this.insertLeft(text, position_left, this.lineWidth, lineWrap);
	}

	public PrintFormatBuilder insertRight(final String text, final int position_right){
		return this.insertRight(text, position_right, this.lineWidth);
	}

	public PrintFormatBuilder insertCenter(final String text, final int position){
		return this.insertCenter(text, position, DEFAULT_LINE_WRAP);
	}

	public PrintFormatBuilder insertCenter(final String text, final int position, final LineWrap lineWrap){
		return this.insertCenter(text, position, this.lineWidth, lineWrap);
	}


	/**
	 * Insert right print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	public PrintFormatBuilder insertRight(final String text) {
		return this.insertRight(text, this.getLineWidth()-1);
	}


	public PrintFormatBuilder left(final String text) {
		return this.left(text, DEFAULT_LINE_WRAP);
	}

	public PrintFormatBuilder left(final String text, final LineWrap lineWrap) {
		//complete char buffer line?
		this.completeCharBuffer();

		// If we don't wrap lines, instead user insert left to cut to only the current line
		if(lineWrap == LineWrap.NO_WRAP){
			return this.insertLeft(text, 0, this.lineWidth, LineWrap.NO_WRAP).nl();
		}

		//add all lines left aligned
		int line = 0;
		String[] strs = PrintStringUtil.getLines(text, this.lineWidth, "\n");
		for (String value : strs) {
			if(line++ != 0) {
				this.nl();
			}
			this.builder.append(value);
		}

		return this;
	}

	public PrintFormatBuilder center(final String text) {
		//complete char buffer line?
		this.completeCharBuffer();

		//add all lines center aligned
		String[] strs = PrintStringUtil.getLines(text, this.lineWidth, "\n");
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


	public PrintFormatBuilder right(final String text) {
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
	protected PrintFormatBuilder insertLeft(final String text, final int position_left, final int width){
		return this.insertLeft(text, position_left, width, DEFAULT_LINE_WRAP);
	}

	protected PrintFormatBuilder insertLeft(final String text, final int position_left, final int width, final LineWrap lineWrap){
		if (text == null) {
			return this;
		}

		this.lineBufferInUse = true;
		final int positionLeft = PrintStringUtil.indexLeft(width, position_left);
		// If we are not wrapping, add as much as we can into the current line buffer
		if(lineWrap == LineWrap.NO_WRAP) {
			PrintStringUtil.insertLeftAligned(this.charBuffer, positionLeft, text, width);
		}
		else {
			final int maxStringLength = width - positionLeft;
			String[] lines = PrintStringUtil.getLines(text, maxStringLength, "");
			 if(lineWrap == LineWrap.WRAP) {
				 int line = 0;
				 for (String lineStr : lines) {
					 this.insertLeft(lineStr, positionLeft, width, LineWrap.NO_WRAP);
					 if (lines.length > 1 && line++ < lines.length - 1) {
						 this.nl();
					 }
				 }
			 }
			else if(lineWrap == LineWrap.WRAP_LEFT) {
				 this.insertLeft(lines[0], position_left, width, LineWrap.NO_WRAP);
				 if (lines.length > 1) {
					 this.nl();
					 this.insertLeft(text.substring(lines[0].length(), text.length()), 0, width, LineWrap.WRAP);
				 }
			 }
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
	protected PrintFormatBuilder insertRight(final String text, final int position_right, final int width){
		if (text == null) {
			return this;
		}
		this.lineBufferInUse = true;
		final int positionLeft = PrintStringUtil.indexLeft(width, position_right);
		PrintStringUtil.insertRightAligned(this.charBuffer, positionLeft, text, width);
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
	protected PrintFormatBuilder insertCenter(final String text, final int position, final int width){
		return this.insertCenter(text, position, width, DEFAULT_LINE_WRAP);
	}

	protected PrintFormatBuilder insertCenter(final String text, final int position, final int width, LineWrap lineWrap) {
		if (text == null) {
			return this;
		}

		this.lineBufferInUse = true;

		// The true position from the left to insert the string
		final int positionLeft = PrintStringUtil.indexLeft(width, position);

		if (lineWrap == LineWrap.NO_WRAP) {
			PrintStringUtil.insertCenterAligned(this.charBuffer, positionLeft, text, width);
		} else {
			final int availableSpace = PrintStringUtil.maxStrLengthCenter(width, positionLeft);
			String[] lines = PrintStringUtil.getLines(text, availableSpace, "");
			if (lineWrap == LineWrap.WRAP) {
				int line = 0;
				for (String lineStr : lines) {
					this.insertCenter(lineStr, positionLeft, width, LineWrap.NO_WRAP);
					if (lines.length > 1 && line++ < lines.length - 1) {
						this.nl();
					}
				}
			} else if (lineWrap == LineWrap.WRAP_LEFT) {
				// New offset if we need to move the center due to a smaller line
				//                                       New offset                              Move on position left if uneven line length
				final int centerOffset = positionLeft;//(positionLeft - ((width - lines[0].length()) / 2)) + (lines[0].length() % 2 > 0 ? 1 : 0) ;
				this.insertCenter(lines[0], centerOffset, width, LineWrap.NO_WRAP);
				if (lines.length > 1) {
					this.nl();
					this.insertLeft(text.substring(lines[0].length(), text.length()), 0, width, LineWrap.WRAP);
				}
			}
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

	public PrintFormatBuilder feed(){
		this.nl();
		return this;
	}
}
