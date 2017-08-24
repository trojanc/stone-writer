package coza.trojanc.receipt.template.fields;

/**
 * A class representing simple static text to be printed.
 * @author Charl Thiem
 */
public class DynamicText extends AbstractTextItem{


	private String contextKey;


	/**
	 * Text constructor.
	 */
	public DynamicText() {
		super();
	}

	public DynamicText(AbstractTextItem source){
		super(source);
	}

	public String getContextKey() {
		return contextKey;
	}

	public void setContextKey(String contextKey) {
		this.contextKey = contextKey;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("DynamicText[")
			.append("key=").append(this.contextKey)
			.append(",align=").append(this.getAlignment())
			.append(",offset=").append(this.getOffset())
			.append("]")
			.toString();
	}
}
