package coza.trojanc.receipt.template.process.fields;

/**
 * Created by Charl-PC on 2017-06-15.
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
