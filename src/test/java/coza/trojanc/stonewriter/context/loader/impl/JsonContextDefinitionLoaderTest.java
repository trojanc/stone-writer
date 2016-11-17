package coza.trojanc.stonewriter.context.loader.impl;

import coza.trojanc.stonewriter.TestUtils;
import coza.trojanc.stonewriter.context.ContextDefinition;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * Created by Charl-PC on 2016-11-17.
 */
public class JsonContextDefinitionLoaderTest {
	@Test
	public void load() throws Exception {
	}

	@Test
	public void write() throws Exception {
		JsonContextDefinitionLoader loader = new JsonContextDefinitionLoader();
		ContextDefinition definition = TestUtils.createContextDefinitions();
		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		loader.write(definition, boas);
		System.out.println(boas);
	}

}