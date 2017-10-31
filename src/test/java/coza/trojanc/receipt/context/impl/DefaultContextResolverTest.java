package coza.trojanc.receipt.context.impl;

import coza.trojanc.receipt.TestUtils;
import coza.trojanc.receipt.context.ContextDefinition;
import coza.trojanc.receipt.context.ContextMap;
import coza.trojanc.receipt.context.ContextResolver;
import coza.trojanc.receipt.context.test.SoldItem;
import coza.trojanc.receipt.context.test.TestTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for {@link DefaultContextResolver}
 * @author Charl Thiem
 */
public class DefaultContextResolverTest {

	private ContextMap resolvedVariables;

	@BeforeEach
	public void setup(){
		ContextResolver resolver = new DefaultContextResolver();
		ContextDefinition contextDefinition = TestUtils.createContextDefinition();
		Map<String, Object> contextVariables = TestUtils.createContextVariables();
		resolvedVariables = resolver.resolve(contextDefinition, contextVariables);
	}

	@Test
	public void resolveString() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.CTX_TRADER_NAME));
		assertEquals(TestTransaction.VALUE_TRADERNAME, resolvedVariables.get(TestUtils.CTX_TRADER_NAME));
	}

	@Test
	public void resolveNumber() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.CTX_NUM_ITEMS));
		assertEquals(Integer.toString(TestTransaction.VALUE_NUM_ITEMS), resolvedVariables.get(TestUtils.CTX_NUM_ITEMS));
	}

	@Test
	public void resolveFormattedDate() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.CTX_TRANSACTION_DATE));
		String expectedDate = new SimpleDateFormat(TestUtils.DATE_FORMAT).format(TestTransaction.VALUE_TRANSACTION_DATE);
		assertEquals(expectedDate, resolvedVariables.get(TestUtils.CTX_TRANSACTION_DATE));
	}

	@Test
	public void resolveFormattedDateTime() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.CTX_TRANSACTION_SYSTEM_DATETIME));
		String expectedDate = DateTimeFormatter.ofPattern(TestUtils.DATE_FORMAT).format(TestTransaction.VALUE_SYSTEM_DATE_TIME);
		assertEquals(expectedDate, resolvedVariables.get(TestUtils.CTX_TRANSACTION_SYSTEM_DATETIME));
	}

	@Test
	public void resolveArrayString() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.CTX_SOLD_ITEMS_NAME_1));
		assertTrue(resolvedVariables.has(TestUtils.CTX_SOLD_ITEMS_NAME_2));
		assertEquals(SoldItem.SOLD_ITEM1_NAME, resolvedVariables.get(TestUtils.CTX_SOLD_ITEMS_NAME_1));
		assertEquals(SoldItem.SOLD_ITEM2_NAME, resolvedVariables.get(TestUtils.CTX_SOLD_ITEMS_NAME_2));
	}

	@Test
	public void resolveArraySize() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.CTX_SOLD_ITEMS_LENGTH));
		assertEquals("2", resolvedVariables.get(TestUtils.CTX_SOLD_ITEMS_LENGTH));
	}

	@Test
	public void resolveArrayDecimal() throws Exception {
		assertTrue(resolvedVariables.has(TestUtils.CTX_SOLD_ITEMS_VALUE_1));
		assertTrue(resolvedVariables.has(TestUtils.CTX_SOLD_ITEMS_VALUE_2));

		assertEquals(new DecimalFormat("#0.00").format(SoldItem.SOLD_ITEM1_VALUE), resolvedVariables.get(TestUtils.CTX_SOLD_ITEMS_VALUE_1));
		assertEquals(new DecimalFormat("#0.00").format(SoldItem.SOLD_ITEM2_VALUE), resolvedVariables.get(TestUtils.CTX_SOLD_ITEMS_VALUE_2));
	}


}