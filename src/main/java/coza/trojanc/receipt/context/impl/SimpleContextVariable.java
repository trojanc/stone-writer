package coza.trojanc.receipt.context.impl;

import coza.trojanc.receipt.context.ContextVariable;
import coza.trojanc.receipt.context.DynamicType;

/**
 * A simple implementation of a {@link ContextVariable}.
 * @author Charl Thiem
 */
public class SimpleContextVariable implements ContextVariable {

	/**
	 * Text to be printed
	 */
	private String expression;

	/**
	 * The Dynamic type for the field, by default it is a {@link DynamicType#String}
	 */
	private DynamicType type = DynamicType.String;

	/**
	 * Formatting to apply to value
	 * The following fomatters will be applied to each of these types
	 * <table>
	 *    <tr>
	 *        <th>DynamicType</th>
	 *        <th>Class</th>
	 *     </tr>
	 *     <tr>
	 *         <td>Date</td>
	 *         <td>SimpleDateFormat</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Number</td>
	 *         <td>String.format()</td>
	 *     </tr>
	 *     <tr>
	 *         <td>Decimal</td>
	 *         <td>DecimalFormat</td>
	 *     </tr>
	 * </table>
	 */
	private String formatting;

	/**
	 * The key where this item will be available in the {@link coza.trojanc.receipt.context.ContextMap} when the item
	 * is resolved.
	 */
	private String key;

	/**
	 * Creates a new instance of a {@link SimpleContextVariable}.
	 */
	public SimpleContextVariable(){
	}

	/**
	 * Creates a new instance of a {@link SimpleContextVariable}.
	 * @param key The key where this item will be available in the {@link coza.trojanc.receipt.context.ContextMap} when the item
	 * is resolved.
	 * @param type
	 * @param expression
	 * @param formatting
	 */
	public SimpleContextVariable(String key, DynamicType type, String expression, String formatting) {
		this.expression = expression;
		this.type = type;
		this.formatting = formatting;
		this.key = key;
	}

	@Override
	public DynamicType getType() {
		return this.type;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public String getFormatting() {
		return this.formatting;
	}

	@Override
	public String getExpression() {
		return this.expression;
	}

	/**
	 * Sets expression used to resolve the variable from input variables.
	 *
	 * @param expression the expression
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * Sets type.
	 *
	 * @param type the type
	 */
	public void setType(DynamicType type) {
		this.type = type;
	}

	/**
	 * Sets formatting.
	 *
	 * @param formatting the formatting
	 */
	public void setFormatting(String formatting) {
		this.formatting = formatting;
	}

	/**
	 * Sets key.
	 *
	 * @param key the key
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
