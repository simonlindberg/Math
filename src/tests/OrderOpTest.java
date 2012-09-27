package tests;

import junit.framework.TestCase;
import session.Session;

public class OrderOpTest extends TestCase {
	private Session se = new Session();

	public void testMul() {
		assertEquals("2+2*10 = 22.0", se.input("2+2*10"));
	}

	public void testSqrt() {
		assertEquals("5!+sqrt(9) = 123.0", se.input("5!+sqrt(9)"));
	}
	public void testSqrtMul() {
		assertEquals("5!*sqrt(9) = 360.0", se.input("5!*sqrt(9)"));
	}
	public void testSqrtExp() {
		assertEquals("3!^sqrt(9) = 216.0", se.input("3!^sqrt(9)"));
	}

	public void testExp() {
		assertEquals("2^2*3! = 4096.0", se.input("2^2*3!"));
	}

	public void testExps() {
		assertEquals("2^9+1 = 1024.0", se.input("2^9+1"));
	}

	public void testExp2() {
		assertEquals("sqrt(3+6) = 3.0", se.input("sqrt(3+6)"));
	}

}