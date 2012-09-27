package tests;

import junit.framework.TestCase;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.unaryExpressions.Factorial;

public class FactiorialTest extends TestCase {
	public void testSimple() {
		Factorial fac = new Factorial(new Number(2));
		assertEquals(fac.toString(), "2.0!");
	}

	public void testAddition() {
		Factorial fac = new Factorial(new Addition(new Number(21), new Number(
				12)));
		assertEquals(fac.toString(), "(21.0+12.0)!");
	}
}
