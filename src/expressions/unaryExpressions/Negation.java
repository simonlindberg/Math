package expressions.unaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Negation extends UnaryExpression {
	public Negation(Expression expr) {
		super(expr);
	}

	public String toString() {
		return "-" + expr.toString();
	}

	@Override
	public double calculate() throws ExpressionException {
		return -expr.calculate();
	}
}
