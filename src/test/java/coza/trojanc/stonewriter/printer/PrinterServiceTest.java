package coza.trojanc.stonewriter.printer;

import coza.trojanc.stonewriter.TestUtils;
import coza.trojanc.stonewriter.printer.impl.PlainTextPrinter;
import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.process.impl.DefaultTemplateProcessor;
import coza.trojanc.stonewriter.template.process.ProcessedTemplate;
import coza.trojanc.stonewriter.template.process.TemplateProcessor;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class PrinterServiceTest {
	@Test
	public void print() throws Exception {
		final PrintTemplate template = TestUtils.createTemplate();
		final Map<String, String> context = TestUtils.createResolvedVariables();
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