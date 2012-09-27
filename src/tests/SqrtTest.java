package tests;

import junit.framework.TestCase;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.binaryExpressions.Division;
import expressions.unaryExpressions.CubeRoot;
import expressions.unaryExpressions.Sqrt;

public class SqrtTest extends TestCase {
	public void testSimple() {
		assertEquals(new Sqrt(new Number(2.0)).toString(), "(\u221A2.0)");
	}

	public void testSimple2() {
		assertEquals(
				new Sqrt(new Addition(new Number(2), new Number(3.0))).toString(),
				"(\u221A(2.0+3.0))");
	}

	public void testSimple3() {
		assertEquals(new Division(new CubeRoot(new Number(2)), new CubeRoot(
				new Number(3.0))).toString(), "((\u221B2.0)/(\u221B3.0))");
	}

	public void testCubeRoot() {
		assertEquals(new CubeRoot(new Number(2)).toString(), "(\u221B2.0)");
	}

	public void testCubeRootSimple2() {
		assertEquals(
				new CubeRoot(new Addition(new Number(2.0), new Number(3.0)))
						.toString(),
				"(\u221B(2.0+3.0))");
	}

	public void testCubeRootSimple3() {
		assertEquals(new Division(new CubeRoot(new Number(2.0)), new CubeRoot(
				new Number(3.0))).toString(), "((\u221B2.0)/(\u221B3.0))");
	}
}
