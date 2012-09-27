package expressions.unaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Log extends Logarithm {

	public Log(Expression expr) {
		super(expr, "log");
	}

	public double calculate() throws ExpressionException {
		return Math.log10(expr.calculate());
	}

}
