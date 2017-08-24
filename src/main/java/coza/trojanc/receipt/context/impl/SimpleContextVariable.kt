package coza.trojanc.receipt.context.impl

import coza.trojanc.receipt.context.ContextVariable
import coza.trojanc.receipt.context.DynamicType

/**
 * A simple implementation of a [ContextVariable].
 * @author Charl Thiem
 */
class SimpleContextVariable : ContextVariable {
    /**
     * Text to be printed
     */
    private var expression: String = ""

    /**
     * The Dynamic type for the field, by default it is a [DynamicType.String]
     */
    private var type = DynamicType.String

    /**
     * Formatting to apply to value
     * The following fomatters will be applied to each of these types
     * <table>
     * <tr>
     * <th>DynamicType</th>
     * <th>Class</th>
    </tr> *
     * <tr>
     * <td>Date</td>
     * <td>SimpleDateFormat</td>
    </tr> *
     * <tr>
     * <td>Number</td>
     * <td>String.format()</td>
    </tr> *
     * <tr>
     * <td>Decimal</td>
     * <td>DecimalFormat</td>
    </tr> *
    </table> *
     */
    private var formatting: String? = null

    /**
     * The key where this item will be available in the [coza.trojanc.receipt.context.ContextMap] when the item
     * is resolved.
     */
    private var key: String = ""


    /**
     * Creates a new instance of a [SimpleContextVariable].
     * @param key The key where this item will be available in the [coza.trojanc.receipt.context.ContextMap] when the item
     * is resolved.
     * @param type
     * @param expression
     * @param formatting
     */
    constructor() {
    }

    constructor(key: String, type: DynamicType, expression: String, formatting: String){
        this.expression = expression
        this.type = type
        this.formatting = formatting
        this.key = key
    }

    override fun getType(): DynamicType {
        return this.type
    }

    override fun getKey(): String {
        return this.key
    }

    override fun getFormatting(): String? {
        return this.formatting
    }

    override fun getExpression(): String {
        return this.expression
    }

    /**
     * Sets expression used to resolve the variable from input variables.
     *
     * @param expression the expression
     */
    fun setExpression(expression: String) {
        this.expression = expression
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    fun setType(type: DynamicType) {
        this.type = type
    }

    /**
     * Sets formatting.
     *
     * @param formatting the formatting
     */
    fun setFormatting(formatting: String?) {
        this.formatting = formatting
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    fun setKey(key: String) {
        this.key = key
    }
}
