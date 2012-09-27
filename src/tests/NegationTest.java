package tests;

import junit.framework.TestCase;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.unaryExpressions.Negation;

public class NegationTest extends TestCase {
	public void testSimple() {
		Negation neg = new Negation(new Number(12));
		assertEquals(neg.toString(), "-12.0");
		neg = new Negation(neg);
		assertEquals(neg.toString(), "--12.0");
	}

	public void testNegateAddition() {
		Negation neg = new Negation(
				new Addition(new Number(12), new Number(21)));
		assertEquals(neg.toString(), "-(12.0+21.0)");
	}
}
