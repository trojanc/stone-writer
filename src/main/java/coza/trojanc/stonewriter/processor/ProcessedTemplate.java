package coza.trojanc.stonewriter.processor;

import coza.trojanc.stonewriter.processor.fields.ProcessedLineItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charl-PC on 2016-10-11.
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
