package coza.trojanc.stonewriter.template.fields;

/**
 * A class representing simple static text to be printed
 */
public class Text extends AbstractTextItem{

	/**
	 * Text to be printed
	 */
	private String text;
	/**
	 * Text constructor.
	 */ 
	public Text() {
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
