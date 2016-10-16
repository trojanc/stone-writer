package coza.trojanc.stonewriter.template.fields;


/**
 * Text - Defines a printer feed element.   
 */
public class Feed implements TemplateLine {

	//number of lines to feed
	private int lines;
	
	/**
	 * Feed constructor.
	 */ 
	public Feed() {
		this.lines = 1;
	}
	
	public Feed(int amount) {
		this.lines = amount;
	}
	
	public int getFeedAmount() {
		return this.lines;
	}

}
