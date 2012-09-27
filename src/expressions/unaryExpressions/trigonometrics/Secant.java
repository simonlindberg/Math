package expressions.unaryExpressions.trigonometrics;

import expressions.Expression;
import expressions.ExpressionException;

public class Secant extends TrigonometricFunction {

	public Secant(Expression expr) {
		super(expr, "sec");
	}

	public double calculate() throws ExpressionException {
		return 1 / Math.cos(expr.calculate());
	}
}
