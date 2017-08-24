package coza.trojanc.receipt.template.process.fields;

import coza.trojanc.receipt.shared.Align;
import coza.trojanc.receipt.shared.Mode;

/**
 * @author Charl Thiem
 */
public class ProcessedText{
	/** global printing mode bitmap - product of MODE_* constants above ORed together */
	private Mode mode = Mode.NORMAL;

	/** global alignment */
	private Align alignment = Align.LEFT;

	private String text;

	private Integer offset;

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Align getAlignment() {
		return alignment;
	}

	public void setAlignment(Align alignment) {
		this.alignment = alignment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
}
