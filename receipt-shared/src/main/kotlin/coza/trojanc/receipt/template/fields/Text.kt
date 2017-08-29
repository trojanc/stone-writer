package coza.trojanc.receipt.template.fields

/**
 * A class representing simple static text to be printed.
 * @author Charl Thiem
 */
class Text: AbstractTextItem{

    /**
     * Text to be printed
     */
    private var text: String? = null

    /**
     * Text constructor.
     */
    constructor() : super() {
    }

    constructor(source: AbstractTextItem): super(source) {
    }


    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    override fun toString(): String {
        return StringBuilder()
                .append("Text[")
                .append("text=").append(this.text)
                .append(",align=").append(this.getAlignment())
                .append(",offset=").append(this.getOffset())
                .append("]")
                .toString()
    }
}
