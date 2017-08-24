package coza.trojanc.receipt.template.process.fields

import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.shared.Mode

/**
 * @author Charl Thiem
 */
class ProcessedText {
    /** global printing mode bitmap - product of MODE_* constants above ORed together  */
    var mode = Mode.NORMAL

    /** global alignment  */
    var alignment = Align.LEFT

    var text: String? = null

    var offset: Int? = null
}
