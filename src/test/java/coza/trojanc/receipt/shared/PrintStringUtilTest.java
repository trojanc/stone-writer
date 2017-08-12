package coza.trojanc.receipt.shared;

import coza.trojanc.receipt.loader.Loader;
import coza.trojanc.receipt.loader.YamlLoader;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by charl on 2016/10/14.
 */
public class PrintStringUtilTest {


	interface PrintInsertFunction<B, I, V, W> {
		void apply(B b, I i, V v, W w);
	}

	private void line(){
		System.out.println(PrintStringUtil.createStringOfChar(30, '-'));
	}

	private static String insertTestDescription(PrintStringUtilTestCases.TestInstance testInstance, String placement){
		return String.format("%d chars %s at %d with %d", testInstance.getText().length(), placement, testInstance.getIndex(), testInstance.getLineWidth());
	}

	private void testInsert(PrintStringUtilTestCases.TestInstance testInstance,
							PrintInsertFunction<char[], Integer, String, Integer> function,
							Function<PrintStringUtilTestCases.TestInstance, String> resultFunction){
		line();
		final char[] buffer = PrintStringUtil.getLineBuffer(testInstance.getLineWidth());
		function.apply(buffer, testInstance.getIndex(), testInstance.getText(), testInstance.getMaxLength());
		System.out.println("|" + new String(buffer) + "|");
		assertArrayEquals(resultFunction.apply(testInstance).replaceAll("\\|", "").toCharArray(), buffer);
	}

	@TestFactory
	public List<DynamicTest> testInsert() throws IOException {
		YamlLoader<PrintStringUtilTestCases> loader = new PrintStringUtilTestCasesLoader();
		PrintStringUtilTestCases test = loader.load(getClass().getResourceAsStream("/stringutil-results.yml"));
		List<DynamicTest> tests = new ArrayList<>();
		test.getTests().forEach(testInstance -> {

			// Add the left insert test
			tests.add(DynamicTest.dynamicTest(insertTestDescription(testInstance, "insert left"),
					() -> testInsert(testInstance, PrintStringUtil::insertLeftAligned, PrintStringUtilTestCases.TestInstance::getResultLeft)));

			// Add the right insert test
			tests.add(DynamicTest.dynamicTest(insertTestDescription(testInstance, "insert right"),
					() -> testInsert(testInstance, PrintStringUtil::insertRightAligned, PrintStringUtilTestCases.TestInstance::getResultRight)));

			// Add the center insert test
			tests.add(DynamicTest.dynamicTest(insertTestDescription(testInstance, "insert center"),
					() -> testInsert(testInstance, PrintStringUtil::insertCenterAligned, PrintStringUtilTestCases.TestInstance::getResultCenter)));
		});
		return tests;
	}


	@Test
	public void indexLeft5p1(){
		final int index = PrintStringUtil.indexLeft(5, 1);
		assertEquals(1, index);
	}

	@Test
	public void indexLeft5p0(){
		final int index = PrintStringUtil.indexLeft(5, 0);
		assertEquals(0, index);
	}

	@Test
	public void indexLeft5p10(){
		assertThrows(IllegalArgumentException.class, () -> {
			final int index = PrintStringUtil.indexLeft(5, 10);
			assertEquals(0, index);
		});
	}

	@Test
	public void indexLeft5p_10(){
		assertThrows(IllegalArgumentException.class, () -> {
			PrintStringUtil.indexLeft(5, -5);
		});
	}

	@Test
	public void indexLeft5p_5(){
		assertThrows(IllegalArgumentException.class, () -> {
			PrintStringUtil.indexLeft(5, -5);
		});
	}

	@Test
	public void indexLeft5p_1(){
		final int index = PrintStringUtil.indexLeft(5, -1);
		assertEquals(3, index);
	}

	@Test
	public void indexLeft5p_2(){
		final int index = PrintStringUtil.indexLeft(5, -2);
		assertEquals(2, index);
	}

	@Test
	public void maxStrLengthCenter0Width5(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(5, 0);
		assertEquals(1, maxSize);
	}

	@Test
	public void maxStrLengthCenter0Width6(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(6, 0);
		assertEquals(1, maxSize);
	}

	@Test
	public void maxStrLengthCenter1Width5(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(5, 1);
		assertEquals(3, maxSize);
	}

	@Test
	public void maxStrLengthCenter1Width6(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(6, 1);
		assertEquals(3, maxSize);
	}

	@Test
	public void maxStrLengthCenter2Width5(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(5, 2);
		assertEquals(5, maxSize);
	}

	@Test
	public void maxStrLengthCenter2Width6(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(6, 2);
		assertEquals(5, maxSize);
	}

	@Test
	public void maxStrLengthCenter3Width5(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(5, 3);
		assertEquals(3, maxSize);
	}

	@Test
	public void maxStrLengthCenter4Width5(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(5, 4);
		assertEquals(1, maxSize);
	}

	@Test
	public void maxStrLengthCenter4Width6(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(6, 4);
		assertEquals(3, maxSize);
	}

	@Test
	public void maxStrLengthCenter5Width6(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(6, 5);
		assertEquals(1, maxSize);
	}

	@Test
	public void maxStrLengthCenter3Width6(){
		final int maxSize = PrintStringUtil.maxStrLengthCenter(6, 3);
		assertEquals(5, maxSize);
	}

	@Test
	public void createRightPaddedString() throws Exception {
		final String value = PrintStringUtil.createRightPaddedString("123", 10, 'x');
		final String expected = "123xxxxxxx";
		System.out.println(">" + value + "<");
		assertEquals(expected, value);
	}

	@Test
	public void createStringOfChar() throws Exception {
		final String value = PrintStringUtil.createStringOfChar(10, 'x');
		final String expected = "xxxxxxxxxx";
		System.out.println(">" + value + "<");
		assertEquals(expected, value);
	}

	@Test
	public void createLeftPaddedString() throws Exception {
		final String value = PrintStringUtil.createLeftPaddedString("123", 10, '-');
		final String expected = "-------123";
		System.out.println(">" + value + "<");
		assertEquals(expected, value);
	}

	@Test
	public void getLines() throws Exception {
		final String[] value = PrintStringUtil.getLines("this is a very long line", 6, "|");
		final String[] expected = {"this", "is a", "very", "long", "line"};
		Arrays.asList(value).forEach(item -> System.out.println(">" + item + "<"));
		assertArrayEquals(expected, value);
	}

	@Test
	public void getLinesWithNewLines() throws Exception {
		final String[] value = PrintStringUtil.getLines("this is a very long |But should be good|", 20, "|");
		// TODO there is a bug here, a blank line should not be added
		final String[] expected = {"this is a very long", "", "But should be good"};
		Arrays.asList(value).forEach(item -> System.out.println(">" + item + "<"));
		assertArrayEquals(expected, value);
	}

	@Test
	public void rtrim() throws Exception {
		final String value = PrintStringUtil.rtrim("123   ");
		final String expected = "123";
		System.out.println(">" + value + "<");
		assertEquals(expected, value);
	}

	@Test
	public void rtrimWithLeftPadding() throws Exception {
		final String value = PrintStringUtil.rtrim("   123   ");
		final String expected = "   123";
		System.out.println(">" + value + "<");
		assertEquals(expected, value);
	}

	@Test
	public void left() throws Exception {
		final String value = PrintStringUtil.left("1234567890", 3);
		final String expected = "123";
		System.out.println(">" + value + "<");
		assertEquals(expected, value);
	}

	@Test
	public void trimRight() throws Exception {
		final String value = PrintStringUtil.trimRight("123   ");
		final String expected = "123";
		System.out.println(">" + value + "<");
		assertEquals(expected, value);
	}

	@Test
	public void trimRightWithLeftPadding() throws Exception {
		final String value = PrintStringUtil.trimRight("   123   ");
		final String expected = "   123";
		System.out.println(">" + value + "<");
		assertEquals(expected, value);
	}

}