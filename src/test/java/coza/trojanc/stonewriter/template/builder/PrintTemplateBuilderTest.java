package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.TestUtils;
import coza.trojanc.stonewriter.printer.impl.HtmlPrinter;
import coza.trojanc.stonewriter.template.PrintTemplate;
import coza.trojanc.stonewriter.template.process.DefaultTemplateProcessor;
import coza.trojanc.stonewriter.template.process.ProcessedTemplate;
import coza.trojanc.stonewriter.template.process.TemplateProcessor;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintTemplateBuilderTest {

	@Test
	public void testBuildReceipt(){

		final PrintTemplate template = TestUtils.createTemplate();
		final Map<String, String> context = TestUtils.createResolvedVariables();
		TemplateProcessor processor = new DefaultTemplateProcessor();
		ProcessedTemplate processedTemplate = processor.process(template, context);

		HtmlPrinter printer = new HtmlPrinter();
		printer.init();
		printer.print(processedTemplate);
		printer.getHtml();



	}
}
