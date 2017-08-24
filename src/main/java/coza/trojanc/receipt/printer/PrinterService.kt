package coza.trojanc.receipt.printer

import coza.trojanc.receipt.format.PrintFormatBuilder
import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.shared.PrintStringUtil
import coza.trojanc.receipt.template.process.ProcessedTemplate
import coza.trojanc.receipt.template.process.fields.ProcessedFeed
import coza.trojanc.receipt.template.process.fields.ProcessedFillLine
import coza.trojanc.receipt.template.process.fields.ProcessedLine
import coza.trojanc.receipt.template.process.fields.ProcessedText

/**
 * @author Charl Thiem
 */
class PrinterService {

    private var builder: PrintFormatBuilder? = null

    fun print(template: ProcessedTemplate, builder: PrintFormatBuilder) {
        this.builder = builder

        template.getItems().forEach { processedLineItem ->

            if (ProcessedFeed::class.java.isAssignableFrom(processedLineItem.javaClass)) {
                builder.feed()
            } else if (ProcessedLine::class.java.isAssignableFrom(processedLineItem.javaClass)) {
                val line = processedLineItem as ProcessedLine
                printLine(line)
            } else if (ProcessedFillLine::class.java.isAssignableFrom(processedLineItem.javaClass)) {
                val line = processedLineItem as ProcessedFillLine
                builder.left(PrintStringUtil.createStringOfChar(builder.lineWidth, line.getCharacter()))
                builder.nl()
            }
        }

    }

    private fun printLine(line: ProcessedLine) {
        line.getLineItems().forEach { processedText -> printText(processedText, line.getLineItems().size == 1) }
        builder!!.nl()
    }

    private fun printText(processedText: ProcessedText, onlyItemInLine: Boolean) {
        var offset = processedText.getOffset()
        if (!onlyItemInLine) {
            if (Align.RIGHT == processedText.getAlignment()) {
                offset = if (offset == null) builder!!.lineWidth - 1 else offset
            } else if (Align.CENTER == processedText.getAlignment()) {
                offset = if (offset == null) builder!!.lineWidth / 2 else offset
            } else {
                offset = if (offset == null) 0 else offset
            }
        }

        // Calculate offset based on alignment
        builder!!.insertText(processedText.getText(), offset, processedText.getAlignment())
    }
}
