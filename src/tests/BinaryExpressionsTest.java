package tests;

import junit.framework.TestCase;
import expressions.Expression;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.binaryExpressions.Division;
import expressions.binaryExpressions.Exponential;
import expressions.binaryExpressions.Multiplication;

public class BinaryExpressionsTest extends TestCase {

	public void testExpWithSimpleNumber() {
		Exponential exp = new Exponential(new Number(2.0), Expression.NULL);
		assertEquals(exp.toString(), "2.0");
	}

	public void testSimpleExp() {
		Exponential exp = new Exponential(new Number(2.0), new Number(3.0));
		assertEquals(exp.toString(), "(2.0^3.0)");
	}

	public void testNumberWithExpAsExp() {
		Exponential exp = new Exponential(new Number(2.0), new Exponential(
				new Number(3.0), new Number(4.0)));
		assertEquals(exp.toString(), "(2.0^(3.0^4.0))");
	}

	public void testExpWithNumberAsExp() {
		Exponential exp = new Exponential(new Exponential(new Number(3.0),
				new Number(4.0)), new Number(2.0));
		assertEquals(exp.toString(), "((3.0^4.0)^2.0)");
	}

	public void testExpWithExpAsExp() {
		Exponential exp = new Exponential(new Exponential(new Number(2.0),
				new Number(3.0)), new Exponential(new Number(4.0),
				new Number(5)));
		assertEquals(exp.toString(), "((2.0^3.0)^(4.0^5.0))");
	}

	public void testAdding() {
		Addition add = new Addition(new Number(1), new Number(2.0));
		assertEquals("(1.0+2.0)", add.toString());
	}

	public void testEquals() {
		Expression exp = new Addition(new Number(66), new Number(99));
		Expression expr = new Addition(new Number(66), new Number(98));
		assertFalse(exp.equals(expr));
		assertTrue(exp.equals(exp));
		assertFalse(exp.equals(null));

		Expression exp1 = new Addition(new Number(66), null);

		assertFalse(exp1.equals(exp));
		Expression nil = new Addition(null, new Number(9));
		assertFalse(nil.equals(exp));
		nil = new Addition(new Number(9), null);
		assertFalse(nil.equals(exp));
	}

	public void testHash() {
		Expression exp = new Addition(new Number(2), new Number(1));
		assertEquals(exp.hashCode(), exp.hashCode());
		exp = new Addition(null, null);
		assertEquals(exp.hashCode(), exp.hashCode());
	}

	public void testAddNumber() {
		Addition exp = new Addition(new Number(2.0), new Addition(new Number(
				3.0), new Number(4.0)));
		assertEquals(exp.toString(), "(2.0+(3.0+4.0))");
	}

	public void testAddAdd() {
		Addition exp = new Addition(new Addition(new Number(3.0), new Number(
				4.0)), new Addition(new Number(3.0), new Number(4.0)));
		assertEquals(exp.toString(), "((3.0+4.0)+(3.0+4.0))");
	}

	public void testSimpleDiv() {
		Division exp = new Division(new Number(2.0), new Number(3.0));
		assertEquals(exp.toString(), "(2.0/3.0)");
	}

	public void testDivNumberAdd() {
		Division exp = new Division(new Number(2.0), new Addition(new Number(
				3.0), new Number(4.0)));
		assertEquals(exp.toString(), "(2.0/(3.0+4.0))");
	}

	public void testDiv() {
		Division exp = new Division(new Addition(new Number(3.0), new Number(
				4.0)), new Addition(new Number(3.0), new Number(4.0)));
		assertEquals(exp.toString(), "((3.0+4.0)/(3.0+4.0))");
	}

	public void testMultiply() {
		Multiplication exp = new Multiplication(new Number(2.0),
				new Multiplication(new Number(3.0), new Number(4.0)));
		assertEquals(exp.toString(), "(2.0*(3.0*4.0))");
	}
}
