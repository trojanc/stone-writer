package coza.trojanc.receipt.format

import coza.trojanc.receipt.shared.LineWrap
import coza.trojanc.receipt.shared.PrintStringUtil

/**
 * An abstract implementation of a print text layout builder
 *
 * @author Charl Thiem
 */
abstract class AbstractPlainTextFormatBuilder
/**
 * Creates a new instance of a `AbstractThinClientPrintBuilder` setting
 * the line width.
 *
 * @param line_width The number of characters that can be displayed on a line
 * @param invalidCharsRegex Regular expression of invalid characters
 * @param invalidCharReplacement The character that should be used to replace the invalid character
 */
protected constructor(line_width: Int, invalidCharsRegex: String? = null,
					  /**
					   * Char that should be placed in the place of an invalid char
					   */
					  protected val invalidCharReplacement: Char = ' ') : AbstractFormatBuilder(line_width) {

	/**
	 * Builder used to create the text to display/print
	 */
	private val builder = StringBuilder()

	/** The default text in a char buffer when starting a line  */
	private val defaultCharBuffer: CharArray

	/**
	 * An array of chars to the width of the line
	 */
	protected val charBuffer: CharArray;

	/**
	 * Chars that should be removed from strings as it could possible break the print command
	 */
	protected val invalidCharsPattern: Regex?

	init {
		this.defaultCharBuffer = PrintStringUtil.getLineBuffer(line_width)
		this.charBuffer = CharArray(getLineWidth())
		if (invalidCharsRegex != null) {
			this.invalidCharsPattern = Regex(invalidCharsRegex)
		} else {
			this.invalidCharsPattern = null
		}
		initialize()
	}

	protected fun getStringBuilder() :StringBuilder {
		return this.builder;
	}

	/**
	 * Checks a String and removes illegal characters
	 *
	 * @param str the str
	 * @return string string
	 */
	protected fun fixCharacters(str: String?): String {
		if (str == null) {
			return ""        //TODO confirm behaviour
		}

		if (this.invalidCharsPattern != null) {
			return this.invalidCharsPattern.replace(str, this.invalidCharReplacement.toString())
		} else {
			return str
		}
	}

	/**
	 * Clears the char buffer;
	 */
	protected fun resetCharBuffer() {
		PrintStringUtil.arraycopy(defaultCharBuffer, 0, this.charBuffer, 0, getLineWidth())
		this.lineBufferInUse = false
	}

	/**
	 * Initialize print format builder.
	 *
	 * @return the print format builder
	 */
	open fun initialize(): PrintFormatBuilder {
		this.resetCharBuffer()
		return this
	}

	override fun getFormat(): Any {
		if (this.lineBufferInUse) {
			this.completeCharBuffer()
		}
		return this.builder.toString()
	}

	override fun insertLeft(text: String, index: Int): PrintFormatBuilder {
		return this.insertLeft(text, index, getLineWidth())
	}

	override fun insertLeft(text: String, index: Int, lineWrap: LineWrap): PrintFormatBuilder {
		return this.insertLeft(text, index, getLineWidth(), lineWrap)
	}

	override fun insertRight(text: String, index: Int): PrintFormatBuilder {
		return this.insertRight(text, index, getLineWidth())
	}

	override fun insertRight(text: String, index: Int, lineWrap: LineWrap): PrintFormatBuilder {
		return this.insertRight(text, index, getLineWidth(), lineWrap)
	}

	override fun insertCenter(text: String, position: Int): PrintFormatBuilder {
		return this.insertCenter(text, position, getLineWidth(), PrintFormatBuilder.DEFAULT_LINE_WRAP)
	}

	override fun insertCenter(text: String, index: Int, lineWrap: LineWrap): PrintFormatBuilder {
		return this.insertCenter(text, index, getLineWidth(), lineWrap)
	}


	/**
	 * Insert right print format builder.
	 *
	 * @param text the text
	 * @return the print format builder
	 */
	fun insertRight(text: String): PrintFormatBuilder {
		return this.insertRight(text, getLineWidth() - 1)
	}


	override fun left(text: String): PrintFormatBuilder {
		return this.left(text, PrintFormatBuilder.DEFAULT_LINE_WRAP)
	}

	override fun left(text: String, lineWrap: LineWrap): PrintFormatBuilder {
		//complete char buffer line?
		this.completeCharBuffer()
		this.insertLeft(text, 0, getLineWidth(), lineWrap)
		this.completeCharBuffer()
		return this
	}

	override fun center(text: String): PrintFormatBuilder {
		return this.center(text, PrintFormatBuilder.DEFAULT_LINE_WRAP)
	}

	override fun center(text: String, lineWrap: LineWrap): PrintFormatBuilder {
		//complete char buffer line?
		this.completeCharBuffer()
		this.insertCenter(text, getLineWidth() / 2, getLineWidth(), lineWrap)
		this.completeCharBuffer()
		return this
	}


	override fun right(text: String): PrintFormatBuilder {
		return this.right(text, PrintFormatBuilder.DEFAULT_LINE_WRAP)
	}

	override fun right(text: String, lineWrap: LineWrap): PrintFormatBuilder {
		//complete char buffer line?
		this.completeCharBuffer()
		this.insertRight(text, getLineWidth() - 1, getLineWidth(), lineWrap)
		this.completeCharBuffer()
		return this
	}


	/**
	 * Inserts left given a specific line width
	 *
	 * @param text the text
	 * @param index the position left
	 * @param width the width
	 * @return print format builder
	 */
	protected fun insertLeft(text: String, index: Int, width: Int): PrintFormatBuilder {
		return this.insertLeft(text, index, width, PrintFormatBuilder.DEFAULT_LINE_WRAP)
	}

	protected fun insertLeft(text: String?, index: Int, width: Int, lineWrap: LineWrap): PrintFormatBuilder {
		if (text == null) {
			return this
		}

		this.lineBufferInUse = true
		val indexLeft = PrintStringUtil.indexLeft(width, index)
		// If we are not wrapping, add as much as we can into the current line buffer
		if (lineWrap == LineWrap.NO_WRAP) {
			PrintStringUtil.insertLeftAligned(this.charBuffer, indexLeft, text, width)
		} else {
			val maxStringLength = width - indexLeft
			val lines = PrintStringUtil.getLines(text, maxStringLength, "")
			if (lineWrap == LineWrap.WRAP) {
				var line = 0
				for (lineStr in lines) {
					this.insertLeft(lineStr, indexLeft, width, LineWrap.NO_WRAP)
					if (lines.size > 1 && line++ < lines.size - 1) {
						this.nl()
					}
				}
			} else if (lineWrap == LineWrap.WRAP_LEFT) {
				this.insertLeft(lines[0], indexLeft, width, LineWrap.NO_WRAP)
				if (lines.size > 1) {
					this.nl()
					this.insertLeft(text.substring(lines[0].length, text.length), 0, width, LineWrap.WRAP)
				}
			}
		}
		return this
	}

	/**
	 * Inserts Right given a specific line width
	 *
	 * @param text the text
	 * @param index the position right
	 * @param width the width
	 * @return print format builder
	 */
	protected fun insertRight(text: String, index: Int, width: Int): PrintFormatBuilder {
		return this.insertRight(text, index, width, PrintFormatBuilder.DEFAULT_LINE_WRAP)
	}

	protected fun insertRight(text: String?, index: Int, width: Int, lineWrap: LineWrap): PrintFormatBuilder {
		if (text == null) {
			return this
		}
		this.lineBufferInUse = true
		val indexLeft = PrintStringUtil.indexLeft(width, index)
		if (lineWrap == LineWrap.NO_WRAP) {
			PrintStringUtil.insertRightAligned(this.charBuffer, indexLeft, text, width)
		} else {
			// We have as much space as we are from the left
			val availableSpace = indexLeft + 1
			val lines = PrintStringUtil.getLines(text, availableSpace, "")
			this.insertRight(lines[0], indexLeft, width, LineWrap.NO_WRAP)
			if (lineWrap == LineWrap.WRAP) {
				var line = 0
				for (lineStr in lines) {
					this.insertRight(lineStr, indexLeft, width, LineWrap.NO_WRAP)
					if (lines.size > 1 && line++ < lines.size - 1) {
						this.nl()
					}
				}
			} else if (lineWrap == LineWrap.WRAP_LEFT) {
				if (lines.size > 1) {
					this.nl()
					this.insertLeft(text.substring(lines[0].length, text.length), 0, width, LineWrap.WRAP)
				}
			}
		}
		return this
	}

	/**
	 * Inserts centered given a specific line width
	 *
	 * @param text the text
	 * @param index the position
	 * @param width the width
	 * @return print format builder
	 */
	protected fun insertCenter(text: String, index: Int, width: Int): PrintFormatBuilder {
		return this.insertCenter(text, index, width, PrintFormatBuilder.DEFAULT_LINE_WRAP)
	}

	protected fun insertCenter(text: String?, index: Int, width: Int, lineWrap: LineWrap): PrintFormatBuilder {
		if (text == null) {
			return this
		}

		this.lineBufferInUse = true

		// The true position from the left to insert the string
		val indexLeft = PrintStringUtil.indexLeft(width, index)

		if (lineWrap == LineWrap.NO_WRAP) {
			PrintStringUtil.insertCenterAligned(this.charBuffer, indexLeft, text, width)
		} else {
			val availableSpace = PrintStringUtil.maxStrLengthCenter(width, indexLeft)
			val lines = PrintStringUtil.getLines(text, availableSpace, "")
			if (lineWrap == LineWrap.WRAP) {
				var line = 0
				for (lineStr in lines) {
					this.insertCenter(lineStr, indexLeft, width, LineWrap.NO_WRAP)
					if (lines.size > 1 && line++ < lines.size - 1) {
						this.nl()
					}
				}
			} else if (lineWrap == LineWrap.WRAP_LEFT) {
				// New offset if we need to move the center due to a smaller line
				//                                       New offset                              Move on position left if uneven line length
				this.insertCenter(lines[0], indexLeft, width, LineWrap.NO_WRAP)
				if (lines.size > 1) {
					this.nl()
					this.insertLeft(text.substring(lines[0].length, text.length), 0, width, LineWrap.WRAP)
				}
			}
		}

		return this
	}


	/**
	 * Flushes the char buffer
	 */
	fun completeCharBuffer() {
		if (this.lineBufferInUse) {
			this.builder.append(charBuffer.joinToString(""))
			this.resetCharBuffer()
		}
	}


	/**
	 * Complete the current char buffer.
	 * Be ware that implementations can override this and add newline characters to the string
	 * @return
	 */
	override fun nl(): PrintFormatBuilder {
		completeCharBuffer()
		return this
	}

	override fun feed(): PrintFormatBuilder {
		this.nl()
		this.nl()
		this.nl()
		return this
	}
}
/**
 * Creates a new instance of a `AbstractThinClientPrintBuilder` setting
 * the line width.
 *
 * @param line_width The number of characters that can be displayed on a line
 */
