package coza.trojanc.receipt.template.process.impl

import coza.trojanc.receipt.context.ContextMap
import coza.trojanc.receipt.context.ContextResolver
import coza.trojanc.receipt.context.impl.DefaultContextMap
import coza.trojanc.receipt.template.PrintTemplate
import coza.trojanc.receipt.template.fields.*
import coza.trojanc.receipt.template.process.ProcessedTemplate
import coza.trojanc.receipt.template.process.TemplateProcessor
import coza.trojanc.receipt.template.process.fields.ProcessedFeed
import coza.trojanc.receipt.template.process.fields.ProcessedFillLine
import coza.trojanc.receipt.template.process.fields.ProcessedLine
import coza.trojanc.receipt.template.process.fields.ProcessedText


/**
 * @author Charl Thiem
 */
class DefaultTemplateProcessor : TemplateProcessor {

    /**
     * The resulting processed template.
     */
    private var processedTemplate: ProcessedTemplate? = null

    /**
     * Context being used to retrieve dynamic values for the template.
     */
    private var context: ContextMap? = null

    /**
     * Process a template item.
     * @param item The template item to process.
     */
    private fun processTemplateItem(item: TemplateLine) {
        this.processTemplateItem(item, null)
    }

    private fun processTemplateItem(item: TemplateLine, repeatPrefix: String?) {

        // If it is a feed
        if (item is Feed) {
            val feed = item as Feed
            for (i in 0..feed.feedAmount - 1) {
                processedTemplate!!.addProcessedLineItem(ProcessedFeed())
            }
        } else if (item is Line) {
            processLine(item, repeatPrefix)
        } else if (item is FillLine) {
            processFillLine(item)
        } else if (item is RepeatBlock) {
            processRepeatBlock(item)
        }

    }


    /**
     * Process a repeat block
     * @param repeatBlock The repeat block to process
     */
    private fun processRepeatBlock(repeatBlock: RepeatBlock) {
        val keyPrefix = repeatBlock.getRepeatOn()
        val repeatSize = context!!.get(keyPrefix!! + ContextResolver.ARRAY_LENGTH_SUFFIX)!!.toInt()
        for (idx in 0 until repeatSize) {
            repeatBlock.getLines().forEach { line -> processTemplateItem(line, "$keyPrefix[$idx]") }
        }
    }

    /**
     * Process a fill line.
     * @param fillLine
     */
    private fun processFillLine(fillLine: FillLine) {
        processedTemplate!!.addProcessedLineItem(ProcessedFillLine(fillLine.character))
    }

    /**
     * Process a line that can contain text
     * @param line The line to process
     */
    private fun processLine(line: Line, repeatPrefix: String?) {
        val processedLine = ProcessedLine()
        processedTemplate!!.addProcessedLineItem(processedLine)

        line.getLineItems().forEach { lineItem ->
            if (lineItem is Text) {
                addStaticText(lineItem, processedLine)
            } else if (lineItem is DynamicText) {
                addDynamicText(lineItem, processedLine, repeatPrefix)
            }
        }
    }

    private fun addStaticText(text: Text, processedLine: ProcessedLine) {
        val processedText = ProcessedText()
        processedText.setAlignment(text.getAlignment())
        processedText.setMode(text.getMode())
        processedText.setText(text.getText()!!)
        processedText.setOffset(text.getOffset())
        processedLine.addProcessedText(processedText)
    }

    private fun addDynamicText(text: DynamicText, processedLine: ProcessedLine, repeatPrefix: String?) {
        val processedText = ProcessedText()
        processedText.setAlignment(text.getAlignment())
        processedText.setMode(text.getMode())
        if(text.getOffset() != null) {
            processedText.setOffset(text.getOffset()!!)
        }

        var contextKey: String?
        if (repeatPrefix == null) {
            contextKey = text.getContextKey();
        } else {
            contextKey = repeatPrefix
            if (!(text.getContextKey() == null || text.getContextKey()!!.isEmpty())) {
                contextKey += text.getContextKey()
            }
        }
        processedText.setText(context!!.get(contextKey!!)!!)
        processedLine.addProcessedText(processedText)
    }


    override fun process(template: PrintTemplate): ProcessedTemplate {
        return process(template, DefaultContextMap())
    }

    override fun process(template: PrintTemplate, context: ContextMap): ProcessedTemplate {
        this.processedTemplate = ProcessedTemplate()
        this.context = context

        template.getLines().forEach {this.processTemplateItem(it)}
        return this.processedTemplate!!
    }
}
