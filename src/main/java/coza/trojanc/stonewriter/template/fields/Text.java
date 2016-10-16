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
		super();
	}

	public Text(AbstractTextItem source){
		super(source);
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("Text[")
			.append("text=").append(this.text)
			.append(",align=").append(this.getAlignment())
			.append(",offset=").append(this.getOffset())
			.append("]")
			.toString();
	}
}
