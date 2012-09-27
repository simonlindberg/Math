package tests;

import junit.framework.TestCase;
import expressions.Constant;
import expressions.Expression;
import expressions.ExpressionException;
import expressions.Number;
import expressions.unaryExpressions.Sqrt;

public class ConstantTest extends TestCase {
	public void testConstEquals() {
		Constant c1 = new Constant("C", new Number(2));
		Constant c2 = new Constant("C", new Sqrt(new Number(1)));
		assertEquals(c1, c2);
		assertEquals(c1.hashCode(), c2.hashCode());
		c2 = new Constant("B", new Number(2));
		assertFalse(c1.equals((c2)));
		assertEquals(c1.hashCode(), c1.hashCode());
		c2 = new Constant(null, new Number(2));
		assertEquals(c2.hashCode(), c2.hashCode());
	}

	public void testToString() {
		Constant c1 = new Constant("C", new Number(2));
		assertEquals(c1.toString(), "C = 2.0");
		c1 = new Constant("C", Expression.NULL);
		assertEquals(c1.toString(), "C = NULL");
	}

	public void testCalculate() throws ExpressionException {
		Constant c1 = new Constant("C", new Number(2));
		assertEquals(c1.calculate(), 2.0);
		c1 = new Constant("C", new Sqrt(new Number(9)));
		assertEquals(c1.calculate(), 3.0);
	}

	public void testSetExpr() throws ExpressionException {
		Constant c1 = new Constant("C", new Number(2));
		c1.setExpression(new Sqrt(new Number(9)));
		assertFalse(c1.toString().equals((2.0)));
		assertEquals(c1.calculate(), 3.0);
	}

	public void testEquals() {
		Constant c = new Constant("C", new Number(2));
		assertTrue(c.equals(c));
		assertFalse(c.equals(null));
		assertFalse(c.equals(new Number(2)));
		assertFalse(new Constant(null, new Number(9)).equals(c));
		assertTrue(new Constant(null, new Number(9)).equals(new Constant(null,
				new Number(9))));
	}
	// public void testPItoString() {
	// try {
	// assertEquals("\u03C0", new Constant("pi").toString());
	// assertEquals("\u03C0", new Constant("PI").toString());
	// assertEquals("\u03C0", new Constant("Pi").toString());
	// } catch (Exception e) {
	// }
	// }
	//
	// public void testPHItoString() {
	// try {
	// assertEquals("\u03C6", new Constant("phi").toString());
	// assertEquals("\u03C6", new Constant("PHI").toString());
	// assertEquals("\u03C6", new Constant("Phi").toString());
	// } catch (Exception e) {
	// }
	// }
	//
	// public void testEtoString() {
	// try {
	// assertEquals("e", new Constant("e").toString());
	// } catch (Exception e) {
	// }
	// }
	//
	// public void testWrongE() {
	// try {
	// new Constant("E");
	// fail();
	// } catch (ExpressionException e) {
	// assertTrue(true);
	// }
	// }
}
