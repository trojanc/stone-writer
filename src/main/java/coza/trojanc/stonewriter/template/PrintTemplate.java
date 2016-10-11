package coza.trojanc.stonewriter.template;

import coza.trojanc.stonewriter.template.fields.PrintableTemplateItem;

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
	private List<PrintableTemplateItem> lines = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PrintableTemplateItem> getLines() {
		return lines;
	}

	public void setLines(List<PrintableTemplateItem> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PrintTemplate[")
		.append("name=").append(this.getName()).append(",")
		.append("lines=[");
		String linesString = lines.stream()
				.map(printableTemplateItem -> printableTemplateItem.toString())
				.collect(Collectors.joining(","));
		sb.append(linesString);
		sb.append("]")
		.append("]");
		return sb.toString();
	}
}
