package coza.trojanc.receipt.template.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a line within a template
 */
public class Line implements TemplateLine {

	private final List<TemplateTextItem> lineItems;

	public Line(){
		lineItems = new ArrayList<>();
	}

	public List<TemplateTextItem> getLineItems() {
		return lineItems;
	}

	public void addLineItem(TemplateTextItem item){
		lineItems.add(item);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
		.append("Line[");
		lineItems.forEach(templateTextItem -> {
			sb.append(templateTextItem.toString());
		});
		sb.append("]");
		return sb.toString();
	}
}
