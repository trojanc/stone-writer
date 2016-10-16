package coza.trojanc.stonewriter.printer;

import coza.trojanc.stonewriter.TestUtils;
import coza.trojanc.stonewriter.printer.impl.HtmlPrinter;
import coza.trojanc.stonewriter.printer.layout.PlainTextLayoutBuilder;
import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.process.DefaultTemplateProcessor;
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

		HtmlPrinter htmlPrinter = new HtmlPrinter();
		htmlPrinter.init();
		htmlPrinter.print(processedTemplate);
		htmlPrinter.getHtml();

		PrinterService printerService = new PrinterService();
		PlainTextLayoutBuilder printer = new PlainTextLayoutBuilder(40);
		printerService.print(processedTemplate, printer);
		System.out.println(printer.toString());


	}

}