package coza.trojanc.receipt.context.loader.impl;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.context.ContextDefinition;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * Created by Charl-PC on 2016-11-17.
 */
public class JsonContextDefinitionLoaderTest {
	@Test
	public void load() throws Exception {
		JsonContextDefinitionLoader loader = new JsonContextDefinitionLoader();
		ContextDefinition contextDefinition = loader.load(getClass().getResourceAsStream("/context.json"));
	}

	/**
	 * Test the we can write a context definition to JSON without errors
	 * @throws Exception
	 */
	@Test
	public void write() throws Exception {
		JsonContextDefinitionLoader loader = new JsonContextDefinitionLoader();
		ContextDefinition definition = TestUtils.createContextDefinitions();
		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		loader.write(definition, boas);
		System.out.println(boas);
	}

}