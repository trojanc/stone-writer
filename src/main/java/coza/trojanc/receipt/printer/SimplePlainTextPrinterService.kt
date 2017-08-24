package coza.trojanc.receipt.printer

import coza.trojanc.receipt.context.ContextMap
import coza.trojanc.receipt.format.PrintFormatBuilder
import coza.trojanc.receipt.format.impl.PlainTextFormatBuilder
import coza.trojanc.receipt.template.PrintTemplate
import coza.trojanc.receipt.template.loader.impl.JsonTemplateDefinitionLoader
import coza.trojanc.receipt.template.process.ProcessedTemplate
import coza.trojanc.receipt.template.process.TemplateProcessor
import coza.trojanc.receipt.template.process.impl.DefaultTemplateProcessor

import java.io.IOException

/**
 * @author Charl Thiem
 */
object SimplePlainTextPrinterService {

    private val loader = JsonTemplateDefinitionLoader()

    @Throws(IOException::class)
    fun createReceipt(width: Int, templateDefinition: String, contextMap: ContextMap): String {
        return createReceipt(width, loader.load(templateDefinition), contextMap)
    }

    fun createReceipt(width: Int, template: PrintTemplate, contextMap: ContextMap): String {
        // Process the template
        val processor = DefaultTemplateProcessor()
        val processedTemplate = processor.process(template, contextMap)

        // Print the template
        val printerService = PrinterService()
        val printerBuilder = PlainTextFormatBuilder(width)
        printerService.print(processedTemplate, printerBuilder)
        return printerBuilder.format as String
    }
}
