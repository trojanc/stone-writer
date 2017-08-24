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
import java.util.function.Consumer


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
        if (Feed::class.java.isAssignableFrom(item.javaClass)) {
            val feed = item as Feed
            for (i in 0..feed.feedAmount - 1) {
                processedTemplate!!.addProcessedLineItem(ProcessedFeed())
            }
        } else if (Line::class.java.isAssignableFrom(item.javaClass)) {
            processLine(item as Line, repeatPrefix)
        } else if (FillLine::class.java.isAssignableFrom(item.javaClass)) {
            processFillLine(item as FillLine)
        } else if (RepeatBlock::class.java.isAssignableFrom(item.javaClass)) {
            processRepeatBlock(item as RepeatBlock)
        }

    }


    /**
     * Process a repeat block
     * @param repeatBlock The repeat block to process
     */
    private fun processRepeatBlock(repeatBlock: RepeatBlock) {
        val keyPrefix = repeatBlock.getRepeatOn()
        val repeatSize = Integer.parseInt(context!![keyPrefix!! + ContextResolver.ARRAY_LENGTH_SUFFIX]!!)
        for (idx in 0..repeatSize - 1) {
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
            if (Text::class.java.isAssignableFrom(lineItem.javaClass)) {
                addStaticText(lineItem as Text, processedLine)
            } else if (DynamicText::class.java.isAssignableFrom(lineItem.javaClass)) {
                addDynamicText(lineItem as DynamicText, processedLine, repeatPrefix)
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
            if (!(text.getContextKey() == null || text.getContextKey()!!.length == 0)) {
                contextKey += text.getContextKey()
            }
        }
        processedText.setText(context!![contextKey!!]!!)
        processedLine.addProcessedText(processedText)
    }


    override fun process(template: PrintTemplate): ProcessedTemplate {
        return process(template, DefaultContextMap(0))
    }

    override fun process(template: PrintTemplate, context: ContextMap): ProcessedTemplate {
        this.processedTemplate = ProcessedTemplate()
        this.context = context

        template.getLines().forEach {this.processTemplateItem(it)}
        return this.processedTemplate!!
    }
}
