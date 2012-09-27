package expressions.unaryExpressions.trigonometrics;

import expressions.Expression;
import expressions.ExpressionException;

public class Cosecant extends TrigonometricFunction {

	public Cosecant(Expression expr) {
		super(expr, "csc");
	}

	@Override
	public double calculate() throws ExpressionException {
		return 1 / Math.sin(expr.calculate());
	}
}
