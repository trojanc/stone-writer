package coza.trojanc.receipt.template.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a repeating section of the receipt.
 * <p>
 * A repeated section can consist of one or more line items, and repeats on a context item array
 * @author Charl Thiem
 */
public class RepeatBlock implements TemplateLine{

	/**+
	 * Context item to repeat this block on
	 */
	private String repeatOn;

	/**
	 * Lines to repeat
	 */
	private List<TemplateLine> lines = new ArrayList<>();

	/**
	 * Gets repeat on.
	 *
	 * @return the repeat on
	 */
	public String getRepeatOn() {
		return repeatOn;
	}

	/**
	 * Sets repeat on.
	 *
	 * @param repeatOn the repeat on
	 */
	public void setRepeatOn(String repeatOn) {
		this.repeatOn = repeatOn;
	}

	/**
	 * Gets lines.
	 *
	 * @return the lines
	 */
	public List<TemplateLine> getLines() {
		return lines;
	}

	/**
	 * Sets lines.
	 *
	 * @param lines the lines
	 */
	public void setLines(List<TemplateLine> lines) {
		this.lines = lines;
	}
}
