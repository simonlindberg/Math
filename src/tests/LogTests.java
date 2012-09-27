package tests;

import junit.framework.TestCase;
import expressions.ExpressionException;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.unaryExpressions.Ln;
import expressions.unaryExpressions.Log;

public class LogTests extends TestCase {
	public void testLogSimple() throws ExpressionException{
		assertEquals(0.0, new Log(new Number(1)).calculate());
		assertEquals(1.0, new Log(new Number(10)).calculate());
		assertEquals(0.9542425094393249, new Log(new Number(9)).calculate());

	}

	public void testLogToString() throws ExpressionException{
		assertEquals("(log 1.0)", new Log(new Number(1)).toString());
		assertEquals("(log 10.0)", new Log(new Number(10)).toString());
		assertEquals("(log(log 10.0))",
				new Log(new Log(new Number(10))).toString());
		assertEquals("(log(2.0+1.0))", new Log(new Addition(new Number(2),
				new Number(1))).toString());
	}

	public void testLnSimple() throws ExpressionException{
		assertEquals(0.0, new Ln(new Number(1)).calculate());
		assertEquals(2.302585092994046, new Ln(new Number(10)).calculate());
		assertEquals(2.1972245773362196, new Ln(new Number(9)).calculate());

	}

	public void testLnToString() {
		assertEquals("(ln 1.0)", new Ln(new Number(1)).toString());
		assertEquals("(ln 10.0)", new Ln(new Number(10)).toString());
		assertEquals("(ln(2.0+1.0))", new Ln(new Addition(new Number(2),
				new Number(1))).toString());
	}
}
