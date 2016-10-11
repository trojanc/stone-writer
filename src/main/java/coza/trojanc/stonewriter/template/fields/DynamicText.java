package coza.trojanc.stonewriter.template.fields;

import coza.trojanc.stonewriter.shared.DynamicType;

/**
 * A class representing simple static text to be printed
 */
public class DynamicText extends AbstractTextItem{


	/**
	 * Text to be printed
	 */
	private String expression;

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
	 * Text constructor.
	 */
	public DynamicText() {
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public DynamicType getType() {
		return type;
	}

	public void setType(DynamicType type) {
		this.type = type;
	}

	public String getFormatting() {
		return formatting;
	}

	public void setFormatting(String formatting) {
		this.formatting = formatting;
	}
}
