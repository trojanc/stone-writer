package coza.trojanc.receipt.template.process.fields

/**
 * @author Charl Thiem
 */
class ProcessedLine : ProcessedLineItem {

    private val lineItems: MutableList<ProcessedText> = ArrayList<ProcessedText>()

    fun getLineItems(): List<ProcessedText> {
        return lineItems
    }

    fun addProcessedText(processedText: ProcessedText){
        this.lineItems.add(processedText);
    }
}
