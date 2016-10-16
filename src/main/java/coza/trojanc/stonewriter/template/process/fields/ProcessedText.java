package coza.trojanc.stonewriter.template.process.fields;

import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.shared.Mode;

/**
 * Created by Charl-PC on 2016-10-11.
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
