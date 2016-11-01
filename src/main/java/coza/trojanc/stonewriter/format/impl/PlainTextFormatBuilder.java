package coza.trojanc.stonewriter.format.impl;

import coza.trojanc.stonewriter.format.AbstractPlainTextFormatBuilder;
import coza.trojanc.stonewriter.format.PrintFormatBuilder;

public class PlainTextFormatBuilder extends AbstractPlainTextFormatBuilder {

	public PlainTextFormatBuilder(int line_width) {
		super(line_width);
	}

	public PlainTextFormatBuilder(int line_width, String invalidCharsRegex, char invalidCharReplacement) {
		super(line_width, invalidCharsRegex, invalidCharReplacement);
	}

	@Override
	public PrintFormatBuilder nl() {
		super.nl();
		super.builder.append("\n");
		return this;
	}

	//
//	/** Defines the prompt lines */
//	private StringBuilder builder;
//
//	/** An array of chars to the width of the line */
//	private char[] charBuffer;
//
//	/** The default text in a char buffer when starting a line */
//	private final char[] defaultCharBuffer;
//
//	/** The display width */
//	private final int width;
//
//	/** flag indicating that the char buffer has been used */
//	private boolean charBufferInUse;
//
//	/** Flag if busy with first line */
//	private boolean firstLine = true;
//
//	public static final String NEW_LINE = "\n";
//
//	/**
//	 *
//	 * Creates a new instance of a(n) <code>PlainTextLayoutBuilder</code>.
//	 * @param width Maximum number of characters in a line
//	 */
//	public PlainTextLayoutBuilder(int width) {
//
//		if (width == 0){
//			throw new IllegalArgumentException("Invalid size specified (" + width + ")");
//		}
//
//		this.builder = new StringBuilder();
//		this.width = width;
//		this.defaultCharBuffer = PrintStringUtil.getLineBuffer(width);
//		this.charBuffer = new char[this.defaultCharBuffer.length];
//		this.resetCharBuffer();
//	}
//
//
//	/**
//	 * Completes the line we were busy building in the char buffer and returns true.
//	 * If we were not busy building a line it returns false.
//	 *
//	 * @return
//	 */
//	private boolean completeCharBuffer() {
//		if (this.charBufferInUse) {
//			if (!this.firstLine) {
//				this.builder.append(NEW_LINE);
//			}
//			else {
//				this.firstLine = false;
//			}
//			this.builder.append(new String(this.charBuffer));
//			this.resetCharBuffer();
//			return true;
//		}
//		else
//			return false;
//	}
//	/**
//	 * Reset the char buffer to the default value
//	 */
//	private void resetCharBuffer() {
//		System.arraycopy(this.defaultCharBuffer, 0, this.charBuffer, 0, this.width);
//		this.charBufferInUse = false;
//	}
//
//
//
//	public PrintTextLayoutBuilder left(String text) {
//		//complete char buffer line?
//		this.completeCharBuffer();
//
//		//add all lines left aligned
//		int w = this.width;
//
//		String[] strs = PrintStringUtil.getLines(text, w, "\n");
//		for (String value : strs) {
//			this.nl();
//			this.builder.append(value);
//		}
//
//		return this;
//	}
//
//	public PrintTextLayoutBuilder center(String text) {
//		//complete char buffer line?
//		this.completeCharBuffer();
//
//		int w = this.width;
//
//		//add all lines center aligned
//		String[] strs = PrintStringUtil.getLines(text, w, "\n");
//		int pos = this.width / 2;
//		for (String value : strs) {
//			this.nl();
//			PrintStringUtil.insertCenterAligned(this.charBuffer, pos, value, width);
//			this.builder.append(new String(this.charBuffer));
//			this.resetCharBuffer();
//		}
//
//		return this;
//	}
//
//
//	public PrintTextLayoutBuilder right(String text) {
//		//complete char buffer line?
//		this.completeCharBuffer();
//
//		//add all lines right aligned
//		String[] strs = PrintStringUtil.getLines(text, width, "\n");
//		int pos = width;
//		for (String value : strs) {
//			PrintStringUtil.insertRightAligned(this.charBuffer, pos, value, width);
//			this.nl();
//			this.builder.append(new String(this.charBuffer));
//			this.resetCharBuffer();
//		}
//
//		return this;
//	}
//
//	public PrintTextLayoutBuilder insertLeft(String text, int position_left) {
//		this.charBufferInUse = true;
//		if (position_left < 0) {
//			PrintStringUtil.insertLeftAligned(this.charBuffer, width + position_left - 1, text, width);
//		}
//		else {
//			PrintStringUtil.insertLeftAligned(this.charBuffer, position_left, text, width);
//		}
//		return this;
//	}
//
//
//	public PrintTextLayoutBuilder insertCenter(String text, int position) {
//		this.charBufferInUse = true;
//		if (position < 0) {
//			PrintStringUtil.insertCenterAligned(this.charBuffer, width + position - 1, text, width);
//		}
//		else {
//			PrintStringUtil.insertCenterAligned(this.charBuffer, position, text, width);
//		}
//		return this;
//	}
//
//	public PrintTextLayoutBuilder insertRight(String text, int position_right) {
//		this.charBufferInUse = true;
//		if (position_right < 0) {
//			PrintStringUtil.insertRightAligned(this.charBuffer, width + position_right - 1, text, width);
//		}
//		else {
//			PrintStringUtil.insertRightAligned(this.charBuffer, position_right, text, width);
//		}
//		return this;
//	}
//
//
//	@Override
//	public PrintTextLayoutBuilder insertText(String text, Integer offset, Align align) {
//		if(align == Align.LEFT){
//			if(offset == null){
//				left(text);
//			}
//			else{
//				insertLeft(text, offset);
//			}
//		}
//		else if(align == Align.CENTER){
//			if(offset == null){
//				center(text);
//			}
//			else{
//				insertCenter(text, offset);
//			}
//		}
//		else if(align == Align.RIGHT){
//			if(offset == null){
//				right(text);
//			}
//			else{
//				insertRight(text, offset);
//			}
//		}
//		return this;
//	}
//
//	public PrintTextLayoutBuilder nl() {
//		//complete char buffer line?
//		if (!this.completeCharBuffer()) {
//			if (!this.firstLine) {
//				this.builder.append(NEW_LINE);
//			}
//		}
//
//		this.firstLine = false;
//		return this;
//	}
//
//
//	public String toString() {
//		return this.builder.toString();
//	}
//
//	/**
//	 * @return the width
//	 */
//	public int getLineWidth() {
//		return this.width;
//	}

}
