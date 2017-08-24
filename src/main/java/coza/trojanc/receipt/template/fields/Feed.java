package coza.trojanc.receipt.template.fields;


/**
 * Text - Defines a printer feed element.
 * @author Charl Thiem
 */
public class Feed implements TemplateLine {

	//number of lines to feed
	private int lines;
	
	/**
	 * Feed constructor.
	 */ 
	public Feed() {
		this.lines = 3;
	}
	
	public Feed(int amount) {
		this.lines = amount;
	}
	
	public int getFeedAmount() {
		return this.lines;
	}

	public void setFeedAmount(int amount){
		this.lines = amount;
	}

}
