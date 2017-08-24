package coza.trojanc.receipt.context

/**
 * An interface defining the basic functions required for a `ContextVariable` implementation.
 * A context variable is a variable that will be resolved by using the `expression` and input variables.
 * @author Charl Thiem
 */
interface ContextVariable {

    /**
     * Gets type.
     *
     * @return the type
     */
    fun getType(): DynamicType

    /**
     * Gets key.
     *
     * @return the key
     */
    fun getKey(): String

    /**
     * Gets formatting.
     *
     * @return the formatting
     */
    fun getFormatting(): String?

    /**
     * Gets expression.
     *
     * @return the expression
     */
    fun getExpression(): String
}
