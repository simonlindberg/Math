package expressions.unaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class CubeRoot extends UnaryExpression {

	public CubeRoot(Expression expr) {
		super(expr);
	}

	public String toString() {
		return "(\u221B" + expr.toString() + ")";
	}

	@Override
	public double calculate() throws ExpressionException {
		return Math.pow(expr.calculate(), 1.0 / 3);
	}

}
