package coza.trojanc.receipt.template.process.loader.impl;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.template.process.ProcessedTemplate;
import coza.trojanc.receipt.template.process.loader.ProcessedTemplateLoader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static coza.trojanc.receipt.TestUtils.TEST_OUT;

/**
 * Unit test for {@link JsonProcessedTemplateLoader}
 */
public class JsonProcessedTemplateLoaderTest {

	@Test
	public void testGenerateJson() throws IOException {
		ProcessedTemplateLoader loader = new JsonProcessedTemplateLoader();
		ProcessedTemplate processedTemplate = TestUtils.getProcessedTemplate();

		loader.write(processedTemplate, TEST_OUT);
	}

	@Test
	public void testLoadBackJsonTemplate() throws IOException {
		ProcessedTemplateLoader loader = new JsonProcessedTemplateLoader();
		ProcessedTemplate processedTemplate = TestUtils.getProcessedTemplate();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		loader.write(processedTemplate, byteArrayOutputStream);
		loader.load(byteArrayOutputStream.toString());
	}
}