package coza.trojanc.stonewriter.processor.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class ProcessedLine  implements ProcessedLineItem{

	private List<ProcessedText> lineItems = new ArrayList<>();

	public List<ProcessedText> getLineItems() {
		return lineItems;
	}
}
