package coza.trojanc.receipt.template.process.fields

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.shared.Mode

/**
 * @author Charl Thiem
 */
class ProcessedText {
    /** global printing mode bitmap - product of MODE_* constants above ORed together  */
    private var mode = Mode.NORMAL

    /** global alignment  */
    private var alignment = Align.LEFT

    private var text: String = "";

    private var offset: Int? = null

    fun getMode(): Mode {
        return mode
    }

    fun setMode(mode: Mode) {
        this.mode = mode
    }

    fun getAlignment(): Align {
        return alignment
    }

    fun setAlignment(alignment: Align) {
        this.alignment = alignment
    }

    fun getText(): String {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getOffset(): Int? {
        return offset
    }

    fun setOffset(offset: Int?) {
        this.offset = offset
    }
}
