package coza.trojanc.receipt.printer;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.context.ContextMap;
import coza.trojanc.receipt.printer.impl.PlainTextPrinter;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.process.impl.DefaultTemplateProcessor;
import coza.trojanc.receipt.template.process.ProcessedTemplate;
import coza.trojanc.receipt.template.process.TemplateProcessor;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class PrinterServiceTest {
	@Test
	public void print() throws Exception {
		final PrintTemplate template = TestUtils.createTemplate();
		final ContextMap context = TestUtils.createResolvedVariables();
		TemplateProcessor processor = new DefaultTemplateProcessor();
		ProcessedTemplate processedTemplate = processor.process(template, context);

		PrinterService printerService = new PrinterService();
		Printer plainTextPrinter = new PlainTextPrinter();
		printerService.print(processedTemplate, plainTextPrinter.getLayoutBuilder());
		String printedText = (String)plainTextPrinter.getLayoutBuilder().getFormat();
		System.out.println(printedText);
//		assertArrayEquals(expected, buffer);

	}

}