package coza.trojanc.stonewriter.context;

import coza.trojanc.stonewriter.TestUtils;
import coza.trojanc.stonewriter.context.impl.DefaultContextResolver;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Charl-PC on 2016-10-16.
 */
public class DefaultContextResolverTest {

	private ContextMap resolvedVariables;

	@Before
	public void setup(){
		ContextResolver resolver = new DefaultContextResolver();
		ContextDefinition contextDefinition = TestUtils.createContextDefinitions();
		Map<String, Object> contextVariables = TestUtils.createContextVariables();
		resolvedVariables = resolver.resolve(contextDefinition, contextVariables);
	}

	@Test
	public void resolveString() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.KEY_PLAYER_NAME));
		assertEquals("Tautua", resolvedVariables.get(TestUtils.KEY_PLAYER_NAME));
	}

	@Test
	public void resolveNumber() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.KEY_PLAYER_AGE));
		assertEquals("21", resolvedVariables.get(TestUtils.KEY_PLAYER_AGE));
	}

	@Test
	public void resolveFormattedDate() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.KEY_PLAYER_BIRTH));
		assertEquals("2016-10-01", resolvedVariables.get(TestUtils.KEY_PLAYER_BIRTH));
	}


}