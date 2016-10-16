/*
 * Created on Jul 22, 2005
 *
 * Author RiaanL
 */
package coza.trojanc.stonewriter.template.fields;

import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.shared.Mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a Line message element in the Prompt message element
 */
public class Line implements TemplateLine {

	/** alignment - Set to null if not specified */
	private Align align;

	/** mode - Set to 0 if not specified */
	private Mode mode;

	private List<TemplateTextItem> lineItems = new ArrayList<>();

	public List<TemplateTextItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<TemplateTextItem> lineItems) {
		this.lineItems = lineItems;
	}

	/**
	 * Line constructor
	 */
	public Line() {
		this.align = Align.LEFT;
		this.mode = Mode.NORMAL;
	}

	
	/**
	 * Returns the alignment
	 * @return
	 */
	public Align getAlign() {
		return align;
	}

	
	/**
	 * Returns the mode
	 * @return
	 */
	public Mode getMode() {
		return this.mode;
	}
	


	/**
	 * Sets the alignment
	 * @param align
	 */
	public void setAlign(Align align) {
		this.align = align;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
		.append("Line[")
		.append("align=").append(this.align.toString())
		.append(",mode=").append(this.mode.toString())
		.append("]");
		return sb.toString();
	}
}
