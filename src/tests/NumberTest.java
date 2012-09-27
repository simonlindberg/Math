package tests;

import junit.framework.TestCase;
import expressions.Expression;
import expressions.ExpressionException;
import expressions.Number;

public class NumberTest extends TestCase {

	public void testConstrutor() {
		Number num = new Number(111.0);
		assertEquals("111.0", num.toString());
	}

	public void testLarge() {
		Number num = new Number(111111.0);
		assertEquals("111111.0", num.toString());
	}

	public void testLarge5() {
		Number num = new Number(1.7976931348623157E308);
		assertEquals("1.7976931348623157E308", num.toString());
	}

	public void testEquals() {
		Number num = new Number(111.0);
		assertTrue(num.equals(num));
		assertFalse(num.equals(null));
	}

	public void testExprNULL() throws ExpressionException {
		assertEquals("NULL", Expression.NULL.toString());
	}
}
