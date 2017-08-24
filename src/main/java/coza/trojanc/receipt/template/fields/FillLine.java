package coza.trojanc.receipt.template.fields;

/**
 * A line that is filled with the specified character.
 * @author Charl Thiem
 */
public class FillLine implements TemplateLine {

	private char character = ' ';

	public FillLine(){}
	public FillLine(char character){
		this.character = character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public char getCharacter() {
		return character;
	}
}
