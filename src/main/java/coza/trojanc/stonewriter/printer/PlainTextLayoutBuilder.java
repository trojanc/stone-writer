package coza.trojanc.stonewriter.printer;

import coza.trojanc.stonewriter.processor.layout.TextLayoutBuilder;
import coza.trojanc.stonewriter.shared.PrintStringUtil;

public class PlainTextLayoutBuilder implements TextLayoutBuilder {
	
	private static final String emptyString = "                                                  "; // 50
	/** Defines the prompt lines */
	protected StringBuilder builder;
	/** An array of chars to the width of the line */
	protected char[] charBuffer;
	/** The default text in a char buffer when starting a line */
	protected char[] defaultCharBuffer;
	/** The display width */
	private int width;
	/** The display height */
	private int height;		// TODO this is never looked at, the builder should  not allow more than x lines!!
	/** flag indicating that the char buffer has been used */
	private boolean charBufferInUse;
	/** append index */
	private int appendIndex;
	/** Flag if busy with first line */
	private boolean firstLine = true;
	
	public static final String NEW_LINE = "\n";
	
	/**
	 * 
	 * Creates a new instance of a(n) <code>PlainTextLayoutBuilder</code>.
	 * @param width Maximum number of characters in a line
	 * @param height Maximum number of lines.
	 */
	public PlainTextLayoutBuilder(int width, int height) {
		this(width, height, null);
	}
	
	/**
	 * Creates a new instance of a <code>PlainTextLayoutBuilder</code>.
	 * @param width Maximum number of characters in a line
	 * @param height Maximum number of lines.
	 * @param defaultCharBuffer Default character buffer.
	 */
	public PlainTextLayoutBuilder(int width, int height, char[] defaultCharBuffer) {
		
		if (width == 0 || height == 0){
			throw new IllegalArgumentException("Invalid size specified (" + width + "x" + height+ ")");
		}
		
		this.builder = new StringBuilder();
		this.width = width;
		this.height= height;
		this.appendIndex = 0;
		if (defaultCharBuffer == null) {
			this.defaultCharBuffer = emptyString.substring(0, width).toCharArray();
		}
		else {
			this.defaultCharBuffer = defaultCharBuffer;
		}
		this.charBuffer = new char[this.defaultCharBuffer.length];
		this.resetCharBuffer();
		
		if (this.defaultCharBuffer.length != this.width) {
			throw new IllegalArgumentException("Incompatible default char buffer and display width");
		}
	}
	

	/**
	 * Completes the line we were busy building in the char buffer and returns true.
	 * If we were not busy building a line it returns false.
	 * 
	 * @return
	 */
	private boolean completeCharBuffer() {
		if (this.charBufferInUse) {
			if (!this.firstLine) {
				this.builder.append(NEW_LINE);
			}
			else {
				this.firstLine = false;
			}
			this.builder.append(new String(this.charBuffer));
			this.appendIndex = 0;
			this.resetCharBuffer();
			return true;
		}
		else
			return false;
	}
	/**
	 * Reset the char buffer to the default value
	 */
	protected void resetCharBuffer() {
		System.arraycopy(this.defaultCharBuffer, 0, this.charBuffer, 0, this.width);
		this.charBufferInUse = false;
	}
	
	
	public TextLayoutBuilder append(String text) {
		if (this.charBufferInUse){
			PrintStringUtil.insertLeftAligned(this.charBuffer, this.appendIndex, text, width);
			this.appendIndex += text.length();
		} else {
			this.builder.append(text);
		}
		return this;
	}

	public TextLayoutBuilder append(long number) {
		this.append(Long.toString(number));
		return this;
	}

	

	public TextLayoutBuilder left(String text) {
		//complete char buffer line?
		this.completeCharBuffer();
		
		//add all lines left aligned
		int w = this.width;
		
		String[] strs = PrintStringUtil.getLines(text, w, "\n");
		for (String value : strs) {
			this.nl();
			this.builder.append(value);
		}
		
		return this;
	}

	public TextLayoutBuilder center(String text) {
		//complete char buffer line?
		this.completeCharBuffer();
		
		int w = this.width;

		//add all lines center aligned
		String[] strs = PrintStringUtil.getLines(text, w, "\n");
		int pos = this.width / 2;
		for (String value : strs) {
			this.nl();
			PrintStringUtil.insertCenterAligned(this.charBuffer, pos, value, width);
			this.builder.append(new String(this.charBuffer));
			this.resetCharBuffer();
		}
		
		return this;
	}


	public TextLayoutBuilder right(String text) {
		//complete char buffer line?
		this.completeCharBuffer();
			
		//add all lines right aligned
		String[] strs = PrintStringUtil.getLines(text, width, "\n");
		int pos = width;
		for (String value : strs) {
			PrintStringUtil.insertRightAligned(this.charBuffer, pos, value, width);
			this.nl();
			this.builder.append(new String(this.charBuffer));
			this.resetCharBuffer();
		}
		
		return this;
	}

	public TextLayoutBuilder insertLeft(String text, int position_left) {
		this.charBufferInUse = true;
		if (position_left < 0) {
			PrintStringUtil.insertLeftAligned(this.charBuffer, width + position_left - 1, text, width);
		}
		else {
			PrintStringUtil.insertLeftAligned(this.charBuffer, position_left, text, width);
		}
		this.appendIndex += text.length();				//DOTO FIX BUG
		return this;
	}


	public TextLayoutBuilder insertCenter(String text, int position) {
		this.charBufferInUse = true;
		if (position < 0) {
			PrintStringUtil.insertCenterAligned(this.charBuffer, width + position - 1, text, width);
		}
		else {
			PrintStringUtil.insertCenterAligned(this.charBuffer, position, text, width);
		}
		this.appendIndex += (text.length() / 2);		//DOTO FIX BUG
		return this;
	}

	public TextLayoutBuilder insertRight(String text, int position_right) {
		this.charBufferInUse = true;
		if (position_right < 0) {
			PrintStringUtil.insertRightAligned(this.charBuffer, width + position_right - 1, text, width);
		}
		else {
			PrintStringUtil.insertRightAligned(this.charBuffer, position_right, text, width);
		}
		this.appendIndex = this.width - position_right - 1;		//DOTO FIX BUG
		return this;	
	}
	

	public TextLayoutBuilder insertLeft(long number, int position_left) {
		this.insertLeft(Long.toString(number), position_left);
		return this;
	}
	

	public TextLayoutBuilder insertCenter(long number, int position) {
		this.insertCenter(Long.toString(number), position);
		return this;
	}


	public TextLayoutBuilder insertRight(long number, int position_right) {
		this.insertRight(Long.toString(number), position_right);
		return this;
	}



	public TextLayoutBuilder nl() {
		//complete char buffer line?
		if (!this.completeCharBuffer()) {
			if (!this.firstLine) {
				this.builder.append(NEW_LINE);
			}
		}

		this.firstLine = false;
		return this;
	}

	public TextLayoutBuilder nl(int num_line) {
		//complete char buffer line?
		this.completeCharBuffer();

		if (num_line <= 0)
			return this;
		
		//add lines
		for (int i = 0; i < num_line; i++) {
			this.builder.append(NEW_LINE);
		}
		
		return this;
	}

	public byte[] toBytes() {
		return this.toString().getBytes();
	}

	public char[] toChars() {
		return this.toString().toCharArray();
	}

	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		return this.builder.toString();
	}

	/**
	 * @return the width
	 */
	public int getLineWidth() {
		return this.width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Unit test method
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		String ruler = "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"; 
		String defaultLine; 
		char[] defaultCharBuffer;
		
		
		
		
		/**
		 * Test left(), center() and right()
		 * Supports left center and right align  
		 */
		//change the line with to test different widths
		int line_width = 20;
		int height = 20;
		int word_min_length = 3;
		int word_max_length = 10;
		defaultLine = "                                                                                                                                            "; 
		defaultCharBuffer = defaultLine.substring(0, line_width).toCharArray();
		PlainTextLayoutBuilder plb = new PlainTextLayoutBuilder(line_width, height, defaultCharBuffer);
		String text;
		int position; 
		System.out.println();
		System.out.println();
		System.out.println("Test PromptLayoutBuilder with left center and right align supported");
		//test left right and center
		for (int i = 0; i < 5; i++) {
			text = "Test PromptLayoutBuilder with left center and right align supported";
			//position = (int)(Math.random() * line_width);
			double r = Math.random();
			if (r < 0.33) {
				System.out.println("left(\"" + text + "\")");
				plb.left(text);
			} else if (r < 0.66) {
				System.out.println("center(\"" + text + "\")");
				plb.center(text);
			} else {
				System.out.println("right(\"" + text + "\")");
				plb.right(text);
			}
		}
		plb.nl();
		
		//test insertLeft insertRight and insertCenter
		for (int i = 0; i < 5; i++) {
			text = "Test PromptLayoutBuilder with left center and right align supported";
			position = (int)(Math.random() * line_width);
			double r = Math.random();
			if (r < 0.33) {
				System.out.println("insertLeft(\"" + text + "\", " + position + ")");
				plb.insertLeft(text, position);
			} else if (r < 0.66) {
				System.out.println("insertCenter(\"" + text + "\", " + position + ")");
				plb.insertCenter(text, position);
			} else {
				System.out.println("insertRight(\"" + text + "\", " + position + ")");
				plb.insertRight(text, position);
			}
		}
		plb.nl();
		
		System.out.println("Result:");
		System.out.println(plb.toString());
		


		/**
		 * Test left(), center() and right()
		 * Supports left center and right align  
		 */
		defaultLine = ".                                                                                                                                           "; 
		defaultCharBuffer = defaultLine.substring(0, line_width).toCharArray();
		plb = new PlainTextLayoutBuilder(line_width, height, defaultCharBuffer);
		System.out.println();
		System.out.println();
		System.out.println("Test PromptLayoutBuilder with left center and right align NOT supported");
		//test left right and center
		for (int i = 0; i < 5; i++) {
			text = "Test PromptLayoutBuilder with left center and right align NOT supported";
			//position = (int)(Math.random() * line_width);
			double r = Math.random();
			if (r < 0.33) {
				System.out.println("left(\"" + text + "\")");
				plb.left(text);
			} else if (r < 0.66) {
				System.out.println("center(\"" + text + "\")");
				plb.center(text);
			} else {
				System.out.println("right(\"" + text + "\")");
				plb.right(text);
			}
		}
		plb.nl();

		//test insertLeft insertRight and insertCenter
		for (int i = 0; i < 3; i++) {
			text ="Test PromptLayoutBuilder with left center and right align NOT supported";
			position = (int)(Math.random() * line_width);
			double r = Math.random();
			if (r < 0.33) {
				System.out.println("insertLeft(\"" + text + "\", " + position + ")");
				plb.insertLeft(text, position);
			} else if (r < 0.66) {
				System.out.println("insertCenter(\"" + text + "\", " + position + ")");
				plb.insertCenter(text, position);
			} else {
				System.out.println("insertRight(\"" + text + "\", " + position + ")");
				plb.insertRight(text, position);
			}
		}
		plb.nl();

		System.out.println("Result:");
		System.out.println(plb.toString());

		/**
		 * Test a fake display layout
		 */
		defaultLine = ".                                                                                                                                           "; 
		defaultCharBuffer = defaultLine.substring(0, 40).toCharArray();
		plb = new PlainTextLayoutBuilder(40, 40, defaultCharBuffer);
		System.out.println();
		System.out.println();
		System.out.println("Test PromptLayoutBuilder with left center and right align NOT supported and fake display text");

		plb.center("Hello World");
		plb.center("Welcome");
		plb.insertLeft("t1_2", 2).insertCenter("centered_13", 13).insertRight("right_3", 3).nl();
		plb.insertLeft("t1_1", 1).insertCenter("centered_10", 10).insertRight("right_1", 1).nl();
		plb.left("blah1 blah2 blah3 blah4 blah5 blah6 blah7 blah8 blah9 blah0 blah1 blah2 blah3 blah4 blah5 blah6 blah7 blah8 blah9 blah0");
		plb.center("blah1 blah2 blah3 blah4 blah5 blah6 blah7 blah8 blah9 blah0 blah1 blah2 blah3 blah4 blah5 blah6 blah7 blah8 blah9 blah0");
		plb.right("blah1 blah2 blah3 blah4 blah5 blah6 blah7 blah8 blah9 blah0 blah1 blah2 blah3 blah4 blah5 blah6 blah7 blah8 blah9 blah0");
		
		plb = new PlainTextLayoutBuilder(line_width, height, null);
		plb.left("Limits:");
		plb.center("Maximum Quantity");
		plb.center(new StringBuilder().append("1000.00").append(" ").append("L").toString());
		System.out.println("Formatted message:\n>" + plb.toString() + "<");
		
		
		plb = new PlainTextLayoutBuilder(line_width, height, null);
		plb.append("\\bn");
		plb.center("Goods total:");
		plb.center("R 1,234.56");
		plb.center("Max cash amount:");
		plb.center("R 10,000.00");
		System.out.println("Formatted message:\n>" + plb.toString() + "<");
		
//		System.out.println("Result:");
//		System.out.println(plb.toString());
	}


	public TextLayoutBuilder insertChar(char chr, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public TextLayoutBuilder insertRight(String text) {
		return this.insertRight(text, this.getLineWidth()-1);
	}

	public TextLayoutBuilder insertRight(long number) {
		return this.insertRight(number, this.getLineWidth()-1);
	}
}
