package coza.trojanc.stonewriter.processor.layout;

/** 
 * A interface for basic text styling.
 */
public interface TextLayoutBuilder {

	
	/**
	 * Gets this builder as an array of bytes.
	 * @return
	 */
	byte[] toBytes();

	/**
	 * Returns this builder as an array of chars
	 * @return
	 */
	char[] toChars();

	/**
	 * Append the raw data to the builder at the last index of the used line.
	 * @param text Raw data to append.
	 * @return
	 */
	TextLayoutBuilder append(String text);

	/**
	 * Appends a number from the current position in the current line.
	 * @param number Number to append to the current line.
	 * @return
	 */
	TextLayoutBuilder append(long number);

	/**
	 * Adds align of text center aligned.
	 * @param text Text to be placed in this line.
	 * @return
	 */
	TextLayoutBuilder center(String text);

	/**
	 * Adds a line of text Right aligned
	 * @param text Text to be placed in this line.
	 * @return
	 */
	TextLayoutBuilder right(String text);

	/**
	 * Adds a line of text left aligned
	 * @param text Text to be placed in this line.
	 * @return
	 */
	TextLayoutBuilder left(String text);


	/**
	 * Ends the current line, or adds a blank line
	 * @return
	 */
	TextLayoutBuilder nl();
	
	/**
	 * Ends the current line, or adds a number of empty lines.
	 * @param num_line Number of empty lines to add (will actually
	 * be one less, if the current line has not been completed)
	 * @return
	 */
	TextLayoutBuilder nl(int num_line);

	/**
	 * Inserts a line and aligns it left at the specified position from left
	 * @param text Text to add in the line.
	 * @param position_left Number of padding columns from the left.
	 * @return
	 */
	TextLayoutBuilder insertLeft(String text, int position_left);

	/**
	 * Inserts a line and aligns it left at the specified position from left
	 * @param number Number to add in the line.
	 * @param position_left Number of padding columns from the left.
	 * @return
	 */
	TextLayoutBuilder insertLeft(long number, int position_left);

	/**
	 * Inserts text positioned the specified number of spaces from the right
	 * @param text Text to add in the line.
	 * @param position_right Number of padding columns from the right.
	 * @return
	 */
	TextLayoutBuilder insertRight(String text, int position_right);
	
	/**
	 * Inserts text positioned at the very right.
	 * @param text Text to add in the line.
	 * @return
	 */
	TextLayoutBuilder insertRight(String text);

	/**
	 * Inserts a integer positioned the specified number of spaces from the right.
	 * @param number Number to add in the line
	 * @param position_right Number of padding columns from the right.
	 * @return
	 */
	TextLayoutBuilder insertRight(long number, int position_right);
	
	/**
	 * Inserts a number at the very right.
	 * @param number Number to add in the line
	 * @return
	 */
	TextLayoutBuilder insertRight(long number);

	/**
	 * Inserts string centered at the specified position
	 * @param text Text to add in the line.
	 * @param position Position in line where the text should be centered
	 * @return
	 */
	TextLayoutBuilder insertCenter(String text, int position);

	/**
	 * Inserts a number centerd at the specified position.
	 * @param number Number to add in the line
	 * @param position Position in line where the text should be centered
	 * @return
	 */
	TextLayoutBuilder insertCenter(long number, int position);
	
	/**
	 * Inserts a char into the specific position
	 * 
	 * @param chr
	 * @param position
	 * @return
	 */
	TextLayoutBuilder insertChar(char chr, int position);
	
	/**
	 * Returns the line width for the specific builder
	 * @return
	 */
	int getLineWidth();
}
