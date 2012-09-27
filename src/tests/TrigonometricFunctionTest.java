package tests;

import junit.framework.TestCase;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.binaryExpressions.Division;
import expressions.unaryExpressions.trigonometrics.Cosine;
import expressions.unaryExpressions.trigonometrics.Sine;

public class TrigonometricFunctionTest extends TestCase {
	public void testSimple() {
		Sine sin = new Sine(new Number(1.0));
		assertEquals(sin.toString(), "sin(1.0)");
	}

	public void testAdd() {
		Sine sin = new Sine(new Addition(new Number(1.0), new Number(1.0)));
		assertEquals(sin.toString(), "sin(1.0+1.0)");
	}

	public void testSineAddAdd() {
		Sine sin = new Sine(new Addition(new Number(1.0), new Addition(
				new Number(2.0), new Number(3.0))));
		assertEquals(sin.toString(), "sin(1.0+(2.0+3.0))");
	}

	public void testCosAddAdd() {
		Cosine sin = new Cosine(new Addition(new Number(1.0), new Addition(
				new Number(2.0), new Number(3.0))));
		assertEquals(sin.toString(), "cos(1.0+(2.0+3.0))");
	}

	public void testCosDivAdd() {
		Cosine sin = new Cosine(new Division(new Number(1.0), new Addition(
				new Number(2.0), new Number(3.0))));
		assertEquals(sin.toString(), "cos(1.0/(2.0+3.0))");
	}

	public void testAddCos() {
		Addition sin = new Addition(new Sine(new Number(9)), new Sine(
				new Number(2.0)));
		assertEquals(sin.toString(), "(sin(9.0)+sin(2.0))");
	}
}
