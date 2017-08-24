package coza.trojanc.receipt.template.process.fields;

/**
 * @author Charl Thiem
 */
public class ProcessedFillLine implements ProcessedLineItem {

	private char character = ' ';
	public ProcessedFillLine(){
	}

	public ProcessedFillLine(char character){
		this.character = character;
	}

	public char getCharacter() {
		return character;
	}
}
