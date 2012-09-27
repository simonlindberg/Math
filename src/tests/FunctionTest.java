package tests;

import junit.framework.TestCase;
import session.Session;
import expressions.Function;
import expressions.Number;

public class FunctionTest extends TestCase {
    Session se = new Session();

    public void testCreateFunc() {
	assertEquals("Function f(a) is defined", se.input("f(a) = sin(a)"));
    }

    public void testCreateUseFunc() {
	se.input("g(a) = sin(a)");
	assertEquals("g(0) = 0.0", se.input("g(0)"));
    }

    public void testOverrideFunc() {
	se.input("a(a) = a+6");
	se.input("a(a) = 14+a");
	assertEquals("a(6) = 20.0", se.input("a(6)"));
    }

    public void testConstantFunc() {
	se.input("t(a) = a+6");
	se.input("b = 14");
	assertEquals("t(b) = 20.0", se.input("t(b)"));
    }

    public void testConstantInFunc() {
	se.input("t(a) = a+6+b");
	se.input("b = 14");
	assertEquals("t(5) = 25.0", se.input("t(5)"));
    }

    public void testMultipleFunc() {
	se.input("q(a,b) = a*b");
	int a = 20;
	int b = 2;
	double ab = a * b;
	assertEquals("q(" + a + "," + b + ") = " + ab,
		se.input("q(" + a + "," + b + ")"));
    }

    public void testEquals() {
	Function f = new Function("f", null, null);
	Function F = new Function("F", null, null);
	assertFalse(f.equals(F));
	assertTrue(f.equals(f));
	assertFalse(f.equals(null));
	assertFalse(f.equals(new Number(0)));
	assertEquals(f.hashCode(), f.hashCode());
	f = new Function(null, null, null);
	assertFalse(f.equals(F));
	F = new Function(null, null, null);
	assertTrue(f.equals(F));
	assertEquals(f.hashCode(), F.hashCode());
    }

    public void testParam() {
	se.input("f(a,b)=a*b");
	assertEquals("Error in function parameters", se.input("f(10)"));
    }

    public void testFuncInFunc() {
	se.input("f(a) = a*10");
	se.input("g(b) = b");
	assertEquals("f(g(2)) = 20.0", se.input("f(g(2))"));
    }
}
