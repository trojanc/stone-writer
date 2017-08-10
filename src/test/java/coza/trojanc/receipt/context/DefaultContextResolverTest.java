package coza.trojanc.receipt.context;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.context.impl.DefaultContextResolver;
import coza.trojanc.receipt.context.test.TestTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for {@link DefaultContextResolver}
 */
public class DefaultContextResolverTest {

	private ContextMap resolvedVariables;

	@BeforeEach
	public void setup(){
		ContextResolver resolver = new DefaultContextResolver();
		ContextDefinition contextDefinition = TestUtils.createContextDefinitions();
		Map<String, Object> contextVariables = TestUtils.createContextVariables();
		resolvedVariables = resolver.resolve(contextDefinition, contextVariables);
	}

	@Test
	public void resolveString() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.KEY_TRADER_NAME));
		assertEquals(TestTransaction.VALUE_TRADERNAME, resolvedVariables.get(TestUtils.KEY_TRADER_NAME));
	}

	@Test
	public void resolveNumber() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.KEY_NUM_ITEMS));
		assertEquals(Integer.toString(TestTransaction.VALUE_NUM_ITEMS), resolvedVariables.get(TestUtils.KEY_NUM_ITEMS));
	}

	@Test
	public void resolveFormattedDate() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.KEY_TRANSCACTION_DATE));
		String expectedDate = new SimpleDateFormat(TestUtils.DATE_FORMAT).format(TestTransaction.VALUE_TRANSACTION_DATE);
		assertEquals(expectedDate, resolvedVariables.get(TestUtils.KEY_TRANSCACTION_DATE));
	}


}