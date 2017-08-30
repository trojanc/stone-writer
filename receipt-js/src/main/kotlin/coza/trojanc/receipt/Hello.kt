package coza.trojanc.receipt

import coza.trojanc.receipt.context.ContextMap
import coza.trojanc.receipt.context.impl.DefaultContextMap
import coza.trojanc.receipt.printer.PrinterService
import coza.trojanc.receipt.printer.impl.PlainTextPrinter
import coza.trojanc.receipt.shared.Align
import coza.trojanc.receipt.template.builder.PrintTemplateBuilder
import coza.trojanc.receipt.template.process.ProcessedTemplate
import coza.trojanc.receipt.template.process.impl.DefaultTemplateProcessor

fun main(args: Array<String>) {

	val template = PrintTemplateBuilder().name("Test Template")
		.line()
		.text("Test page").align(Align.CENTER)
		.fillLine('-')
		.feed()
		.build()

	val processedTemplate: ProcessedTemplate = DefaultTemplateProcessor().process(template);
	println("Template processed")
	val printerService = PrinterService()
	val plainTextPrinter = PlainTextPrinter()
	printerService.print(processedTemplate, plainTextPrinter.getLayoutBuilder())
	println("Template serviced")
	val printedText = plainTextPrinter.getLayoutBuilder().getFormat() as String
	println(printedText)

}

