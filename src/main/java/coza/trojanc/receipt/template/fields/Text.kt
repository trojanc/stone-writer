package coza.trojanc.receipt.template.fields

/**
 * A class representing simple static text to be printed.
 * @author Charl Thiem
 */
class Text : AbstractTextItem {

    /**
     * Text to be printed
     */
    var text: String? = null

    /**
     * Text constructor.
     */
    constructor() : super() {}

    constructor(source: AbstractTextItem) : super(source) {}

    override fun toString(): String {
        return StringBuilder()
                .append("Text[")
                .append("text=").append(this.text)
                .append(",align=").append(this.alignment)
                .append(",offset=").append(this.offset)
                .append("]")
                .toString()
    }
}
