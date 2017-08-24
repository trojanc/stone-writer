package coza.trojanc.receipt.format;

import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.shared.LineWrap;

/**
 * @author Charl Thiem
 */
public interface PrintFormatBuilder {

	/**
	 * The constant DEFAULT_LINE_WRAP.
	 */
	LineWrap DEFAULT_LINE_WRAP = LineWrap.WRAP;

	/**
	 * Insert text print format builder.
	 *
	 * @param text   the text
	 * @param offset the offset
	 * @param align  the align
	 * @return the print format builder
	 */
	PrintFormatBuilder insertText(final String text, final Integer offset, final Align align);

	/**
	 * Insert left print format builder.
	 *
	 * @param text          the text
	 * @param position_left the position left
	 * @return the print format builder
	 */
	PrintFormatBuilder insertLeft(final String text, final int position_left);

	/**
	 * Insert left print format builder.
	 *
	 * @param text          the text
	 * @param position_left the position left
	 * @param lineWrap      the line wrap
	 * @return print format builder
	 */
	PrintFormatBuilder insertLeft(final String text, final int position_left, final LineWrap lineWrap);

	/**
	 * Left print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	PrintFormatBuilder left(final String text);

	PrintFormatBuilder left(final String text, final LineWrap lineWrap);

	/**
	 * Insert center print format builder.
	 *
	 * @param text     the text
	 * @param position the position
	 * @return the print format builder
	 */
	PrintFormatBuilder insertCenter(final String text, final int position);


	/**
	 * Insert center print format builder.
	 *
	 * @param text     the text
	 * @param position the position
	 * @param lineWrap the line wrap
	 * @return the print format builder
	 */
	PrintFormatBuilder insertCenter(final String text, final int position, final LineWrap lineWrap);

	/**
	 * Center print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	PrintFormatBuilder center(final String text, final LineWrap lineWrap);

	PrintFormatBuilder center(final String text);

	/**
	 * Insert right print format builder.
	 *
	 * @param text           the text
	 * @param index the position right
	 * @return the print format builder
	 */
	PrintFormatBuilder insertRight(final String text, final int index);

	/**
	 *
	 * @param text
	 * @param index
	 * @param lineWrap
	 * @return
	 */
	PrintFormatBuilder insertRight(final String text, final int index, final LineWrap lineWrap);

	/**
	 * Right print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	PrintFormatBuilder right(final String text);

	PrintFormatBuilder right(final String text, final LineWrap lineWrap);

	/**
	 * Nl print format builder.
	 *
	 * @return the print format builder
	 */
	PrintFormatBuilder nl();

	/**
	 * Add a feed
	 *
	 * @return print format builder
	 */
	PrintFormatBuilder feed();

	/**
	 * Gets format.
	 *
	 * @return the format
	 */
	Object getFormat();

	/**
	 * Gets line width.
	 *
	 * @return the line width
	 */
	int getLineWidth();
}
