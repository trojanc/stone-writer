package coza.trojanc.stonewriter.template.process;

import coza.trojanc.stonewriter.template.process.fields.ProcessedLineItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a template that has been processed
 * Created by Charl on 2016-10-11.
 */
public class ProcessedTemplate {

	private List<ProcessedLineItem> items = new ArrayList<>();

	public List<ProcessedLineItem> getItems() {
		return items;
	}

	public void setItems(List<ProcessedLineItem> items) {
		this.items = items;
	}
}
