package coza.trojanc.stonewriter.processor.layout;

/**
 * A class designed to test a layout builder
 */
public class LayoutBuilderTester {

	/**
	 * Runs a number of commands on a <code>PrintTextLayoutBuilder</code> and prints them to
	 * the standard output stream.
	 * 
	 * @param builder
	 */
	public static final PrintTextLayoutBuilder testPrintBuilder(PrintTextLayoutBuilder builder) {



		builder.left("Line Width: " + builder.getLineWidth());
		builder.line();

		builder.left("left");
		builder.right("right");
		builder.center("center");
		builder.line();

		builder.leftB("left bold");
		builder.line();

		builder.centerB("center bold");
		builder.line();

		builder.lineDouble();
		builder.ff();


		return builder;
	}
}
