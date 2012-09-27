package expressions.unaryExpressions.trigonometrics;

import expressions.Expression;
import expressions.ExpressionException;

public class Cotangent extends TrigonometricFunction {

	public Cotangent(Expression expr) {
		super(expr, "cot");
	}

	@Override
	public double calculate() throws ExpressionException {
		return 1 / Math.tan(expr.calculate());
	}
}
