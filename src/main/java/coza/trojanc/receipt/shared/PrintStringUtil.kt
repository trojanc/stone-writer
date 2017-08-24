package coza.trojanc.receipt.shared

import java.util.ArrayList

/**
 * @author Charl Thiem
 */
object PrintStringUtil {

    /**
     * Get line buffer char [ ].
     *
     * @param lineSize     the line size
     * @param fillWithChar the fill with char
     * @return the char [ ]
     */
    @JvmOverloads
    fun getLineBuffer(lineSize: Int, fillWithChar: Char = ' '): CharArray {
        val buffer = CharArray(lineSize)
        for (i in 0..lineSize - 1) {
            buffer[i] = fillWithChar
        }

        return buffer
    }

    /**
     * Parses one 'line' of chars from a string for the given width so that no words are chopped in half.
     *
     * @param str               the str
     * @param str_length        the str length
     * @param width             the width
     * @param start_index       the start index
     * @param end_index         the end index
     * @param length            the length
     * @param tab_index         the tab index
     * @param new_line_sequence the new line sequence
     * @return line
     */
    fun getLine(str: String?, str_length: Int, width: Int, start_index: IntArray, end_index: IntArray, length: IntArray, tab_index: IntArray, new_line_sequence: String?): Boolean {
        var new_line_sequence = new_line_sequence
        if (str == null || str_length <= 0 || start_index[0] >= str_length)
            return false

        if (new_line_sequence == null) {
            new_line_sequence = ""
        }

        /*
		 * find start
		 */
        while (start_index[0] < str_length && str[start_index[0]] == ' ')
            start_index[0]++
        if (start_index[0] >= str_length)
            return false

        /*
		 * parse string
		 */
        tab_index[0] = -1
        val max_index = Math.min(start_index[0] + width - 1, str_length - 1)
        var break_index = -1
        var last_space_index = -1
        end_index[0] = start_index[0]
        while (end_index[0] <= max_index) {
            /*
			 * keep track of spaces as line breaks
			 */
            if (str[end_index[0]] == ' ') {
                last_space_index = end_index[0]
            } else if (new_line_sequence.length > 0 && str[end_index[0]] == new_line_sequence[0] || str[end_index[0]] == '\n') {
                /*
				 * allow '\n' as the default newline
				 */
                if (str[end_index[0]] == '\n') {
                    break_index = end_index[0]
                    end_index[0]++
                    break
                } else if (new_line_sequence.length == 1) {
                    break_index = end_index[0]
                    end_index[0]++
                    break
                } else {
                    /*
					 * ignore double \\ when the new line sequence starts with a \
					 */
                    if (new_line_sequence.length > 0 && new_line_sequence[0] == '\\' && end_index[0] + 1 < str_length && str[end_index[0] + 1] == '\\') {
                        end_index[0]++
                    } else {
                        var i = 1
                        while (i < new_line_sequence.length && end_index[0] + i < str_length && new_line_sequence[i] == str[end_index[0] + i])
                            i++

                        /*
						 * match, new line
						 */
                        if (i == new_line_sequence.length) {
                            break_index = end_index[0]
                            end_index[0] += new_line_sequence.length
                            break
                        }
                    }/*
					 * else verify if the new line sequence matches
					 */
                }/*
				 * more than one char newline
				 *//*
				 * one char new line char
				 */
            } else if (str[end_index[0]] == '\t') {
                tab_index[0] = end_index[0]
            }/*
			 * keep track of new line chars as line breaks
			 */

            /*
			 * next char
			 */
            end_index[0]++
        }

        /*
		 * no break, use last space char
		 */
        if (break_index == -1) {
            if (end_index[0] == str_length || last_space_index == -1)
                break_index = max_index + 1
            else
                break_index = last_space_index
            // set end index
            end_index[0] = break_index
        }

        /*
		 * if space char, find first
		 */
        if (break_index < str_length && str[break_index] == ' ') {
            while (break_index - 1 > start_index[0] && str[break_index - 1] == ' ') {
                break_index--
            }
        }

        /*
		 * length
		 */
        length[0] = break_index - start_index[0]

        return true
    }


    /**
     * Insert a value into a char array representing a line, so that the
     * value will be inserted at the `destPos` in the line.
     *
     * @param line Character array representing the line.
     * @param destPos Index in line where the value should be inserted
     * @param value Value that must be inserted in the line
     * @param maxLength Maximum number of characters from the value that may be added
     */
    private fun insert(line: CharArray, destPos: Int, value: CharArray?, maxLength: Int) {
        var destPos = destPos
        if (value == null) {
            return
        }
        var length = value.size    // Number of characters from value that will be inserted into line
        var srcPos = 0            // Starting index in value

        //verify max length
        if (maxLength < length) {
            /*
			 * If the length of the value to add is larger than <code>maxLength</code>
			 * move up the starting index of the value so that the last characters
			 * of the <code>value</code> will fit inside the <code>maxLength</code>
			 * constraint.
			 */
            srcPos = srcPos + (length - maxLength)
            length = maxLength
        }

        //verify destPos
        if (destPos < 0) {
            srcPos = srcPos - destPos // same as (srcPos + ABS(destPos))
            length = length + destPos // same as (length - ABS(destPos))
            destPos = 0
        } else if (destPos >= line.size) {
            /*
			 * If the index where the value should be inserted is larger than
			 * the size of the line, then nothing will be added, so just return
			 * (line array will be unchanged)
			 */
            return
        }

        /*
		 * verify length
		 * If the value will not fit into the line, adjust the number
		 * of characters to only include those that will fit in the line.
		 */
        if (destPos + length >= line.size)
            length = line.size - destPos

        //copy
        System.arraycopy(value, srcPos, line, destPos, length)
    }

    /**
     * Insert a value into a char array, left aligned
     *
     * @param line      the line
     * @param position  the position
     * @param value     the value
     * @param maxLength the max length
     */
    fun insertLeftAligned(line: CharArray, position: Int, value: String, maxLength: Int) {
        val valueLength = value.length

        // If the string is longer than the max length, we cut the string on right to keeo
        // only the left most characters
        if (maxLength < valueLength) {
            insert(line, position, value.substring(0, maxLength).toCharArray(), maxLength)
        } else {
            insert(line, position, value.toCharArray(), maxLength)
        }

    }

    /**
     * Insert a value into a char array, center aligned at a position
     *
     * @param line      the line buffer to insert the value to.
     * @param position The position to center the value on.
     * @param value     the value to add centered
     * @param maxLength The max length of the string being inserted
     */
    fun insertCenterAligned(line: CharArray, position: Int, value: String?, maxLength: Int) {
        if (value == null) {
            return
        }

        // We can also hit the sides of the line buffer when the maxlength and position are just right
        // | | | | | |0|1|2|3|4|
        // | | | | | | |^| | | | - offset (1)
        // | | | | |v|a|l|u|e| |- value (5)
        // | | | | | |x|x|x| | |- maxLength (3)
        // | | | | | |a|l|u| | |- result (3)

        val valueLength = value.length

        // If the string is longer than the max length, we have to work out the
        // substring so that the string appears centered in the available area
        // |0|1|2|3|4|
        // |v|a|l|u|e| - value (5)
        // | |x|x|x| | - maxLength (3)
        // | |a|l|u| | - result
        if (maxLength < valueLength) {

            // How much is the value longer than the available space
            val overshoot = valueLength - maxLength

            // How much do we need to shift up the start index for the substring
            val offset = overshoot / 2

            // How much do we need to additionally substract for the end of the string
            val end = if (overshoot % 2 > 0) 1 else 0
            insert(line, position - maxLength / 2, value.substring(offset, valueLength - (offset + end)).toCharArray(), maxLength)
        } else {
            insert(line, position - valueLength / 2, value.toCharArray(), valueLength)
        }// The value fits perfectly in the line buffer
    }

    /**
     * Insert a value into a char array, center aligned at the middel of the line specified.
     *
     * @param line      Line in which the value should be added.
     * @param value     Value to insert in the line
     * @param maxLength the max length
     */
    fun insertCenterAligned(line: CharArray, value: String, maxLength: Int) {
        insertCenterAligned(line, line.size / 2, value, maxLength)
    }

    /**
     * Creates a String that will be padded on the right with the defined padChar so
     * that the resulting String will use all the available space.
     * If the String specified is longer than the available space the String will be
     * substringed to fit into the space.
     * Eg.
     * ("123",4,'0')    = 1230
     * ("123465",4,'0') = 1234
     *
     * @param value    String value that needs to be padded
     * @param fillSize Available space to fill.
     * @param padChar  Character to use for padding.
     * @return string
     */
    fun createRightPaddedString(value: String?, fillSize: Int, padChar: Char): String {

        val STR_SIZE = value?.length ?: 0
        /* No need to fill, possibly need to substring */
        if (STR_SIZE >= fillSize) {
            return value!!.substring(0, fillSize)
        }
        val spaces = fillSize - STR_SIZE
        val builder = StringBuilder(fillSize)

        /*
		 * Append the value if not null
		 */
        if (value != null) {
            builder.append(value)
        }

        for (i in 0..spaces - 1) {
            builder.append(padChar)
        }
        return builder.toString()
    }

    /**
     * Creates a String in length if the specified `length` filled with the specified `character`.
     *
     * @param length    The length the string should be.
     * @param character Character that should be used to fill the string.
     * @return string
     */
    fun createStringOfChar(length: Int, character: Char): String {
        return createLeftPaddedString("", length, character)
    }

    /**
     * Creates a String that will be padded on the left with the defined padChar so
     * that the resulting String will use all the available space.
     * If the String specified is longer than the available space the String will be
     * substringed to fit into the space.
     * Eg.
     * ("123",4,'0')    = 0123
     * ("123465",4,'0') = 1234
     *
     * @param value    String value that needs to be padded
     * @param fillSize Available space to fill.
     * @param padChar  Character to use for padding.
     * @return string
     */
    fun createLeftPaddedString(value: String?, fillSize: Int, padChar: Char): String {

        val STR_SIZE = value?.length ?: 0
        /* No need to fill, possibly need to substring */
        if (STR_SIZE > fillSize) {
            return value!!.substring(0, fillSize)
        }
        val spaces = fillSize - STR_SIZE
        val builder = StringBuilder(fillSize)
        for (i in 0..spaces - 1) {
            builder.append(padChar)
        }

        if (value != null) {
            builder.append(value)
        }
        /*
		 * Create a line filled with the padChar and the length of the fillSize
		 * Insert the value right aligned, therefore it is left padded
		 * Is nice idea for method reuse, but java has no new String(padChar,fillSize)
		 * constructor.
		 */
        return builder.toString()
    }

    /**
     * Name value string.
     *
     * @param line      Default line char array (may be set to null)
     * @param name      the name
     * @param name_pos  Index position of the name
     * @param value     the value
     * @param value_pos Index position of the value.
     * @param width     Length of the string.
     * @return string
     */
    fun nameValue(line: CharArray?, name: String, name_pos: Int, value: String, value_pos: Int, width: Int): String {
        var line = line
        var value_pos = value_pos
        if (line == null) {
            line = createStringOfChar(width, ' ').toCharArray()
        }
        val name_length = name.length
        insertLeftAligned(line, name_pos, name, name_length)

        //verify that the value position is valid
        if (name_pos + name_length >= value_pos - 2 || value_pos < 0 || value_pos >= width) {
            value_pos = name_pos + name_length + 2
        }
        //colon just in front of value position
        line[value_pos - 2] = ':'

        insertLeftAligned(line, value_pos, value, value.length)
        return String(line)
    }

    /**
     * Insert a value into a char array, right aligned
     * If the value of `position` is negative the position will be taken as an index
     * from the right, else the it will be taken as an index position from the left.
     *
     * @param line      Array containing the line in which the value should be added
     * @param position  Index where last character of the `value` must be right aligned on
     * @param value     The value to add in the line
     * @param maxLength the max length
     */
    fun insertRightAligned(line: CharArray, position: Int, value: String?, maxLength: Int) {
        if (value == null) {
            return
        }

        val length = value.length
        // If the value is longer than the available space, but the line to keep
        // The right-most characters
        if (maxLength < length) {
            insert(line, position - maxLength + 1, value.substring(value.length - maxLength).toCharArray(), maxLength)
        } else {
            insert(line, position - length + 1, value.toCharArray(), length)
        }
    }


    /**
     * Parses a string and builds an array of wrapped strings from it.
     *
     * @param str               the str
     * @param width             the width
     * @param new_line_sequence the new line sequence
     * @return string [ ]
     */
    fun getLines(str: String, width: Int, new_line_sequence: String): Array<String> {
        val strs = ArrayList<String>()
        val start_index = IntArray(1)
        val end_index = IntArray(1)
        val length = IntArray(1)
        val tab_index = IntArray(1)
        start_index[0] = 0
        end_index[0] = 0
        length[0] = 0
        while (getLine(str, str.length, width, start_index, end_index, length, tab_index, new_line_sequence)) {
            strs.add(str.substring(start_index[0], start_index[0] + length[0]))
            // next
            start_index[0] = end_index[0]
        }

        return strs.toTypedArray()
    }

    /**
     * Removes trailing spaces from the source string.
     * Note only SPACE (0x20) will be removed, NOT newlines,tabs, etc.
     *
     * @param source String to remove trailing spaces from.
     * @return String without trailing spaces.
     */
    fun rtrim(source: String): String {
        return source.replace("\\x20+$".toRegex(), "")
    }


    /**
     * Returns the left most num chars of the string
     *
     * @param str the str
     * @param num the num
     * @return string
     */
    fun left(str: String, num: Int): String {
        val len = Math.min(str.length, num)
        return str.substring(0, len)
    }


    /**
     * Trims the right side of the string of all whitespace
     *
     * @param str the str
     * @return string
     */
    fun trimRight(str: String): String {
        var i = str.length
        while (i > 0 && str[i - 1] <= ' ') {
            i--
        }
        return if (i == str.length) {
            str
        } else {
            str.substring(0, i)
        }
    }

    /**
     * Calculate the true position from the left
     * @param width Width within the position is set
     * @param index The position within the width
     * @return
     */
    fun indexLeft(width: Int, index: Int): Int {
        // If the position is negative, work out the position from the right
        if (index < 0) {
            if (width + index <= 0) {
                throw IllegalArgumentException("Position is larger than the width")
            }
            return width + index - 1
        } else return if (index >= width) {
            throw IllegalArgumentException("Position is larger than the width")
        } else {
            index
        }// If the position is larger than the width, we cannot place it
    }

    /**
     * Determine if we can use the full width to center with, or if we are going to hit any side first, we have to adapt
     * the length to still be centered.
     * @param width Width of the total string when centered
     * @param position Position the string should be added to
     * @return
     */
    fun maxStrLengthCenter(width: Int, position: Int): Int {
        val positionLeft = indexLeft(width, position)

        // Position is in the middel of the width, therefore we can use the full width
        return if (width / 2 == positionLeft) {
            width - if (width % 2 == 0) 1 else 0
        } else if (positionLeft < width / 2) {
            positionLeft * 2 + 1
        } else {
            (width - (positionLeft + 1)) * 2 + 1
        }// We'll be hitting the right side
        // We'll be hitting the left
    }

}
/**
 * Get line buffer char [ ].
 *
 * @param lineSize the line size
 * @return the char [ ]
 */
