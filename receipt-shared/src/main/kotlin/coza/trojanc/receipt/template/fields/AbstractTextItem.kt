package coza.trojanc.receipt.template.fields

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.shared.Mode

/**
 * @author Charl Thiem
 */
abstract class AbstractTextItem : TemplateTextItem {


    /** global printing mode bitmap - product of MODE_* constants above ORed together  */
    private var mode = Mode.NORMAL

    /** global alignment  */
    private var alignment = Align.LEFT

    private var offset: Int? = null

    constructor(){}

    constructor(source: AbstractTextItem){
        this.mode = source.mode
        this.offset = source.offset
        this.alignment = source.alignment
    }


    override fun getMode(): Mode {
        return mode
    }

    fun setMode(mode: Mode) {
        this.mode = mode
    }

    override fun getAlignment(): Align {
        return alignment
    }

    fun setAlignment(alignment: Align) {
        this.alignment = alignment
    }

    fun setOffset(offset: Int?) {
        this.offset = offset
    }

    override fun getOffset(): Int? {
        return offset
    }
}
