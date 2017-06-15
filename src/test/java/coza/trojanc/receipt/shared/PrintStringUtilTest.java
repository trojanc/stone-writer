package coza.trojanc.receipt.shared;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by charl on 2016/10/14.
 */
public class PrintStringUtilTest {


	@Test
	public void getLine() throws Exception {
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
	public void nameValue() throws Exception {

	}

	@Test
	public void insertRightAligned() throws Exception {
		final int LINE_SIZE = 10;
		char[] buffer = PrintStringUtil.getLineBuffer(LINE_SIZE);
		PrintStringUtil.insertRightAligned(buffer, LINE_SIZE-1, "test", LINE_SIZE);
		final char[] expected = {' ',' ',' ',' ',' ',' ','t','e','s','t'};
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