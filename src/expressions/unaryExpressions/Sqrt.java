package expressions.unaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Sqrt extends UnaryExpression {

	public Sqrt(Expression expr) {
		super(expr);
	}

	/*
	 * "\u221A" = sqrt tecken.
	 */
	public String toString() {
		return "(\u221A" + expr.toString() + ")";
	}

	@Override
	public double calculate() throws ExpressionException{
		return Math.sqrt(expr.calculate());
	}

}
