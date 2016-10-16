package coza.trojanc.stonewriter.template.fields;

/**
 * A class representing simple static text to be printed
 */
public class DynamicText extends AbstractTextItem{


	private String contextKey;


	/**
	 * Text constructor.
	 */
	public DynamicText() {
	}


	public String getContextKey() {
		return contextKey;
	}

	public void setContextKey(String contextKey) {
		this.contextKey = contextKey;
	}
}
