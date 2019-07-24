package coza.trojanc.receipt.template;

import coza.trojanc.receipt.template.fields.TemplateLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A print template defines all details and formulas that must be printed
 * @author Charl Thiem
 */
public class PrintTemplate {

	/**
	 * Name of this template
	 */
	private String name;

	/**
	 * List of lines to be printed
	 */
	private List<TemplateLine> lines = new ArrayList<>();

	/**
	 * Get the name of the template
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the template
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the lines for this template
	 *
	 * @return lines
	 */
	public List<TemplateLine> getLines() {
		return lines;
	}

	/**
	 * Add a line to the template
	 *
	 * @param line the line
	 */
	public void addLine(TemplateLine line){
		this.lines.add(line);
	}

	/**
	 * Return a  string representing the template.
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PrintTemplate[")
		.append("name=").append(this.getName()).append(",")
		.append("lines=[");
		String linesString = lines.stream()
				.map(Object::toString)
				.collect(Collectors.joining(","));
		sb.append(linesString);
		sb.append("]")
		.append("]");
		return sb.toString();
	}
}
