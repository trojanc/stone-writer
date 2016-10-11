package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.printer.HtmlPrinter;
import coza.trojanc.stonewriter.processor.ProcessedTemplate;
import coza.trojanc.stonewriter.processor.TemplateProcessor;
import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.template.PrintTemplate;
import org.junit.Test;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintTemplateBuilderTest {

	@Test
	public void testBuildReceipt(){
		PrintTemplateBuilder builder = new PrintTemplateBuilder();

		PrintTemplate template = builder.name("Test Template")
			.addLine()
				.addText().text("Hello").align(Align.LEFT)
				.addText().text("world").align(Align.RIGHT)
			.addLine()
				.addText().text("Awsumness!")
			.build();


		TemplateProcessor processor = new TemplateProcessor(template);
		ProcessedTemplate processedTemplate = processor.process();

		HtmlPrinter printer = new HtmlPrinter();
		printer.init();
		printer.print(processedTemplate);
		printer.getHtml();
	}
}
