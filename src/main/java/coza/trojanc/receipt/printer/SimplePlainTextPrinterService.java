package coza.trojanc.receipt.printer;

import coza.trojanc.receipt.context.ContextMap;
import coza.trojanc.receipt.format.PrintFormatBuilder;
import coza.trojanc.receipt.format.impl.PlainTextFormatBuilder;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.loader.impl.JsonTemplateDefinitionLoader;
import coza.trojanc.receipt.template.process.ProcessedTemplate;
import coza.trojanc.receipt.template.process.TemplateProcessor;
import coza.trojanc.receipt.template.process.impl.DefaultTemplateProcessor;

import java.io.IOException;

/**
 * @author Charl Thiem
 */
public class SimplePlainTextPrinterService {

	private static final JsonTemplateDefinitionLoader loader = new JsonTemplateDefinitionLoader();

	public static String createReceipt(int width, String templateDefinition, ContextMap contextMap) throws IOException {
		return createReceipt(width, loader.load(templateDefinition), contextMap);
	}

	public static String createReceipt(int width, PrintTemplate template, ContextMap contextMap){
		// Process the template
		TemplateProcessor processor = new DefaultTemplateProcessor();
		ProcessedTemplate processedTemplate = processor.process(template, contextMap);

		// Print the template
		PrinterService printerService = new PrinterService();
		PrintFormatBuilder printerBuilder = new PlainTextFormatBuilder(width);
		printerService.print(processedTemplate, printerBuilder);
		return (String)printerBuilder.getFormat();
	}
}
