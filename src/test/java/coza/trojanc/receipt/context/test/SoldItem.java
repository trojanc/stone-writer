package coza.trojanc.receipt.context.test;

/**
 * @author Charl Thiem
 */
public class SoldItem {

	public static final String SOLD_ITEM1_NAME = "Hake parsel";
	public static final double SOLD_ITEM1_VALUE = 200.9;
	public static final String SOLD_ITEM2_NAME = "Large Chips";
	public static final double SOLD_ITEM2_VALUE = 30.59;

	private final String name;
	private final double value;

	public SoldItem(String name, double value){
		this.name = name;
		this.value = value;
	}

	public String getName(){
		return name;
	}

	public double getValue(){
		return value;
	}
}
