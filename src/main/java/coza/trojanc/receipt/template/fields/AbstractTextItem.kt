package coza.trojanc.receipt.template.fields

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.shared.Mode

/**
 * @author Charl Thiem
 */
abstract class AbstractTextItem : TemplateTextItem {


    /** global printing mode bitmap - product of MODE_* constants above ORed together  */
    override var mode = Mode.NORMAL

    /** global alignment  */
    override var alignment = Align.LEFT

    override var offset: Int? = null

    constructor() {}

    constructor(source: AbstractTextItem) {
        this.mode = source.mode
        this.offset = source.offset
        this.alignment = source.alignment
    }
}
