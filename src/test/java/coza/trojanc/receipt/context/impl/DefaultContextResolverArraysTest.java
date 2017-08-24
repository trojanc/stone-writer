package coza.trojanc.receipt.context.impl;

import org.junit.Test;

import static coza.trojanc.receipt.TestUtils.EXPR_SOLD_ITEMS_VALUE;
import static org.junit.Assert.*;

/**
 * @author Charl Thiem
 */
public class DefaultContextResolverArraysTest {

	private static final String VALID_EXPRESSION_1 = "message[]";
	private static final String VALID_EXPRESSION_2 = "message[].value";
	private static final String INVALID_EXPRESSION_1 = "message";
	private static final String INVALID_EXPRESSION_2 = "message.value";

	@Test
	public void testValidArray1(){
		final boolean isArrayExpression = DefaultContextResolver.isArrayExpression(VALID_EXPRESSION_1);
		assertTrue("Expected to be a valid array expression", isArrayExpression);
	}

	@Test
	public void testGetExpressionPrefix1(){
		final String prefix = DefaultContextResolver.getArrayExpressionPrefix(VALID_EXPRESSION_1);
		assertEquals("Did not receive the expected prefix", "message", prefix);
	}

	@Test
	public void testGetExpressionSuffix1(){
		final String suffix = DefaultContextResolver.getArrayExpressionSuffix(VALID_EXPRESSION_1);
		assertEquals("Did not receive the expected suffix", "", suffix);
	}

	@Test
	public void testValidArray2(){
		final boolean isArrayExpression = DefaultContextResolver.isArrayExpression(VALID_EXPRESSION_2);
		assertTrue("Expected to be a valid array expression", isArrayExpression);
	}

	@Test
	public void testGetExpressionPrefix2(){
		final String prefix = DefaultContextResolver.getArrayExpressionPrefix(VALID_EXPRESSION_2);
		assertEquals("Did not receive the expected prefix", "message", prefix);
	}

	@Test
	public void testGetExpressionSuffix2(){
		final String suffix = DefaultContextResolver.getArrayExpressionSuffix(VALID_EXPRESSION_2);
		assertEquals("Did not receive the expected suffix", ".value", suffix);
	}

	@Test
	public void testValidArray3(){
		final boolean isArrayExpression = DefaultContextResolver.isArrayExpression(EXPR_SOLD_ITEMS_VALUE);
		assertTrue("Expected to be a valid array expression", isArrayExpression);
	}

	@Test
	public void testGetExpressionPrefix3(){
		final String prefix = DefaultContextResolver.getArrayExpressionPrefix(EXPR_SOLD_ITEMS_VALUE);
		assertEquals("Did not receive the expected prefix", "transaction.soldItems", prefix);
	}

	@Test
	public void testGetExpressionSuffix3(){
		final String suffix = DefaultContextResolver.getArrayExpressionSuffix(EXPR_SOLD_ITEMS_VALUE);
		assertEquals("Did not receive the expected suffix", ".value", suffix);
	}

	@Test
	public void testInvalidArray1(){
		final boolean isArrayExpression = DefaultContextResolver.isArrayExpression(INVALID_EXPRESSION_1);
		assertFalse("Expected to be a valid array expression", isArrayExpression);
	}

	@Test
	public void testInvalidArray2(){
		final boolean isArrayExpression = DefaultContextResolver.isArrayExpression(INVALID_EXPRESSION_2);
		assertFalse("Expected to be a valid array expression", isArrayExpression);
	}

}