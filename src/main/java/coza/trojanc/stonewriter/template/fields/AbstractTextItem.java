package coza.trojanc.stonewriter.template.fields;

import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.shared.Mode;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public abstract class AbstractTextItem implements TemplateTextItem{


	/** global printing mode bitmap - product of MODE_* constants above ORed together */
	private Mode mode = Mode.NORMAL;

	/** global alignment */
	private Align alignment = Align.LEFT;

	private Integer offset;

	public AbstractTextItem(){}

	public AbstractTextItem(AbstractTextItem source){
		this.mode = source.mode;
		this.offset = source.offset;
		this.alignment = source.alignment;
	}


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

	public void setOffset(Integer offset){
		this.offset = offset;
	}

	@Override
	public Integer getOffset() {
		return offset;
	}
}
