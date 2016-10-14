package coza.trojanc.stonewriter.processor.layout;

/**
 * An interface for styling printing.
 * @author Charl Thiem
 */
public interface PrintTextLayoutBuilder extends TextLayoutBuilder {

	/**
	 * Initialises the printer.
	 * @return
	 */
	PrintTextLayoutBuilder initialize();

	/**
	 * Appends a left aligned line in bold.
	 * @param text Text to add in this line.
	 * @return
	 */
	PrintTextLayoutBuilder leftB(String text);
	
	
	/**
	 * Adds a center aligned line in bold.
	 * @param text Text to add in this line.
	 * @return
	 */
	TextLayoutBuilder centerB(String text);

	
	/**
	 * Performs a form feed.
	 * @return
	 */
	PrintTextLayoutBuilder ff();
	

	/**
	 * Draws a double line over the width of the slip 
	 * @return
	 */
	PrintTextLayoutBuilder lineDouble();

	/**
	 * Draws a normal width line over the width of the slip
	 * @return
	 */
	PrintTextLayoutBuilder line();
	
	/**
	 * Draws a 'line' over the width of the slip using the specific char 
	 * @return
	 */
	PrintTextLayoutBuilder line(char chr);
	


}
