package coza.trojanc.receipt.shared;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by charl on 2016/10/14.
 */
public class PrintStringUtilTest {

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
	public void insertLeftAligned9() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = {' ',' ','5','6','7','8','9',' ',' ',' '};
		PrintStringUtil.insertLeftAligned(buffer, 2, "123456789", 5);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertLeftAligned3() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = {' ',' ','7','8','9',' ',' ',' ',' ',' '};
		PrintStringUtil.insertLeftAligned(buffer, 2, "123456789", 3);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAligned() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = {' ',' ',' ', ' ','1','2','3',' ',' ',' '};
		PrintStringUtil.insertCenterAligned(buffer, 10/2, "123", 3);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAligned3chars10Line() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = {' ',' ',' ',' ','1','2','3',' ',' ',' '};
		PrintStringUtil.insertCenterAligned(buffer, "123", 3);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAligned13chars10Line() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = "2345678901".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, "1234567890123", 10);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAligned14chars10Line() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = "3456789012".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, "12345678901234", 10);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAligned14chars10LineMax5() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = "   56789  ".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, "12345678901234", 5);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAligned13chars10LineMax5() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = "   56789  ".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, "1234567890123", 5);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAligned5chars10LineMax3() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(5);
		final char[] expected = " alu ".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, "value", 3);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenter1Aligned5chars10LineMax3() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(5);
		final char[] expected = "alu  ".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, 1, "value", 3);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenter1Aligned5chars10LineMax5() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(5);
		final char[] expected = "alue ".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, 1, "value", 5);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenter3Aligned5chars10LineMax3() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(5);
		final char[] expected = "  alu".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, 3, "value", 3);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenter3Aligned5chars10LineMax5() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(5);
		final char[] expected = " valu".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, 3, "value", 5);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}


	@Test
	public void insertCenter3Aligned13chars10LineMax5() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = " 56789    ".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, 3, "1234567890123",5);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenter3Aligned14chars10LineMax5() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = " 56789    ".toCharArray();
		PrintStringUtil.insertCenterAligned(buffer, 3, "12345678901234", 5);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAligned3chars9Line() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(9);
		final char[] expected = {' ',' ',' ','1','2','3',' ',' ',' '};
		PrintStringUtil.insertCenterAligned(buffer, "123", 3);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAlignedAt1With3Chars10Line() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = {'1','2','3',' ',' ',' ',' ',' ',' ',' '};
		PrintStringUtil.insertCenterAligned(buffer, 1, "123", 3);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
	}

	@Test
	public void insertCenterAlignedAt1With5Chars10Line() throws Exception {
		char[] buffer = PrintStringUtil.getLineBuffer(10);
		final char[] expected = {'2','3','4','5',' ',' ',' ',' ',' ',' '};
		PrintStringUtil.insertCenterAligned(buffer, 1, "12345", 5);
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
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
	public void insertRightAligned() throws Exception {
		final int LINE_SIZE = 10;
		char[] buffer = PrintStringUtil.getLineBuffer(LINE_SIZE);
		PrintStringUtil.insertRightAligned(buffer, LINE_SIZE-1, "test", LINE_SIZE);
		final char[] expected = "      test".toCharArray();
		System.out.println(">" + new String(buffer) + "<");
		assertArrayEquals(expected, buffer);
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