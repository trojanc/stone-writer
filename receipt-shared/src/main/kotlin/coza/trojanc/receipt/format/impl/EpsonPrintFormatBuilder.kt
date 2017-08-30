package coza.trojanc.receipt.format.impl

import coza.trojanc.receipt.format.AbstractPlainTextFormatBuilder
import coza.trojanc.receipt.format.PrintFormatBuilder
import coza.trojanc.receipt.shared.PrintStringUtil

/**
 * The type Epson print format builder.
 * @author Charl Thiem
 */
class EpsonPrintFormatBuilder : AbstractPlainTextFormatBuilder {

    /**
     * Paper sensors<br></br>
     * NOTE: This can not be used inline to apply it to the printer
     */
    object Sensors {
        /**
         * The constant ROLL_NEAR_END.
         */
        val ROLL_NEAR_END: Byte = 0x01
        /**
         * The constant ROLL_NEAR_END2.
         */
        val ROLL_NEAR_END2: Byte = 0x02
        /**
         * The constant ROLL_END.
         */
        val ROLL_END: Byte = 0x04
        /**
         * The constant ROLL_END2.
         */
        val ROLL_END2: Byte = 0x08
    }

    /**
     * Modes of underlining<br></br>
     * NOTE: Adding this inline will not change the undeline mode, it has
     * to be applied **with** the command
     */
    object Undeline_Mode {
        /**
         * The constant OFF.
         */
        val OFF: Byte = 0x00
        /**
         * The constant ONE_DOT.
         */
        val ONE_DOT: Byte = 0x01
        /**
         * The constant TWO_DOT.
         */
        val TWO_DOT: Byte = 0x02
    }

    /**
     * Styles of cutting the paper
     * NOTE: Adding this inline will not perform a papper cut, it has
     * to be applied **with** the command
     */
    object Cut_Style {
        /**
         * The constant FULL.
         */
        val FULL: Byte = 0x00
        /**
         * The constant PARTIALLY.
         */
        val PARTIALLY: Byte = 0x01
    }

    /**
     * Printing modes
     * NOTE: Adding this inline will not change the print mode mode, it has
     * to be applied **with** the command
     */
    object Print_Mode {
        /**
         * The constant DEFAULT.
         */
        val DEFAULT: Byte = 0x00
        /**
         * The constant EMPH_ON.
         */
        val EMPH_ON: Byte = 0x08
        /**
         * The constant DOUBLE_HEIGHT.
         */
        val DOUBLE_HEIGHT: Byte = 0x10
        /**
         * The constant DOUBLE_WIDTH.
         */
        val DOUBLE_WIDTH: Byte = 0x20
        /**
         * The constant UNDERLINE_ON.
         */
        val UNDERLINE_ON = 0x80.toByte()
    }

    /**
     * Printing fonts
     * NOTE: Adding this inline will not change the printer font, it has
     * to be applied **with** the command
     */
    object Print_Font {
        /**
         * The constant FONT_A.
         */
        val FONT_A: Byte = 0x00
        /**
         * The constant FONT_B.
         */
        val FONT_B: Byte = 0x01
    }

    /**
     * Printing alignments
     * NOTE: Adding this inline will not change the printer alignment, it has
     * to be applied **with** the command
     */
    object Print_Align {
        /**
         * The constant LEFT.
         */
        val LEFT: Byte = 0x00
        /**
         * The constant CENTER.
         */
        val CENTER: Byte = 0x01
        /**
         * The constant RIGHT.
         */
        val RIGHT: Byte = 0x02
    }


    /** Alignment printer is currently in  */
    private var current_alignment = Print_Align.LEFT
    /** Print mode printer is currently in  */
    private var current_mode = Print_Mode.DEFAULT

    /**
     * Creates a new instance of a `EpsonPrintBuilder`
     */
    constructor() : super(40) {}

    /**
     * Creates a new instances of `EpsonPrintBuilder` setting
     * widrth of the paper.
     *
     * @param line_width the line width
     */
    constructor(line_width: Int) : super(line_width) {}


    override fun initialize(): PrintFormatBuilder {
        super.initialize()
        super.getStringBuilder().append(0x1B.toChar()).append(0x40.toChar())
        this.current_alignment = Print_Align.LEFT
        this.current_mode = Print_Mode.DEFAULT
        return this
    }

    override fun center(text: String): PrintFormatBuilder {
        this.changeMode(Print_Mode.DEFAULT)
        this.setAlignment(Print_Align.CENTER)
        this.printTextAsLines(text, false)
        return this
    }

    override fun left(text: String): PrintFormatBuilder {
        this.setAlignment(Print_Align.LEFT)
        this.printTextAsLines(text, false)
        return this
    }

    override fun nl(): PrintFormatBuilder {
        super.nl()
        // Clear current style
        //this.changeMode(Print_Mode.DEFAULT); NOT NEEDED AS EACH LINE RESETS STYLE
        /* Move to next line */
        super.getStringBuilder().append(0x0A.toChar())
        return this
    }


    override fun right(text: String): PrintFormatBuilder {
        this.changeMode(Print_Mode.DEFAULT)
        this.setAlignment(Print_Align.RIGHT)
        this.printTextAsLines(text, false)
        return this
    }


    override fun insertCenter(text: String, position: Int): PrintFormatBuilder {
        this.setAlignment(Print_Align.LEFT)
        this.changeMode(Print_Mode.DEFAULT)
        return super.insertCenter(text, position)
    }


    override fun insertLeft(text: String, position_left: Int): PrintFormatBuilder {
        this.setAlignment(Print_Align.LEFT)
        this.changeMode(Print_Mode.DEFAULT)
        return super.insertLeft(text, position_left)
    }

    override fun insertRight(text: String, position_right: Int): PrintFormatBuilder {
        this.setAlignment(Print_Align.LEFT)
        this.changeMode(Print_Mode.DEFAULT)
        return super.insertRight(text, position_right)
    }

    /**
     * Splits the text specified into multiple lines.
     * @param text
     */
    private fun printTextAsLines(text: String, double_width: Boolean) {
        var text = text
        text = super.fixCharacters(text)
        val line = PrintStringUtil.getLines(text, if (double_width) getLineWidth() / 2 else getLineWidth(), " ")
        for (i in line.indices) {
            super.getStringBuilder().append(line[i])
            this.nl()
        }
    }

    /**
     * Changes the alignment of printing
     *
     * @param alignment Byte representing the new aligmnet style.
     */
    protected fun setAlignment(alignment: Byte) {
        /* Only change the alignment if it REALY changed */
        if (this.current_alignment != alignment) {
            this.current_alignment = alignment
            super.getStringBuilder().append(0x1B.toChar()).append(0x61.toChar()).append(alignment.toChar())
        }
    }

    /**
     * Sets the font of the printer
     *
     * @param font Byte representing the new font.
     */
    protected fun setFont(font: Byte) {
        super.getStringBuilder().append(0x1B.toChar()).append(0x4D.toChar()).append(font.toChar())
    }

    /**
     * Changes the printing mode of the printer
     *
     * @param mode Byte representing the new print mode.
     */
    protected fun changeMode(mode: Byte) {
        // Only change the mode if it REALY changed
        if (this.current_mode != mode) {
            this.current_mode = mode
            super.getStringBuilder().append(0x1B.toChar()).append(0x21.toChar()).append(mode.toChar())
        }
    }

}
