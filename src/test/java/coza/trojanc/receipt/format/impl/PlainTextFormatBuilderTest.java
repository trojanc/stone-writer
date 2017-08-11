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

	interface PrintFunction<B, T, I, W> {
		B apply(B b, T t, I u, W v);
	}

	private void testInsert(PrintTest.TestInstance testInstance,
							PrintFunction<PrintFormatBuilder, String, Integer, LineWrap> function,
							Function<PrintTest.TestInstance, String> resultFunction){
		line();
		PrintFormatBuilder builder = new PlainTextFormatBuilder(testInstance.getLineWidth());
		function.apply(builder, testInstance.getText(), testInstance.getIndex(), testInstance.getLineWrap());
		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(resultFunction.apply(testInstance).replaceAll("\\|", ""), result);
	}

//	private void testAlignment(PrintTest.TestInstance testInstance,
//							Function<PrintFormatBuilder, String> function,
//							Function<PrintTest.TestInstance, String> resultFunction){
//		line();
//		PrintFormatBuilder builder = new PlainTextFormatBuilder(testInstance.getLineWidth());
//		function.apply(builder, testInstance.getText());
//		String result = (String)builder.getFormat();
//		System.out.println(result);
//		assertEquals(resultFunction.apply(testInstance).replaceAll("\\|", ""), result);
//	}

	private static String testDescription(PrintTest.TestInstance testInstance, String placement){
		return String.format("%s line %d chars %s at %d with %d",  testInstance.getLineWrap(), testInstance.getText().length(), placement, testInstance.getIndex(), testInstance.getLineWidth());
	}

	@TestFactory
	public List<DynamicTest> insertAtTests() throws IOException {
		PrintTestLoader loader = new PrintTestLoader();
		PrintTest test = loader.load(getClass().getResourceAsStream("/line-test.yml"));
		List<DynamicTest> tests = new ArrayList<>();
		test.getTests().forEach(testInstance -> {

			// Add the left insert test
			tests.add(DynamicTest.dynamicTest(testDescription(testInstance, "insert left"),
					() -> testInsert(testInstance, PrintFormatBuilder::insertLeft, PrintTest.TestInstance::getResultInsertLeft)));

			// Add the right insert test
			tests.add(DynamicTest.dynamicTest(testDescription(testInstance, "insert right"),
					() -> testInsert(testInstance, PrintFormatBuilder::insertRight, PrintTest.TestInstance::getResultInsertRight)));

			// Add the center insert test
			tests.add(DynamicTest.dynamicTest(testDescription(testInstance, "insert center"),
					() -> testInsert(testInstance, PrintFormatBuilder::insertCenter, PrintTest.TestInstance::getResultInsertCenter)));
		});
		return tests;
	}


	@Test
	public void testSample(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.right("Hello");
		builder.nl();
		builder.left("LEFTY");
		builder.nl();
		builder.center("CENTER");

		String result = (String)builder.getFormat();
		String expected =
				"               Hello\n" +
						"LEFTY               \n" +
						"       CENTER       ";


		System.out.println(result);
		assertEquals(expected, result);
	}

}