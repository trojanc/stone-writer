package coza.trojanc.receipt.template.fields;

/**
 * A line that is filled with the specified character
 */
public class FillLine implements TemplateLine {

	private final char character;

	public FillLine(char character){
		this.character = character;
	}

	public char getCharacter() {
		return character;
	}
}
