package tests;

import junit.framework.TestCase;
import session.Session;

public class ExactTrigvalues extends TestCase {
	private Session se = new Session();

	public void testExactSine() {
		assertEquals("sin(pi) = 0.0", se.input("sin(pi)"));
		assertEquals("sin(pi*3) = 0.0", se.input("sin(pi*3)"));
		assertEquals("sin(pi/2) = 1.0", se.input("sin(pi/2)"));
		assertEquals("sin(pi/3) = " + Math.sqrt(3) / 2, se.input("sin(pi/3)"));
		assertEquals("sin(pi/4) = " + Math.sqrt(2) / 2, se.input("sin(pi/4)"));
		assertEquals("sin(pi*(3/2)) = -1.0", se.input("sin(pi*(3/2))"));
		assertEquals("sin(pi/6) = 0.5", se.input("sin(pi/6)"));
	}

	public void testExactCos() {
		assertEquals("cos(pi) = -1.0", se.input("cos(pi)"));
		assertEquals("cos(pi/3) = 0.5", se.input("cos(pi/3)"));
		assertEquals("cos(pi/2) = 1.0", se.input("cos(pi/2)"));
		assertEquals("cos(pi/3) = 0.5", se.input("cos(pi/3)"));
		assertEquals("cos(pi/4) = " + Math.sqrt(2) / 2, se.input("cos(pi/4)"));
		assertEquals("cos(pi/6) = " + Math.sqrt(3) / 2, se.input("cos(pi/6)"));
		assertEquals("cos(pi*(3/2)) = 0.0", se.input("cos(pi*(3/2))"));
	}
}
