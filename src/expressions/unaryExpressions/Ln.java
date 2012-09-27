package expressions.unaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Ln extends Logarithm {

	public Ln(Expression expr) {
		super(expr, "ln");
	}

	@Override
	public double calculate() throws ExpressionException {
		return Math.log(expr.calculate());
	}
}
