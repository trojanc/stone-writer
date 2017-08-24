package coza.trojanc.receipt.shared;

/**
 * @author Charl Thiem
 */
public enum Align {
	LEFT("left"),
	RIGHT("right"),
	CENTER("center");


	String value;
	Align(String value){
		this.value = value;
	}
}
