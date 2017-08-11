package coza.trojanc.receipt.format.impl;

import coza.trojanc.receipt.format.PrintFormatBuilder;
import coza.trojanc.receipt.format.PrintTest;
import coza.trojanc.receipt.format.PrintTestLoader;
import coza.trojanc.receipt.shared.LineWrap;
import coza.trojanc.receipt.shared.PrintStringUtil;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Charl-PC on 2016-11-01.
 */
public class PlainTextFormatBuilderTest {


	private void line(){
		System.out.println(PrintStringUtil.createStringOfChar(30, '-'));
	}

	interface PrintInsertFunction<B, T, I, W> {
		B apply(B b, T t, I u, W v);
	}

	interface PrintAlignFunction<B, T, W> {
		B apply(B b, T t, W w);
	}

	private void testInsert(PrintTest.TestInstance testInstance,
							PrintInsertFunction<PrintFormatBuilder, String, Integer, LineWrap> function,
							Function<PrintTest.TestInstance, String> resultFunction){
		line();
		PrintFormatBuilder builder = new PlainTextFormatBuilder(testInstance.getLineWidth());
		function.apply(builder, testInstance.getText(), testInstance.getIndex(), testInstance.getLineWrap());
		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(resultFunction.apply(testInstance).replaceAll("\\|", ""), result);
	}

	private void testAlignment(PrintTest.TestInstance testInstance,
							PrintAlignFunction<PrintFormatBuilder, String, LineWrap> function,
							Function<PrintTest.TestInstance, String> resultFunction){
		line();
		PrintFormatBuilder builder = new PlainTextFormatBuilder(testInstance.getLineWidth());
		function.apply(builder, testInstance.getText(), testInstance.getLineWrap());
		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(resultFunction.apply(testInstance).replaceAll("\\|", ""), result);
	}

	private static String insertTestDescription(PrintTest.TestInstance testInstance, String placement){
		return String.format("%s line %d chars %s at %d with %d",  testInstance.getLineWrap(), testInstance.getText().length(), placement, testInstance.getIndex(), testInstance.getLineWidth());
	}

	private static String alignTestDescription(PrintTest.TestInstance testInstance, String placement){
		return String.format("%s line %d chars %s with %d",  testInstance.getLineWrap(), testInstance.getText().length(), placement, testInstance.getLineWidth());
	}

	@TestFactory
	public List<DynamicTest> testInsert() throws IOException {
		PrintTestLoader loader = new PrintTestLoader();
		PrintTest test = loader.load(getClass().getResourceAsStream("/plaintext-insert-results.yml"));
		List<DynamicTest> tests = new ArrayList<>();
		test.getTests().forEach(testInstance -> {

			// Add the left insert test
			tests.add(DynamicTest.dynamicTest(insertTestDescription(testInstance, "insert left"),
					() -> testInsert(testInstance, PrintFormatBuilder::insertLeft, PrintTest.TestInstance::getResultLeft)));

			// Add the right insert test
			tests.add(DynamicTest.dynamicTest(insertTestDescription(testInstance, "insert right"),
					() -> testInsert(testInstance, PrintFormatBuilder::insertRight, PrintTest.TestInstance::getResultRight)));

			// Add the center insert test
			tests.add(DynamicTest.dynamicTest(insertTestDescription(testInstance, "insert center"),
					() -> testInsert(testInstance, PrintFormatBuilder::insertCenter, PrintTest.TestInstance::getResultCenter)));
		});
		return tests;
	}


	@TestFactory
	public List<DynamicTest> testAlignment() throws IOException {
		PrintTestLoader loader = new PrintTestLoader();
		PrintTest test = loader.load(getClass().getResourceAsStream("/plaintext-align-results.yml"));
		List<DynamicTest> tests = new ArrayList<>();
		test.getTests().forEach(testInstance -> {

			// Add the left insert test
			tests.add(DynamicTest.dynamicTest(alignTestDescription(testInstance, "left"),
					() -> testAlignment(testInstance, PrintFormatBuilder::left, PrintTest.TestInstance::getResultLeft)));

			// Add the right insert test
			tests.add(DynamicTest.dynamicTest(alignTestDescription(testInstance, "right"),
					() -> testAlignment(testInstance, PrintFormatBuilder::right, PrintTest.TestInstance::getResultRight)));

			// Add the center insert test
			tests.add(DynamicTest.dynamicTest(alignTestDescription(testInstance, "center"),
					() -> testAlignment(testInstance, PrintFormatBuilder::center, PrintTest.TestInstance::getResultCenter)));
		});
		return tests;
	}

	@Test
	public void testTester(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(5);
		builder.center("123456798");
		String result = (String)builder.getFormat();
		System.out.println(result);
	}
}