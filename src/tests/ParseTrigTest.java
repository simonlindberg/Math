package tests;

import java.io.IOException;

import junit.framework.TestCase;
import parse.ExprParser;
import expressions.ExpressionException;
import expressions.Number;
import expressions.binaryExpressions.Subtraction;
import expressions.unaryExpressions.trigonometrics.Cosecant;
import expressions.unaryExpressions.trigonometrics.Cosine;
import expressions.unaryExpressions.trigonometrics.Cotangent;
import expressions.unaryExpressions.trigonometrics.Secant;
import expressions.unaryExpressions.trigonometrics.Sine;
import expressions.unaryExpressions.trigonometrics.Tangent;

public class ParseTrigTest extends TestCase {
	private ExprParser ep;

	public void testSine() throws IOException, ExpressionException {
		ep = new ExprParser("sin(0)");
		assertEquals(ep.build().calculate(),
				new Sine(new Number(0)).calculate());
	}

	public void testCos() throws IOException, ExpressionException {
		ep = new ExprParser("cos(0)");
		assertEquals(ep.build().calculate(),
				new Cosine(new Number(0)).calculate());
	}

	public void testTan() throws IOException, ExpressionException {
		ep = new ExprParser("tan(0)");
		assertEquals(ep.build().calculate(),
				new Tangent(new Number(0)).calculate());
	}

	public void testCot() throws IOException, ExpressionException {
		ep = new ExprParser("cot(0)");
		assertEquals(ep.build().calculate(),
				new Cotangent(new Number(0)).calculate());
	}

	public void testCsc() throws IOException, ExpressionException {
		ep = new ExprParser("csc(0)");
		assertEquals(ep.build().calculate(),
				new Cosecant(new Number(0)).calculate());
	}

	public void testSec() throws IOException, ExpressionException {
		ep = new ExprParser("sec(0)");
		assertEquals(ep.build().calculate(),
				new Secant(new Number(0)).calculate());
	}

	public void testSine2() throws IOException, ExpressionException {
		ep = new ExprParser("sin(sin(0)");
		assertEquals(ep.build().calculate(),
				new Sine(new Sine(new Number(0))).calculate());
	}

	public void testCos2() throws IOException, ExpressionException {
		ep = new ExprParser("cos(cos(0)");
		assertEquals(ep.build().calculate(), new Cosine(new Cosine(
				new Number(0))).calculate());
	}

	public void testTan2() throws IOException, ExpressionException {
		ep = new ExprParser("tan(tan(0)");
		assertEquals(ep.build().calculate(), new Tangent(new Tangent(
				new Number(0))).calculate());
	}

	public void testCot2() throws IOException, ExpressionException {
		ep = new ExprParser("cot(cot(0)");
		assertEquals(ep.build().calculate(), new Cotangent(new Cotangent(
				new Number(0))).calculate());
	}

	public void testCsc2() throws IOException, ExpressionException {
		ep = new ExprParser("csc(csc(0)");
		assertEquals(ep.build().calculate(), new Cosecant(new Cosecant(
				new Number(0))).calculate());
	}

	public void testSec2() throws IOException, ExpressionException {
		ep = new ExprParser("sec(sec(0)");
		assertEquals(ep.build().calculate(), new Secant(new Secant(
				new Number(0))).calculate());
	}

	public void testSine3() throws IOException, ExpressionException {
		ep = new ExprParser("sin(1-1)");
		assertEquals(ep.build().calculate(), new Sine(new Subtraction(
				new Number(1), new Number(1))).calculate());
	}

	public void testCos3() throws IOException, ExpressionException {
		ep = new ExprParser("cos(1-1)");
		assertEquals(ep.build().calculate(), new Cosine(new Subtraction(
				new Number(1), new Number(1))).calculate());
	}

	public void testTan3() throws IOException, ExpressionException {
		ep = new ExprParser("tan(1-1)");
		assertEquals(ep.build().calculate(), new Tangent(new Subtraction(
				new Number(1), new Number(1))).calculate());
	}

	public void testCot3() throws IOException, ExpressionException {
		ep = new ExprParser("cot(1-1)");
		assertEquals(ep.build().calculate(), new Cotangent(new Subtraction(
				new Number(1), new Number(1))).calculate());
	}

	public void testCsc3() throws IOException, ExpressionException {
		ep = new ExprParser("csc(1-1)");
		assertEquals(ep.build().calculate(), new Cosecant(new Subtraction(
				new Number(1), new Number(1))).calculate());
	}

	public void testSec3() throws IOException, ExpressionException {
		ep = new ExprParser("sec(1-1)");
		assertEquals(ep.build().calculate(), new Secant(new Subtraction(
				new Number(1), new Number(1))).calculate());
	}
}
