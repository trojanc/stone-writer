package coza.trojanc.receipt.format;

import coza.trojanc.receipt.shared.Align;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public interface PrintFormatBuilder {

	/**
	 * Insert text print format builder.
	 *
	 * @param text the text
	 * @param offset the offset
	 * @param align the align
	 * @return the print format builder
	 */
	PrintFormatBuilder insertText(String text, Integer offset, Align align);

	/**
	 * Insert left print format builder.
	 *
	 * @param text the text
	 * @param position_left the position left
	 * @return the print format builder
	 */
	PrintFormatBuilder insertLeft(String text, int position_left);

	/**
	 * Left print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	PrintFormatBuilder left(String text);

	/**
	 * Insert center print format builder.
	 *
	 * @param text the text
	 * @param position the position
	 * @return the print format builder
	 */
	PrintFormatBuilder insertCenter(String text, int position);

	/**
	 * Center print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	PrintFormatBuilder center(String text);

	/**
	 * Insert right print format builder.
	 *
	 * @param text the text
	 * @param position_right the position right
	 * @return the print format builder
	 */
	PrintFormatBuilder insertRight(String text, int position_right);

	/**
	 * Right print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	PrintFormatBuilder right(String text);

	/**
	 * Nl print format builder.
	 *
	 * @return the print format builder
	 */
	PrintFormatBuilder nl();

	/**
	 * Gets format.
	 *
	 * @return the format
	 */
	Object getFormat();

	int getLineWidth();
}
