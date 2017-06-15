package coza.trojanc.receipt.template;

import coza.trojanc.receipt.template.fields.TemplateLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A print template defines all details and formulas that must be printed
 * Created by Charl-PC on 2016-10-10.
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TemplateLine> getLines() {
		return lines;
	}

	public void addLine(TemplateLine line){
		this.lines.add(line);
	}

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
