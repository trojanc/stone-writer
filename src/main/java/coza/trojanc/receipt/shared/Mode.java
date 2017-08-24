package coza.trojanc.receipt.shared;

/**
 * @author Charl Thiem
 */
public enum Mode {
	NORMAL("normal"),
	BOLD("bold");


	private String value;
	Mode(String value){
		this.value = value;
	}
}
