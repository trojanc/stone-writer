package coza.trojanc.receipt.template.fields

/**
 * A class representing simple static text to be printed.
 * @author Charl Thiem
 */
class DynamicText : AbstractTextItem {


    private var contextKey: String? = null


    /**
     * Text constructor.
     */
    constructor(): super() {
    }

    constructor(source: AbstractTextItem): super(source) {
    }

    fun getContextKey(): String? {
        return contextKey
    }

    fun setContextKey(contextKey: String) {
        this.contextKey = contextKey
    }

    override fun toString(): String {
        return StringBuilder()
                .append("DynamicText[")
                .append("key=").append(this.contextKey)
                .append(",align=").append(this.getAlignment())
                .append(",offset=").append(this.getOffset())
                .append("]")
                .toString()
    }
}
