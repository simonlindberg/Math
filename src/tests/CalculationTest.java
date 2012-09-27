package tests;

import junit.framework.TestCase;
import expressions.ExpressionException;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.binaryExpressions.Division;
import expressions.binaryExpressions.Exponential;
import expressions.binaryExpressions.Multiplication;
import expressions.binaryExpressions.Subtraction;
import expressions.unaryExpressions.CubeRoot;
import expressions.unaryExpressions.Factorial;
import expressions.unaryExpressions.Negation;
import expressions.unaryExpressions.Sqrt;
import expressions.unaryExpressions.trigonometrics.Cosine;
import expressions.unaryExpressions.trigonometrics.Sine;

public class CalculationTest extends TestCase {

	public void testAddNumber() throws ExpressionException {
		assertEquals(2.0,
				new Addition(new Number(1), new Number(1)).calculate());
	}

	public void testAddAdd() throws ExpressionException {
		assertEquals(10.0, new Addition(new Addition(new Number(1), new Number(
				2)), new Addition(new Number(3), new Number(4))).calculate());
	}

	public void testSqrtNumber() throws ExpressionException {
		assertEquals(2.0, new Sqrt(new Number(4)).calculate());
	}

	public void testNumber() throws ExpressionException {
		assertEquals(4.0, new Number(4).calculate());
	}

	public void testDivNumber() throws ExpressionException {
		assertEquals(2.0,
				new Division(new Number(4), new Number(2)).calculate());
	}

	public void testDivAddNumber() throws ExpressionException {
		assertEquals(2.0, new Division(new Addition(new Number(4),
				new Number(2)), new Number(3)).calculate());
	}

	public void testMulAddNumber() throws ExpressionException {
		assertEquals(18.0, new Multiplication(new Addition(new Number(4),
				new Number(2)), new Number(3)).calculate());
	}

	public void testSubAddNumber() throws ExpressionException {
		assertEquals(3.0, new Subtraction(new Addition(new Number(4),
				new Number(2)), new Number(3)).calculate());
	}

	public void testExp() throws ExpressionException {
		assertEquals(1024.0,
				new Exponential(new Number(2), new Number(10)).calculate());
	}

	public void testExpAdd() throws ExpressionException {
		assertEquals(1024.0, new Exponential(new Number(2), new Addition(
				new Number(3), new Number(7))).calculate());
	}

	public void testSqrtSqrtNumber() throws ExpressionException {
		assertEquals(2.0, new Sqrt(new Sqrt(new Number(16))).calculate());
	}

	public void testCubeNumber() throws ExpressionException {
		assertEquals(2.0, new CubeRoot(new Number(8)).calculate());
	}

	public void testNegNegNumber() throws ExpressionException {
		assertEquals(2.0, new Negation(new Negation(new Number(2))).calculate());
	}

	public void testNegNumber() throws ExpressionException {
		assertEquals(-2.0, new Negation(new Number(2)).calculate());
	}

	public void testFac2() throws ExpressionException {
		assertEquals(2.0, new Factorial(new Number(2)).calculate());
	}

	public void testFac1() throws ExpressionException {
		assertEquals(1.0, new Factorial(new Number(1)).calculate());
	}

	public void testFacNumber() throws ExpressionException {
		assertEquals(2 * 3 * 4 * 5.0, new Factorial(new Number(5)).calculate());
	}

	public void testSin() throws ExpressionException {
		assertEquals(0.8414709848078965, new Sine(new Number(1)).calculate());
	}

	public void testSin2() throws ExpressionException {
		assertEquals(1.0, new Sine(new Number(Math.toRadians(90))).calculate());
	}

	public void testCos() throws ExpressionException {
		assertEquals(Math.cos(2), new Cosine(new Number(2)).calculate());
	}
}
