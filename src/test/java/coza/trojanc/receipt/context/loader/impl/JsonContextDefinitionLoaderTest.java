package coza.trojanc.receipt.context.loader.impl;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.context.ContextDefinition;
import coza.trojanc.receipt.context.loader.ContextDefinitionLoader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

/**
 * @author Charl Thiem
 */
public class JsonContextDefinitionLoaderTest {
	@Test
	public void load() throws Exception {
		ContextDefinitionLoader loader = new JsonContextDefinitionLoader();
		ContextDefinition contextDefinition = loader.load(getClass().getResourceAsStream("/context.json"));
	}

	/**
	 * Test the we can write a context definition to JSON without errors
	 * @throws Exception
	 */
	@Test
	public void write() throws Exception {
		ContextDefinitionLoader loader = new JsonContextDefinitionLoader();
		ContextDefinition definition = TestUtils.createContextDefinition();
		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		loader.write(definition, boas);
		System.out.println(boas);
	}

}