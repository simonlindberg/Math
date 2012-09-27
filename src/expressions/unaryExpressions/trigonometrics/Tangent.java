package expressions.unaryExpressions.trigonometrics;

import expressions.Expression;
import expressions.ExpressionException;

public class Tangent extends TrigonometricFunction {

	public Tangent(Expression expr) {
		super(expr, "tan");
	}

	public double calculate() throws ExpressionException {
		return Math.tan(expr.calculate());
	}
}
