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
			this.completeCharBuffer(); // Complete the line
		}
		return this.builder.toString();
	}

	public PrintFormatBuilder insertLeft(final String text, final int index){
		return this.insertLeft(text, index, this.lineWidth);
	}

	public PrintFormatBuilder insertLeft(final String text, final int index, final LineWrap lineWrap){
		return this.insertLeft(text, index, this.lineWidth, lineWrap);
	}

	public PrintFormatBuilder insertRight(final String text, final int index){
		return this.insertRight(text, index, this.lineWidth);
	}

	public PrintFormatBuilder insertRight(final String text, final int index, final LineWrap lineWrap){
		return this.insertRight(text, index, lineWidth, lineWrap);
	}

	public PrintFormatBuilder insertCenter(final String text, final int index){
		return this.insertCenter(text, index, this.lineWidth, DEFAULT_LINE_WRAP);
	}

	public PrintFormatBuilder insertCenter(final String text, final int index, final LineWrap lineWrap){
		return this.insertCenter(text, index, this.lineWidth, lineWrap);
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
		this.insertLeft(text, 0, this.lineWidth, lineWrap);
		this.completeCharBuffer();
		return this;
	}

	public PrintFormatBuilder center(final String text) {
		return this.center(text, DEFAULT_LINE_WRAP);
	}
	public PrintFormatBuilder center(final String text, final LineWrap lineWrap) {
		//complete char buffer line?
		this.completeCharBuffer();
		this.insertCenter(text, this.lineWidth / 2, this.lineWidth, lineWrap);
		this.completeCharBuffer();
		return this;
	}


	public PrintFormatBuilder right(final String text) {
		return this.right(text, DEFAULT_LINE_WRAP);
	}

	public PrintFormatBuilder right(final String text, final LineWrap lineWrap) {
		//complete char buffer line?
		this.completeCharBuffer();
		this.insertRight(text, this.lineWidth-1, this.lineWidth, lineWrap);
		this.completeCharBuffer();
		return this;
	}


	/**
	 * Inserts left given a specific line width
	 *
	 * @param text the text
	 * @param index the position left
	 * @param width the width
	 * @return print format builder
	 */
	protected PrintFormatBuilder insertLeft(final String text, final int index, final int width){
		return this.insertLeft(text, index, width, DEFAULT_LINE_WRAP);
	}

	protected PrintFormatBuilder insertLeft(final String text, final int index, final int width, final LineWrap lineWrap){
		if (text == null) {
			return this;
		}

		this.lineBufferInUse = true;
		final int indexLeft = PrintStringUtil.indexLeft(width, index);
		// If we are not wrapping, add as much as we can into the current line buffer
		if(lineWrap == LineWrap.NO_WRAP) {
			PrintStringUtil.insertLeftAligned(this.charBuffer, indexLeft, text, width);
		}
		else {
			final int maxStringLength = width - indexLeft;
			String[] lines = PrintStringUtil.getLines(text, maxStringLength, "");
			if(lineWrap == LineWrap.WRAP) {
				int line = 0;
				for (String lineStr : lines) {
					this.insertLeft(lineStr, indexLeft, width, LineWrap.NO_WRAP);
					if (lines.length > 1 && line++ < lines.length - 1) {
						this.nl();
					}
				}
			}
			else if(lineWrap == LineWrap.WRAP_LEFT) {
				this.insertLeft(lines[0], indexLeft, width, LineWrap.NO_WRAP);
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
	 * @param index the position right
	 * @param width the width
	 * @return print format builder
	 */
	protected PrintFormatBuilder insertRight(final String text, final int index, final int width){
		return this.insertRight(text, index, width, DEFAULT_LINE_WRAP);
	}

	protected PrintFormatBuilder insertRight(final String text, final int index, final int width, LineWrap lineWrap){
		if (text == null) {
			return this;
		}
		this.lineBufferInUse = true;
		final int indexLeft = PrintStringUtil.indexLeft(width, index);
		if (lineWrap == LineWrap.NO_WRAP) {
			PrintStringUtil.insertRightAligned(this.charBuffer, indexLeft, text, width);
		}
		else{
			// We have as much space as we are from the left
			final int availableSpace = indexLeft+1;
			String[] lines = PrintStringUtil.getLines(text, availableSpace, "");
			this.insertRight(lines[0], indexLeft, width, LineWrap.NO_WRAP);
			if (lineWrap == LineWrap.WRAP) {
				int line = 0;
				for (String lineStr : lines) {
					this.insertRight(lineStr, indexLeft, width, LineWrap.NO_WRAP);
					if (lines.length > 1 && line++ < lines.length - 1) {
						this.nl();
					}
				}
			}
			else if (lineWrap == LineWrap.WRAP_LEFT) {
				if (lines.length > 1) {
					this.nl();
					this.insertLeft(text.substring(lines[0].length(), text.length()), 0, width, LineWrap.WRAP);
				}
			}
		}
		return this;
	}

	/**
	 * Inserts centered given a specific line width
	 *
	 * @param text the text
	 * @param index the position
	 * @param width the width
	 * @return print format builder
	 */
	protected PrintFormatBuilder insertCenter(final String text, final int index, final int width){
		return this.insertCenter(text, index, width, DEFAULT_LINE_WRAP);
	}

	protected PrintFormatBuilder insertCenter(final String text, final int index, final int width, LineWrap lineWrap) {
		if (text == null) {
			return this;
		}

		this.lineBufferInUse = true;

		// The true position from the left to insert the string
		final int indexLeft = PrintStringUtil.indexLeft(width, index);

		if (lineWrap == LineWrap.NO_WRAP) {
			PrintStringUtil.insertCenterAligned(this.charBuffer, indexLeft, text, width);
		} else {
			final int availableSpace = PrintStringUtil.maxStrLengthCenter(width, indexLeft);
			String[] lines = PrintStringUtil.getLines(text, availableSpace, "");
			if (lineWrap == LineWrap.WRAP) {
				int line = 0;
				for (String lineStr : lines) {
					this.insertCenter(lineStr, indexLeft, width, LineWrap.NO_WRAP);
					if (lines.length > 1 && line++ < lines.length - 1) {
						this.nl();
					}
				}
			} else if (lineWrap == LineWrap.WRAP_LEFT) {
				// New offset if we need to move the center due to a smaller line
				//                                       New offset                              Move on position left if uneven line length
				final int centerOffset = indexLeft;//(positionLeft - ((width - lines[0].length()) / 2)) + (lines[0].length() % 2 > 0 ? 1 : 0) ;
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
		if (this.lineBufferInUse){
			this.builder.append(String.valueOf(charBuffer));
			this.resetCharBuffer();
		}
	}


	/**
	 * Complete the current char buffer.
	 * Be ware that implementations can override this and add newline characters to the string
	 * @return
	 */
	public PrintFormatBuilder nl() {
		completeCharBuffer();
		return this;
	}

	public PrintFormatBuilder feed(){
		this.nl();
		this.nl();
		this.nl();
		return this;
	}
}
