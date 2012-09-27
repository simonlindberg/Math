package tests;

import java.io.IOException;

import junit.framework.TestCase;
import parse.ExprParser;
import expressions.Expression;
import expressions.ExpressionException;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.binaryExpressions.Division;
import expressions.binaryExpressions.Exponential;
import expressions.binaryExpressions.Multiplication;
import expressions.binaryExpressions.Subtraction;
import expressions.unaryExpressions.CubeRoot;
import expressions.unaryExpressions.Factorial;
import expressions.unaryExpressions.Ln;
import expressions.unaryExpressions.Log;
import expressions.unaryExpressions.Negation;
import expressions.unaryExpressions.Sqrt;

public class ParserTest extends TestCase {
	private ExprParser ep;

	public void testNullNumber() throws IOException, ExpressionException {
		ep = new ExprParser("");
		assertEquals(ep.build(), Expression.NULL);
	}

	public void testNumber() throws IOException, ExpressionException {
		ep = new ExprParser("2");
		assertEquals(ep.build(), new Number(2));
		ep = new ExprParser("9001");
		assertEquals(ep.build(), new Number(9001));
		ep = new ExprParser("10E18");
		assertEquals(ep.build(), new Number(10E18));
	}

	public void testAdd() throws IOException, ExpressionException {
		ep = new ExprParser("2+3");
		assertEquals(ep.build(), new Addition(new Number(2), new Number(3)));
	}

	public void testAddAdd() throws IOException, ExpressionException {
		ep = new ExprParser("1+(2+3)");
		assertEquals(ep.build(), new Addition(new Number(1), new Addition(
				new Number(2), new Number(3))));
	}

	public void testAddAdd2() throws IOException, ExpressionException {
		ep = new ExprParser("(1+2)+3");
		assertEquals(ep.build(), new Addition(new Addition(new Number(1),
				new Number(2)), new Number(3)));
	}

	public void testMul() throws IOException, ExpressionException {
		ep = new ExprParser("100*5");
		assertEquals(ep.build(), new Multiplication(new Number(100),
				new Number(5)));
	}

	public void testMulmul() throws IOException, ExpressionException {
		ep = new ExprParser("100*5");

		ep = new ExprParser("1*(2*3)");
		assertEquals(ep.build(), new Multiplication(new Number(1),
				new Multiplication(new Number(2), new Number(3))));
	}

	public void testMulMul2() throws IOException, ExpressionException {
		ep = new ExprParser("(1*2)*3");
		assertEquals(ep.build(), new Multiplication(new Multiplication(
				new Number(1), new Number(2)), new Number(3)));
	}

	public void testSub() throws IOException, ExpressionException {
		ep = new ExprParser("100-5");
		assertEquals(ep.build(),
				new Subtraction(new Number(100), new Number(5)));
	}

	public void testSubSub() throws IOException, ExpressionException {
		ep = new ExprParser("1-(2-3)");
		assertEquals(ep.build(), new Subtraction(new Number(1),
				new Subtraction(new Number(2), new Number(3))));
	}

	public void testSubSub2() throws IOException, ExpressionException {
		ep = new ExprParser("(1-2)-3");
		assertEquals(ep.build(), new Subtraction(new Subtraction(new Number(1),
				new Number(2)), new Number(3)));
	}

	public void testDiv() throws IOException, ExpressionException {
		ep = new ExprParser("100/5");
		assertEquals(ep.build(), new Division(new Number(100), new Number(5)));
	}

	public void testDivDiv() throws IOException, ExpressionException {
		ep = new ExprParser("1/(2/3)");
		assertEquals(ep.build(), new Division(new Number(1), new Division(
				new Number(2), new Number(3))));
	}

	public void testDivDiv2() throws IOException, ExpressionException {
		ep = new ExprParser("(1/2)/3");
		assertEquals(ep.build(), new Division(new Division(new Number(1),
				new Number(2)), new Number(3)));
	}

	public void testExp() throws IOException, ExpressionException {
		ep = new ExprParser("100^5");
		assertEquals(ep.build(),
				new Exponential(new Number(100), new Number(5)));
	}

	public void testExpExp() throws IOException, ExpressionException {
		ep = new ExprParser("1^(2^3)");
		assertEquals(ep.build(), new Exponential(new Number(1),
				new Exponential(new Number(2), new Number(3))));
	}

	public void testExpExp2() throws IOException, ExpressionException {
		ep = new ExprParser("(1^2)^3");
		assertEquals(ep.build().calculate(), new Exponential(new Exponential(
				new Number(1), new Number(2)), new Number(3)).calculate());
	}

	public void testLog() throws IOException, ExpressionException {
		ep = new ExprParser("(log 100)");
		assertEquals(ep.build().calculate(),
				new Log(new Number(100)).calculate());
	}

	public void testLogLog() throws IOException, ExpressionException {
		ep = new ExprParser("log(log 10)");
		assertEquals(ep.build().calculate(),
				new Log(new Log(new Number(10))).calculate());
	}

	public void testLn() throws IOException, ExpressionException {
		ep = new ExprParser("ln 100");
		assertEquals(ep.build().calculate(),
				new Ln(new Number(100)).calculate());
	}

	public void testLnLn() throws IOException, ExpressionException {
		ep = new ExprParser("ln(ln 10)");
		assertEquals(ep.build().calculate(),
				new Ln(new Ln(new Number(10))).calculate());
	}

	public void testCube() throws IOException, ExpressionException {
		ep = new ExprParser("cube(100)");
		assertEquals(ep.build().calculate(),
				new CubeRoot(new Number(100)).calculate());
	}

	public void testCubeCube() throws IOException, ExpressionException {
		ep = new ExprParser("cube(cube 512)");
		assertEquals(ep.build().calculate(), new CubeRoot(new CubeRoot(
				new Number(512))).calculate());
	}

	public void testFac() throws IOException, ExpressionException {
		ep = new ExprParser("100!");
		assertEquals(ep.build().calculate(),
				new Factorial(new Number(100)).calculate());
	}

	public void testFacFac() throws IOException, ExpressionException {
		ep = new ExprParser("3!!");
		assertEquals(ep.build().calculate(), new Factorial(new Factorial(
				new Number(3))).calculate());
	}

	public void testNeg() throws IOException, ExpressionException {
		ep = new ExprParser("-100");
		assertEquals(ep.build().calculate(), new Number(-100).calculate());
	}

	public void testNeg2() throws IOException, ExpressionException {
		ep = new ExprParser("-(100+2)");
		assertEquals(ep.build().calculate(), new Negation(new Addition(
				new Number(100), new Number(2))).calculate());
	}

	public void testNegFac() throws IOException, ExpressionException {
		ep = new ExprParser("-3!");
		assertEquals(ep.build().calculate(), new Negation(new Factorial(
				new Number(3))).calculate());
	}

	public void testNegFacAdd() throws IOException, ExpressionException {
		ep = new ExprParser("-(3+2)!");
		assertEquals(ep.build().calculate(), new Negation(new Factorial(
				new Addition(new Number(3), new Number(2)))).calculate());
	}

	public void testSqrt() throws IOException, ExpressionException {
		ep = new ExprParser("sqrt(9)");
		assertEquals(ep.build().calculate(),
				new Sqrt(new Number(9)).calculate());
	}

	public void testSqrtMul() throws IOException, ExpressionException {
		ep = new ExprParser("sqrt(9*12)");
		assertEquals(ep.build().calculate(), new Sqrt(new Multiplication(
				new Number(9), new Number(12))).calculate());
	}

	public void testAdvanced() throws IOException, ExpressionException {
		ep = new ExprParser("sqrt(5!^3!)");
		assertEquals(ep.build().calculate(),
				new Sqrt(new Exponential(new Factorial(new Number(5)),
						new Factorial(new Number(3)))).calculate());
	}

	public void testNegation() throws IOException, ExpressionException {
		ep = new ExprParser("2------1");
		assertEquals(ep.build().calculate(), 3.0);
		ep = new ExprParser("2-----1");
		assertEquals(ep.build().calculate(), 1.0);
	}

	public void testSqrtX() throws IOException, ExpressionException {
		ep = new ExprParser(
				"sqrt(sqrt(sqrt(sqrt(sqrt(sqrt(sqrt(sqrt(sqrt(sqrt(2))))))))))");
		assertEquals(ep.build().calculate(), new Sqrt(new Sqrt(new Sqrt(
				new Sqrt(new Sqrt(new Sqrt(new Sqrt(new Sqrt(new Sqrt(new Sqrt(
						new Number(2))))))))))).calculate());
	}

	public void testAdvanceds() throws IOException, ExpressionException {
		ep = new ExprParser("sqrt(5!*(1/2))");
		assertEquals(ep.build().calculate(), new Sqrt(new Multiplication(
				new Factorial(new Number(5)), new Division(new Number(1),
						new Number(2)))).calculate());
	}

	public void testMoreAdvance() throws IOException, ExpressionException {
		ep = new ExprParser("asdast1dg*f^-57");
		assertEquals(ep.build(), Expression.NULL);
	}

	public void testMore() throws IOException, ExpressionException {
		ep = new ExprParser("20asdf");
		assertEquals(ep.build(), Expression.NULL);
		ep = new ExprParser("20.0");
		assertEquals(ep.build(), new Number(20));
		ep = new ExprParser("20.0*3.14");
		assertEquals(ep.build(), new Multiplication(new Number(20), new Number(
				3.14)));
	}
}
