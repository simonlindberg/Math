package tests;

import java.io.IOException;

import junit.framework.TestCase;
import parse.ExprParser;
import session.Session;

public class SessionTest extends TestCase {

	Session se = new Session();

	public void testInput() {
		assertEquals("Error in input", se.input(""));
		assertEquals("Error in input", se.input("d123asd998"));
		assertEquals("2 = 2.0", se.input("2"));
		assertEquals("2*(5+7) = 24.0", se.input("2*(5+7)"));
	}

	public void testConstantsWhiteSpaces() {
		assertEquals("b = 1.0", se.input("b = 1"));
		assertEquals("a = 2.0", se.input("a=2"));
		assertEquals("a = 3.0", se.input("a= 3"));
		assertEquals("a = 4.0", se.input("a =4"));
	}

	public void testWordAsCons() {
		assertEquals("FUCK = 3.0", se.input("FUCK=3"));
		assertEquals("FUCKme = 3.0", se.input("FUCKme=3"));
	}

	public void testDecimal() {
		assertEquals("a = 3.14", se.input("a=3.14"));
		assertEquals("3.14*2 = 6.28", se.input("3.14*2"));
		assertEquals("2,0 = 2.0", se.input("2,0"));
	}

	public void testUsingConstants() {
		se.input("a=2");
		assertEquals("a*2 = 4.0", se.input("a*2"));
		se.input("a=2");
		se.input("a=5!");
		assertEquals("a = 120.0", se.input("a"));
		se.input("b=cube(8)");
		assertEquals("a*b = 240.0", se.input("a*b"));
	}

	public void testInvalidInput() {
		se = new Session();
		se.input("a =10");
		assertEquals("Wrong use of constant", se.input("asd"));
		assertEquals("Wrong use of constant", se.input("a2"));
		assertEquals("Wrong use of constant", se.input("20as"));

	}

	public void testMultipleCons() {
		se = new Session();
		se.input("a=2-1");

		se.input("b=4*2");

		se.input("c=4+4");

		se.input("d=69");

		assertEquals("a+b+c+d = 86.0", se.input("a+b+c+d"));
	}

	public void testInvalidConsName() throws IOException {
		ExprParser ep = new ExprParser("");
		for (String invalid : ep.getValidInputs()) {
			assertEquals(invalid + " is an invalid constant name",
					se.input(invalid + " = 2*3"));
		}
		assertEquals("e is an invalid constant name", se.input("e = 2*3"));
	}

	public void testFalseCons() {
		assertEquals("Error in input", se.input("a = "));
	}
}
