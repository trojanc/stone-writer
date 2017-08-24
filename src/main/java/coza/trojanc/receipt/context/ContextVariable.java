package coza.trojanc.receipt.context;

/**
 * An interface defining the basic functions required for a <code>ContextVariable</code> implementation.
 * A context variable is a variable that will be resolved by using the <code>expression</code> and input variables.
 * @author Charl Thiem
 */
public interface ContextVariable {

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	DynamicType getType();

	/**
	 * Gets key.
	 *
	 * @return the key
	 */
	String getKey();

	/**
	 * Gets formatting.
	 *
	 * @return the formatting
	 */
	String getFormatting();

	/**
	 * Gets expression.
	 *
	 * @return the expression
	 */
	String getExpression();
}
