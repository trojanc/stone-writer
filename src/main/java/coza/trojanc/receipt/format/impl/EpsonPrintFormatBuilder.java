package coza.trojanc.receipt.format.impl;

import coza.trojanc.receipt.format.AbstractPlainTextFormatBuilder;
import coza.trojanc.receipt.format.PrintFormatBuilder;
import coza.trojanc.receipt.shared.PrintStringUtil;

/**
 * The type Epson print format builder.
 * @author Charl Thiem
 */
public class EpsonPrintFormatBuilder extends AbstractPlainTextFormatBuilder {

	/**
	 * Paper sensors<br>
	 * NOTE: This can not be used inline to apply it to the printer
	 */
	public static class Sensors {
		/**
		 * The constant ROLL_NEAR_END.
		 */
		public static final byte	ROLL_NEAR_END	= 0x01;
		/**
		 * The constant ROLL_NEAR_END2.
		 */
		public static final byte	ROLL_NEAR_END2	= 0x02;
		/**
		 * The constant ROLL_END.
		 */
		public static final byte	ROLL_END		= 0x04;
		/**
		 * The constant ROLL_END2.
		 */
		public static final byte	ROLL_END2		= 0x08;
	}

	/**
	 * Modes of underlining<br>
	 * NOTE: Adding this inline will not change the undeline mode, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Undeline_Mode {
		/**
		 * The constant OFF.
		 */
		public static final byte	OFF		= 0x00;
		/**
		 * The constant ONE_DOT.
		 */
		public static final byte	ONE_DOT	= 0x01;
		/**
		 * The constant TWO_DOT.
		 */
		public static final byte	TWO_DOT	= 0x02;
	}

	/**
	 * Styles of cutting the paper
	 * NOTE: Adding this inline will not perform a papper cut, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Cut_Style {
		/**
		 * The constant FULL.
		 */
		public static final byte	FULL		= 0x00;
		/**
		 * The constant PARTIALLY.
		 */
		public static final byte	PARTIALLY	= 0x01;
	}

	/**
	 * Printing modes
	 * NOTE: Adding this inline will not change the print mode mode, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Print_Mode {
		/**
		 * The constant DEFAULT.
		 */
		public static final byte	DEFAULT			= 0x00;
		/**
		 * The constant EMPH_ON.
		 */
		public static final byte	EMPH_ON			= 0x08;
		/**
		 * The constant DOUBLE_HEIGHT.
		 */
		public static final byte	DOUBLE_HEIGHT	= 0x10;
		/**
		 * The constant DOUBLE_WIDTH.
		 */
		public static final byte	DOUBLE_WIDTH	= 0x20;
		/**
		 * The constant UNDERLINE_ON.
		 */
		public static final byte	UNDERLINE_ON	= (byte) 0x80;
	}

	/**
	 * Printing fonts
	 * NOTE: Adding this inline will not change the printer font, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Print_Font {
		/**
		 * The constant FONT_A.
		 */
		public static final byte	FONT_A	= 0x00;
		/**
		 * The constant FONT_B.
		 */
		public static final byte	FONT_B	= 0x01;
	}

	/**
	 * Printing alignments
	 * NOTE: Adding this inline will not change the printer alignment, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Print_Align {
		/**
		 * The constant LEFT.
		 */
		public static final byte	LEFT	= 0x00;
		/**
		 * The constant CENTER.
		 */
		public static final byte	CENTER	= 0x01;
		/**
		 * The constant RIGHT.
		 */
		public static final byte	RIGHT	= 0x02;
	}
	
	
	/** Alignment printer is currently in */
	private byte current_alignment = Print_Align.LEFT;
	/** Print mode printer is currently in */
	private byte current_mode = Print_Mode.DEFAULT;

	/**
	 * Creates a new instance of a <code>EpsonPrintBuilder</code>
	 */
	public EpsonPrintFormatBuilder(){
		super(40);
	}

	/**
	 * Creates a new instances of <code>EpsonPrintBuilder</code> setting
	 * widrth of the paper.
	 *
	 * @param line_width the line width
	 */
	public EpsonPrintFormatBuilder(int line_width) {
		super(line_width);
	}


	public PrintFormatBuilder initialize(){
		super.initialize();
		super.builder.append((char) 0x1B).append((char) 0x40);
		this.current_alignment = Print_Align.LEFT;
		this.current_mode = Print_Mode.DEFAULT;
		return this;
	}

	public PrintFormatBuilder center(String text) {
		this.changeMode(Print_Mode.DEFAULT);
		this.setAlignment(Print_Align.CENTER);
		this.printTextAsLines(text, false);
		return this;
	}

	public PrintFormatBuilder left(String text) {
		this.setAlignment(Print_Align.LEFT);
		this.printTextAsLines(text, false);
		return this;
	}

	public PrintFormatBuilder nl() {
		super.nl();
		// Clear current style
		//this.changeMode(Print_Mode.DEFAULT); NOT NEEDED AS EACH LINE RESETS STYLE
		/* Move to next line */
		super.builder.append((char) 0x0A);
		return this;
	}


	public PrintFormatBuilder right(String text) {
		this.changeMode(Print_Mode.DEFAULT);
		this.setAlignment(Print_Align.RIGHT);
		this.printTextAsLines(text, false);
		return this;
	}
	

	@Override
	public PrintFormatBuilder insertCenter(String text, int position) {
		this.setAlignment(Print_Align.LEFT);
		this.changeMode(Print_Mode.DEFAULT);
		return super.insertCenter(text, position);
	}
	

	@Override
	public PrintFormatBuilder insertLeft(String text, int position_left) {
		this.setAlignment(Print_Align.LEFT);
		this.changeMode(Print_Mode.DEFAULT);
		return super.insertLeft(text, position_left);
	}

	@Override
	public PrintFormatBuilder insertRight(String text, int position_right) {
		this.setAlignment(Print_Align.LEFT);
		this.changeMode(Print_Mode.DEFAULT);
		return super.insertRight(text, position_right);
	}

	/**
	 * Splits the text specified into multiple lines.
	 * @param text
	 */
	private void printTextAsLines(String text, boolean double_width) {
		text = super.fixCharacters(text);
		String [] line = PrintStringUtil.getLines(text, (double_width ? lineWidth/2 : lineWidth) , " ");
		for(int i = 0 ; i < line.length ; i++) {
			super.builder.append(line[i]);
			this.nl();
		}
	}

	/**
	 * Changes the alignment of printing
	 *
	 * @param alignment Byte representing the new aligmnet style.
	 */
	protected void setAlignment(byte alignment) {
		/* Only change the alignment if it REALY changed */
		if (this.current_alignment != alignment) {
			this.current_alignment = alignment;
			super.builder.append((char) 0x1B).append((char) 0x61).append((char) alignment);
		}
	}

	/**
	 * Sets the font of the printer
	 *
	 * @param font Byte representing the new font.
	 */
	protected void setFont(byte font) {
		super.builder.append((char) 0x1B).append((char) 0x4D).append((char) font);
	}

	/**
	 * Changes the printing mode of the printer
	 *
	 * @param mode Byte representing the new print mode.
	 */
	protected void changeMode(byte mode) {
		// Only change the mode if it REALY changed
		if (this.current_mode != mode) {
			this.current_mode = mode;
			super.builder.append((char) 0x1B).append((char) 0x21).append((char) mode);	
		}
	}

}
