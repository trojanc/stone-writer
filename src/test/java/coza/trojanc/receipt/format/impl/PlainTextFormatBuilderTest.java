package coza.trojanc.receipt.format.impl;

import coza.trojanc.receipt.format.PrintFormatBuilder;
import coza.trojanc.receipt.shared.LineWrap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Charl-PC on 2016-11-01.
 */
public class PlainTextFormatBuilderTest {

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
		assertEquals("LEFTY", result);
	}

	@Test
	public void testCenterEven(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.center("CENTER");

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("       CENTER       ", result);
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
		assertEquals("    123       456   \n", result);
	}

	@Test
	public void testInsertCenterLongWrapLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertCenter("123", 5)
			.insertCenter("123456789012345678901234567890", 15, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("    123      12345  \n" +
				"             67890  \n" +
				"             12345  \n" +
				"             67890  \n" +
				"             12345  \n" +
				"             67890  \n", result);
	}

	@Test
	public void testInsertCenterLongWrapLineWithSpaces(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertCenter("123", 5)
			.insertCenter("This is a very long piece of text I would like to wrap", 15, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("    123      This   \n" +
				"             is a   \n" +
				"             very   \n" +
				"             long   \n" +
				"             piece  \n" +
				"              of    \n" +
				"             text   \n" +
				"               I    \n" +
				"             would  \n" +
				"             like   \n" +
				"              to    \n" +
				"             wrap   \n", result);
	}

	@Test
	public void testInsertCenter1LongWrapLineLeft(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(5);
		builder.insertCenter("value", 1, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("valu \ne    \n", result);
	}

	@Test
	public void testInsertCenterLongWrapLineLeft(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertCenter("12345678901234567890123456789012345678901234567890", 15, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("    1123456789012345\n" +
				"67\n" +
				"12345678901234567890\n", result);
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
	public void testInsertLeftFitSameLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
			.insertLeft("456", 10);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       456       \n", result);
	}

	@Test
	public void testInsertLeftLongWrapLine(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
			.insertLeft("123456789012345678901234567890", 10, LineWrap.WRAP);

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       1234567890\n          1234567890\n          1234567890\n", result);
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
				"          wrap      \n", result);
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
	public void testInsertLeftLongCutLineLeftSpaces(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.insertLeft("123", 0)
			.insertLeft("This is a very long piece of text I would like to wrap", 10, LineWrap.WRAP_LEFT).nl();

		String result = (String)builder.getFormat();
		System.out.println(result);
		assertEquals("123       This is a \nvery long piece of  \ntext I would like   \nto wrap             \n", result);
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
	public void testSample(){
		PrintFormatBuilder builder = new PlainTextFormatBuilder(20);
		builder.right("Hello");
		builder.nl();
		builder.left("LEFTY");
		builder.nl();
		builder.center("CENTER");

		String result = (String)builder.getFormat();
		String expected = new StringBuilder()
			.append("               Hello\n")
			.append("LEFTY\n")
			.append("       CENTER       ")
			.toString();


		System.out.println(result);
		assertEquals(expected, result);
	}

}