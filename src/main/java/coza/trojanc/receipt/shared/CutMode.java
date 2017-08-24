package coza.trojanc.receipt.shared;

/**
 * @author Charl Thiem
 */
public enum CutMode {
	FULL("full"),
	PARTIAL("partial");


	private String value;
	CutMode(String value){
		this.value = value;
	}
}
