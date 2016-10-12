package coza.trojanc.stonewriter.template.builder;

import coza.trojanc.stonewriter.printer.HtmlPrinter;
import coza.trojanc.stonewriter.processor.ProcessedTemplate;
import coza.trojanc.stonewriter.processor.TemplateProcessor;
import coza.trojanc.stonewriter.shared.Align;
import coza.trojanc.stonewriter.shared.DynamicType;
import coza.trojanc.stonewriter.template.PrintTemplate;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charl-PC on 2016-10-11.
 */
public class PrintTemplateBuilderTest {

	public static class Foo{

		public String getName(){
			return "Tautua";
		}

		public int getAge(){
			return 21;
		}

		public Date getBirth(){
			return new Date();
		}
	}

	@Test
	public void testBuildReceipt(){
		PrintTemplateBuilder builder = new PrintTemplateBuilder();

		PrintTemplate template = builder.name("Test Template")
			.addLine()
				.addText().text("Hello").align(Align.LEFT)
				.addText().text("world").align(Align.RIGHT)
			.addLine()
				.addDynamicText().expression("foo.name")
				.addDynamicText().expression("foo.age").type(DynamicType.Number).formatting("R% 8d%n")
				.addDynamicText().expression("foo.birth").type(DynamicType.Date).formatting("YYYY-MM-dd")
			.build();

		Map<String, Object> context = new HashMap<>();
		context.put("foo", new Foo());

		TemplateProcessor processor = new TemplateProcessor(template, context);
		ProcessedTemplate processedTemplate = processor.process();

		HtmlPrinter printer = new HtmlPrinter();
		printer.init();
		printer.print(processedTemplate);
		printer.getHtml();
	}
}
