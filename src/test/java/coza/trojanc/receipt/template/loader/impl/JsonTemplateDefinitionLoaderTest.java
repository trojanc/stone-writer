package coza.trojanc.receipt.template.loader.impl;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.template.PrintTemplate;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by charl on 2017/06/15.
 */
public class JsonTemplateDefinitionLoaderTest{

	@Test
	public void testGenerateJson() throws IOException {
		JsonTemplateDefinitionLoader loader = new JsonTemplateDefinitionLoader();
		PrintTemplate template = TestUtils.createTemplate();

		loader.write(template, System.out);

	}

	@Test
	public void testLoadBackJsonTemplate() throws IOException {
		JsonTemplateDefinitionLoader loader = new JsonTemplateDefinitionLoader();
		PrintTemplate template = TestUtils.createTemplate();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		loader.write(template, byteArrayOutputStream);

		template = loader.load(byteArrayOutputStream.toString());
	}

}