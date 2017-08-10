package coza.trojanc.receipt.format.impl;

import coza.trojanc.receipt.format.PrintFormatBuilder;
import coza.trojanc.receipt.shared.LineWrap;
import coza.trojanc.receipt.shared.PrintStringUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Charl-PC on 2016-11-01.
 */
public class PlainTextFormatBuilderTest {
	@Before
	public void line(){
		System.out.println(PrintStringUtil.createStringOfChar(30, '-'));
	}

	@Test
	public void testRight(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.right("Hello");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("               Hello", result);
	}

	/**
	 * Test that long lines are split without breaking words
	 */
	@Test
	public void testRightLongWordsLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.right("Hello, this is a really long line");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("    Hello, this is a\n    really long line", result);
	}

	/**
	 * Test that long text are forcefully split over lines
	 */
	@Test
	public void testRightLongTextLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.right("012345678901234567890123456789");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("01234567890123456789\n          0123456789", result);
	}

	@Test
	public void testLeft(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.left("LEFTY");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("LEFTY               ", result);
	}

	@Test
	public void testCenter6Line20(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.center("CENTER");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("       CENTER       ", result);
	}

	@Test
	public void testCenter6Line8(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(8);
		builder.center("CENTER");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(" CENTER ", result);
	}

	@Test
	public void testCenter6Line7(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(7);
		builder.center("CENTER");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("CENTER ", result);
	}

	@Test
	public void testCenterUneven(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.center("CENTER1");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("       CENTER1      ", result);
	}

	@Test
	public void testCenterLeftFitSameLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertCenter("123", 5)
				.insertCenter("456", 15);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("    123       456   ", result);
	}

	@Test
	public void testInsert20Center15Long30WrapLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertCenter("123", 5)
				.insertCenter("123456789012345678901234567890", 15, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"    123    123456789\n" +
				"           012345678\n" +
				"           901234567\n" +
				"              890   ", result);
	}

	@Test
	public void testInsert5Center1Long5WrapLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(5);
		builder.insertCenter("value", 1, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("val  \n" +
				"ue   ", result);
	}

	@Test
	public void testInsert5Center1Long10WrapLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(5);
		builder.insertCenter("abcdefghij", 1, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("abc  \n" +
				"def  \n" +
				"ghi  \n" +
				" j   ", result);
	}

	@Test
	public void testInsertCenterLongWrapLineWithSpaces(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertCenter("123", 5)
				.insertCenter("This is a very long piece of text I would like to wrap", 15, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"    123     This is \n" +
				"            a very  \n" +
				"             long   \n" +
				"           piece of \n" +
				"            text I  \n" +
				"             would  \n" +
				"            like to \n" +
				"             wrap   ", result);
	}

	@Test
	public void testInsertCenter1LongWrapLineLeft(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(5);
		builder.insertCenter("value", 1, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"val  \n" +
				"ue   \n", result);
	}

	@Test
	public void testInsertCenterLongWrapLineLeft(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertCenter("12345678901234567890123456789012345678901234567890", 15, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"           123456789\n" +
				"01234567890123456789\n" +
				"01234567890123456789\n" +
				"0                   \n", result);
	}

	@Test
	public void testInsertCenterLongNoWrap(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertCenter("1234567890123456790", 15, LineWrap.NO_WRAP).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("      12345678901234\n", result);
	}

	@Test
	public void testInsertCenterLongCutLineLeftSpaces(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
				.insertLeft("This is a very long piece of text I would like to wrap", 10, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       This is a \nvery long piece of  \ntext I would like   \nto wrap             \n", result);
	}

	@Test
	public void testInsertLeftStart(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123                 ", result);
	}

	@Test
	public void testInsertLeftEnd(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 19);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"                   1\n" +
				"                   2\n" +
				"                   3", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertLeftPassedEnd(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 20);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"                   1\n" +
				"                   2\n" +
				"                   3", result);
	}

	@Test
	public void testInsertLeftFitSameLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
				.insertLeft("456", 10);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       456       ", result);
	}

	@Test
	public void testInsertLeftLongWrapLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
				.insertLeft("123456789012345678901234567890", 10, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       1234567890\n          1234567890\n          1234567890", result);
	}

	@Test
	public void testInsertLeftLongWrapLineWithSpaces(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
				.insertLeft("This is a very long piece of text I would like to wrap", 10, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       This is a \n" +
				"          very long \n" +
				"          piece of  \n" +
				"          text I    \n" +
				"          would     \n" +
				"          like to   \n" +
				"          wrap      ", result);
	}

	@Test
	public void testInsertLeftLongWrapLineLeft(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
				.insertLeft("123456789012345678901234567890123456789012345678901234567890", 10, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       1234567890\n12345678901234567890\n12345678901234567890\n1234567890          \n", result);
	}

	@Test
	public void testInsertRightLongWrapLineLeft(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertRight("123", 3)
				.insertRight("123456789012345678901234567890123456789012345678901234567890", 10, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"12345678901         \n" +
				"23456789012345678901\n" +
				"23456789012345678901\n" +
				"234567890           \n", result);
	}

	@Test
	public void testInsertLeftLongWrapLeftSpaces(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
				.insertLeft("This is a very long piece of text I would like to wrap", 10, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       This is a \nvery long piece of  \ntext I would like   \nto wrap             \n", result);
	}

	@Test
	public void testInsertRightLongWrapLeftSpaces(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertRight("123", 2)
				.insertRight("This is a very long piece of text I would like to wrap", 15, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"12This is a very    \n" +
				"long piece of text  \n" +
				"I would like to wrap\n", result);
	}

	@Test
	public void testInsertLeftFitSameLineMultipleLines(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
				.insertLeft("456", 10)
				.nl()
				.insertLeft("abc", 2)
				.insertLeft("xyz", 12)
				.nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       456       \n  abc       xyz     \n", result);
	}

	@Test
	public void testInsertRightFitSameLineMultipleLines(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertRight("123", 5)
				.insertRight("456", 10)
				.nl()
				.insertRight("abc", 5)
				.insertRight("xyz", 12)
				.insertRight("xyz", 19)
				.nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals(""+
				"   123  456         \n" +
				"   abc    xyz    xyz\n", result);
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