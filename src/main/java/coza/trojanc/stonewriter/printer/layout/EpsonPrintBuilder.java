package coza.trojanc.stonewriter.printer.impl;

import coza.trojanc.stonewriter.printer.AbstractPrintTextLayoutBuilder;
import coza.trojanc.stonewriter.processor.layout.PrintTextLayoutBuilder;
import coza.trojanc.stonewriter.processor.layout.TextLayoutBuilder;
import coza.trojanc.stonewriter.shared.PrintStringUtil;

public class EpsonPrintBuilder extends AbstractPrintTextLayoutBuilder {

	/**
	 * Paper sensors<br>
	 * NOTE: This can not be used inline to apply it to the printer
	 */
	public static class Sensors {
		public static final byte	ROLL_NEAR_END	= 0x01;
		public static final byte	ROLL_NEAR_END2	= 0x02;
		public static final byte	ROLL_END		= 0x04;
		public static final byte	ROLL_END2		= 0x08;
	}

	/** 
	 * Modes of underlining<br>
	 * NOTE: Adding this inline will not change the undeline mode, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Undeline_Mode {
		public static final byte	OFF		= 0x00;
		public static final byte	ONE_DOT	= 0x01;
		public static final byte	TWO_DOT	= 0x02;
	}

	/**
	 * Styles of cutting the paper
	 * NOTE: Adding this inline will not perform a papper cut, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Cut_Style {
		public static final byte	FULL		= 0x00;
		public static final byte	PARTIALLY	= 0x01;
	}

	/**
	 * Printing modes
	 * NOTE: Adding this inline will not change the print mode mode, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Print_Mode {
		public static final byte	DEFAULT			= 0x00;
		public static final byte	EMPH_ON			= 0x08;
		public static final byte	DOUBLE_HEIGHT	= 0x10;
		public static final byte	DOUBLE_WIDTH	= 0x20;
		public static final byte	UNDERLINE_ON	= (byte) 0x80;
	}

	/**
	 * Printing fonts
	 * NOTE: Adding this inline will not change the printer font, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Print_Font {
		public static final byte	FONT_A	= 0x00;
		public static final byte	FONT_B	= 0x01;
	}

	/**
	 * Printing alignments
	 * NOTE: Adding this inline will not change the printer alignment, it has
	 * to be applied <strong>with</strong> the command
	 */
	public static class Print_Align {
		public static final byte	LEFT	= 0x00;
		public static final byte	CENTER	= 0x01;
		public static final byte	RIGHT	= 0x02;
	}
	
	
	/** Alignment printer is currently in */
	private byte current_alignment = Print_Align.LEFT;
	/** Print mode printer is currently in */
	private byte current_mode = Print_Mode.DEFAULT;

	/**
	 * Creates a new instance of a <code>EpsonPrintBuilder</code>
	 */
	public EpsonPrintBuilder(){
		super();
	}

	/**
	 * Creates a new instances of <code>EpsonPrintBuilder</code> setting 
	 * widrth of the paper.
	 * @param line_width
	 */
	public EpsonPrintBuilder(int line_width) {
		super(line_width);
	}


	public PrintTextLayoutBuilder initialize(){
		super.initialize();
		super.builder.append((char) 0x1B).append((char) 0x40);
		this.current_alignment = Print_Align.LEFT;
		this.current_mode = Print_Mode.DEFAULT;
		return this;
	}

	public PrintTextLayoutBuilder leftB(String text) {
		this.setAlignment(Print_Align.LEFT);
		this.changeMode(Print_Mode.DEFAULT);
		/* Turn on emphasise */
		super.builder.append((char)0x1B).append((char)0x45).append((char)0x01);
		this.printTextAsLines(text, false); // TODO not yet sure how this works for bold
		/* Turn off emphasise */
		super.builder.append((char)0x1B).append((char)0x45).append((char)0x00);
		return this;
	}

	public TextLayoutBuilder append(String text) {
		/* If busy with complete it and start a new line. */
		if (super.charBufferInUse){
			this.nl();
		}
		super.builder.append(text);
		return this;
	}


	public TextLayoutBuilder center(String text) {
		this.changeMode(Print_Mode.DEFAULT);
		this.setAlignment(Print_Align.CENTER);
		this.printTextAsLines(text, false);
		return this;
	}

	public TextLayoutBuilder left(String text) {
		this.setAlignment(Print_Align.LEFT);
		this.printTextAsLines(text, false);
		return this;
	}

	public TextLayoutBuilder nl() {
		if (super.charBufferInUse){
			super.builder.append(String.valueOf(charBuffer));
			/* Clear each character */
			for (int i = 0; i < lineWidth; i++){
				super.charBuffer[i] = ' ';
			}
			super.appendIndex = 0;
			super.charBufferInUse = false;
		}
		// Clear current style
		//this.changeMode(Print_Mode.DEFAULT); NOT NEEDED AS EACH LINE RESETS STYLE
		/* Move to next line */
		super.builder.append((char) 0x0A);
		return this;
	}

	public TextLayoutBuilder nl(int num_line) {
		if (super.charBufferInUse){
			nl();
			num_line--;
		}
		/* Feed too big will be set to max */
		if (num_line > 255) {
			num_line = 255;
		}
		if (num_line > 0){
			super.builder.append((char) 0x1B).append((char) 0x64).append((char) num_line);
		}
		return this;
	}


	public TextLayoutBuilder right(String text) {
		this.changeMode(Print_Mode.DEFAULT);
		this.setAlignment(Print_Align.RIGHT);
		this.printTextAsLines(text, false);
		return this;
	}
	

	@Override
	public TextLayoutBuilder insertCenter(String text, int position) {
		this.setAlignment(Print_Align.LEFT);
		this.changeMode(Print_Mode.DEFAULT);
		return super.insertCenter(text, position);
	}
	

	@Override
	public TextLayoutBuilder insertLeft(String text, int position_left) {
		this.setAlignment(Print_Align.LEFT);
		this.changeMode(Print_Mode.DEFAULT);
		return super.insertLeft(text, position_left);
	}

	@Override
	public TextLayoutBuilder insertRight(String text, int position_right) {
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
	 * @param font Byte representing the new font.
	 */
	protected void setFont(byte font) {
		super.builder.append((char) 0x1B).append((char) 0x4D).append((char) font);
	}

	/**
	 * Changes the printing mode of the printer
	 * @param mode Byte representing the new print mode.
	 */
	protected void changeMode(byte mode) {
		// Only change the mode if it REALY changed
		if (this.current_mode != mode) {
			this.current_mode = mode;
			super.builder.append((char) 0x1B).append((char) 0x21).append((char) mode);	
		}
	}

	/**
	 * Runs a unit test on this class. Test will be printed in console in epson escape characters.
	 * @param args No arguments required.
	 */
	public static void main(String[] args) {
		EpsonPrintBuilder builder = new EpsonPrintBuilder(40);
		builder.createTestPrint();
		System.out.println(builder.toString());
	}

	public PrintTextLayoutBuilder ff() {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintTextLayoutBuilder line() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public PrintTextLayoutBuilder line(char chr) {
		return null;
	}

	public PrintTextLayoutBuilder lineDouble() {
		// TODO Auto-generated method stub
		return this;
	}

	public PrintTextLayoutBuilder lineChar(char chr) {
		// TODO Auto-generated method stub
		return null;
	}

	public TextLayoutBuilder centerB(String text) {
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
