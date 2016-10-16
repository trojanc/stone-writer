package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.TestUtils;
import coza.trojanc.stonewriter.printer.HtmlPrinter;
import coza.trojanc.stonewriter.shared.Align;
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
		PrintTemplateBuilder builder = new PrintTemplateBuilder();

		PrintTemplate template = builder.name("Test Template")
			.line()
				.text("Hello")
					.align(Align.LEFT)
			.line()
				.text("world")
					.align(Align.RIGHT)
			.line()
				.text("Hers Johnny!")
					.align(Align.RIGHT)
			.line()
				.dynamicText(TestUtils.KEY_PLAYER_AGE)
				.dynamicText(TestUtils.KEY_PLAYER_NAME)
				.dynamicText(TestUtils.KEY_PLAYER_BIRTH)
			.build();



		final Map<String, String> context = TestUtils.createResolvedVariables();
		TemplateProcessor processor = new DefaultTemplateProcessor();
		ProcessedTemplate processedTemplate = processor.process(template, context);

		HtmlPrinter printer = new HtmlPrinter();
		printer.init();
		printer.print(processedTemplate);
		printer.getHtml();
	}
}
