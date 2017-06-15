package coza.trojanc.receipt.template.loader.impl;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.template.PrintTemplate;
import coza.trojanc.receipt.template.loader.TemplateDefinitionLoader;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by charl on 2017/06/15.
 */
public class XmlTemplateDefinitionLoaderTest {

	@Test
	public void testGenerateJson() throws IOException {
		TemplateDefinitionLoader loader = new XmlTemplateDefinitionLoader();
		PrintTemplate template = TestUtils.createTemplate();

		loader.write(template, System.out);
	}

	@Test
	public void testLoadBackJsonTemplate() throws IOException {
		TemplateDefinitionLoader loader = new XmlTemplateDefinitionLoader();
		PrintTemplate template = TestUtils.createTemplate();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		loader.write(template, byteArrayOutputStream);

		template = loader.load(byteArrayOutputStream.toString());
	}

}