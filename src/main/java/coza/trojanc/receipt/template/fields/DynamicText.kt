package coza.trojanc.receipt.template.fields

/**
 * A class representing simple static text to be printed.
 * @author Charl Thiem
 */
class DynamicText : AbstractTextItem {


    var contextKey: String? = null


    /**
     * Text constructor.
     */
    constructor() : super() {}

    constructor(source: AbstractTextItem) : super(source) {}

    override fun toString(): String {
        return StringBuilder()
                .append("DynamicText[")
                .append("key=").append(this.contextKey)
                .append(",align=").append(this.alignment)
                .append(",offset=").append(this.offset)
                .append("]")
                .toString()
    }
}
