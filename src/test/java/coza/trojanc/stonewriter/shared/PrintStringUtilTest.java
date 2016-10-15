package coza.trojanc.stonewriter.shared;

import org.junit.Test;

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
        assertEquals(expected, value);
    }

    @Test
    public void createStringOfChar() throws Exception {

    }

    @Test
    public void createLeftPaddedString() throws Exception {

    }

    @Test
    public void nameValue() throws Exception {

    }

    @Test
    public void insertRightAligned() throws Exception {

    }

    @Test
    public void getLines() throws Exception {

    }

    @Test
    public void rtrim() throws Exception {

    }

    @Test
    public void left() throws Exception {

    }

    @Test
    public void trimRight() throws Exception {

    }

}