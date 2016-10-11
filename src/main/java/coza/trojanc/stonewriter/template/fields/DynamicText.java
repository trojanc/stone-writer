package coza.trojanc.stonewriter.template.fields;

/**
 * A class representing simple static text to be printed
 */
public class DynamicText extends AbstractTextItem{


	/**
	 * Text to be printed
	 */
	private String expression;
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
}
