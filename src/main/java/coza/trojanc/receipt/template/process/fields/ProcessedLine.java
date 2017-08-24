package coza.trojanc.receipt.template.process.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charl Thiem
 */
public class ProcessedLine implements ProcessedLineItem{

	private List<ProcessedText> lineItems = new ArrayList<>();

	public List<ProcessedText> getLineItems() {
		return lineItems;
	}
}
