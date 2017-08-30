package coza.trojanc.receipt.template.process

import coza.trojanc.receipt.template.process.fields.ProcessedLineItem

/**
 * A class representing a template that has been processed
 * @author Charl Thiem
 */
class ProcessedTemplate {

    private val items: MutableList<ProcessedLineItem> = ArrayList<ProcessedLineItem>()

    fun getItems(): List<ProcessedLineItem> {
        return items
    }

    fun setItems(items: List<ProcessedLineItem>) {
        this.items.clear();
        this.items.addAll(items);
    }

    fun addProcessedLineItem(processedLineItem: ProcessedLineItem){
        this.items.add(processedLineItem);
    }
}
